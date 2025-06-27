//Projeto feito pelo IntelliJ IDEA, ainda em andamento//

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class PlaylistGUI extends JFrame {
    private ArrayList<Musica> playlist = new ArrayList<>();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> listaMusicas = new JList<>(listModel);

    private Clip clip;
    private int musicaAtual = -1;

    public PlaylistGUI() {
        setTitle("🎧 Playlist de Músicas");
        setSize(1080, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Campos de entrada
        JTextField campoTitulo = new JTextField(15);
        JTextField campoArtista = new JTextField(15);
        JTextField campoDuracao = new JTextField(5);

        JButton botaoEscolherArquivo = new JButton("Selecionar Música");
        final JFileChooser fileChooser = new JFileChooser();
        final String[] caminhoArquivo = new String[1];

        botaoEscolherArquivo.addActionListener(e -> {
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                caminhoArquivo[0] = fileChooser.getSelectedFile().getAbsolutePath();
            }
        });

        MusicaDAO dao = new MusicaDAO();
        playlist.addAll(dao.carregarMusicas());
        for (Musica musica : playlist) {
            listModel.addElement(musica.toString());
        }

        JButton botaoAdicionar = new JButton("Adicionar");
        JButton botaoRemover = new JButton("Remover Selecionada");

        // Painel de entrada
        JPanel painelEntrada = new JPanel();
        painelEntrada.add(new JLabel("Título:"));
        painelEntrada.add(campoTitulo);
        painelEntrada.add(new JLabel("Artista:"));
        painelEntrada.add(campoArtista);
        painelEntrada.add(new JLabel("Duração (s):"));
        painelEntrada.add(campoDuracao);
        painelEntrada.add(botaoEscolherArquivo);
        painelEntrada.add(botaoAdicionar);
        JLabel labelArquivoSelecionado = new JLabel("Nenhum arquivo selecionado");
        painelEntrada.add(labelArquivoSelecionado);

        // Scroll e lista
        JScrollPane scrollPane = new JScrollPane(listaMusicas);

        // Painel de controles
        JPanel painelControles = new JPanel();
        JButton botaoReproduzir = new JButton("▶️ Reproduzir");
        JButton botaoPausar = new JButton("⏸️ Pausar");
        JButton botaoAnterior = new JButton("⏮️ Anterior");
        JButton botaoProxima = new JButton("⏭️ Próxima");
        JButton botaoVoltar = new JButton("Voltar");
        painelControles.add(botaoReproduzir);
        painelControles.add(botaoPausar);
        painelControles.add(botaoAnterior);
        painelControles.add(botaoProxima);
        painelControles.add(botaoVoltar);

        // Layout principal
        add(painelEntrada, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        JPanel painelInferior = new JPanel(new BorderLayout());
        painelInferior.add(botaoRemover, BorderLayout.NORTH);
        painelInferior.add(painelControles, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);

        // Ações de botões
        botaoAdicionar.addActionListener(e -> {
            String titulo = campoTitulo.getText().trim();
            String artista = campoArtista.getText().trim();
            String duracaoTexto = campoDuracao.getText().trim();

            if (titulo.isEmpty() || artista.isEmpty() || duracaoTexto.isEmpty() || caminhoArquivo[0] == null) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos e selecione o arquivo de música!");
                return;
            }

            try {
                int duracao = Integer.parseInt(duracaoTexto);
                Musica musica = new Musica(titulo, artista, duracao, caminhoArquivo[0]);
                playlist.add(musica);
                listModel.addElement(musica.toString());

                campoTitulo.setText("");
                campoArtista.setText("");
                campoDuracao.setText("");
                caminhoArquivo[0] = null;
                labelArquivoSelecionado.setText("Nenhum arquivo selecionado");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Duração deve ser um número inteiro.");
            }
        });

        botaoEscolherArquivo.addActionListener(e -> {
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                caminhoArquivo[0] = fileChooser.getSelectedFile().getAbsolutePath();
                labelArquivoSelecionado.setText("Selecionado: " + fileChooser.getSelectedFile().getName());
            }
        });


        botaoRemover.addActionListener(e -> {
            int index = listaMusicas.getSelectedIndex();
            if (index != -1) {
                playlist.remove(index);
                listModel.remove(index);
                if (clip != null && clip.isRunning()) clip.stop();
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma música para remover.");
            }
        });

        botaoReproduzir.addActionListener(e -> {
            int index = listaMusicas.getSelectedIndex();
            if (index != -1) {
                tocarMusica(index);
            } else if (musicaAtual != -1) {
                resumeMusica();
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma música para tocar.");
            }
        });

        botaoPausar.addActionListener(e -> pausarMusica());

        botaoProxima.addActionListener(e -> {
            if (!playlist.isEmpty()) {
                musicaAtual = (musicaAtual + 1) % playlist.size();
                tocarMusica(musicaAtual);
                listaMusicas.setSelectedIndex(musicaAtual);
            }
        });

        botaoAnterior.addActionListener(e -> {
            if (!playlist.isEmpty()) {
                musicaAtual = (musicaAtual - 1 + playlist.size()) % playlist.size();
                tocarMusica(musicaAtual);
                listaMusicas.setSelectedIndex(musicaAtual);
            }
        });

        botaoVoltar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Voltando para a tela anterior...");
            dispose();
        });

        // Clique duplo para tocar música
        listaMusicas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int index = listaMusicas.locationToIndex(evt.getPoint());
                    if (index != -1) {
                        tocarMusica(index);
                    }
                }
            }
        });
    }

    private void tocarMusica(int index) {
        try {
            if (clip != null && clip.isRunning()) clip.stop();
            if (clip != null) clip.close();

            Musica musica = playlist.get(index);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(musica.getCaminhoArquivo()));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            musicaAtual = index;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao tocar a música: " + ex.getMessage());
        }
    }

    private void pausarMusica() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    private void resumeMusica() {
        if (clip != null && !clip.isRunning()) {
            clip.start();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PlaylistGUI().setVisible(true));
    }
}
