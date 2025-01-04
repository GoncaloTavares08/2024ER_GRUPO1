package modelos;

import java.util.List;

public class Livro extends Documento{
    private int anoEdicao;
    private String ISBN;
    private List<String> autores;

    public Livro(String titulo, String editora, String categoria, int anoEdicao, String ISBN,
                 List<String> autores) {
        super(titulo, editora, categoria);
        this.anoEdicao = anoEdicao;
        this.ISBN = ISBN;
        this.autores = autores;
    }

    public int getAnoEdicao() {
        return anoEdicao;
    }

    public void setAnoEdicao(int anoEdicao) {
        this.anoEdicao = anoEdicao;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public List<String> getAutores() {
        return autores;
    }

    public void setAutores(List<String> autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
        return "[" +
                "Título: " + getTitulo() +
                "; Editora: " + getEditora() +
                "; Categoria: " + getCategoria() +
                "; Ano de Edição: " + getAnoEdicao() +
                "; ISBN: " + getISBN() +
                "; Autores: " + getAutores().toString().replace("[", "").replace("]", "").replace(" ", "") +
                ']';
    }

}
