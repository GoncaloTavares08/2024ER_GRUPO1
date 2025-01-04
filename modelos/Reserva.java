package modelos;

import java.time.LocalDate;
import java.util.List;

public class Reserva extends Transacao {
    private LocalDate dataRegisto;
    private LocalDate dataFim;

    public Reserva(String numero, Utente utente, List<Documento> documentos, LocalDate dataInicio, LocalDate dataRegisto, LocalDate dataFim) {
        super(numero, utente, documentos, dataInicio);
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
