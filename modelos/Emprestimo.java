package modelos;

import javax.print.Doc;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Emprestimo extends Transacao {
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
                "; Data de Inicio: " + getDataInicio() +
                "; Data Prevista Devolução: " + getDataPrevistaDevolucao() +
                "; Data Efetiva Devolução: " + getDataEfetivaDevolucao() +
                ']';
    }

    public String toFileString() {
        ArrayList<String> titulosDocumentos = new ArrayList<>();
        for (Documento documento : this.documentos) {
            titulosDocumentos.add(documento.getTitulo());
        }
        String output = getNumero() +
                "|" + getUtente().getNif() +
                "|" + titulosDocumentos.toString().replace("[", "").replace("]", "") +
                "|" + getDataInicio().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
                "|" + getDataPrevistaDevolucao().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        if (getDataEfetivaDevolucao() != null) {
            output += "|" + getDataEfetivaDevolucao().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } else {
            output += "|Nao Devolvido";
        }
        return output;
    }
}
