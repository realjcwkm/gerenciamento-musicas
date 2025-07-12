package dao;

import model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe de Acesso a Dados para a entidade Usuario.
 * Contém os métodos para interagir com a tabela 'usuarios' no banco.
 * @author Kalebe Nobre de Aquino
 */
public class UsuarioDAO {

    /**
     * Autentica um usuário com base no email e na senha.
     * @param email O email do usuário para login.
     * @param senha A senha do usuário (corresponde a senha_hash no banco).
     * @return um objeto Usuario se a autenticação for bem-sucedida, ou null se falhar.
     * @throws SQLException se ocorrer um erro de comunicação com o banco.
     */
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