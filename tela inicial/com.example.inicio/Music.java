package com.example.inicio;

public class Music {
    private String titulo;
    private String artista;
    private String duracao;
    private String genero;
    private String path;
    private String imagemPath;

    public Music(String titulo, String artista, String duracao, String genero, String path, String imagemPath) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracao = duracao;
        this.genero = genero;
        this.path = path;
        this.imagemPath = imagemPath;
    }

    public String getTitulo() { return titulo; }
    public String getArtista() { return artista; }
    public String getDuracao() { return duracao; }
    public String getGenero() { return genero; }
    public String getPath() { return path; }
    public String getImagemPath() { return imagemPath; }

    @Override
    public String toString() {
        return titulo + " - " + artista;
    }
}
