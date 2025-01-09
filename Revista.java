public class Revista extends Documento {
    private String ISSN;
    private String dataPublicacao;

    public Revista(String titulo, String editora, String categoria, String ISSN, String dataPublicacao) {
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
                "; Editora: " + getEditora() +
                "; Categoria: " + getCategoria() +
                "; ISSN: " + getISSN() +
                "; Data de Publicação: " + getDataPublicacao() +
                ']';
    }

    public String toFileString(){
        return getTitulo() +
                "|" + getEditora() +
                "|" + getCategoria() +
                "|" + getISSN() +
                "|" + getDataPublicacao();
    }

    public static Revista fromString(String dados) {
        String[] partes = dados.split("\\|");
        String titulo = partes[0];
        String editora = partes[1];
        String categoria = partes[2];
        String ISSN = partes[3];
        String dataPublicacao = partes[4];
        return new Revista(titulo, editora, categoria, ISSN, dataPublicacao);
    }

    @Override
    public String getIdentificador() {
        return ISSN;
    }
}
