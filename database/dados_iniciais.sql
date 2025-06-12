INSERT INTO usuarios (nome, email, senha_hash) VALUES 
('João Silva', 'joao.silva@email.com', '$2a$10$xFsTOfVV4q3uBNf2JYqR3eRplphjUfQ7Ue9L7jN1z1wC1bD1vYQOe'),
('Maria Oliveira', 'maria.oliveira@email.com', '$2a$10$yH2eE3rT7uVNmKk5XwvZ8eRplphjUfQ7Ue9L7jN1z1wC1bD1vYQOe'),
('Carlos Souza', 'carlos.souza@email.com', '$2a$10$zJ3fF4gV5hWmNl6YwXeY9eRplphjUfQ7Ue9L7jN1z1wC1bD1vYQOe');

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