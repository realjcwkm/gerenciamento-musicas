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
import com.gerenciamentomusicas.view.components.RoundJButton;
import com.gerenciamentomusicas.view.components.RoundJPasswordField;
import com.gerenciamentomusicas.view.components.RoundJTextField;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

// Importa a TelaLogin, pois TelaRegistro vai abri-la para voltar
import com.gerenciamentomusicas.TelaLogin;
import com.gerenciamentomusicas.util.ImageUtil;

public class TelaRegistro extends JFrame {
    private JLabel labelIconeMusica;
    private RoundJTextField campoNomeUsuario;
    private RoundJTextField campoEmail;
    private RoundJPasswordField campoSenha;
    private RoundJPasswordField campoConfirmarSenha;
    private RoundJButton botaoRegistrar;

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

        painelConteudo.add(Box.createRigidArea(new Dimension(0, 50)));

        URL musicalNoteUrl = getClass().getResource("/resources/images/musical_note.png"); //
        if (musicalNoteUrl != null) {
            ImageIcon originalIcon = new ImageIcon(musicalNoteUrl);
            ImageIcon scaledIcon = ImageUtil.scaleImage(originalIcon, 100, 100); 
            this.labelIconeMusica = new JLabel(scaledIcon);
        } else {
            this.labelIconeMusica = new JLabel("♫ (Imagem não encontrada)");
            System.err.println("Erro: Imagem musical_note.png não encontrada em /resources/images/");
        }
        this.labelIconeMusica.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelConteudo.add(this.labelIconeMusica);
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 30)));

        this.campoNomeUsuario = new RoundJTextField();
        addPlaceholder(this.campoNomeUsuario, "NOME DE USUÁRIO");
        setupTextField(this.campoNomeUsuario);
        painelConteudo.add(this.campoNomeUsuario);
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 15)));

        this.campoEmail = new RoundJTextField();
        addPlaceholder(this.campoEmail, "E-MAIL");
        setupTextField(this.campoEmail);
        painelConteudo.add(this.campoEmail);
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 15)));

        this.campoSenha = new RoundJPasswordField();
        addPlaceholder(this.campoSenha, "SENHA");
        setupTextField(this.campoSenha);
        painelConteudo.add(this.campoSenha);
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 15)));

        this.campoConfirmarSenha = new RoundJPasswordField();
        addPlaceholder(this.campoConfirmarSenha, "CONFIRMAR SENHA");
        setupTextField(this.campoConfirmarSenha);
        painelConteudo.add(this.campoConfirmarSenha);
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 25)));

        this.botaoRegistrar = new RoundJButton("REGISTRAR");
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
    
    /**
     * Adiciona um texto de placeholder a um JTextField.
     * O texto some quando o campo ganha foco e reaparece se o campo ficar vazio.
     * @param textField O campo de texto a ser modificado.
     * @param placeholder O texto de ajuda a ser exibido.
     */
    private void addPlaceholder(JTextField textField, String placeholder) {
        Color originalColor = textField.getForeground();
        Color placeholderColor = Color.GRAY;

        textField.setText(placeholder);
        textField.setForeground(placeholderColor);
        textField.setHorizontalAlignment(JTextField.LEFT);

        textField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(originalColor);
                    textField.setHorizontalAlignment(JTextField.LEFT);
                }
            }
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(placeholderColor);
                    textField.setText(placeholder);
                    textField.setHorizontalAlignment(JTextField.LEFT);
                }
            }
        });
    }

    /**
     * Versão especial e corrigida do addPlaceholder para JPasswordField.
     * @param passwordField O campo de senha a ser modificado.
     * @param placeholder O texto de ajuda a ser exibido.
     */
    private void addPlaceholder(JPasswordField passwordField, String placeholder) {
        final Color originalColor = passwordField.getForeground();
        final Color placeholderColor = Color.GRAY;
        final char defaultEchoChar = passwordField.getEchoChar();

        passwordField.setText(placeholder);
        passwordField.setForeground(placeholderColor);
        passwordField.setEchoChar((char) 0);
        passwordField.setHorizontalAlignment(JTextField.LEFT);

        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(passwordField.getPassword()).equals(placeholder)) {
                    passwordField.setText("");
                    passwordField.setForeground(originalColor);
                    passwordField.setEchoChar(defaultEchoChar);
                    passwordField.setHorizontalAlignment(JTextField.LEFT);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (new String(passwordField.getPassword()).isEmpty()) {
                    passwordField.setForeground(placeholderColor);
                    passwordField.setText(placeholder);
                    passwordField.setEchoChar((char) 0);
                    passwordField.setHorizontalAlignment(JTextField.LEFT);
                }
            }
        });
    }
}