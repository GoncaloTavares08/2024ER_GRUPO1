import java.util.ArrayList;

public class Emprestimo extends Transacao{
    private String dataPrevistaDevolucao;
    private String dataEfetivaDevolucao;


    public Emprestimo(String numero, Utente utente, ArrayList<Livro> livros, String dataInicio, String dataPrevistaDevolucao) {
        super(numero, utente, livros, dataInicio);
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.dataEfetivaDevolucao = "Nao Devolvido";
    }

    public Emprestimo(String numero, Utente utente, ArrayList<Livro> livros, String dataInicio, String dataPrevistaDevolucao, String dataEfetivaDevolucao) {
        super(numero, utente, livros, dataInicio);
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.dataEfetivaDevolucao = dataEfetivaDevolucao;
    }

    public String getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }
    public void setDataPrevistaDevolucao(String dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }
    public String getDataEfetivaDevolucao() {
        return dataEfetivaDevolucao;
    }
    public void setDataEfetivaDevolucao(String dataEfetivaDevolucao) {
        this.dataEfetivaDevolucao = dataEfetivaDevolucao;
    }

    @Override
    public String toString() {
        ArrayList<String> isbnLivros = new ArrayList<>();
        for (Livro livro : getLivros()) {
            isbnLivros.add(livro.getISBN());
        }
        return "[" +
                "Número: " + getNumero() +
                "; NIF do Utente: " + getUtente().getNif() +
                "; ISBN dos Livros: " + isbnLivros.toString().replace("[", "").replace("]", "").replace(" ", "") +
                "; Data de Inicio: " + getDataInicio() +
                "; Data Prevista Devolução: " + getDataPrevistaDevolucao() +
                "; Data Efetiva Devolução: " + getDataEfetivaDevolucao() +
                ']';
    }

    public String toFileString(){
        ArrayList<String> isbnLivros = new ArrayList<>();
        for (Livro livro : getLivros()) {
            isbnLivros.add(livro.getISBN());
        }
        return getNumero() +
                "|" + getUtente().getNif() +
                "|" + isbnLivros.toString().replace("[", "").replace("]", "").replace(" ", "") +
                "|" + getDataInicio() +
                "|" + getDataPrevistaDevolucao() +
                "|" + getDataEfetivaDevolucao();
    }

    public static Emprestimo fromString(String dados) {
        String[] partes = dados.split("\\|");
        String numero = partes[0];
        String nif = partes[1];
        Utente utente = SistemaGestaoBiblioteca.procurarUtentePorNIF(nif);
        if (utente == null) {
            throw new IllegalArgumentException("Utente com NIF " + nif + " não encontrado.");
        }
        ArrayList<Livro> livros = new ArrayList<>();
        for (String isbn : partes[2].split(",")) {
            Livro livro = SistemaGestaoBiblioteca.procurarLivroPorISBN(isbn.trim());
            if (livro == null) {
                throw new IllegalArgumentException("Livro com ISBN " + isbn + " não encontrado.");
            }
            livros.add(livro);
        }
        String dataInicio = partes[3];
        String dataPrevistaDevolucao = partes[4];
        String dataEfetivaDevolucao = partes[5];
        if (dataEfetivaDevolucao.equals("Nao Devolvido")) {
            return new Emprestimo(numero, utente, livros, dataInicio, dataPrevistaDevolucao);
        }else {
            return new Emprestimo(numero, utente, livros, dataInicio, dataPrevistaDevolucao, dataEfetivaDevolucao);
        }
    }
}
