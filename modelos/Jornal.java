package modelos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * A classe Jornal representa um documento do tipo jornal, que contém informações
 * como título, editora, categoria, ISSN e data de publicação.
 *
 * @author [Grupo1]
 * @version 1.0
 */
public class Jornal extends Documento {
    private String ISSN;
    private LocalDate dataPublicacao;
    /**
     * Construtor da classe Jornal.
     *
     * @param titulo          O título do jornal.
     * @param editora        A editora do jornal.
     * @param categoria      A categoria do jornal.
     * @param ISSN           O ISSN do jornal.
     * @param dataPublicacao  A data de publicação do jornal.
     */
    public Jornal(String titulo, String editora, String categoria, String ISSN, LocalDate dataPublicacao) {
        super(titulo, editora, categoria);
        this.ISSN = ISSN;
        this.dataPublicacao = dataPublicacao;
    }
    /**
     * Obtém o ISSN do jornal.
     *
     * @return ISSN do jornal.
     */
    public String getISSN() {
        return ISSN;
    }
    /**
     * Define o ISSN do jornal.
     *
     * @param ISSN O novo ISSN do jornal.
     */
    public void setISSN(String ISSN) {
        this.ISSN = ISSN;
    }
    /**
     * Obtém a data de publicação do jornal.
     *
     * @return Data de publicação do jornal.
     */
    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }
    /**
     * Define a data de publicação do jornal.
     *
     * @param dataPublicacao A nova data de publicação do jornal.
     */
    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }
    /**
     * Retorna uma representação em string do objeto do tipo Jornal.
     *
     * @return String formatada com as informações do jornal.
     */
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
    /**
     * Retorna uma string formatada para ser utilizada em ficheiro.
     *
     * @return String com os dados do jornal separados por '|'.
     */
    public String toFileString() {
        return getTitulo() +
                "|" + getEditora() +
                "|" + getCategoria() +
                "|" + getISSN() +
                "|" + getDataPublicacao().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
    /**
     * Cria um objeto Jornal a partir de uma string com os dados do jornal.
     *
     * @param dados A string que contém os dados do jornal separados por '|'.
     * @return Objeto do tipo Jornal com os dados extraídos da string.
     */
    public static Jornal fromString(String dados) {
        String[] partes = dados.split("\\|");
        String titulo = partes[0];
        String editora = partes[1];
        String categoria = partes[2];
        String ISSN = partes[3];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataPublicacao = LocalDate.parse(partes[4], formatter);
        return new Jornal(titulo, editora, categoria, ISSN, dataPublicacao);
    }
    /**
     * Obtém o identificador do documento, que neste caso é o ISSN.
     *
     * @return ISSN do jornal.
     */
    @Override
    public String getIdentificadorDocumento() {
        return this.ISSN;
    }
}
