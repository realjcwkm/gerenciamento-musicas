package com.gerenciamentomusicas;

import javax.swing.SwingUtilities;
import com.gerenciamentomusicas.TelaLogin; // Importa a TelaLogin do mesmo pacote

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaLogin telaLogin = new TelaLogin();
            telaLogin.setVisible(true);
        });
    }
}