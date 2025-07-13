package com.gerenciamentomusicas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter; // Importação para o MouseListener
import java.awt.event.MouseEvent;   // Importação para o MouseListener
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

// Importa a TelaRegistro, pois TelaLogin vai abri-la
import com.gerenciamentomusicas.view.TelaRegistro;

public class TelaLogin extends JFrame {
    private JLabel labelIconeMusica;
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
        this.setTitle("Login");
        this.setSize(1080, 720);
        this.setMinimumSize(new Dimension(1080, 720));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(230, 230, 230));

        JPanel painelConteudo = new JPanel();
        painelConteudo.setBackground(Color.BLACK);
        painelConteudo.setLayout(new BoxLayout(painelConteudo, BoxLayout.Y_AXIS));
        painelConteudo.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        JPanel painelCentralizado = new JPanel(new GridBagLayout());
        painelCentralizado.setBackground(new Color(230, 230, 230));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        painelCentralizado.add(painelConteudo, gbc);
        this.add(painelCentralizado, "Center");

        // Carregar e configurar o ícone da nota musical
        URL musicalNoteUrl = getClass().getResource("/resources/images/musical_note.png"); //
        if (musicalNoteUrl != null) {
            ImageIcon originalIcon = new ImageIcon(musicalNoteUrl);
            Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            this.labelIconeMusica = new JLabel(new ImageIcon(scaledImage));
        } else {
            this.labelIconeMusica = new JLabel("♫ (Imagem não encontrada)");
            System.err.println("Erro: Imagem musical_note.png não encontrada em /resources/images/");
        }
        this.labelIconeMusica.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelConteudo.add(this.labelIconeMusica);
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 30)));

        this.campoUsuarioEmail = new JTextField("NOME DE USUÁRIO OU E-MAIL");
        setupTextField(this.campoUsuarioEmail);
        painelConteudo.add(this.campoUsuarioEmail);
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 15)));

        this.campoSenha = new JPasswordField("SENHA");
        setupTextField(this.campoSenha);
        painelConteudo.add(this.campoSenha);
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 25)));

        this.botaoLogin = new JButton("LOGIN");
        this.botaoLogin.setMaximumSize(new Dimension(300, 50));
        this.botaoLogin.setPreferredSize(new Dimension(300, 50));
        this.botaoLogin.setBackground(new Color(0, 102, 255));
        this.botaoLogin.setForeground(Color.WHITE);
        this.botaoLogin.setFont(new Font("Arial", Font.BOLD, 18));
        this.botaoLogin.setBorderPainted(false);
        this.botaoLogin.setFocusPainted(false);
        this.botaoLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelConteudo.add(this.botaoLogin);
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 20)));

        this.linkRecuperarSenha = new JLabel("<html><u>Recuperar senha</u></html>");
        this.linkRecuperarSenha.setForeground(new Color(0, 102, 255));
        this.linkRecuperarSenha.setFont(new Font("Arial", Font.PLAIN, 14));
        this.linkRecuperarSenha.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.linkRecuperarSenha.setMaximumSize(new Dimension(300, 20));
        this.linkRecuperarSenha.setPreferredSize(new Dimension(300, 20));
        this.linkRecuperarSenha.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        painelConteudo.add(this.linkRecuperarSenha);
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 35)));

        this.textoNaoPossuiConta = new JLabel("Não possui uma conta?");
        this.textoNaoPossuiConta.setForeground(Color.WHITE);
        this.textoNaoPossuiConta.setFont(new Font("Arial", Font.PLAIN, 14));
        this.textoNaoPossuiConta.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.textoNaoPossuiConta.setMaximumSize(new Dimension(300, 20));
        this.textoNaoPossuiConta.setPreferredSize(new Dimension(300, 20));
        this.textoNaoPossuiConta.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        painelConteudo.add(this.textoNaoPossuiConta);

        this.linkRegistrar = new JLabel("<html><u>REGISTRE-SE</u></html>");
        this.linkRegistrar.setForeground(new Color(0, 102, 255));
        this.linkRegistrar.setFont(new Font("Arial", Font.BOLD, 14));
        this.linkRegistrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.linkRegistrar.setMaximumSize(new Dimension(300, 20));
        this.linkRegistrar.setPreferredSize(new Dimension(300, 20));
        this.linkRegistrar.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        painelConteudo.add(this.linkRegistrar);
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 25)));

        // --- ADIÇÃO: Listener para o link REGISTRE-SE ---
        this.linkRegistrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Fecha a tela de login atual
                dispose();
                // Abre a tela de registro
                SwingUtilities.invokeLater(() -> {
                    new TelaRegistro();
                });
            }
        });
        // --- FIM DA ADIÇÃO ---

        this.textoOu = new JLabel("ou");
        this.textoOu.setForeground(Color.WHITE);
        this.textoOu.setFont(new Font("Arial", Font.PLAIN, 14));
        this.textoOu.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.textoOu.setMaximumSize(new Dimension(300, 20));
        this.textoOu.setPreferredSize(new Dimension(300, 20));
        this.textoOu.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        painelConteudo.add(this.textoOu);
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel painelBotoesSociais = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        painelBotoesSociais.setBackground(Color.BLACK);
        painelBotoesSociais.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelBotoesSociais.setMaximumSize(new Dimension(300, 40));
        painelBotoesSociais.setPreferredSize(new Dimension(300, 40));

        URL facebookUrl = getClass().getResource("/resources/images/facebook.png"); //
        this.botaoFacebook = createSocialButton(facebookUrl, "f (Imagem não encontrada)");
        painelBotoesSociais.add(this.botaoFacebook);

        URL googleUrl = getClass().getResource("/resources/images/google.png"); //
        this.botaoGoogle = createSocialButton(googleUrl, "G (Imagem não encontrada)");
        painelBotoesSociais.add(this.botaoGoogle);

        URL twitterUrl = getClass().getResource("/resources/images/twitter.png"); //
        this.botaoTwitter = createSocialButton(twitterUrl, "t (Imagem não encontrada)");
        painelBotoesSociais.add(this.botaoTwitter);

        painelConteudo.add(painelBotoesSociais);

        this.setVisible(true);
    }

    private void setupTextField(JTextField textField) {
        textField.setMaximumSize(new Dimension(300, 40));
        textField.setPreferredSize(new Dimension(300, 40));
        textField.setBackground(new Color(60, 60, 60));
        textField.setForeground(Color.WHITE);
        textField.setCaretColor(Color.WHITE);
        textField.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private JButton createSocialButton(URL imageUrl, String fallbackText) {
        JButton button;
        if (imageUrl != null) {
            ImageIcon originalIcon = new ImageIcon(imageUrl);
            Image scaledImage = originalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            button = new JButton(new ImageIcon(scaledImage));
        } else {
            button = new JButton(fallbackText);
            System.err.println("Erro: Imagem social '" + fallbackText + "' não encontrada em /resources/images/");
        }
        button.setPreferredSize(new Dimension(40, 40));
        button.setBackground(new Color(60, 60, 60));
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        return button;
    }
}