//Projeto feito pelo IntelliJ IDEA, ainda em andamento//

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlaylistGUI extends JFrame {
    private ArrayList<Musica> playlist = new ArrayList<>();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> listaMusicas = new JList<>(listModel);

    public PlaylistGUI() {
        setTitle("🎧 Playlist de Músicas");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Componentes
        JTextField campoTitulo = new JTextField(15);
        JTextField campoArtista = new JTextField(15);
        JTextField campoDuracao = new JTextField(5);
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
        painelEntrada.add(botaoAdicionar);

        // Scroll e lista
        JScrollPane scrollPane = new JScrollPane(listaMusicas);

        // Painel principal
        add(painelEntrada, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(botaoRemover, BorderLayout.SOUTH);

        // Ações
        botaoAdicionar.addActionListener(e -> {
            String titulo = campoTitulo.getText().trim();
            String artista = campoArtista.getText().trim();
            String duracaoTexto = campoDuracao.getText().trim();

            if (titulo.isEmpty() || artista.isEmpty() || duracaoTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
                return;
            }

            try {
                int duracao = Integer.parseInt(duracaoTexto);
                Musica musica = new Musica(titulo, artista, duracao);
                playlist.add(musica);
                listModel.addElement(musica.toString());

                campoTitulo.setText("");
                campoArtista.setText("");
                campoDuracao.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Duração deve ser um número inteiro.");
            }
        });

        botaoRemover.addActionListener(e -> {
            int index = listaMusicas.getSelectedIndex();
            if (index != -1) {
                playlist.remove(index);
                listModel.remove(index);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma música para remover.");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PlaylistGUI().setVisible(true));
    }
}
