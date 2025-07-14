package dao;

import model.Musica;
import java.sql.*;
import java.util.*;

public class MusicaDAO {
    public List<Musica> listarPorPlaylist(int idPlaylist) throws SQLException {
        List<Musica> musicas = new ArrayList<>();
        String sql = """
            SELECT m.* FROM musicas m
            INNER JOIN playlist_musicas pm ON pm.id_musica = m.id
            WHERE pm.id_playlist = ?
        """;
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPlaylist);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                musicas.add(new Musica(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("artista"),
                        rs.getString("caminho_arquivo")
                ));
            }
        }
        return musicas;
    }

    public List<Musica> pesquisar(String termo) throws SQLException {
        List<Musica> musicas = new ArrayList<>();
        String sql = "SELECT * FROM musicas WHERE titulo LIKE ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + termo + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                musicas.add(new Musica(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("artista"),
                        rs.getString("caminho_arquivo")
                ));
            }
        }
        return musicas;
    }
}
