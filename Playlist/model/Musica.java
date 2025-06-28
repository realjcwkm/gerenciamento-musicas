package model;

public class Musica {
    private int id;
    private String titulo;
    private String artista;
    private String caminhoArquivo;

    public Musica(int id, String titulo, String artista, String caminhoArquivo) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.caminhoArquivo = caminhoArquivo;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getArtista() { return artista; }
    public String getCaminhoArquivo() { return caminhoArquivo; }

    @Override
    public String toString() {
        return titulo + " - " + artista;
    }
}
