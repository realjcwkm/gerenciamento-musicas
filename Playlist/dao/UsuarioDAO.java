package dao;
import model.Usuario;
import java.sql.*;

public class UsuarioDAO {
    public Usuario autenticar(String email, String senha) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha_hash = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
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
    
    /**
     * Atualiza os dados de um usuário existente no banco.
     * @param usuario O objeto   Usuario com o ID e os novos dados.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    public void atualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuarios SET nome = ?, email = ?, senha_hash = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenhaHash());
            stmt.setInt(4, usuario.getId());

            stmt.executeUpdate();
        }
    }
}