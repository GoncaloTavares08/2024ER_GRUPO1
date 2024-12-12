import java.util.ArrayList;

public class Emprestimo extends Transacao{
    private String dataPrevistaDevolucao;
    private String dataEfetivaDevolucao;


    public Emprestimo(String numero, Utente utente, ArrayList<Livro> livros, String dataInicio, String dataPrevistaDevolucao) {
        super(numero, utente, livros, dataInicio);
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.dataEfetivaDevolucao = null;
    }
}
