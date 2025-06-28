package ui;
import dao.PlaylistDAO;
import dao.MusicaDAO;
import model.Playlist;
import model.Musica;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class PlaylistGUI extends JFrame {
    private PlaylistDAO playlistDAO = new PlaylistDAO();
    private MusicaDAO musicaDAO = new MusicaDAO();
    private PlayerMusica player = new PlayerMusica();
    private JList<Playlist> listaPlaylists = new JList<>();
    private JList<Musica> listaMusicas = new JList<>();
    private JButton btnNovaPlaylist = new JButton("➕ Nova Playlist");
    private JButton btnEditarPlaylist = new JButton("✏️ Editar Playlist");
    private JButton btnPlay = new JButton("▶️ Play"), btnPause = new JButton("⏸️ Pause"),
            btnNext = new JButton("⏭️ Próxima"), btnSearch = new JButton("🔍 Pesquisar"),
            btnVoltar = new JButton("⬅️ Voltar"), btnSair = new JButton("❌ Sair");
    private JButton btnAbrirArquivo = new JButton("📂 Abrir Arquivo");

    public PlaylistGUI() {
        setTitle("Minha Playlist");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1080, 720);
        setLayout(new BorderLayout());

        JLabel tituloLabel = new JLabel("🎵 Minha Playlist");
        tituloLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        tituloLabel.setForeground(new Color(50, 50, 150));

        // Painel playlists
        JPanel painelEsquerdo = new JPanel(new BorderLayout());
        painelEsquerdo.add(new JLabel("Playlists:"), BorderLayout.NORTH);
        painelEsquerdo.add(new JScrollPane(listaPlaylists), BorderLayout.CENTER);
        add(painelEsquerdo, BorderLayout.WEST);
        painelEsquerdo.add(btnNovaPlaylist, BorderLayout.SOUTH);
        painelEsquerdo.add(btnEditarPlaylist, BorderLayout.NORTH);

        // Painel músicas
        JPanel painelCentro = new JPanel(new BorderLayout());
        painelCentro.add(new JLabel("Músicas:"), BorderLayout.NORTH);
        painelCentro.add(new JScrollPane(listaMusicas), BorderLayout.CENTER);
        add(painelCentro, BorderLayout.CENTER);   // Botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnPlay); painelBotoes.add(btnPause);
        painelBotoes.add(btnNext); painelBotoes.add(btnSearch);
        painelBotoes.add(btnAbrirArquivo);
        painelBotoes.add(btnVoltar); painelBotoes.add(btnSair);
        add(painelBotoes, BorderLayout.SOUTH);
        btnNovaPlaylist.addActionListener(e -> criarPlaylist());
        btnEditarPlaylist.addActionListener(e -> editarPlaylist());

        listaPlaylists.addListSelectionListener(e -> {
            Playlist p = listaPlaylists.getSelectedValue();
            if (p != null) carregarMusicasDaPlaylist(p.getId());
        });

        btnPlay.addActionListener(e -> {
            Musica m = listaMusicas.getSelectedValue();
            if (m != null) {
                try {
                    player.tocar(m.getCaminhoArquivo());
                } catch (Exception ex) { ex.printStackTrace(); }
            }
        });

        btnPause.addActionListener(e -> player.parar());
        btnAbrirArquivo.addActionListener(e -> abrirArquivo());
        btnSair.addActionListener(e -> System.exit(0));
        setVisible(true);
    }
    private void carregarPlaylists() {
        try {
            List<Playlist> playlists = playlistDAO.listar();
            listaPlaylists.setListData(playlists.toArray(new Playlist[0]));
        } catch (SQLException e) { e.printStackTrace(); }
    }

    private void abrirArquivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecione um arquivo de música MP3");
        int resultado = fileChooser.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            String caminhoArquivo = fileChooser.getSelectedFile().getAbsolutePath();
            try {
                player.tocar(caminhoArquivo);
                Musica musicaTemporaria = new Musica(-1, fileChooser.getSelectedFile().getName(), "Arquivo Local", caminhoArquivo);
                listaMusicas.setListData(new Musica[]{musicaTemporaria});
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao tocar arquivo: " + ex.getMessage());
            }
        }
    }

    private void criarPlaylist() {
        String nome = JOptionPane.showInputDialog(this, "Nome da nova playlist:");
        if (nome != null && !nome.isEmpty()) {
            String desc = JOptionPane.showInputDialog(this, "Descrição:");
            try {
                playlistDAO.criar(nome, desc);
                carregarPlaylists();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao criar playlist: " + ex.getMessage());
            }
        }
    }

    private void editarPlaylist() {
        Playlist p = listaPlaylists.getSelectedValue();
        if (p == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma playlist para editar.");
            return;
        }
        String novoNome = JOptionPane.showInputDialog(this, "Novo nome:", p.getNome());
        if (novoNome != null && !novoNome.isEmpty()) {
            String novaDesc = JOptionPane.showInputDialog(this, "Nova descrição:", p.getDescricao());
            try {
                playlistDAO.editar(p.getId(), novoNome, novaDesc);
                carregarPlaylists();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao editar playlist: " + ex.getMessage());
            }
        }
    }

    private void carregarMusicasDaPlaylist(int idPlaylist) {
        try {
            List<Musica> musicas = musicaDAO.listarPorPlaylist(idPlaylist);
            listaMusicas.setListData(musicas.toArray(new Musica[0]));
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
