import java.util.ArrayList;
import java.util.List;

public class Reserva extends Transacao{
    private String dataRegisto;
    private String dataFim;

    public Reserva(String numero, Utente utente, ArrayList<Livro> livros, String dataInicio, String dataRegisto, String dataFim) {
        super(numero, utente, livros, dataInicio);
        this.dataRegisto = dataRegisto;
        this.dataFim = dataFim;
    }

    public String getDataRegisto() {
        return dataRegisto;
    }
    public void setDataRegisto(String dataRegisto) {
        this.dataRegisto = dataRegisto;
    }
    public String getDataFim() {
        return dataFim;
    }
    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
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
                "; Data de Registo: " + getDataRegisto() +
                "; Data de Inicio: " + getDataInicio() +
                "; Data de Fim: " + getDataFim() +
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
                "|" + getDataRegisto() +
                "|" + getDataInicio() +
                "|" + getDataFim();
    }

    public static Reserva fromString(String dados) {
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
        String dataRegisto = partes[3];
        String dataInicio = partes[4];
        String dataFim = partes[5];
        return new Reserva(numero, utente, livros, dataInicio, dataRegisto, dataFim);
    }

}
