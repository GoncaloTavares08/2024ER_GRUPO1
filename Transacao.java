import java.util.ArrayList;

public class Transacao {
    private String numero;
    private Utente utente;
    private ArrayList<Documento> documentos;
    private String dataInicio;

    public Transacao(String numero, Utente utente, ArrayList<Documento> documentos, String dataInicio) {
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
    public ArrayList<Documento> getDocumentos() {
        return documentos;
    }
    public void setDocumentos(ArrayList<Documento> documentos) {
        this.documentos = documentos;
    }
    public String getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }
}
