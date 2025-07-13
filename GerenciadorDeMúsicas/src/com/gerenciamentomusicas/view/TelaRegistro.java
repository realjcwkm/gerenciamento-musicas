package com.gerenciamentomusicas.view; // Pacote correto para a pasta 'view'

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

// Importa a TelaLogin, pois TelaRegistro vai abri-la para voltar
import com.gerenciamentomusicas.TelaLogin;

public class TelaRegistro extends JFrame {
    private JLabel labelIconeMusica;
    private JTextField campoNomeUsuario;
    private JTextField campoEmail;
    private JPasswordField campoSenha;
    private JPasswordField campoConfirmarSenha;
    private JButton botaoRegistrar;

    public TelaRegistro() {
        this.setTitle("Registro");
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

        this.campoNomeUsuario = new JTextField("NOME DE USUÁRIO");
        setupTextField(this.campoNomeUsuario);
        painelConteudo.add(this.campoNomeUsuario);
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 15)));

        this.campoEmail = new JTextField("E-MAIL");
        setupTextField(this.campoEmail);
        painelConteudo.add(this.campoEmail);
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 15)));

        this.campoSenha = new JPasswordField("SENHA");
        setupTextField(this.campoSenha);
        painelConteudo.add(this.campoSenha);
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 15)));

        this.campoConfirmarSenha = new JPasswordField("CONFIRMAR SENHA");
        setupTextField(this.campoConfirmarSenha);
        painelConteudo.add(this.campoConfirmarSenha);
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 25)));

        this.botaoRegistrar = new JButton("REGISTRAR");
        this.botaoRegistrar.setMaximumSize(new Dimension(300, 50));
        this.botaoRegistrar.setPreferredSize(new Dimension(300, 50));
        this.botaoRegistrar.setBackground(new Color(0, 102, 255));
        this.botaoRegistrar.setForeground(Color.WHITE);
        this.botaoRegistrar.setFont(new Font("Arial", Font.BOLD, 18));
        this.botaoRegistrar.setBorderPainted(false);
        this.botaoRegistrar.setFocusPainted(false);
        this.botaoRegistrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelConteudo.add(this.botaoRegistrar);
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 20)));

        // --- ADIÇÃO: Link "Voltar para Login" ---
        JLabel linkVoltarLogin = new JLabel("<html><u>Voltar para Login</u></html>");
        linkVoltarLogin.setForeground(new Color(0, 102, 255));
        linkVoltarLogin.setFont(new Font("Arial", Font.PLAIN, 14));
        linkVoltarLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        linkVoltarLogin.setMaximumSize(new Dimension(300, 20));
        linkVoltarLogin.setPreferredSize(new Dimension(300, 20));
        linkVoltarLogin.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        painelConteudo.add(linkVoltarLogin);
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 10))); // Espaço após o link

        linkVoltarLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Fecha a tela de registro atual
                dispose();
                // Abre a tela de login
                SwingUtilities.invokeLater(() -> {
                    new TelaLogin();
                });
            }
        });
        // --- FIM DA ADIÇÃO ---

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
}