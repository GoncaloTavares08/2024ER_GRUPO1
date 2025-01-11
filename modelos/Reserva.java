package modelos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    @Override
    public String toString() {
        List<String> titulosDocumentos = new ArrayList<>();
        for (Documento documento : getDocumentos()) {
            titulosDocumentos.add(documento.getTitulo());
        }
        return "[" +
                "Número: " + getNumero() +
                "; NIF do Utente: " + getUtente().getNif() +
                "; ISBN dos Livros: " + titulosDocumentos.toString().replace("[", "").replace("]", "") +
                "; Data de Registo: " + getDataRegisto() +
                "; Data de Inicio: " + getDataInicio() +
                "; Data de Fim: " + getDataFim() +
                ']';
    }

    public String toFileString() {
        ArrayList<String> titulosDocumentos = new ArrayList<>();
        for (Documento documento : this.documentos) {
            titulosDocumentos.add(documento.getTitulo());
        }
        return getNumero() +
                "|" + getUtente().getNif() +
                "|" + titulosDocumentos.toString().replace("[", "").replace("]", "") +
                "|" + getDataRegisto().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
                "|" + getDataInicio().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
                "|" + getDataFim().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}
