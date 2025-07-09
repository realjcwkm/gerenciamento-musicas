package com.example.playlist;

import dao.MusicaDAO;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.Musica;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class MainController {

    @FXML private ListView<Musica> listaMusicas;
    @FXML private ImageView imgCapa;
    @FXML private ImageView imgUsuario;
    @FXML private Label lblTitulo;
    @FXML private Label lblArtista;
    @FXML private Button btnPlay, btnProxima, btnVoltar;

    private final MusicaDAO musicaDAO = new MusicaDAO();
    private MediaPlayer player;
    private int musicaAtual = -1;
    private List<Musica> musicas;

    @FXML
    public void initialize() {
        carregarImagemUsuario();
        carregarMusicas();

        listaMusicas.setOnMouseClicked(this::handleClickMusica);
        listaMusicas.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Musica musica, boolean empty) {
                super.updateItem(musica, empty);
                setText(empty || musica == null ? "" : musica.getTitulo());
            }
        });

        btnPlay.setOnAction(e -> tocarOuPausar());
        btnProxima.setOnAction(e -> tocarProxima());
        btnVoltar.setOnAction(e -> tocarAnterior());
    }

    private void carregarImagemUsuario() {
        try {
            Image userImage = new Image(getClass().getResourceAsStream("/com/example/playlist/imagens/user.png"));
            imgUsuario.setImage(userImage);
        } catch (Exception e) {
            System.out.println("Imagem do usuário não encontrada.");
        }
    }

    private void carregarMusicas() {
        musicas = List.of(
                new Musica(1, "Construção", "Chico Buarque", "src/main/resources/audio/construcao.mp3"),
                new Musica(2, "Alegria, Alegria", "Caetano Veloso", "src/main/resources/audio/alegria.mp3")
        );
        listaMusicas.getItems().addAll(musicas);
    }


    private void handleClickMusica(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Musica selecionada = listaMusicas.getSelectionModel().getSelectedItem();
            if (selecionada != null) {
                musicaAtual = musicas.indexOf(selecionada);
                tocarMusica(selecionada);
            }
        }
    }

    private void tocarOuPausar() {
        if (player == null) {
            tocarMusicaSelecionada();
        } else {
            MediaPlayer.Status status = player.getStatus();
            if (status == MediaPlayer.Status.PLAYING) {
                player.pause();
                btnPlay.setText("▶");
            } else {
                player.play();
                btnPlay.setText("⏸");
            }
        }
    }

    private void tocarMusicaSelecionada() {
        Musica m = listaMusicas.getSelectionModel().getSelectedItem();
        if (m != null) {
            musicaAtual = musicas.indexOf(m);
            tocarMusica(m);
        }
    }

    private void tocarMusica(Musica musica) {
        if (player != null) {
            player.stop();
        }

        try {
            File file = new File(musica.getCaminhoArquivo());
            if (!file.exists()) {
                System.out.println("Arquivo não encontrado: " + file.getAbsolutePath());
                return;
            }

            Media media = new Media(file.toURI().toString());
            player = new MediaPlayer(media);
            player.play();
            btnPlay.setText("⏸");

            // Atualiza a interface com informações
            lblTitulo.setText(musica.getTitulo());
            lblArtista.setText(musica.getArtista());

            // Caminho da imagem
            String nomeImagem = musica.getTitulo()
                    .toLowerCase()
                    .replace("ã", "a")
                    .replace("á", "a")
                    .replace("é", "e")
                    .replace("í", "i")
                    .replace("ó", "o")
                    .replace("ú", "u")
                    .replace("ç", "c")
                    .replaceAll("[^a-z0-9]", "") + ".jpg";

            String caminhoImagem = "/com/example/playlist/imagens/" + nomeImagem;
            URL resource = getClass().getResource(caminhoImagem);

            if (resource != null) {
                imgCapa.setImage(new Image(resource.toExternalForm()));
            } else {
                imgCapa.setImage(new Image(getClass().getResource("/com/example/playlist/imagens/default.png").toExternalForm()));
            }

        } catch (Exception e) {
            System.out.println("Erro ao tocar música: " + e.getMessage());
        }
    }

    private void tocarProxima() {
        if (musicas == null || musicas.isEmpty()) return;
        musicaAtual = (musicaAtual + 1) % musicas.size();
        tocarMusica(musicas.get(musicaAtual));
    }

    private void tocarAnterior() {
        if (musicas == null || musicas.isEmpty()) return;
        musicaAtual = (musicaAtual - 1 + musicas.size()) % musicas.size();
        tocarMusica(musicas.get(musicaAtual));
    }

    @FXML
    private void handleSair() {
        Platform.exit();
    }
}
