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
import dao.UsuarioDAO;
import model.Usuario;
import javax.swing.JOptionPane;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import com.gerenciamentomusicas.view.components.RoundJTextField;
import com.gerenciamentomusicas.view.components.RoundJPasswordField;
import com.gerenciamentomusicas.view.components.RoundJButton;
import com.gerenciamentomusicas.util.ImageUtil;

// Importa a TelaRegistro, pois TelaLogin vai abri-la
import com.gerenciamentomusicas.view.TelaRegistro;

public class TelaLogin extends JFrame {
    private JLabel labelIconeMusica;
    private RoundJTextField campoUsuarioEmail;
    private RoundJPasswordField campoSenha;
    private RoundJButton botaoLogin;
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
        painelConteudo.setFocusable(true); 
        painelConteudo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                painelConteudo.requestFocusInWindow();
            }
        });

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
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 60)));

        this.campoUsuarioEmail = new RoundJTextField();
        setupTextField(this.campoUsuarioEmail);
        addPlaceholder(this.campoUsuarioEmail, "NOME DE USUÁRIO OU E-MAIL");
        painelConteudo.add(this.campoUsuarioEmail);
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 15)));

        this.campoSenha = new RoundJPasswordField();
        setupTextField(this.campoSenha);
        addPlaceholder(this.campoSenha, "SENHA");
        painelConteudo.add(this.campoSenha);
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 30)));

        this.botaoLogin = new RoundJButton("LOGIN");
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
        // --- CÓDIGO PARA ADICIONAR A LÓGICA DE LOGIN ---
        this.botaoLogin.addActionListener(e -> {
            String login = campoUsuarioEmail.getText();
            String senha = new String(campoSenha.getPassword());

            // Validação simples
            if (login.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha os campos de e-mail e senha.", "Campos Vazios", JOptionPane.WARNING_MESSAGE);
                return;
            }
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            try {
                Usuario usuarioAutenticado = usuarioDAO.autenticar(login, senha);

                if (usuarioAutenticado != null) {
                    String mensagemCompleta = "<html>"
                            + "Login realizado com sucesso! Bem-vindo, <b>" + usuarioAutenticado.getNome() + "</b>.<br><br>"
                            + "A parte de Autenticação (em Java Swing) foi concluída.<br><br>"
                            + "<hr>" // Adiciona uma linha divisória
                            + "<b>Próximo Passo:</b><br>"
                            + "Para testar o restante da aplicação (em JavaFX), por favor, siga<br>"
                            + "as instruções no arquivo <b>README.md</b>."
                            + "</html>";

                    JOptionPane.showMessageDialog(
                        this, 
                        mensagemCompleta, 
                        "Módulo Concluído - Próximos Passos", 
                        JOptionPane.INFORMATION_MESSAGE
                    );

                    System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(this, "E-mail ou senha incorretos.", "Falha na Autenticação", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao conectar com o banco de dados.", "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        painelConteudo.add(Box.createRigidArea(new Dimension(0, 35)));
        
        JLabel textoNaoPossuiConta = new JLabel("Não possui uma conta?");
        textoNaoPossuiConta.setForeground(Color.WHITE);
        textoNaoPossuiConta.setFont(new Font("Arial", Font.PLAIN, 14));
        JLabel linkRegistrar = new JLabel("REGISTRE-SE"); 
        linkRegistrar.setForeground(Color.WHITE);
        linkRegistrar.setFont(new Font("Arial", Font.BOLD, 14));
        linkRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        linkRegistrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TelaRegistro telaDeRegistro = new TelaRegistro();
                telaDeRegistro.setVisible(true);
                dispose(); 
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                linkRegistrar.setText("<html><u>REGISTRE-SE</u></html>");
                linkRegistrar.setForeground(new Color(50, 150, 255));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                linkRegistrar.setText("REGISTRE-SE");
                linkRegistrar.setForeground(Color.WHITE);
            }
        });

        JPanel painelRegistro = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0)); 
        painelRegistro.setBackground(Color.BLACK);
        painelRegistro.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelRegistro.add(textoNaoPossuiConta);
        painelRegistro.add(linkRegistrar);
        painelConteudo.add(painelRegistro);
        
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 50)));

        this.textoOu = new JLabel("ou");
        this.textoOu.setForeground(Color.WHITE);
        this.textoOu.setFont(new Font("Arial", Font.PLAIN, 14));
        this.textoOu.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelConteudo.add(this.textoOu);

        painelConteudo.add(Box.createRigidArea(new Dimension(0, 50)));

        JPanel painelBotoesSociais = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        painelBotoesSociais.setBackground(Color.BLACK);
        painelBotoesSociais.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelBotoesSociais.setMaximumSize(new Dimension(300, 40));
        painelBotoesSociais.setPreferredSize(new Dimension(300, 40));

        URL facebookUrl = getClass().getResource("/resources/images/facebook.png");
        this.botaoFacebook = createSocialButton(facebookUrl, "f (Imagem não encontrada)");
        painelBotoesSociais.add(this.botaoFacebook);

        URL googleUrl = getClass().getResource("/resources/images/google.png"); //
        this.botaoGoogle = createSocialButton(googleUrl, "G (Imagem não encontrada)");
        painelBotoesSociais.add(this.botaoGoogle);

        URL twitterUrl = getClass().getResource("/resources/images/twitter.png"); //
        this.botaoTwitter = createSocialButton(twitterUrl, "t (Imagem não encontrada)");
        painelBotoesSociais.add(this.botaoTwitter);

        painelConteudo.add(painelBotoesSociais);
        
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 100)));

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
        textField.setHorizontalAlignment(JTextField.CENTER);
    }

    private JButton createSocialButton(URL imageUrl, String fallbackText) {
        JButton button;
        if (imageUrl != null) {
            ImageIcon originalIcon = new ImageIcon(imageUrl);
            ImageIcon scaledIcon = ImageUtil.scaleImage(originalIcon, 36, 36);
            button = new JButton(scaledIcon);
        } else {
            button = new JButton(fallbackText);
            System.err.println("Erro: Imagem social '" + fallbackText + "' não encontrada.");
        }

        button.setPreferredSize(new Dimension(40, 40));
        button.setBackground(new Color(60, 60, 60));
        button.setBorderPainted(false);
        button.setFocusPainted(false);

        return button;
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