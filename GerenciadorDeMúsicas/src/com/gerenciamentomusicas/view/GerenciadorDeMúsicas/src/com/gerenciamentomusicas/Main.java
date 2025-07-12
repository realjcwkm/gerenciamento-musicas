package com.gerenciamentomusicas;

import javax.swing.SwingUtilities;
import com.gerenciamentomusicas.view.TelaRegistro; // Importa a TelaRegistro

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaRegistro().setVisible(true); // Cria e exibe a TelaRegistro
        });
    }
}