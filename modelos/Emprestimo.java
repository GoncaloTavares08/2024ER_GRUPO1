package modelos;

import java.time.LocalDate;
import java.util.List;

public class Emprestimo extends Transacao{
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataEfetivaDevolucao;

    public Emprestimo(String numero, Utente utente, List<Documento> documentos, LocalDate dataInicio, LocalDate dataPrevistaDevolucao) {
        super(numero, utente, documentos, dataInicio);
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.dataEfetivaDevolucao = null;
    }

    public Emprestimo(String numero, Utente utente, List<Documento> documentos, LocalDate dataInicio, LocalDate dataPrevistaDevolucao, LocalDate dataEfetivaDevolucao) {
        super(numero, utente, documentos, dataInicio);
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.dataEfetivaDevolucao = dataEfetivaDevolucao;
    }

    public boolean estaAtrasado(int numeroDias) {
        if (dataPrevistaDevolucao != null) {
            return dataPrevistaDevolucao.plusDays(numeroDias).isBefore(dataEfetivaDevolucao);
        } else {
            return dataPrevistaDevolucao.plusDays(numeroDias).isBefore(LocalDate.now());
        }
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }
    public void setDataPrevistaDevolucao(LocalDate dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }
    public LocalDate getDataEfetivaDevolucao() {
        return dataEfetivaDevolucao;
    }
    public void setDataEfetivaDevolucao(LocalDate dataEfetivaDevolucao) {
        this.dataEfetivaDevolucao = dataEfetivaDevolucao;
    }

}
