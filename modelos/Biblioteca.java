package modelos;

import utilitarios.Memoria;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> livros = new ArrayList<>();
    private List<Jornal> jornais = new ArrayList<>();
    private List<Revista> revistas = new ArrayList<>();
    private List<Utente> utentes = new ArrayList<>();
    private List<Reserva> reservas = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public Biblioteca() {
        this.livros = Memoria.carregarLivros();
        this.jornais = Memoria.carregarJornais();
        this.revistas = Memoria.carregarRevistas();
        this.utentes = Memoria.carregarUtentes();
        this.emprestimos = Memoria.carregarEmprestimos(this);
        this.reservas = Memoria.carregarReservas(this);
    }

    public List<Livro> getLivros() {
        return this.livros;
    }

    public boolean temLivros() {
        return !this.livros.isEmpty();
    }

    public void adicionarLivro(Livro livro) {
        this.livros.add(livro);
    }

    public boolean removerLivroPorTitulo(String titulo) {
        Livro livroParaRemover = this.getLivroPorTitulo(titulo);
        if (livroParaRemover == null || this.documentoEstaAtivo(livroParaRemover)) {
            return false;
        }
        return this.livros.remove(livroParaRemover);
    }

    public Livro getLivroPorTitulo(String titulo) {
        for (Livro livro : this.livros) {
            if (livro.getTitulo().equals(titulo)) {
                return livro;
            }
        }
        return null;
    }

    public Livro getLivroPorIsbn(String isbn) {
        for (Livro livro : this.livros) {
            if (livro.getISBN().equals(isbn)) {
                return livro;
            }
        }
        return null;
    }

    public boolean documentoEstaAtivo(Documento documento) {
        return this.getDocumentosAtivos().contains(documento);
    }

    public List<Documento> getDocumentosAtivos() {
        List<Documento> documentosAtivos = new ArrayList<>();
        List<Transacao> transacoes = this.getTransacoes();
        for (Transacao transacao : transacoes) {
            documentosAtivos.addAll(transacao.getDocumentos());
        }
        return documentosAtivos;
    }

    public List<Documento> getDocumentos() {
        List<Documento> documentos = new ArrayList<>(this.livros);
        documentos.addAll(this.jornais);
        documentos.addAll(this.revistas);
        return documentos;
    }

    public Documento getDocumentoPorTitulo(String titulo) {
        for (Documento documento : this.getDocumentos()) {
            if (documento.getTitulo().equalsIgnoreCase(titulo)) {
                return documento;
            }
        }
        return null;
    }

    public boolean utenteEstaAtivo(Utente utente) {
        return this.getUtentesAtivos().contains(utente);
    }

    public List<Utente> getUtentesAtivos() {
        List<Utente> utentesAtivos = new ArrayList<>();
        List<Transacao> transacoes = this.getTransacoes();
        for (Transacao transacao : transacoes) {
            utentesAtivos.add(transacao.getUtente());
        }
        return utentesAtivos;
    }

    public Utente getUtentePorNif(String nif) {
        for (Utente utente : this.utentes) {
            if (utente.getNif().equals(nif)) {
                return utente;
            }
        }
        return null;
    }

    public boolean removerUtentePorNif(String nif) {
        Utente utenteParaRemover = this.getUtentePorNif(nif);
        if (utenteParaRemover == null || this.utenteEstaAtivo(utenteParaRemover)) {
            return false;
        }
        return this.utentes.remove(utenteParaRemover);
    }

    public List<Jornal> getJornais() {
        return this.jornais;
    }

    public Jornal getJornalPorISSN(String issn) {
        for (Jornal jornal : this.jornais) {
            if (jornal.getISSN().equals(issn)) {
                return jornal;
            }
        }
        return null;
    }

    public boolean removerJornalPorIssn(String issn) {
        Jornal jornalParaRemover = this.getJornalPorISSN(issn);
        if (jornalParaRemover == null || this.documentoEstaAtivo(jornalParaRemover)) {
            return false;
        }
        return this.jornais.remove(jornalParaRemover);
    }

    public List<Revista> getRevistas() {
        return this.revistas;
    }

    public Revista getRevistaPorISSN(String issn) {
        for (Revista revista : this.revistas) {
            if (revista.getISSN().equals(issn)) {
                return revista;
            }
        }
        return null;
    }

    public boolean removerRevistaPorIssn(String issn) {
        Revista revistaParaRemover = this.getRevistaPorISSN(issn);
        if (revistaParaRemover == null || this.documentoEstaAtivo(revistaParaRemover)) {
            return false;
        }
        return this.revistas.remove(revistaParaRemover);
    }

    public List<Utente> getUtentes() {
        return this.utentes;
    }

    public List<Reserva> getReservas() {
        return this.reservas;
    }

    public Reserva getReservasPorNumero(String numero) {
        for (Reserva reserva : this.reservas) {
            if (reserva.getNumero().equals(numero)) {
                return reserva;
            }
        }
        return null;
    }

    public List<Emprestimo> getEmprestimos() {
        return this.emprestimos;
    }

    public List<Transacao> getTransacoes() {
        List<Transacao> transacoes = new ArrayList<>(this.emprestimos);
        transacoes.addAll(this.reservas);
        return transacoes;
    }

    public boolean removerReservaPorNumero(String numeroReserva) {
        Reserva reserva = this.getReservasPorNumero(numeroReserva);
        if (reserva != null) {
            return this.reservas.remove(reserva);
        }
        return false;
    }
}
