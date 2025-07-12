package model;

/**
 * Representa a entidade Usuário, alinhada com a tabela 'usuarios' do banco.
 * @author Kalebe Nobre de Aquino
 */
public class Usuario {

    private int id;
    private String nome;
    private String email;
    private String senhaHash; // Corresponde à coluna senha_hash

    // Construtor vazio
    public Usuario() {
    }

    // --- Getters e Setters ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }
}