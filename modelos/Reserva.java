package modelos;

import java.time.LocalDate;
import java.util.ArrayList;

public class Reserva extends Transacao {
    private LocalDate dataRegisto;
    private LocalDate dataFim;

    public Reserva(String numero, Utente utente, ArrayList<Livro> livros, LocalDate dataInicio, LocalDate dataRegisto, LocalDate dataFim) {
        super(numero, utente, livros, dataInicio);
        this.dataRegisto = dataRegisto;
        this.dataFim = dataFim;
    }

    public LocalDate getDataRegisto() {
        return dataRegisto;
    }
    public void setDataRegisto(LocalDate dataRegisto) {
        this.dataRegisto = dataRegisto;
    }
    public LocalDate getDataFim() {
        return dataFim;
    }
    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
}
