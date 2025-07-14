package dao;

import model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public Usuario autenticar(String loginOuEmail, String senha) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE (email = ? OR nome = ?) AND senha_hash = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, loginOuEmail);
            stmt.setString(2, loginOuEmail);
            stmt.setString(3, senha);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setNome(rs.getString("nome"));
                    return usuario;
                }
            }
        }
        return null;
    }

    public void cadastrar(Usuario novoUsuario) throws SQLException {
        String sql = "INSERT INTO usuarios (nome, email, senha_hash) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novoUsuario.getNome());
            stmt.setString(2, novoUsuario.getEmail());
            stmt.setString(3, novoUsuario.getSenhaHash()); 

            stmt.executeUpdate();
        }
    }
}