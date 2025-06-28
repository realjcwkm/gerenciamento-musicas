package dao;
import model.Playlist;
import java.sql.*;
import java.util.*;

public class PlaylistDAO {
    public List<Playlist> listar() throws SQLException {
        List<Playlist> playlists = new ArrayList<>();
        String sql = "SELECT * FROM playlists";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                playlists.add(new Playlist(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao")));
            }
        }
        return playlists;
    }

    public void criar(String nome, String descricao) throws SQLException {
        String sql = "INSERT INTO playlists (nome, descricao) VALUES (?, ?)";
        try (Connection conn = Conexao.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nome); ps.setString(2, descricao); ps.executeUpdate();
        }
    }

    public void editar(int id, String nome, String descricao) throws SQLException {
        String sql = "UPDATE playlists SET nome=?, descricao=? WHERE id=?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nome); ps.setString(2, descricao); ps.setInt(3, id); ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM playlists WHERE id=?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id); ps.executeUpdate();
        }
    }
}
