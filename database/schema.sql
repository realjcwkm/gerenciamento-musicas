CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha_hash VARCHAR(255) NOT NULL,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE musicas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    artista VARCHAR(100) NOT NULL,
    duracao INT,
    genero VARCHAR(50),
    caminho_arquivo VARCHAR(255)
);

CREATE TABLE playlists (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    usuario_id INT NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

CREATE TABLE musicas_playlists (
    playlist_id INT NOT NULL,
    musica_id INT NOT NULL,
    ordem INT,
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

-- Tabela para registrar logs de atividades no sistema.
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
