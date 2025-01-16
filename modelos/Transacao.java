
package modelos;

import java.time.LocalDate;
import java.util.List;
/**
 * Classe abstrata que representa uma transação.
 * Esta classe contém informações sobre a transação, incluindo o número, o utente,
 * os documentos associados e a data de início.
 * @author [Grupo1]
 * @version 1.0
 */
public abstract class Transacao {
    protected String numero;
    protected Utente utente;
    protected List<Documento> documentos;
    protected LocalDate dataInicio;
    /**
     * Construtor da classe Transacao.
     *
     * @param numero      O número da transação.
     * @param utente      O utente associado à transação.
     * @param documentos  A lista de documentos associados à transação.
     * @param dataInicio  A data de início da transação.
     */
    public Transacao(String numero, Utente utente, List<Documento> documentos, LocalDate dataInicio) {
        this.numero = numero;
        this.utente = utente;
        this.documentos = documentos;
        this.dataInicio = dataInicio;
    }
    /**
     * Obtém o número da transação.
     *
     * @return Número da transação.
     */
    public String getNumero() {
        return numero;
    }
    /**
     * Define o número da transação.
     *
     * @param numero O novo número da transação.
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }
    /**
     * Obtém o utente associado à transação.
     *
     * @return Utente associado à transação.
     */
    public Utente getUtente() {
        return utente;
    }
    /**
     * Define o utente associado à transação.
     *
     * @param utente O novo utente associado à transação.
     */
    public void setUtente(Utente utente) {
        this.utente = utente;
    }
    /**
     * Obtém a lista de documentos associados à transação.
     *
     * @return Lista de documentos.
     */
    public List<Documento> getDocumentos() {
        return documentos;
    }
    /**
     * Define a lista de documentos associados à transação.
     *
     * @param documentos A nova lista de documentos.
     */
    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }
    /**
     * Obtém a data de início da transação.
     *
     * @return Data de início da transação.
     */
    public LocalDate getDataInicio() {
        return dataInicio;
    }
    /**
     * Define a data de início da transação.
     *
     * @param dataInicio A nova data de início da transação.
     */
    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }
    /**
     * Metodo abstrato que deve ser implementado pelas subclasses para obter a data de fim da transação.
     *
     * @return Data de fim da transação.
     */
    public abstract LocalDate getDataFim();
    /**
     * Verifica se a transação está ativa entre duas datas.
     *
     * @param periodoInicio A data de início do período a verificar.
     * @param periodoFim   A data de fim do período a verificar.
     * @return True se a transação estiver ativa entre as duas datas, false caso contrário.
     */
    public boolean estaAtivaEntre(LocalDate periodoInicio, LocalDate periodoFim) {
        return (dataInicio.isBefore(periodoFim) || dataInicio.isEqual(periodoFim)) &&
                (this.getDataFim().isAfter(periodoInicio) || this.getDataFim().isEqual(periodoInicio));
    }
}