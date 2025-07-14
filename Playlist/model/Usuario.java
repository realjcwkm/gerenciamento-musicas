package model;

/**
 * Representa a entidade Usuário no sistema.
 * Esta classe serve como um "molde" para carregar e transportar os dados de um 
 * usuário entre as diferentes camadas da aplicação (View, DAO, etc.).
 *
 * @author Kalnobre
 */
public class Usuario {

    private int id;
    private String nome;
    private String email;
    private String senha; // Usaremos 'senha' no Java, que corresponderá a 'senha_hash' no banco

    public Usuario() {
    }

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
