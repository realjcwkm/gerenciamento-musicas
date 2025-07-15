package com.example.playlist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/playlist/MainView.fxml"));

        Scene scene = new Scene(loader.load());

        scene.getStylesheets().add(getClass().getResource("/com/example/playlist/style.css").toExternalForm());

        primaryStage.setTitle("Minha Biblioteca");

        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/playlist/imagens/icon.png")));

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
