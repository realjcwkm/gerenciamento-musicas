package com.example.inicio;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;


import java.io.File;

public class DashboardController {

    @FXML private TextField searchField;
    @FXML private ListView<Music> musicListView;
    @FXML private Label currentMusicLabel;
    @FXML private Label nextMusicLabel;
    @FXML private Button playBtn;
    @FXML private Button pauseBtn;
    @FXML private Button nextBtn;
    @FXML private ImageView albumCover;

    private ObservableList<Music> musicas;
    private MediaPlayer mediaPlayer;
    private int musicaAtualIndex = -1;

    public void initialize() {
        musicas = FXCollections.observableArrayList(
                new Music("Construção", "Chico Buarque", "3:20", "MPB", "src/main/resources/audio/construcao.mp3", "/imagens/construcao.jpg"),
                new Music("Alegria, Alegria", "Caetano Veloso", "2:55", "MPB", "src/main/resources/audio/alegria.mp3", "/imagens/alegria.jpg")
        );

        musicListView.setItems(musicas);
        searchField.textProperty().addListener((obs, oldVal, newVal) -> filtrarMusicas(newVal));

        musicListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                musicaAtualIndex = musicListView.getSelectionModel().getSelectedIndex();
                tocarMusica(musicas.get(musicaAtualIndex));
            }
        });
    }

    private void filtrarMusicas(String filtro) {
        ObservableList<Music> filtrada = FXCollections.observableArrayList();
        for (Music m : musicas) {
            if (m.getTitulo().toLowerCase().contains(filtro.toLowerCase()) ||
                    m.getArtista().toLowerCase().contains(filtro.toLowerCase())) {
                filtrada.add(m);
            }
        }
        musicListView.setItems(filtrada);
    }

    private void tocarMusica(Music musica) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        Media media = new Media(new File(musica.getPath()).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

        currentMusicLabel.setText("Tocando agora: " + musica.getTitulo());

        // Corrigir nome do arquivo da imagem
        String nomeImagem = musica.getTitulo().toLowerCase()
                .replace("ã", "a")
                .replace("ç", "c")
                .replace("é", "e")
                .replace(",", "")
                .replace(" ", "") + ".jpg";

        try {
            albumCover.setImage(new Image(getClass().getResourceAsStream(musica.getImagemPath())));
        } catch (Exception e) {
            System.out.println("Imagem não encontrada: " + musica.getImagemPath());
            albumCover.setImage(null); // Ou imagem padrão
        }
        atualizarProximaMusica();
    }



    private void atualizarProximaMusica() {
        int proximaIndex = musicaAtualIndex + 1;
        if (proximaIndex < musicas.size()) {
            nextMusicLabel.setText("Próxima: " + musicas.get(proximaIndex).getTitulo());
        } else {
            nextMusicLabel.setText("Próxima: ---");
        }
    }

    @FXML
    public void playMusic() {
        if (musicaAtualIndex == -1 && !musicas.isEmpty()) {
            musicaAtualIndex = 0;
            tocarMusica(musicas.get(0));
        } else if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }

    @FXML
    public void pauseMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    @FXML
    public void nextMusic() {
        if (musicaAtualIndex + 1 < musicas.size()) {
            musicaAtualIndex++;
            tocarMusica(musicas.get(musicaAtualIndex));
        }
    }


    @FXML
    private void handleSair(MouseEvent event) {
        Platform.exit();
    }
}
