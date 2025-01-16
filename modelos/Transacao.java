package modelos;

import java.time.LocalDate;
import java.util.List;

public abstract class Transacao {
    protected String numero;
    protected Utente utente;
    protected List<Documento> documentos;
    protected LocalDate dataInicio;

    public Transacao(String numero, Utente utente, List<Documento> documentos, LocalDate dataInicio) {
        this.numero = numero;
        this.utente = utente;
        this.documentos = documentos;
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

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public abstract LocalDate getDataFim();

    public boolean estaAtivaEntre(LocalDate periodoInicio, LocalDate periodoFim) {
        return (dataInicio.isBefore(periodoFim) || dataInicio.isEqual(periodoFim)) &&
                (this.getDataFim().isAfter(periodoInicio) || this.getDataFim().isEqual(periodoInicio));
    }

}
