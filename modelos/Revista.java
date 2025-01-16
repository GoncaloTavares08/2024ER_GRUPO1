package modelos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Revista extends Documento {
    private String ISSN;
    private LocalDate dataPublicacao;

    public Revista(String titulo, String editora, String categoria, String ISSN, LocalDate dataPublicacao) {
        super(titulo, editora, categoria);
        this.ISSN = ISSN;
        this.dataPublicacao = dataPublicacao;
    }

    public String getISSN() {
        return ISSN;
    }

    public void setISSN(String ISSN) {
        this.ISSN = ISSN;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    @Override
    public String toString() {
        return "[" +
                "Título: " + getTitulo() +
                "; Editora: " + getEditora() +
                "; Categoria: " + getCategoria() +
                "; ISSN: " + getISSN() +
                "; Data de Publicação: " + getDataPublicacao().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
                ']';
    }

    public String toFileString() {
        return getTitulo() +
                "|" + getEditora() +
                "|" + getCategoria() +
                "|" + getISSN() +
                "|" + getDataPublicacao().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public static Revista fromString(String dados) {
        String[] partes = dados.split("\\|");
        String titulo = partes[0];
        String editora = partes[1];
        String categoria = partes[2];
        String ISSN = partes[3];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataPublicacao = LocalDate.parse(partes[4], formatter);
        return new Revista(titulo, editora, categoria, ISSN, dataPublicacao);
    }

    @Override
    public String getIdentificadorDocumento() {
        return this.ISSN;
    }
}
