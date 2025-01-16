package modelos;

import utilitarios.Memoria;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * A classe Biblioteca representa uma biblioteca que organiza uma coleção de livros, jornais, revistas,
 * utentes, reservas e empréstimos.
 * @author [Grupo1]
 * @version 1.0
 */
 public class Biblioteca {
    private List<Livro> livros = new ArrayList<>();
    private List<Jornal> jornais = new ArrayList<>();
    private List<Revista> revistas = new ArrayList<>();
    private List<Utente> utentes = new ArrayList<>();
    private List<Reserva> reservas = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();
    private String diretorio;
    /**
     * Cria uma nova instância de Biblioteca.
     * Garante que o diretório e os seus ficheiros são existentes, senão cria-os.
     *
     * @param diretorio Diretório onde os ficheiros da biblioteca estão guardados.
     */
    public Biblioteca(String diretorio) {
        this.diretorio = diretorio;
        Memoria.garantirDiretorioEficheirosExistem(diretorio);
        this.livros = Memoria.carregarLivros(this.diretorio);
        this.jornais = Memoria.carregarJornais(this.diretorio);
        this.revistas = Memoria.carregarRevistas(this.diretorio);
        this.utentes = Memoria.carregarUtentes(this.diretorio);
        this.emprestimos = Memoria.carregarEmprestimos(this);
        this.reservas = Memoria.carregarReservas(this);
    }
    /**
     * Retorna a lista de livros disponíveis na biblioteca.
     *
     * @return Lista de todos os documentos do tipo Livro.
     */
    public List<Livro> getLivros() {
        return this.livros;
    }
    /**
     * Verifica se a biblioteca tem livros.
     *
     * @return True se a biblioteca tiver livros, False caso contrário.
     */
    public boolean temLivros() {
        return !this.livros.isEmpty();
    }
    /**
     * Adiciona um novo livro à biblioteca.
     *
     * @param livro Livro a ser adicionado.
     */
    public void adicionarLivro(Livro livro) {
        this.livros.add(livro);
    }
    /**
     * Remove um livro da biblioteca através do seu título.
     *
     * @param titulo Título do livro a ser removido.
     * @return True se o livro foi removido com sucesso, false caso contrário.
     */
    public boolean removerLivroPorTitulo(String titulo) {
        Livro livroParaRemover = this.getLivroPorTitulo(titulo);
        if (livroParaRemover == null || this.documentoEstaAtivo(livroParaRemover)) {
            return false;
        }
        return this.livros.remove(livroParaRemover);
    }
    /**
     * Obtém um livro da biblioteca pelo seu título.
     *
     * @param titulo Título do livro a ser procurado.
     * @return O livro correspondente ao título, ou null se não encontrado.
     */
    public Livro getLivroPorTitulo(String titulo) {
        for (Livro livro : this.livros) {
            if (livro.getTitulo().equals(titulo)) {
                return livro;
            }
        }
        return null;
    }
    /**
     * Obtém um livro da biblioteca pelo seu ISBN.
     *
     * @param isbn ISBN do livro a ser procurado.
     * @return O livro correspondente ao isbn, ou null se não encontrado.
     */
    public Livro getLivroPorIsbn(String isbn) {
        for (Livro livro : this.livros) {
            if (livro.getISBN().equals(isbn)) {
                return livro;
            }
        }
        return null;
    }
    /**
     * Verifica se um documento está ativo na biblioteca.
     *
     * @param documento Documento a ser verificado.
     * @return True se o documento estiver reservado e/ou emprestado, false caso contrário.
     */
    public boolean documentoEstaAtivo(Documento documento) {
        return this.getDocumentosAtivos().contains(documento);
    }
    /**
     * Devolve a lista de documentos ativos na biblioteca.
     *
     * @return Lista de documentos reservados e/ou emprestados.
     */
    public List<Documento> getDocumentosAtivos() {
        List<Documento> documentosAtivos = new ArrayList<>();
        List<Transacao> transacoes = this.getTransacoes();
        for (Transacao transacao : transacoes) {
            documentosAtivos.addAll(transacao.getDocumentos());
        }
        return documentosAtivos;
    }
    /**
     * Retorna a lista de todos os documentos na biblioteca, incluindo livros, jornais e revistas.
     *
     * @return Lista de todos os documentos existentes na biblioteca.
     */
    public List<Documento> getDocumentos() {
        List<Documento> documentos = new ArrayList<>(this.livros);
        documentos.addAll(this.jornais);
        documentos.addAll(this.revistas);
        return documentos;
    }
    /**
     * Obtém um documento da biblioteca pelo seu identificador.
     *
     * @param id Identificador do documento a ser procurado.
     * @return Documento correspondente ao identificador, ou null se não encontrado.
     */
    public Documento getDocumentoPorIdentificador(String id) {
        for (Documento documento : this.livros) {
            if (documento.getIdentificadorDocumento().equals(id)) {
                return documento;
            }
        }
        for (Documento documento : this.revistas) {
            if (documento.getIdentificadorDocumento().equals(id)) {
                return documento;
            }
        }
        for (Documento documento : this.jornais) {
            if (documento.getIdentificadorDocumento().equals(id)) {
                return documento;
            }
        }
        return null;
    }
    /**
     * Verifica se um utente está ativo na biblioteca.
     *
     * @param utente Utente a ser verificado.
     * @return True se o utente tiver alguma reserva e/ou emprestimo no momento, false caso contrário.
     */
    public boolean utenteEstaAtivo(Utente utente) {
        return this.getUtentesAtivos().contains(utente);
    }
    /**
     * Retorna a lista de utentes ativos na biblioteca.
     *
     * @return Lista de utentes com documentos reservados e/ou emprestados.
     */
    public List<Utente> getUtentesAtivos() {
        List<Utente> utentesAtivos = new ArrayList<>();
        List<Transacao> transacoes = this.getTransacoes();
        for (Transacao transacao : transacoes) {
            utentesAtivos.add(transacao.getUtente());
        }
        return utentesAtivos;
    }
    /**
     * Obtém um utente da biblioteca pelo seu NIF.
     *
     * @param nif NIF do utente a ser procurado.
     * @return Utente correspondente ao NIF, ou null se não encontrado.
     */

    public Utente getUtentePorNif(String nif) {
        for (Utente utente : this.utentes) {
            if (utente.getNif().equals(nif)) {
                return utente;
            }
        }
        return null;
    }
    /**
     * Remove um utente da biblioteca através do seu NIF.
     *
     * @param nif NIF do utente a ser removido.
     * @return True se o utente foi removido com sucesso, false caso contrário.
     */
    public boolean removerUtentePorNif(String nif) {
        Utente utenteParaRemover = this.getUtentePorNif(nif);
        if (utenteParaRemover == null || this.utenteEstaAtivo(utenteParaRemover)) {
            return false;
        }
        return this.utentes.remove(utenteParaRemover);
    }
    /**
     * Retorna a lista de jornais existentes na biblioteca.
     *
     * @return Lista de documentos do tipo Jornal.
     */
    public List<Jornal> getJornais() {
        return this.jornais;
    }
    /**
     * Obtém um jornal da biblioteca pelo seu ISSN.
     *
     * @param issn ISSN do jornal a ser procurado.
     * @return Jornal correspondente ao ISSN, ou null se não encontrado.
     */
    public Jornal getJornalPorISSN(String issn) {
        for (Jornal jornal : this.jornais) {
            if (jornal.getISSN().equals(issn)) {
                return jornal;
            }
        }
        return null;
    }
    /**
     * Remove um jornal da biblioteca através do seu ISSN.
     *
     * @param issn ISSN do jornal a ser removido.
     * @return True se o jornal foi removido com sucesso, false caso contrário.
     */

    public boolean removerJornalPorIssn(String issn) {
        Jornal jornalParaRemover = this.getJornalPorISSN(issn);
        if (jornalParaRemover == null || this.documentoEstaAtivo(jornalParaRemover)) {
            return false;
        }
        return this.jornais.remove(jornalParaRemover);
    }
    /**
     * Retorna a lista de revistas existentes na biblioteca.
     *
     * @return Lista de documentos do tipo Revista.
     */

    public List<Revista> getRevistas() {
        return this.revistas;
    }
    /**
     * Obtém uma revista da biblioteca pelo seu ISSN.
     *
     * @param issn ISSN da revista a ser procurada.
     * @return Revista correspondente ao ISSN, ou null se não encontrada.
     */
    public Revista getRevistaPorISSN(String issn) {
        for (Revista revista : this.revistas) {
            if (revista.getISSN().equals(issn)) {
                return revista;
            }
        }
        return null;
    }
    /**
     * Remove uma revista da biblioteca através do seu ISSN.
     *
     * @param issn ISSN da revista a ser removida.
     * @return True se a revista foi removida com sucesso, false caso contrário.
     */
    public boolean removerRevistaPorIssn(String issn) {
        Revista revistaParaRemover = this.getRevistaPorISSN(issn);
        if (revistaParaRemover == null || this.documentoEstaAtivo(revistaParaRemover)) {
            return false;
        }
        return this.revistas.remove(revistaParaRemover);
    }
    /**
     * Retorna a lista de utentes existentes na biblioteca.
     *
     * @return Lista de utentes.
     */
    public List<Utente> getUtentes() {
        return this.utentes;
    }
    /**
     * Retorna a lista de reservas feitas na biblioteca.
     *
     * @return Lista de reservas.
     */
    public List<Reserva> getReservas() {
        return this.reservas;
    }
    /**
     * Obtém uma reserva da biblioteca pelo seu número.
     *
     * @param numero Número da reserva a ser procurada.
     * @return Reserva correspondente ao número, ou null se não encontrada.
     */
    public Reserva getReservasPorNumero(String numero) {
        for (Reserva reserva : this.reservas) {
            if (reserva.getNumero().equals(numero)) {
                return reserva;
            }
        }
        return null;
    }
    /**
     * Retorna a lista de empréstimos feitos na biblioteca.
     *
     * @return Lista de empréstimos.
     */
    public List<Emprestimo> getEmprestimos() {
        return this.emprestimos;
    }
    /**
     * Retorna a lista de transações (empréstimos e/ou reservas) na biblioteca.
     *
     * @return Lista de transações (empréstimos e/ou reservas).
     */
    public List<Transacao> getTransacoes() {
        List<Transacao> transacoes = new ArrayList<>(this.emprestimos);
        transacoes.addAll(this.reservas);
        return transacoes;
    }
    /**
     * Remove uma reserva da biblioteca pelo seu número.
     *
     * @param numeroReserva Número da reserva a ser removida.
     * @return True se a reserva foi removida com sucesso, false caso contrário.
     */
    public boolean removerReservaPorNumero(String numeroReserva) {
        Reserva reserva = this.getReservasPorNumero(numeroReserva);
        if (reserva != null) {
            return this.reservas.remove(reserva);
        }
        return false;
    }
    /**
     * Transforma reservas em empréstimos, se a data de início da reserva for anterior à data atual.
     */
    public void transformarReservasParaEmprestimos() {
        LocalDate dataAtual = LocalDate.now();
        List<Reserva> reservasParaRemover = new ArrayList<>();
        for (Reserva reserva : this.reservas) {
            if (reserva.getDataInicio().isBefore(dataAtual)) {
                Emprestimo emprestimo = new Emprestimo(
                        String.valueOf(this.emprestimos.size() + 1),
                        reserva.getUtente(),
                        reserva.getDocumentos(),
                        reserva.getDataInicio(),
                        reserva.getDataFim()
                );
                this.emprestimos.add(emprestimo);
                reservasParaRemover.add(reserva);
            }
        }
        this.reservas.removeAll(reservasParaRemover);
    }

    /**
     * Verifica se um documento está disponivel num determinado período de tempo.
     *
     * @param documento Documento a ser verificado.
     * @param dataInicio Data de início do período a verificar.
     * @param dataFim Data de fim do período a verificar.
     * @return True se o documento estiver disponivel no período, false caso contrário.
     */
    public boolean documentoEstaLivreNoPeriodo(Documento documento, LocalDate dataInicio, LocalDate dataFim) {
        List<Transacao> transacoes = this.transacoesNoPeriodo(dataInicio, dataFim);
        for (Transacao transacao : transacoes) {
            if (transacao.getDocumentos().contains(documento)) {
                return false;
            }
        }
        return true;
    }
    /**
     * Retorna uma lista de transações (empréstimos e/ou reservas) que ocorreram num determinado período de tempo.
     *
     * @param dataInicio Data de início do período a verificar.
     * @param dataFim Data de fim do período a verificar.
     * @return Lista de transações que ocorreram no período especificado.
     */
    private List<Transacao> transacoesNoPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        List<Transacao> transacoes = this.getTransacoes();
        List<Transacao> transacoesNoPeriodo = new ArrayList<>();
        for (Transacao transacao : transacoes) {
            if (transacao.estaAtivaEntre(dataInicio, dataFim)) {
                transacoesNoPeriodo.add(transacao);
            }
        }
        return transacoesNoPeriodo;
    }
    /**
     * Retorna o diretório onde os ficheiros da biblioteca estão guardados.
     *
     * @return Diretório da biblioteca.
     */
    public String getDiretorio() {
        return this.diretorio;
    }
}
