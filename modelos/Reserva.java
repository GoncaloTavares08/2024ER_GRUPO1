package modelos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
/**
 * Classe que representa uma reserva de documentos.
 * Esta classe estende a classe Transacao e contém informações sobre a reserva,
 * incluindo datas de registo e fim.
 * @author [João Teixeira]
 * @version 1.0
 */
public class Reserva extends Transacao {
    private LocalDate dataRegisto;
    private LocalDate dataFim;
    /**
     * Construtor da classe Reserva.
     *
     * @param numero      O número da reserva.
     * @param utente     O utente que realiza a reserva.
     * @param documentos  A lista de documentos a serem reservados.
     * @param dataInicio  A data de início da reserva.
     * @param dataRegisto A data em que a reserva foi registada.
     * @param dataFim     A data em que a reserva termina.
     */
    public Reserva(String numero, Utente utente, List<Documento> documentos, LocalDate dataInicio, LocalDate dataRegisto, LocalDate dataFim) {
        super(numero, utente, documentos, dataInicio);
        this.dataRegisto = dataRegisto;
        this.dataFim = dataFim;
    }
    /**
     * Obtém a data de registo da reserva.
     *
     * @return A data de registo.
     */
    public LocalDate getDataRegisto() {
        return dataRegisto;
    }
    /**
     * Define a data de registo da reserva.
     *
     * @param dataRegisto A nova data de registo.
     */
    public void setDataRegisto(LocalDate dataRegisto) {
        this.dataRegisto = dataRegisto;
    }
    /**
     * Obtém a data de fim da reserva.
     *
     * @return A data de fim.
     */
    public LocalDate getDataFim() {
        return dataFim;
    }
    /**
     * Define a data de fim da reserva.
     *
     * @param dataFim A nova data de fim.
     */
    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
    /**
     * Retorna uma representação em string da reserva.
     *
     * @return Uma string que contém informações sobre a reserva.
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
                "; Data de Registo: " + getDataRegisto().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
                "; Data de Inicio: " + getDataInicio().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
                "; Data de Fim: " + getDataFim().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
                ']';
    }
    /**
     * Retorna uma representação em string da reserva formatada para ser salva em ficheiro.
     *
     * @return Uma string formatada para armazenamento em ficheiro.
     */
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