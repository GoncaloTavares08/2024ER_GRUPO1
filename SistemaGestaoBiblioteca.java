import java.util.ArrayList;
import java.util.Scanner;

public class SistemaGestaoBiblioteca {

    private static ArrayList<Livro> livros = new ArrayList<>();
    private static ArrayList<Jornal> jornais = new ArrayList<>();
    private static ArrayList<Utente> utentes = new ArrayList<>();
    private static ArrayList<Reserva> reservas = new ArrayList<>();
    private static ArrayList<Emprestimo> emprestimos = new ArrayList<>();

    public static void menu(){
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n--- Sistema de Gestão de Biblioteca ---");
            System.out.println("1. Gerir");
            System.out.println("2. Pesquisar");
            System.out.println("3. Mostrar");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1 -> gerirGeral();
                case 2 -> pesquisarGeral();
                case 3 -> mostrarGeral();
                case 0 -> {
                    //guardarDados();
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }


    private static void gerirGeral(){
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n--- Menu Gestão ---");
            System.out.println("1. Gerir Livros");
            System.out.println("2. Gerir Jornais");
            System.out.println("3. Gerir Utentes");
            System.out.println("4. Gerir Reservas");
            System.out.println("5. Gerir Empréstimos");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            System.out.println("");
            switch (opcao) {
                case 1 -> gerirLivros();
                //case 2 -> gerirJornais();
                //case 3 -> gerirUtentes();
                //case 4 -> gerirReservas();
                //case 5 -> gerirEmprestimos();
                case 0 -> {
                    menu();
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

    }

    private static void gerirLivros() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n--- Menu Gestão Livros ---");
            System.out.println("1. Adicionar Livros");
            System.out.println("2. Editar Livros");
            System.out.println("3. Mostrar Livros");
            System.out.println("4. Remover Livros");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            System.out.println("");
            switch (opcao) {
                case 1 -> adicionarLivros();
                case 2 -> editarLivros();
                case 3 -> mostrarLivros();
                case 4 -> removerLivros();
                case 0 -> {
                    menu();
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void adicionarLivros() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Editora: ");
        String editora = scanner.nextLine();
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        System.out.print("Ano de Edição: ");
        String anoEdicao = scanner.nextLine();
        System.out.print("ISBN: ");
        String ISBN = scanner.nextLine();
        System.out.print("Número de Autores: ");
        int numAutores = scanner.nextInt();
        ArrayList<String> autores = new ArrayList<>();
        scanner.nextLine(); // consumir o newline do scanner
        for (int i = 0; i < numAutores; i++) {
            System.out.print("Nome do " + (i + 1) + " Autor: ");
            String autor = scanner.nextLine();
            autores.add(autor);
        }
        Livro livro = new Livro(titulo, editora, categoria, anoEdicao, ISBN, autores);
        livros.add(livro);

    }

    private static void editarLivros() {
        mostrarLivros();
        if (!livros.isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Título do Livro a editar: ");
            String titulo = scanner.nextLine();
            Livro livroEditado = null;
            for (Livro livro : livros) {
                if (livro.getTitulo().equals(titulo)) {
                    livroEditado = livro;
                    break;
                }
            }
            if (livroEditado!= null) {
                System.out.println("\n--- Editar Livro ---");
                System.out.print("Novo Título: ");
                String novoTitulo = scanner.nextLine();
                if (!novoTitulo.isEmpty()) {
                    livroEditado.setTitulo(novoTitulo);
                } else {
                    System.out.println("Título não pode estar vazio.");
                }
                System.out.print("Novo Editora: ");
                String novoEditora = scanner.nextLine();
                if (!novoEditora.isEmpty()) {
                    livroEditado.setEditora(novoEditora);
                } else {
                    System.out.println("Editora não pode estar vazia.");
                }
                System.out.print("Nova Categoria: ");
                String novaCategoria = scanner.nextLine();
                if (!novaCategoria.isEmpty()) {
                    livroEditado.setCategoria(novaCategoria);
                } else {
                    System.out.println("Categoria não pode estar vazia.");
                }
                System.out.print("Novo Ano de Edição: ");
                String novoAnoEdicao = scanner.nextLine();
                if (!novoAnoEdicao.isEmpty()) {
                    livroEditado.setAnoEdicao(novoAnoEdicao);
                } else {
                    System.out.println("Ano de Edição não pode estar vazio.");
                }
                System.out.print("Novo ISBN: ");
                String novoISBN = scanner.nextLine();
                if (!novoISBN.isEmpty()) {
                    livroEditado.setISBN(novoISBN);
                } else {
                    System.out.println("ISBN não pode estar vazio.");
                }
                System.out.print("Novo Número de Autores: ");
                int novoNumAutores = scanner.nextInt();
                if (novoNumAutores > 0) {
                    ArrayList<String> autores = new ArrayList<>();
                    scanner.nextLine(); // consumir o newline do scanner
                    autores.clear();
                    for (int i = 0; i < novoNumAutores; i++) {
                        System.out.print("Nome do " + (i + 1) + "º Autor: ");
                        String novoAutor = scanner.nextLine();
                        autores.add(novoAutor);
                    }
                    livroEditado.setAutores(autores);
                    System.out.println("Livro editado com sucesso.");
                } else {
                    System.out.println("Número de Autores não pode estar vazio.");
                }
            }
        }
    }

    private static void mostrarLivros() {
        if (livros.isEmpty()) {
            System.out.println("Não existem livros registados.");
        }else{
            System.out.println("\n--- Lista de Livros ---");
            for (Livro livro : livros) {
                System.out.println(livro);
            }
        }
    }

    private static void removerLivros() {
        mostrarLivros();
        if (!livros.isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Título do Livro a remover: ");
            String titulo = scanner.nextLine();
            Livro livroRemovido = null;
            for (Livro livro : livros) {
                if (livro.getTitulo().equals(titulo)) {
                    livroRemovido = livro;
                    break;
                }
            }
            if (livroRemovido!= null) {
                livros.remove(livroRemovido);
                System.out.println("Livro removido com sucesso.");
            } else {
                System.out.println("Livro não encontrado.");
                removerLivros();
            }
        }
    }





    private static void pesquisarGeral() {
    }

    private static void mostrarGeral() {
    }






}
