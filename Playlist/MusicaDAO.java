import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MusicaDAO {
    private final String url = "jdbc:mysql://localhost/bancodedados";

    public List<Musica> carregarMusicas() {
        List<Musica> musicas = new ArrayList<>();
        String sql = "SELECT titulo, artista, duracao, caminhoArquivo FROM Musicas";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String titulo = rs.getString("titulo");
                String artista = rs.getString("artista");
                int duracao = rs.getInt("duracao");
                String caminho = rs.getString("caminhoArquivo");

                Musica musica = new Musica(titulo, artista, duracao, caminho);
                musicas.add(musica);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return musicas;
    }
