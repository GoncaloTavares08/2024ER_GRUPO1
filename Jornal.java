import java.util.ArrayList;
import java.util.List;

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

    public static Jornal fromString(String dados) {
        dados = dados.replace("[", "").replace("]", "").trim();
        String[] partes = dados.split(", ");
        String titulo = partes[0].split(": ")[1];
        String editora = partes[1].split(": ")[1];
        String categoria = partes[2].split(": ")[1];
        String ISSN = partes[3].split(": ")[1];
        String dataPublicacao = partes[4].split(": ")[1];
        return new Jornal(titulo, editora, categoria, ISSN, dataPublicacao);
    }
}
