import java.util.ArrayList;

public class Livro extends Documentos{
    private String anoEdicao;
    private String ISBN;
    private ArrayList<String> autores;

    public Livro(String titulo, String editora, String categoria, String anoEdicao, String ISBN, ArrayList<String> autores) {
        super(titulo, editora, categoria);
        this.anoEdicao = anoEdicao;
        this.ISBN = ISBN;
        this.autores = autores;
    }

    public String getAnoEdicao() {
        return anoEdicao;
    }
    public void setAnoEdicao(String anoEdicao) {
        this.anoEdicao = anoEdicao;
    }
    public String getISBN() {
        return ISBN;
    }
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    public ArrayList<String> getAutores() {
        return autores;
    }
    public void setAutores(ArrayList<String> autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
        return "[" +
                "Título: " + getTitulo() +
                ", Editora: " + getEditora() +
                ", Categoria: " + getCategoria() +
                ", Ano de Edição: " + getAnoEdicao() +
                ", ISBN: " + getISBN() +
                ", Autores: " + getAutores() +
                ']';
    }

}
