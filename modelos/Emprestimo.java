package modelos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
/**
 * Classe que representa um empréstimo de documentos.
 * Extende a SuperClasse Transacao e contém informações sobre a data de devolução prevista e efetiva.
 * @author [Grupo1]
 * @version 1.0
 */
public class Emprestimo extends Transacao {
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataEfetivaDevolucao;
    /**
     * Construtor para criar um empréstimo, sem data de devolução efetiva.
     *
     * @param numero                  O número do empréstimo.
     * @param utente                  O utente que realiza o empréstimo.
     * @param documentos              A lista de documentos a serem emprestados.
     * @param dataInicio              A data de início do empréstimo.
     * @param dataPrevistaDevolucao   A data prevista para a devolução dos documentos.
     */
    public Emprestimo(String numero, Utente utente, List<Documento> documentos, LocalDate dataInicio, LocalDate dataPrevistaDevolucao) {
        super(numero, utente, documentos, dataInicio);
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.dataEfetivaDevolucao = null;
    }
    /**
     * Construtor para criar um empréstimo, com data de devolução efetiva.
     *
     * @param numero                  O número do empréstimo.
     * @param utente                  O utente que realiza o empréstimo.
     * @param documentos              A lista de documentos a serem emprestados.
     * @param dataInicio              A data de início do empréstimo.
     * @param dataPrevistaDevolucao   A data prevista para a devolução dos documentos.
     * @param dataEfetivaDevolucao    A data efetiva em que os documentos foram devolvidos.
     */
    public Emprestimo(String numero, Utente utente, List<Documento> documentos, LocalDate dataInicio, LocalDate dataPrevistaDevolucao, LocalDate dataEfetivaDevolucao) {
        super(numero, utente, documentos, dataInicio);
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.dataEfetivaDevolucao = dataEfetivaDevolucao;
    }

    /**
     * Verifica se o empréstimo está atrasado com base no número de dias inserido.
     *
     * @param numeroDias O número de dias a considerar para verificar o atraso.
     * @return True se o empréstimo está atrasado, false caso contrário.
     */
    public boolean estaAtrasado(int numeroDias) {
        if (dataEfetivaDevolucao != null) {
            return dataPrevistaDevolucao.plusDays(numeroDias).isBefore(dataEfetivaDevolucao);
        } else {
            return dataPrevistaDevolucao.plusDays(numeroDias).isBefore(LocalDate.now());
        }
    }
    /**
     * Obtém a data prevista de devolução.
     *
     * @return Data prevista de devolução.
     */
    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }
    /**
     * Define a data prevista de devolução.
     *
     * @param dataPrevistaDevolucao A nova data prevista de devolução.
     */
    public void setDataPrevistaDevolucao(LocalDate dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }
    /**
     * Obtém a data efetiva de devolução.
     *
     * @return Data efetiva de devolução, ou null se não tiver sido devolvido.
     */
    public LocalDate getDataEfetivaDevolucao() {
        return dataEfetivaDevolucao;
    }

    /**
     * Define a data efetiva de devolução.
     *
     * @param dataEfetivaDevolucao A nova data efetiva de devolução.
     */
    public void setDataEfetivaDevolucao(LocalDate dataEfetivaDevolucao) {
        this.dataEfetivaDevolucao = dataEfetivaDevolucao;
    }
    /**
     * Retorna uma representação em string do empréstimo.
     *
     * @return String que representa o empréstimo, incluindo detalhes como número, utente, documentos e datas.
     */
    @Override
    public String toString() {
        List<String> idDocumentos = new ArrayList<>();
        for (Documento documento : getDocumentos()) {
            idDocumentos.add(documento.getIdentificadorDocumento());
        }
        return "[" +
                "Número: " + getNumero() +
                "; NIF do Utente: " + getUtente().getNif() +
                "; ID dos Documentos: " + idDocumentos.toString().replace("[", "").replace("]", "") +
                "; Data de Inicio: " + getDataInicio().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
                "; Data Prevista Devolução: " + getDataPrevistaDevolucao().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
                "; Data Efetiva Devolução: " + (getDataEfetivaDevolucao() != null ? getDataEfetivaDevolucao().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : "Não Devolvido") +
                ']';
    }
    /**
     * Retorna uma representação em string do empréstimo formatada para ser guardado em ficheiro.
     *
     * @return String formatada para armazenamento em arquivo, incluindo número, utente, documentos e datas.
     */
    public String toFileString() {
        ArrayList<String> idDocumentos = new ArrayList<>();
        for (Documento documento : this.documentos) {
            idDocumentos.add(documento.getIdentificadorDocumento());
        }
        String output = getNumero() +
                "|" + getUtente().getNif() +
                "|" + idDocumentos.toString().replace("[", "").replace("]", "") +
                "|" + getDataInicio().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
                "|" + getDataPrevistaDevolucao().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        if (getDataEfetivaDevolucao() != null) {
            output += "|" + getDataEfetivaDevolucao().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } else {
            output += "|Nao Devolvido";
        }
        return output;
    }

    /**
     * Obtém a data de fim do empréstimo, que é a data prevista de devolução.
     *
     * @return Data prevista de devolução.
     */
    @Override
    public LocalDate getDataFim() {
        return getDataPrevistaDevolucao();
    }
}
