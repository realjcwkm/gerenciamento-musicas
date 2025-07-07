package com.example.dashboard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dashboard.fxml"));
        Scene scene = new Scene(loader.load(), 1080, 720);
        scene.getStylesheets().add(getClass().getResource("/estilo.css").toExternalForm());

        primaryStage.setTitle("Dashboard de Estatísticas");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
