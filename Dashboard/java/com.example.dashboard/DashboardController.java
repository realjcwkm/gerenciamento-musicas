package com.example.dashboard;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;

public class DashboardController {

    @FXML private HBox barsBox;
    @FXML private Label tempoTotalLabel;
    @FXML private ToggleButton btnAno;
    @FXML private ToggleButton btnMes;
    @FXML private ToggleButton btnSemana;

    @FXML
    public void initialize() {
        atualizarGraficoSemana(); // inicializa no modo semana
    }

    @FXML
    private void handleAno() {
        deselecionarOutros(btnAno);
        atualizarGraficoAno();
    }

    @FXML
    private void handleMes() {
        deselecionarOutros(btnMes);
        atualizarGraficoMes();
    }

    @FXML
    private void handleSemana() {
        deselecionarOutros(btnSemana);
        atualizarGraficoSemana();
    }

    private void deselecionarOutros(ToggleButton selecionado) {
        btnAno.setSelected(selecionado == btnAno);
        btnMes.setSelected(selecionado == btnMes);
        btnSemana.setSelected(selecionado == btnSemana);
    }

    private void atualizarGraficoSemana() {
        barsBox.getChildren().clear();
        barsBox.getChildren().addAll(
                criarBarra(30), criarBarra(50), criarBarra(70),
                criarBarra(60), criarBarra(90), criarBarra(40), criarBarra(80)
        );
        tempoTotalLabel.setText("16h 37min");
    }

    private void atualizarGraficoMes() {
        barsBox.getChildren().clear();
        barsBox.getChildren().addAll(
                criarBarra(50), criarBarra(60), criarBarra(70),
                criarBarra(80), criarBarra(90)
        );
        tempoTotalLabel.setText("65h 20min");
    }

    private void atualizarGraficoAno() {
        barsBox.getChildren().clear();
        barsBox.getChildren().addAll(
                criarBarra(20), criarBarra(40), criarBarra(60),
                criarBarra(80), criarBarra(100), criarBarra(60), criarBarra(40),
                criarBarra(20), criarBarra(50), criarBarra(70), criarBarra(90), criarBarra(60)
        );
        tempoTotalLabel.setText("320h 15min");
    }

    private Rectangle criarBarra(double altura) {
        Rectangle rect = new Rectangle(10, altura);
        rect.setStyle("-fx-fill: #00BFFF;");
        return rect;
    }

    @FXML
    private void handleSair() {
        System.exit(0);
    }
}
