-- -----------------------------------------------------
-- Tabela: usuarios
-- Descrição: Armazena os dados de cada usuário cadastrado
-- que pode criar playlists e interagir com o sistema.
-- -----------------------------------------------------
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha_hash VARCHAR(255) NOT NULL,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- -----------------------------------------------------
-- Tabela: musicas
-- Descrição: Catálogo central de todas as músicas disponíveis
-- no sistema, com seus metadados.
-- -----------------------------------------------------
CREATE TABLE musicas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    artista VARCHAR(100) NOT NULL,
    duracao INT CHECK (duracao > 0),
    genero VARCHAR(50),
    caminho_arquivo VARCHAR(255)
);

-- -----------------------------------------------------
-- Tabela: playlists
-- Descrição: Armazena as playlists criadas pelos usuários.
-- Cada playlist pertence a um único usuário.
-- -----------------------------------------------------
CREATE TABLE playlists (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    usuario_id INT NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- -----------------------------------------------------
-- Tabela: musicas_playlists
-- Descrição: Tabela de associação (N:M) que liga as músicas
-- às playlists, definindo a ordem de cada música.
-- -----------------------------------------------------
CREATE TABLE musicas_playlists (
    playlist_id INT NOT NULL,
    musica_id INT NOT NULL,
    ordem INT CHECK (ordem >= 1),
    PRIMARY KEY (playlist_id, musica_id),
    FOREIGN KEY (playlist_id) REFERENCES playlists(id),
    FOREIGN KEY (musica_id) REFERENCES musicas(id)
);