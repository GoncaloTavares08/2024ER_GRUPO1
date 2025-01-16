package menus;

import modelos.Biblioteca;
import modelos.Documento;
import modelos.Livro;
import utilitarios.Leitores;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Classe responsável pela gestão de livros na biblioteca.
 * Permite adicionar, editar, mostrar, remover e procurar livros pelo seu ISBN.
 * @author [Grupo1]
 * @version 1.0
 */
public class MenuLivros {
    private Biblioteca biblioteca;
    /**
     * Construtor da classe MenuLivros.
     *
     * @param biblioteca A instância da biblioteca onde os livros serão geridos.
     */
    public MenuLivros(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }
    /**
     * Metodo principal para gerir livros.
     * Apresenta um menu com opções para o utilizador escolher.
     */
    protected void gerirLivros() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n--- Menu Gestão Livros ---");
            System.out.println("1. Adicionar Livros");
            System.out.println("2. Editar Livros");
            System.out.println("3. Mostrar Livros");
            System.out.println("4. Remover Livros");
            System.out.println("5. Procurar Livro por ISBN");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = Leitores.lerNumeroInteiro(scanner);
            System.out.println("");
            switch (opcao) {
                case 1 -> adicionarLivros();
                case 2 -> editarLivros();
                case 3 -> mostrarLivros();
                case 4 -> removerLivros();
                case 5 -> procurarLivroPorISBN();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
    /**
     * Metodo para adicionar um novo livro à biblioteca.
     * Solicita ao utilizador os detalhes do livro e o adiciona à lista de livros.
     */
    private void adicionarLivros() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Título: ");
        String titulo = Leitores.lerStringNaoVazia(scanner);
        System.out.print("Editora: ");
        String editora = Leitores.lerStringNaoVazia(scanner);
        System.out.print("Categoria: ");
        String categoria = Leitores.lerStringNaoVazia(scanner);
        System.out.print("Ano de Edição: ");
        int anoEdicao = Leitores.lerNumeroInteiro(scanner);
        System.out.print("ISBN: ");
        String ISBN = Leitores.lerISBN(scanner);
        System.out.print("Número de Autores: ");
        int numAutores = Leitores.lerNumeroInteiro(scanner);
        ArrayList<String> autores = new ArrayList<>();
        for (int i = 0; i < numAutores; i++) {
            System.out.print("Nome do " + (i + 1) + " Autor: ");
            String autor = Leitores.lerStringNaoVazia(scanner);
            autores.add(autor);
        }
        Livro livro = new Livro(titulo, editora, categoria, anoEdicao, ISBN, autores);
        this.biblioteca.getLivros().add(livro);

    }
    /**
     * Metodo para editar os detalhes de um livro existente.
     * Solicita ao utilizador o título do livro a editar e atualiza os seus dados.
     */
    private void editarLivros() {
        mostrarLivros();
        if (!this.biblioteca.getLivros().isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Título do Livro a editar: ");
            String titulo = Leitores.lerStringNaoVazia(scanner);
            Livro livroEditado = null;
            for (Livro livro : this.biblioteca.getLivros()) {
                if (livro.getTitulo().equals(titulo)) {
                    livroEditado = livro;
                    break;
                }
            }
            if (livroEditado != null) {
                System.out.println("\n--- Editar Livro ---");
                System.out.print("Novo Título: ");
                String novoTitulo = Leitores.lerStringNaoVazia(scanner);
                livroEditado.setTitulo(novoTitulo);
                System.out.print("Nova Editora: ");
                String novoEditora = Leitores.lerStringNaoVazia(scanner);
                livroEditado.setEditora(novoEditora);
                System.out.print("Nova Categoria: ");
                String novaCategoria = Leitores.lerStringNaoVazia(scanner);
                livroEditado.setCategoria(novaCategoria);
                System.out.print("Novo Ano de Edição: ");
                int novoAnoEdicao = Leitores.lerNumeroInteiro(scanner);
                livroEditado.setAnoEdicao(novoAnoEdicao);
                System.out.print("Novo ISBN: ");
                String novoISBN = Leitores.lerISBN(scanner);
                livroEditado.setISBN(novoISBN);
                System.out.print("Novo Número de Autores: ");
                int novoNumAutores = Leitores.lerNumeroInteiro(scanner);
                ArrayList<String> autores = new ArrayList<>();
                livroEditado.getAutores().clear();
                for (int i = 0; i < novoNumAutores; i++) {
                    System.out.print("Nome do " + (i + 1) + "º Autor: ");
                    String novoAutor = Leitores.lerStringNaoVazia(scanner);
                    autores.add(novoAutor);
                }
                livroEditado.setAutores(autores);
                System.out.println("Livro editado com sucesso.");

            }
        }
    }
    /**
     * Metodo para mostrar todos os livros registados na biblioteca.
     * Exibe uma lista de livros ou uma mensagem se não houver livros.
     */
    private void mostrarLivros() {
        if (this.biblioteca.getLivros().isEmpty()) {
            System.out.println("Não existem livros registados.");
        } else {
            System.out.println("\n--- Lista de Livros ---");
            for (Livro livro : this.biblioteca.getLivros()) {
                System.out.println(livro);
            }
        }
    }
    /**
     * Metodo para remover um livro da biblioteca.
     * Solicita ao utilizador o ISBN do livro a remover e elimina-o se nao estiver reservado ou emprestado.
     */
    private void removerLivros() {
        mostrarLivros();
        if (!this.biblioteca.getLivros().isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("ISBN do Livro a remover: ");
            String isbn = Leitores.lerStringNaoVazia(scanner);
            Documento documentoRemovido = null;

            for (Livro livro : this.biblioteca.getLivros()) {
                if (livro.getISBN().equals(isbn)) {
                    documentoRemovido = livro;
                    break;
                }
            }
            if (documentoRemovido != null) {
                if (this.biblioteca.documentoEstaAtivo(documentoRemovido)) {
                    System.out.println("Não é possível remover o livro. Ele está associado a uma reserva ou empréstimo.");
                } else {
                    this.biblioteca.getLivros().remove(documentoRemovido);
                    System.out.println("Livro removido com sucesso.");
                }
            } else {
                System.out.println("Livro não encontrado.");
            }
        }
    }
    /**
     * Metodo para procurar um livro pelo seu ISBN.
     * Solicita ao utilizador o ISBN e exibe os detalhes do livro se o mesmo for encontrado.
     */
    private void procurarLivroPorISBN() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduza o ISBN do livro:");
        String isbn = Leitores.lerStringNaoVazia(scanner);
        Livro livro = this.biblioteca.getLivroPorIsbn(isbn);
        if (livro != null) {
            System.out.println(livro);
        } else {
            System.out.println("Livro não encontrado.");
        }
    }
}
