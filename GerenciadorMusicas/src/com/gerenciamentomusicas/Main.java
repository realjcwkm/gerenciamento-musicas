package com.gerenciamentomusicas;

import javax.swing.SwingUtilities;
// Importa a TelaLogin, que está no mesmo pacote
// Note que TelaRegistro NÃO é importada aqui, pois ela será aberta pela TelaLogin
// import com.gerenciamentomusicas.view.TelaRegistro; // Esta linha deve estar comentada/removida

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // A aplicação inicia mostrando a Tela de Login
            TelaLogin telaLogin = new TelaLogin();
            telaLogin.setVisible(true);
        });
    }
}