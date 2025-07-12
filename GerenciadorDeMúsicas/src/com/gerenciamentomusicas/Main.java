package com.gerenciamentomusicas;

import javax.swing.SwingUtilities;
import com.gerenciamentomusicas.view.TelaRegistro;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaRegistro().setVisible(true);
        });
    }
}