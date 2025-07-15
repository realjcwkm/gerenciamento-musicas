-- Inserindo usuários de teste com senhas literais para fins acadêmicos
INSERT INTO usuarios (nome, email, senha_hash) VALUES 
('João Silva', 'joao.silva@email.com', 'joao123'),
('Maria Oliveira', 'maria.oliveira@email.com', 'maria123'),
('Carlos Souza', 'carlos.souza@email.com', 'carlos123');

INSERT INTO musicas (titulo, artista, duracao, genero, caminho_arquivo) VALUES 
('Bohemian Rhapsody', 'Queen', 354, 'Rock', '/musicas/queen_bohemian.mp3'),
('Shape of You', 'Ed Sheeran', 234, 'Pop', '/musicas/ed_sheeren_shape.mp3'),
('Smells Like Teen Spirit', 'Nirvana', 301, 'Grunge', '/musicas/nirvana_teen_spirit.mp3'),
('Billie Jean', 'Michael Jackson', 294, 'Pop', '/musicas/mj_billie_jean.mp3'),
('Imagine', 'John Lennon', 183, 'Rock', '/musicas/lennon_imagine.mp3');

INSERT INTO playlists (nome, usuario_id) VALUES 
('Rock Clássico', 1),
('Pop Hits', 2),
('Minhas Favoritas', 3);

INSERT INTO musicas_playlists (playlist_id, musica_id, ordem) VALUES 
(1, 1, 1),
(1, 3, 2),
(1, 5, 3),
(2, 2, 1),
(2, 4, 2),
(3, 1, 1),
(3, 2, 2),
(3, 4, 3);