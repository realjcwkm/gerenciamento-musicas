package com.gerenciamentomusicas.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaRegistro extends JFrame {

    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton registerButton;
    private JLabel musicalNoteIcon; // Para a nota musical

    public TelaRegistro() {
        setTitle("REGISTRAR");
        setSize(1080, 720); // Tamanho conforme a tela de registro no Figma
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setResizable(false); // Impede o redimensionamento

        // Painel principal para organizar os componentes
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout()); // Usa BorderLayout para a estrutura principal
        mainPanel.setBackground(new Color(240, 240, 240)); // Fundo cinza claro para a janela

        // Painel central para os campos e botão (fundo preto)
        JPanel centralPanel = new JPanel();
        centralPanel.setBackground(Color.BLACK); // Fundo preto para a área central
        centralPanel.setLayout(new GridBagLayout()); // Usa GridBagLayout para controle preciso

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Margens entre os componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Preenche horizontalmente

        // Ícone da nota musical
        musicalNoteIcon = new JLabel();
        try {
            // O caminho é relativo a 'resources' quando incluído no classpath
            // Apenas musical_note.png
            ImageIcon icon = new ImageIcon(getClass().getResource("/images/musical_note.png"));
            if (icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Redimensiona
                musicalNoteIcon.setIcon(new ImageIcon(image));
            } else {
                 throw new Exception("Imagem musical_note.png não carregada corretamente. Status: " + icon.getImageLoadStatus());
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar a imagem da nota musical: " + e.getMessage());
            musicalNoteIcon.setText("♪"); // Texto de fallback se a imagem falhar
            musicalNoteIcon.setFont(new Font("Arial", Font.BOLD, 80));
            musicalNoteIcon.setForeground(new Color(0, 191, 255)); // Azul claro
        }
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Ocupa duas colunas
        gbc.anchor = GridBagConstraints.CENTER; // Centraliza
        centralPanel.add(musicalNoteIcon, gbc);

        // Rótulo para "NOME DE USUÁRIO"
        JLabel usernameLabel = new JLabel("NOME DE USUÁRIO");
        usernameLabel.setForeground(Color.WHITE); // Texto branco
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Ocupa duas colunas
        gbc.anchor = GridBagConstraints.WEST; // Alinha à esquerda
        centralPanel.add(usernameLabel, gbc);

        // Campo de texto para Nome de Usuário
        usernameField = new JTextField(20); // 20 colunas de largura
        usernameField.setPreferredSize(new Dimension(250, 40)); // Tamanho fixo
        usernameField.setBackground(Color.DARK_GRAY); // Fundo escuro
        usernameField.setForeground(Color.WHITE); // Texto branco
        usernameField.setCaretColor(Color.WHITE); // Cursor branco
        usernameField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Padding interno
        gbc.gridy = 2;
        centralPanel.add(usernameField, gbc);

        // Rótulo para "E-MAIL"
        JLabel emailLabel = new JLabel("E-MAIL");
        emailLabel.setForeground(Color.WHITE);
        gbc.gridy = 3;
        centralPanel.add(emailLabel, gbc);

        // Campo de texto para E-mail
        emailField = new JTextField(20);
        emailField.setPreferredSize(new Dimension(250, 40));
        emailField.setBackground(Color.DARK_GRAY);
        emailField.setForeground(Color.WHITE);
        emailField.setCaretColor(Color.WHITE);
        emailField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        gbc.gridy = 4;
        centralPanel.add(emailField, gbc);

        // Rótulo para "SENHA"
        JLabel passwordLabel = new JLabel("SENHA");
        passwordLabel.setForeground(Color.WHITE);
        gbc.gridy = 5;
        centralPanel.add(passwordLabel, gbc);

        // Campo de senha
        passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(250, 40));
        passwordField.setBackground(Color.DARK_GRAY);
        passwordField.setForeground(Color.WHITE);
        passwordField.setCaretColor(Color.WHITE);
        passwordField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        gbc.gridy = 6;
        centralPanel.add(passwordField, gbc);

        // Rótulo para "CONFIRMAR SENHA"
        JLabel confirmPasswordLabel = new JLabel("CONFIRMAR SENHA");
        confirmPasswordLabel.setForeground(Color.WHITE);
        gbc.gridy = 7;
        centralPanel.add(confirmPasswordLabel, gbc);

        // Campo de confirmação de senha
        confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setPreferredSize(new Dimension(250, 40));
        confirmPasswordField.setBackground(Color.DARK_GRAY);
        confirmPasswordField.setForeground(Color.WHITE);
        confirmPasswordField.setCaretColor(Color.WHITE);
        confirmPasswordField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        gbc.gridy = 8;
        centralPanel.add(confirmPasswordField, gbc);

        // Botão "REGISTRAR"
        registerButton = new JButton("REGISTRAR");
        registerButton.setPreferredSize(new Dimension(250, 45)); // Tamanho fixo para o botão
        registerButton.setBackground(new Color(0, 102, 204)); // Azul vibrante
        registerButton.setForeground(Color.WHITE); // Texto branco
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.setFocusPainted(false); // Remove o destaque de foco
        registerButton.setBorderPainted(false); // Remove a borda
        gbc.gridy = 9;
        gbc.insets = new Insets(30, 10, 10, 10); // Mais espaço acima do botão
        centralPanel.add(registerButton, gbc);

        // Adiciona um ActionListener ao botão (exemplo: para imprimir dados no console)
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                System.out.println("--- Dados de Registro ---");
                System.out.println("Nome de Usuário: " + username);
                System.out.println("E-mail: " + email);
                System.out.println("Senha: " + password);
                System.out.println("Confirmar Senha: " + confirmPassword);

                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(TelaRegistro.this, "As senhas não coincidem!", "Erro de Registro", JOptionPane.ERROR_MESSAGE);
                } else if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(TelaRegistro.this, "Todos os campos devem ser preenchidos!", "Erro de Registro", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(TelaRegistro.this, "Registro bem-sucedido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    // Aqui você pode adicionar a lógica para salvar no banco de dados
                    // ou navegar para outra tela, por exemplo, a TelaLogin
                    // new TelaLogin().setVisible(true);
                    // dispose(); // Fecha a tela atual
                }
            }
        });

        // Adiciona o painel central ao painel principal, centralizando-o
        mainPanel.add(centralPanel, BorderLayout.CENTER);

        add(mainPanel); // Adiciona o painel principal à janela

        setVisible(true); // Torna a janela visível
    }
}