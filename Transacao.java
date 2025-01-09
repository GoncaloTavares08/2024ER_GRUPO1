import java.util.ArrayList;

public class Transacao {
    private String numero;
    private Utente utente;
    private ArrayList<Livro> livros;
    private String dataInicio;

    public Transacao(String numero, Utente utente, ArrayList<Livro> livros, String dataInicio) {
        this.numero = numero;
        this.utente = utente;
        this.livros = livros;
        this.dataInicio = dataInicio;
    }

    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public Utente getUtente() {
        return utente;
    }
    public void setUtente(Utente utente) {
        this.utente = utente;
    }
    public ArrayList<Livro> getLivros() {
        return livros;
    }
    public void setLivros(ArrayList<Livro> livros) {
        this.livros = livros;
    }
    public String getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }
}
