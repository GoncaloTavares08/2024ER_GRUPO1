import java.util.ArrayList;

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
}
