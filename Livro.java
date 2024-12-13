import java.util.ArrayList;
import java.util.List;

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

    public static Livro fromString(String dados) {
        dados = dados.replace("[", "").replace("]", "").trim();
        String[] partes = dados.split(", ");
        String titulo = partes[0].split(": ")[1];
        String editora = partes[1].split(": ")[1];
        String categoria = partes[2].split(": ")[1];
        String anoEdicao = partes[3].split(": ")[1];
        String ISBN = partes[4].split(": ")[1];
        String[] autoresArray = partes[5].split(": ")[1].split(", ");
        ArrayList<String> autores = new ArrayList<>(List.of(autoresArray));
        return new Livro(titulo, editora, categoria, anoEdicao, ISBN, autores);
    }


}
