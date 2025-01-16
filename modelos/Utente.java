package modelos;
/**
 * A classe Utente representa um utilizador com informações pessoais.
 * Contém atributos como NIF, nome, género e contacto, e fornece métodos
 * para gerir e aceder a esses dados.
 * @author [João Teixeira]
 * @version 1.0
 */
public class Utente {
    private String nif;
    private String nome;
    private char genero;
    private String contacto;
    /**
     * Construtor da classe Utente.
     *
     * @param nif     O Número de Identificação Fiscal do utilizador.
     * @param nome    O nome do utilizador.
     * @param genero  O género do utilizador, representado por um carácter.
     * @param contacto O contacto do utilizador.
     */
    public Utente(String nif, String nome, char genero, String contacto) {
        this.nif = nif;
        this.nome = nome;
        this.genero = genero;
        this.contacto = contacto;
    }
    /**
     * Obtém o NIF do utilizador.
     *
     * @return O NIF do utilizador.
     */
    public String getNif() {
        return nif;
    }
    /**
     * Define o NIF do utilizador.
     *
     * @param nif O novo NIF do utilizador.
     */
    public void setNif(String nif) {
        this.nif = nif;
    }
    /**
     * Obtém o nome do utilizador.
     *
     * @return O nome do utilizador.
     */
    public String getNome() {
        return nome;
    }
    /**
     * Define o nome do utilizador.
     *
     * @param nome O novo nome do utilizador.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * Obtém o género do utilizador.
     *
     * @return O género do utilizador.
     */
    public char getGenero() {
        return genero;
    }
    /**
     * Define o género do utilizador.
     *
     * @param genero O novo género do utilizador, representado por um carácter.
     */
    public void setGenero(char genero) {
        this.genero = genero;
    }
    /**
     * Obtém o contacto do utilizador.
     *
     * @return O contacto do utilizador.
     */
    public String getContacto() {
        return contacto;
    }
    /**
     * Define o contacto do utilizador.
     *
     * @param contacto O novo contacto do utilizador.
     */
    public void setContacto(String contacto) {
        this.contacto = contacto;
    }
    /**
     * Retorna uma representação em string do utilizador.
     *
     * @return Uma string formatada com os dados do utilizador.
     */
    @Override
    public String toString() {
        return "[" +
                "NIF: " + getNif() +
                "; Nome: " + getNome() +
                "; Género: " + getGenero() +
                "; Contacto: " + getContacto() +
                ']';
    }
    /**
     * Retorna uma representação em string dos dados do utilizador,
     * formatada para ser armazenada em um arquivo.
     *
     * @return Uma string formatada para armazenamento em arquivo.
     */
    public String toFileString() {
        return getNif() +
                "|" + getNome() +
                "|" + getGenero() +
                "|" + getContacto();
    }
    /**
     * Cria um objeto Utente a partir de uma string formatada.
     *
     * @param dados A string contendo os dados do utilizador, separados por '|'.
     * @return Um objeto Utente com os dados extraídos da string.
     */
    public static Utente fromString(String dados) {
        String[] partes = dados.split("\\|");
        String nif = partes[0];
        String nome = partes[1];
        char genero = partes[2].charAt(0);
        String contacto = partes[3];
        return new Utente(nif, nome, genero, contacto);
    }
}