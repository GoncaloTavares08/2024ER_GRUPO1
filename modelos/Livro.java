package modelos;

import java.util.ArrayList;
import java.util.List;
/**
 * A classe Livro representa um livro que é um tipo de Documento.
 * Contém informações sobre o título, editora, categoria, ano de edição, ISBN e autores do livro.
 * @author [João Teixeira]
 * @version 1.0
 */
public class Livro extends Documento {
    private int anoEdicao;
    private String ISBN;
    private List<String> autores;
    /**
     * Construtor da classe Livro.
     *
     * @param titulo     O título do livro.
     * @param editora    A editora do livro.
     * @param categoria  A categoria do livro.
     * @param anoEdicao  O ano de edição do livro.
     * @param ISBN       O ISBN do livro.
     * @param autores    A lista de autores do livro.
     */
    public Livro(String titulo, String editora, String categoria, int anoEdicao, String ISBN,
                 List<String> autores) {
        super(titulo, editora, categoria);
        this.anoEdicao = anoEdicao;
        this.ISBN = ISBN;
        this.autores = autores;
    }
    /**
     * Obtém o ano de edição do livro.
     *
     * @return O ano de edição do livro.
     */
    public int getAnoEdicao() {
        return anoEdicao;
    }
    /**
     * Define o ano de edição do livro.
     *
     * @param anoEdicao O ano de edição a ser definido.
     */
    public void setAnoEdicao(int anoEdicao) {
        this.anoEdicao = anoEdicao;
    }
    /**
     * Obtém o ISBN do livro.
     *
     * @return O ISBN do livro.
     */
    public String getISBN() {
        return ISBN;
    }
    /**
     * Define o ISBN do livro.
     *
     * @param ISBN O ISBN a ser definido.
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    /**
     * Obtém a lista de autores do livro.
     *
     * @return A lista de autores do livro.
     */
    public List<String> getAutores() {
        return autores;
    }
    /**
     * Define a lista de autores do livro.
     *
     * @param autores A lista de autores a ser definida.
     */
    public void setAutores(List<String> autores) {
        this.autores = autores;
    }
    /**
     * Retorna uma representação em string do livro.
     *
     * @return Uma string que representa o livro.
     */
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
    /**
     * Retorna uma representação em string do livro formatada para ser salva em um arquivo.
     *
     * @return Uma string formatada para arquivo.
     */
    public String toFileString() {
        return getTitulo() +
                "|" + getEditora() +
                "|" + getCategoria() +
                "|" + getAnoEdicao() +
                "|" + getISBN() +
                "|" + getAutores().toString().replace("[", "").replace("]", "").replace(" ", "");
    }
    /**
     * Cria um objeto Livro a partir de uma string formatada.
     *
     * @param linha A string que contém os dados do livro.
     * @return Um objeto Livro criado a partir da string.
     */
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
    /**
     * Obtém o identificador do documento, que neste caso é o ISBN.
     *
     * @return O ISBN do livro como identificador do documento.
     */
    @Override
    public String getIdentificadorDocumento() {
        return this.ISBN;
    }
}
