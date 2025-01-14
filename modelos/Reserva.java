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
        List<String> idDocumentos = new ArrayList<>();
        for (Documento documento : getDocumentos()) {
            idDocumentos.add(documento.getIdentificadorDocumento());
        }
        return "[" +
                "Número: " + getNumero() +
                "; NIF do Utente: " + getUtente().getNif() +
                "; ISBN dos Livros: " + idDocumentos.toString().replace("[", "").replace("]", "") +
                "; Data de Registo: " + getDataRegisto().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
                "; Data de Inicio: " + getDataInicio().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
                "; Data de Fim: " + getDataFim().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
                ']';
    }

    public String toFileString() {
        ArrayList<String> idDocumentos = new ArrayList<>();
        for (Documento documento : this.documentos) {
            idDocumentos.add(documento.getIdentificadorDocumento());
        }
        return getNumero() +
                "|" + getUtente().getNif() +
                "|" + idDocumentos.toString().replace("[", "").replace("]", "") +
                "|" + getDataRegisto().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
                "|" + getDataInicio().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
                "|" + getDataFim().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}
