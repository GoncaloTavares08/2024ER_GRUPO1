import java.util.ArrayList;

public class Emprestimo extends Transacao{
    private String dataPrevistaDevolucao;
    private String dataEfetivaDevolucao;


    public Emprestimo(String numero, Utente utente, ArrayList<Documento> livros, String dataInicio, String dataPrevistaDevolucao) {
        super(numero, utente, livros, dataInicio);
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.dataEfetivaDevolucao = "Nao Devolvido";
    }

    public Emprestimo(String numero, Utente utente, ArrayList<Documento> livros, String dataInicio, String dataPrevistaDevolucao, String dataEfetivaDevolucao) {
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
        ArrayList<String> idDocumentos = new ArrayList<>();
        for (Documento documento : getDocumentos()) {
            idDocumentos.add(documento.getIdentificador());
        }
        return "[" +
                "Número: " + getNumero() +
                "; NIF do Utente: " + getUtente().getNif() +
                "; ISBN dos Livros: " + idDocumentos.toString().replace("[", "").replace("]", "").replace(" ", "") +
                "; Data de Inicio: " + getDataInicio() +
                "; Data Prevista Devolução: " + getDataPrevistaDevolucao() +
                "; Data Efetiva Devolução: " + getDataEfetivaDevolucao() +
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
        ArrayList<Documento> documentos = new ArrayList<>();
        for (String id : partes[2].split(",")) {
            Documento documento = SistemaGestaoBiblioteca.procurarDocumentoPorIdentificador(id.trim());
            if (documento == null) {
                throw new IllegalArgumentException("Documento com identificador " + id + " não encontrado.");
            }
            documentos.add(documento);
        }
        String dataInicio = partes[3];
        String dataPrevistaDevolucao = partes[4];
        String dataEfetivaDevolucao = partes[5];
        if (dataEfetivaDevolucao.equals("Nao Devolvido")) {
            return new Emprestimo(numero, utente, documentos, dataInicio, dataPrevistaDevolucao);
        }else {
            return new Emprestimo(numero, utente, documentos, dataInicio, dataPrevistaDevolucao, dataEfetivaDevolucao);
        }
    }
}
