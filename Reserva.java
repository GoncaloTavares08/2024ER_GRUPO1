import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

public class Reserva extends Transacao{
    private String dataRegisto;
    private String dataFim;

    public Reserva(String numero, Utente utente, ArrayList<Documento> documentos, String dataInicio, String dataRegisto, String dataFim) {
        super(numero, utente, documentos, dataInicio);
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
        ArrayList<String> idDocumentos = new ArrayList<>();
        for (Documento documento : getDocumentos()) {
            idDocumentos.add(documento.getIdentificador());
        }
        return "[" +
                "Número: " + getNumero() +
                "; NIF do Utente: " + getUtente().getNif() +
                "; ID dos Documentos: " + idDocumentos.toString().replace("[", "").replace("]", "").replace(" ", "") +
                "; Data de Registo: " + getDataRegisto() +
                "; Data de Inicio: " + getDataInicio() +
                "; Data de Fim: " + getDataFim() +
                ']';
    }

    public String toFileString(){
        ArrayList<String> idDocumentos = new ArrayList<>();
        for (Documento documento : getDocumentos()) {
            idDocumentos.add(documento.getIdentificador());
        }
        return getNumero() +
                "|" + getUtente().getNif() +
                "|" + idDocumentos.toString().replace("[", "").replace("]", "").replace(" ", "") +
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
        ArrayList<Documento> documentos = new ArrayList<>();
        for (String id : partes[2].split(",")) {
            Documento documento = SistemaGestaoBiblioteca.procurarDocumentoPorIdentificador(id.trim());
            if (documento == null) {
                throw new IllegalArgumentException("Documento com identificador " + id + " não encontrado.");
            }
            documentos.add(documento);
        }
        String dataRegisto = partes[3];
        String dataInicio = partes[4];
        String dataFim = partes[5];
        return new Reserva(numero, utente, documentos, dataInicio, dataRegisto, dataFim);
    }

}
