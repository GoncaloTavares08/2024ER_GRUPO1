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
        if (livroParaRemover == null) {
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

    public List<Revista> getRevistas() {
        return this.revistas;
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

}
