public class Jornal extends Documentos{
    private String ISSN;
    private String dataPublicacao;


    public Jornal(String titulo, String editora, String categoria, String ISSN, String dataPublicacao) {
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
    public String getDataPublicacao() {
        return dataPublicacao;
    }
    public void setDataPublicacao(String dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    @Override
    public String toString() {
        return "[" +
                "Título: " + getTitulo() +
                ", Editora: " + getEditora() +
                ", Categoria: " + getCategoria() +
                ", ISSN: " + getISSN() +
                ", Data de Publicação: " + getDataPublicacao() +
                ']';
    }
}
