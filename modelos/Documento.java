package modelos;
/**
 * Classe abstrata que representa um Documento.
 * Esta classe contém as informações básicas sobre um documento, como título, editora e categoria.
 * @author [João Teixeira]
 * @version 1.0
 */
 public abstract class Documento {
    protected String titulo;
    protected String editora;
    protected String categoria;
    /**
     * Construtor da classe Documento.
     *
     * @param titulo O título do documento.
     * @param editora A editora do documento.
     * @param categoria A categoria do documento.
     */
    public Documento(String titulo, String editora, String categoria) {
        this.titulo = titulo;
        this.editora = editora;
        this.categoria = categoria;
    }
    /**
     * Obtém o título do documento.
     *
     * @return O título do documento.
     */
    public String getTitulo() {
        return titulo;
    }
    /**
     * Define o título do documento.
     *
     * @param titulo O novo título do documento.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    /**
     * Obtém a editora do documento.
     *
     * @return A editora do documento.
     */
    public String getEditora() {
        return editora;
    }

    /**
     * Define a editora do documento.
     *
     * @param editora A nova editora do documento.
     */
    public void setEditora(String editora) {
        this.editora = editora;
    }
    /**
     * Obtém a categoria do documento.
     *
     * @return A categoria do documento.
     */
    public String getCategoria() {
        return categoria;
    }
    /**
     * Define a categoria do documento.
     *
     * @param categoria A nova categoria do documento.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    /**
     *Metodo abstrato que deve ser implementado pelas subclasses para obter o identificador do documento.
     *
     * @return O identificador do documento.
     */
    public abstract String getIdentificadorDocumento();
}

