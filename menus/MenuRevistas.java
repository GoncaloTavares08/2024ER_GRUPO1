package menus;

import modelos.Biblioteca;
import modelos.Revista;
import utilitarios.Leitores;

import java.time.LocalDate;
import java.util.Scanner;

public class MenuRevistas {
    private Biblioteca biblioteca;
    public MenuRevistas(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
        gerirRevistas();
    }

    protected void gerirRevistas() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n--- Menu Gestão Revistas ---");
            System.out.println("1. Adicionar Revistas");
            System.out.println("2. Editar Revistas");
            System.out.println("3. Mostrar Revistas");
            System.out.println("4. Remover Revistas");
            System.out.println(("5. Procurar Revista por ISSN"));
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            System.out.println("");
            switch (opcao) {
                case 1 -> adicionarRevistas();
                case 2 -> editarRevistas();
                case 3 -> mostrarRevistas();
                case 4 -> removerRevistas();
                case 5 -> procurarRevistaPorISSN();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
    private void adicionarRevistas() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Editora: ");
        String editora = scanner.nextLine();
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        System.out.print("ISSN: ");
        String ISSN = Leitores.lerISSN(scanner);
        System.out.print("Data de publicação (dd-MM-yyyy): ");
        LocalDate dataPublicacao = Leitores.lerData(scanner);

        Revista revista = new Revista(titulo, editora, categoria, ISSN, dataPublicacao);
        this.biblioteca.getRevistas().add(revista);
    }
    private void editarRevistas() {
        mostrarRevistas();
        if (!this.biblioteca.getRevistas().isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Título da Revista a editar: ");
            String titulo = scanner.nextLine();
            Revista revistaEditada = null;
            for (Revista revista : this.biblioteca.getRevistas()) {
                if (revista.getTitulo().equals(titulo)) {
                    revistaEditada = revista;
                    break;
                }
            }
            if (revistaEditada != null) {
                System.out.println("\n--- Editar Revista ---");
                System.out.print("Novo Título: ");
                String novoTitulo = scanner.nextLine();
                if (!novoTitulo.isEmpty()) {
                    revistaEditada.setTitulo(novoTitulo);
                } else {
                    System.out.println("Título não pode estar vazio.");
                }
                System.out.print("Nova Editora: ");
                String novaEditora = scanner.nextLine();
                if (!novaEditora.isEmpty()) {
                    revistaEditada.setEditora(novaEditora);
                } else {
                    System.out.println("Editora não pode estar vazia.");
                }
                System.out.print("Nova Categoria: ");
                String novaCategoria = scanner.nextLine();
                if (!novaCategoria.isEmpty()) {
                    revistaEditada.setCategoria(novaCategoria);
                } else {
                    System.out.println("Categoria não pode estar vazia.");
                }
                System.out.print("Novo ISSN: ");
                String novoISSN = Leitores.lerISSN(scanner);
                revistaEditada.setISSN(novoISSN);

                System.out.print("Nova data de publicação (dd-MM-yyyy): ");
                LocalDate novaDataPublicacao = Leitores.lerData(scanner);
                revistaEditada.setDataPublicacao(novaDataPublicacao);
                System.out.println("Revista editada com sucesso");
            } else {
                System.out.println("Revista não encontrada.");
            }
        }

    }
    private void mostrarRevistas() {
        if (this.biblioteca.getRevistas().isEmpty()) {
            System.out.println("Não existem revistas registadas.");
        }else{
            System.out.println("\n--- Lista de Revistas ---");
            for (Revista revista : this.biblioteca.getRevistas()) {
                System.out.println(revista);
            }
        }
    }
    private void removerRevistas() {
        mostrarRevistas();
        if (!this.biblioteca.getRevistas().isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("ISSN do Revista a remover: ");
            String issn = scanner.nextLine();
            boolean revistaFoiRemovido = this.biblioteca.removerRevistaPorIssn(issn);
            if (revistaFoiRemovido) {
                System.out.println("Revista removida com sucesso.");
            } else {
                System.out.println("Revista não pode ser removida. Pode não existir ou estar associada a uma reserva ou empréstimo.");
            }
        }
    }

    private void procurarRevistaPorISSN(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduza o ISSN da revista:");
        String issn = scanner.nextLine();
        Revista revista = this.biblioteca.getRevistaPorISSN(issn);
        if (revista != null) {
            System.out.println(revista);
        } else {
            System.out.println("ISSN inválido");
        }
    }

}
