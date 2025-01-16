package modelos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * A classe Revista representa uma revista, que é um tipo de documento.
 * Esta classe estende a SuperClasse Documento e contém informações específicas
 * sobre revistas, como o ISSN e a data de publicação.
 * @author [Grupo1]
 * @version 1.0
 */
public class Revista extends Documento {
    private String ISSN;
    private LocalDate dataPublicacao;
    /**
     * Construtor da classe Revista.
     *
     * @param titulo          O título da revista.
     * @param editora         A editora da revista.
     * @param categoria       A categoria da revista.
     * @param ISSN           O número ISSN da revista.
     * @param dataPublicacao  A data de publicação da revista.
     */
    public Revista(String titulo, String editora, String categoria, String ISSN, LocalDate dataPublicacao) {
        super(titulo, editora, categoria);
        this.ISSN = ISSN;
        this.dataPublicacao = dataPublicacao;
    }
    /**
     * Obtém o ISSN da revista.
     *
     * @return ISSN da revista.
     */
    public String getISSN() {
        return ISSN;
    }
    /**
     * Define o ISSN da revista.
     *
     * @param ISSN O novo ISSN da revista.
     */
    public void setISSN(String ISSN) {
        this.ISSN = ISSN;
    }
    /**
     * Obtém a data de publicação da revista.
     *
     * @return Data de publicação da revista.
     */
    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }
    /**
     * Define a data de publicação da revista.
     *
     * @param dataPublicacao A nova data de publicação da revista.
     */
    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }
    /**
     * Retorna uma representação em string da revista.
     *
     * @return String formatada com os detalhes da revista.
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
     * Retorna uma string formatada para ser armazenada em um arquivo.
     *
     * @return String com os detalhes da revista, separada por '|'.
     */
    public String toFileString() {
        return getTitulo() +
                "|" + getEditora() +
                "|" + getCategoria() +
                "|" + getISSN() +
                "|" + getDataPublicacao().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
    /**
     * Cria uma instância de Revista a partir de uma string formatada.
     *
     * @param dados A string com os detalhes da revista, separada por '|'.
     * @return Nova instância de Revista.
     */
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
    /**
     * Obtém o identificador do documento, que neste caso é o ISSN.
     *
     * @return ISSN da revista.
     */
    @Override
    public String getIdentificadorDocumento() {
        return this.ISSN;
    }
}