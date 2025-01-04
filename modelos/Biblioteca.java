package modelos;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> livros = new ArrayList<>();
    private List<Jornal> jornais = new ArrayList<>();
    private List<Revista> revistas = new ArrayList<>();
    private List<Utente> utentes = new ArrayList<>();
    private List<Reserva> reservas = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public List<Livro> getLivros() {
        return this.livros;
    }

    public boolean temLivros(){
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

//    public List<Utente> getUtentesAtivos() {
//        List<Utente> utentesAtivos = new ArrayList<>();
//
//        Utente utenteRemovido = null;
//        for (Utente utente : utentesAtivos) {
//            if (utente.getNif().equals(nif)) {
//                utenteRemovido = utente;
//                break;
//            }
//        }
//        if (utenteRemovido != null) {
//            if (utentesAtivos.contains(utenteRemovido)) {
//                System.out.println("Não é possível remover o utente, o mesmo possui reservas ou empréstimos.");
//            } else {
//                utentes.remove(utenteRemovido);
//                System.out.println("Utente removido com sucesso.");
//            }
//        } else {
//            System.out.println("Utente não encontrado.");
//        }
//    }

    public Utente getUtentePorNif(String nif) {
        for (Utente utente : this.utentes) {
            if (utente.getNif().equals(nif)) {
                return utente;
            }
        }
        return null;
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

    public List<Emprestimo> getEmprestimos() {
        return this.emprestimos;
    }

    public List<Transacao> getTransacoes() {
        List<Transacao> transacoes = new ArrayList<>(this.emprestimos);
        transacoes.addAll(this.reservas);
        return transacoes;
    }

}
