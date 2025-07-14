-- -----------------------------------------------------
-- Tabela: usuarios
-- Descrição: Armazena os dados de cada usuário cadastrado
-- que pode criar playlists e interagir com o sistema.
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) UNIQUE NOT NULL,
  `senha_hash` VARCHAR(255) NOT NULL,
  `data_cadastro` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

-- -----------------------------------------------------
-- Tabela: musicas
-- Descrição: Catálogo central de todas as músicas disponíveis
-- no sistema, com seus metadados.
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `musicas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(100) NOT NULL,
  `artista` VARCHAR(100) NOT NULL,
  `duracao` INT CHECK (duracao > 0),
  `genero` VARCHAR(50),
  `caminho_arquivo` VARCHAR(255),
  PRIMARY KEY (`id`)
);

-- -----------------------------------------------------
-- Tabela: playlists
-- Descrição: Armazena as playlists criadas pelos usuários.
-- Cada playlist pertence a um único usuário.
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `playlists` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `usuario_id` INT NOT NULL,
  `data_criacao` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- -----------------------------------------------------
-- Tabela: musicas_playlists
-- Descrição: Tabela de associação (N:M) que liga as músicas
-- às playlists, definindo a ordem de cada música.
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `musicas_playlists` (
  `playlist_id` INT NOT NULL,
  `musica_id` INT NOT NULL,
  `ordem` INT CHECK (ordem >= 1),
  PRIMARY KEY (playlist_id, musica_id),
  FOREIGN KEY (playlist_id) REFERENCES playlists(id),
  FOREIGN KEY (musica_id) REFERENCES musicas(id)
);

-- =====================================================
-- VIEW para Relatório
-- Visão que combina dados de usuários, playlists e músicas
-- para gerar um relatório completo.
-- =====================================================
CREATE OR REPLACE VIEW `relatorio_completo_playlists` AS
SELECT
    u.nome AS 'Dono_da_Playlist',
    p.nome AS 'Nome_da_Playlist',
    m.titulo AS 'Musica',
    m.artista AS 'Artista'
FROM musicas_playlists mp
JOIN musicas m ON mp.musica_id = m.id
JOIN playlists p ON mp.playlist_id = p.id
JOIN usuarios u ON p.usuario_id = u.id
ORDER BY u.nome, p.nome, mp.ordem;

-- =====================================================
-- Tabela e TRIGGER para Auditoria
-- =====================================================
CREATE TABLE IF NOT EXISTS `auditoria` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `acao` VARCHAR(255) NOT NULL,
  `data_hora` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- TRIGGER que dispara automaticamente APÓS a INSERÇÃO de uma nova música.
DELIMITER $$
CREATE TRIGGER `trigger_after_musica_insert`
AFTER INSERT ON `musicas`
FOR EACH ROW
BEGIN
    INSERT INTO auditoria (acao) 
    VALUES (CONCAT('Nova música adicionada: ', NEW.titulo));
END$$
DELIMITER ;