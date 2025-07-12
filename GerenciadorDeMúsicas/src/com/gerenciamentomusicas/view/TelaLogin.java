package com.gerenciamentomusicas.view;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class TelaLogin extends JFrame {

    private JTextField campoUsuarioEmail;
    private JPasswordField campoSenha;
    private JButton botaoLogin;
    private JLabel linkRecuperarSenha;
    private JLabel textoNaoPossuiConta;
    private JLabel linkRegistrar;
    private JLabel textoOu;
    private JButton botaoFacebook;
    private JButton botaoGoogle;
    private JButton botaoTwitter;

    public TelaLogin() {
        setTitle("Login");
        setSize(1080, 720); // Define o tamanho da janela principal
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela
        getContentPane().setBackground(new Color(230, 230, 230)); // Cor de fundo da janela

        // Painel principal (preto no design original)
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Layout vertical
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40)); // Padding

        // Centraliza o mainPanel dentro da janela
        JPanel wrapperPanel = new JPanel(new GridBagLayout()); // Usado para centralizar
        wrapperPanel.setBackground(new Color(230, 230, 230));
        wrapperPanel.add(mainPanel);
        add(wrapperPanel, BorderLayout.CENTER);

        // --- Componentes ---

        // Ícone de Música
        JLabel labelIconeMusica = new JLabel();
        try {
            // Caminho relativo ao classpath: /images/musical_note.png (porque 'src/main/resources' vai para a raiz do classpath)
            URL imageUrl = getClass().getResource("/images/musical_note.png");
            if (imageUrl != null) {
                ImageIcon originalIcon = new ImageIcon(imageUrl);
                Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                labelIconeMusica.setIcon(new ImageIcon(scaledImage));
            } else {
                labelIconeMusica.setText("♫ (Imagem não encontrada)");
                System.err.println("Recurso não encontrado: /images/musical_note.png");
            }
        } catch (Exception e) {
            labelIconeMusica.setText("♫ (Erro ao carregar)");
            System.err.println("Erro ao carregar imagem da nota musical: " + e.getMessage());
        }
        labelIconeMusica.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(labelIconeMusica);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Espaçamento

        // Campo de Usuário/E-mail
        campoUsuarioEmail = new JTextField("NOME DE USUÁRIO OU E-MAIL");
        configurarCampoTexto(campoUsuarioEmail);
        mainPanel.add(campoUsuarioEmail);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo de Senha
        campoSenha = new JPasswordField("SENHA");
        configurarCampoTexto(campoSenha); // Reutiliza a configuração
        mainPanel.add(campoSenha);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        // Botão Login
        botaoLogin = new JButton("LOGIN");
        botaoLogin.setMaximumSize(new Dimension(300, 50));
        botaoLogin.setPreferredSize(new Dimension(300, 50));
        botaoLogin.setBackground(new Color(0, 102, 255));
        botaoLogin.setForeground(Color.WHITE);
        botaoLogin.setFont(new Font("Arial", Font.BOLD, 18));
        botaoLogin.setBorderPainted(false);
        botaoLogin.setFocusPainted(false);
        botaoLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(botaoLogin);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Link Recuperar Senha
        linkRecuperarSenha = new JLabel("<html><u>Recuperar senha</u></html>");
        linkRecuperarSenha.setForeground(new Color(0, 102, 255));
        linkRecuperarSenha.setFont(new Font("Arial", Font.PLAIN, 14));
        linkRecuperarSenha.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(linkRecuperarSenha);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 35)));

        // Texto "Não possui uma conta?"
        textoNaoPossuiConta = new JLabel("Não possui uma conta?");
        textoNaoPossuiConta.setForeground(Color.WHITE);
        textoNaoPossuiConta.setFont(new Font("Arial", Font.PLAIN, 14));
        textoNaoPossuiConta.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(textoNaoPossuiConta);

        // Link Registrar
        linkRegistrar = new JLabel("<html><u>REGISTRE-SE</u></html>");
        linkRegistrar.setForeground(new Color(0, 102, 255));
        linkRegistrar.setFont(new Font("Arial", Font.BOLD, 14));
        linkRegistrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(linkRegistrar);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        // Texto "ou"
        textoOu = new JLabel("ou");
        textoOu.setForeground(Color.WHITE);
        textoOu.setFont(new Font("Arial", Font.PLAIN, 14));
        textoOu.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(textoOu);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Painel para botões sociais
        JPanel socialButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        socialButtonsPanel.setBackground(Color.BLACK);
        socialButtonsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botões Sociais
        botaoFacebook = criarBotaoSocial("/images/facebook.png");
        botaoGoogle = criarBotaoSocial("/images/google.png");
        botaoTwitter = criarBotaoSocial("/images/twitter.png");

        socialButtonsPanel.add(botaoFacebook);
        socialButtonsPanel.add(botaoGoogle);
        socialButtonsPanel.add(botaoTwitter);
        mainPanel.add(socialButtonsPanel);

        setVisible(true);
    }

    // Método auxiliar para configurar campos de texto/senha
    private void configurarCampoTexto(JTextField campo) {
        campo.setMaximumSize(new Dimension(300, 40));
        campo.setPreferredSize(new Dimension(300, 40));
        campo.setBackground(new Color(60, 60, 60));
        campo.setForeground(Color.WHITE);
        campo.setCaretColor(Color.WHITE);
        campo.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        campo.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    // Método auxiliar para criar botões sociais com ícones
    private JButton criarBotaoSocial(String imagePath) {
        JButton button = new JButton();
        try {
            URL imageUrl = getClass().getResource(imagePath);
            if (imageUrl != null) {
                ImageIcon originalIcon = new ImageIcon(imageUrl);
                Image scaledImage = originalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                button.setIcon(new ImageIcon(scaledImage));
            } else {
                button.setText(imagePath.contains("facebook") ? "F" : (imagePath.contains("google") ? "G" : "T"));
                System.err.println("Recurso de imagem social não encontrado: " + imagePath);
            }
        } catch (Exception e) {
            button.setText("Erro");
            System.err.println("Erro ao carregar imagem social " + imagePath + ": " + e.getMessage());
        }
        button.setPreferredSize(new Dimension(40, 40));
        button.setBackground(new Color(60, 60, 60));
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        return button;
    }

    // Este main é para testar a TelaLogin isoladamente. O Main.java principal a chamará.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaLogin::new);
    }
}