package modelos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Jornal extends Documento{
    private String ISSN;
    private LocalDate dataPublicacao;

    public Jornal(String titulo, String editora, String categoria, String ISSN, LocalDate dataPublicacao) {
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

}
