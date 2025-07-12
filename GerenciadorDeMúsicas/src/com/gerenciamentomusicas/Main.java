package com.gerenciamentomusicas; // Declara o pacote para Main.java

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Garante que a interface gráfica seja inicializada na Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaLogin(); // Cria e exibe a TelaLogin
            }
        });
    }
}