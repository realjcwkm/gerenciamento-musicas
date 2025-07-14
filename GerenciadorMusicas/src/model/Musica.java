package model;

public class Musica {
    private final int id;
    private final String titulo;
    private final String artista;
    private final String caminhoArquivo;

    /**
     * Construtor padrão.
     *
     * @param id              Identificador único da música
     * @param titulo          Título da música
     * @param artista         Nome do artista
     * @param caminhoArquivo  Caminho local do arquivo MP3
     */
    public Musica(int id, String titulo, String artista, String caminhoArquivo) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.caminhoArquivo = caminhoArquivo;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }

    @Override
    public String toString() {
        return titulo + " - " + artista;
    }
}
