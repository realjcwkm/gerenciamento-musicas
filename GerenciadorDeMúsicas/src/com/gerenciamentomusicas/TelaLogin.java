// Source code is decompiled from a .class file using FernFlower decompiler.
package com.gerenciamentomusicas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
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
      this.setDefaultCloseOperation(3);
      this.setLocationRelativeTo((Component)null);
      this.getContentPane().setBackground(new Color(230, 230, 230));
      JPanel var1 = new JPanel();
      var1.setBackground(Color.BLACK);
      var1.setLayout(new BoxLayout(var1, 1));
      var1.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
      JPanel var2 = new JPanel(new GridBagLayout());
      var2.setBackground(new Color(230, 230, 230));
      var2.add(var1);
      this.add(var2, "Center");
      URL var3 = this.getClass().getResource("/images/musical_note.png");
      System.out.println("Caminho do recurso da nota musical: " + String.valueOf(var3));

      try {
         if (var3 != null) {
            ImageIcon var4 = new ImageIcon(var3);
            Image var5 = var4.getImage().getScaledInstance(100, 100, 4);
            this.labelIconeMusica = new JLabel(new ImageIcon(var5));
         } else {
            this.labelIconeMusica = new JLabel("♫ (Imagem não encontrada)");
         }
      } catch (Exception var13) {
         this.labelIconeMusica = new JLabel("♫ (Erro ao carregar)");
         System.err.println("Erro ao carregar imagem da nota musical: " + var13.getMessage());
      }

      this.labelIconeMusica.setAlignmentX(0.5F);
      var1.add(this.labelIconeMusica);
      var1.add(Box.createRigidArea(new Dimension(0, 30)));
      this.campoUsuarioEmail = new JTextField("NOME DE USUÁRIO OU E-MAIL");
      this.campoUsuarioEmail.setMaximumSize(new Dimension(300, 40));
      this.campoUsuarioEmail.setPreferredSize(new Dimension(300, 40));
      this.campoUsuarioEmail.setBackground(new Color(60, 60, 60));
      this.campoUsuarioEmail.setForeground(Color.WHITE);
      this.campoUsuarioEmail.setCaretColor(Color.WHITE);
      this.campoUsuarioEmail.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
      this.campoUsuarioEmail.setAlignmentX(0.5F);
      var1.add(this.campoUsuarioEmail);
      var1.add(Box.createRigidArea(new Dimension(0, 15)));
      this.campoSenha = new JPasswordField("SENHA");
      this.campoSenha.setMaximumSize(new Dimension(300, 40));
      this.campoSenha.setPreferredSize(new Dimension(300, 40));
      this.campoSenha.setBackground(new Color(60, 60, 60));
      this.campoSenha.setForeground(Color.WHITE);
      this.campoSenha.setCaretColor(Color.WHITE);
      this.campoSenha.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
      this.campoSenha.setAlignmentX(0.5F);
      var1.add(this.campoSenha);
      var1.add(Box.createRigidArea(new Dimension(0, 25)));
      this.botaoLogin = new JButton("LOGIN");
      this.botaoLogin.setMaximumSize(new Dimension(300, 50));
      this.botaoLogin.setPreferredSize(new Dimension(300, 50));
      this.botaoLogin.setBackground(new Color(0, 102, 255));
      this.botaoLogin.setForeground(Color.WHITE);
      this.botaoLogin.setFont(new Font("Arial", 1, 18));
      this.botaoLogin.setBorderPainted(false);
      this.botaoLogin.setFocusPainted(false);
      this.botaoLogin.setAlignmentX(0.5F);
      var1.add(this.botaoLogin);
      var1.add(Box.createRigidArea(new Dimension(0, 20)));
      this.linkRecuperarSenha = new JLabel("<html><u>Recuperar senha</u></html>");
      this.linkRecuperarSenha.setForeground(new Color(0, 102, 255));
      this.linkRecuperarSenha.setFont(new Font("Arial", 0, 14));
      this.linkRecuperarSenha.setAlignmentX(0.5F);
      var1.add(this.linkRecuperarSenha);
      var1.add(Box.createRigidArea(new Dimension(0, 35)));
      this.textoNaoPossuiConta = new JLabel("Não possui uma conta?");
      this.textoNaoPossuiConta.setForeground(Color.WHITE);
      this.textoNaoPossuiConta.setFont(new Font("Arial", 0, 14));
      this.textoNaoPossuiConta.setAlignmentX(0.5F);
      var1.add(this.textoNaoPossuiConta);
      this.linkRegistrar = new JLabel("<html><u>REGISTRE-SE</u></html>");
      this.linkRegistrar.setForeground(new Color(0, 102, 255));
      this.linkRegistrar.setFont(new Font("Arial", 1, 14));
      this.linkRegistrar.setAlignmentX(0.5F);
      var1.add(this.linkRegistrar);
      var1.add(Box.createRigidArea(new Dimension(0, 25)));
      this.textoOu = new JLabel("ou");
      this.textoOu.setForeground(Color.WHITE);
      this.textoOu.setFont(new Font("Arial", 0, 14));
      this.textoOu.setAlignmentX(0.5F);
      var1.add(this.textoOu);
      var1.add(Box.createRigidArea(new Dimension(0, 20)));
      JPanel var14 = new JPanel(new FlowLayout(1, 20, 0));
      var14.setBackground(Color.BLACK);
      var14.setAlignmentX(0.5F);
      URL var15 = this.getClass().getResource("/images/facebook.png");
      System.out.println("Caminho do recurso Facebook: " + String.valueOf(var15));

      try {
         if (var15 != null) {
            ImageIcon var6 = new ImageIcon(var15);
            Image var7 = var6.getImage().getScaledInstance(30, 30, 4);
            this.botaoFacebook = new JButton(new ImageIcon(var7));
         } else {
            this.botaoFacebook = new JButton("f (Imagem não encontrada)");
         }
      } catch (Exception var12) {
         this.botaoFacebook = new JButton("f (Erro ao carregar)");
         System.err.println("Erro ao carregar imagem do Facebook: " + var12.getMessage());
      }

      this.botaoFacebook.setPreferredSize(new Dimension(40, 40));
      this.botaoFacebook.setBackground(new Color(60, 60, 60));
      this.botaoFacebook.setForeground(Color.WHITE);
      this.botaoFacebook.setBorderPainted(false);
      this.botaoFacebook.setFocusPainted(false);
      URL var16 = this.getClass().getResource("/images/google.png");
      System.out.println("Caminho do recurso Google: " + String.valueOf(var16));

      try {
         if (var16 != null) {
            ImageIcon var17 = new ImageIcon(var16);
            Image var8 = var17.getImage().getScaledInstance(30, 30, 4);
            this.botaoGoogle = new JButton(new ImageIcon(var8));
         } else {
            this.botaoGoogle = new JButton("G (Imagem não encontrada)");
         }
      } catch (Exception var11) {
         this.botaoGoogle = new JButton("G (Erro ao carregar)");
         System.err.println("Erro ao carregar imagem do Google: " + var11.getMessage());
      }

      this.botaoGoogle.setPreferredSize(new Dimension(40, 40));
      this.botaoGoogle.setBackground(new Color(60, 60, 60));
      this.botaoGoogle.setForeground(Color.WHITE);
      this.botaoGoogle.setBorderPainted(false);
      this.botaoGoogle.setFocusPainted(false);
      URL var18 = this.getClass().getResource("/images/twitter.png");
      System.out.println("Caminho do recurso Twitter: " + String.valueOf(var18));

      try {
         if (var18 != null) {
            ImageIcon var19 = new ImageIcon(var18);
            Image var9 = var19.getImage().getScaledInstance(30, 30, 4);
            this.botaoTwitter = new JButton(new ImageIcon(var9));
         } else {
            this.botaoTwitter = new JButton("t (Imagem não encontrada)");
         }
      } catch (Exception var10) {
         this.botaoTwitter = new JButton("t (Erro ao carregar)");
         System.err.println("Erro ao carregar imagem do Twitter: " + var10.getMessage());
      }

      this.botaoTwitter.setPreferredSize(new Dimension(40, 40));
      this.botaoTwitter.setBackground(new Color(60, 60, 60));
      this.botaoTwitter.setForeground(Color.WHITE);
      this.botaoTwitter.setBorderPainted(false);
      this.botaoTwitter.setFocusPainted(false);
      var14.add(this.botaoFacebook);
      var14.add(this.botaoGoogle);
      var14.add(this.botaoTwitter);
      var1.add(var14);
      this.setVisible(true);
   }

   public static void main(String[] var0) {
      new TelaLogin();
   }
}
