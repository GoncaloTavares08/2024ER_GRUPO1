package modelos;

import java.util.ArrayList;
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

    public String toFileString(){
        return getTitulo() +
                "|" + getEditora() +
                "|" + getCategoria() +
                "|" + getAnoEdicao() +
                "|" + getISBN() +
                "|" + getAutores().toString().replace("[", "").replace("]", "").replace(" ", "");
    }

    public static Livro fromString(String linha) {
        String[] partes = linha.split("\\|");
        String titulo = partes[0];
        String editora = partes[1];
        String categoria = partes[2];
        int anoEdicao = Integer.parseInt(partes[3]);
        String ISBN = partes[4];
        String[] autoresArray = partes[5].split(",");
        List<String> autores = new ArrayList<>(List.of(autoresArray));
        return new Livro(titulo, editora, categoria, anoEdicao, ISBN, autores);
    }

}
