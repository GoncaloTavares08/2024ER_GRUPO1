package menus;

import modelos.Biblioteca;
import modelos.Jornal;
import utilitarios.Leitores;

import java.time.LocalDate;
import java.util.Scanner;

public class MenuJornais{
    private Biblioteca biblioteca;
    public MenuJornais(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
        gerirJornais();
    }

    protected void gerirJornais() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n--- Menu Gestão Jornais ---");
            System.out.println("1. Adicionar Jornais");
            System.out.println("2. Editar Jornais");
            System.out.println("3. Mostrar Jornais");
            System.out.println("4. Remover Jornais");
            System.out.println(("5. Procurar Jornal por ISSN"));
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            System.out.println("");
            switch (opcao) {
                case 1 -> adicionarJornais();
                case 2 -> editarJornais();
                case 3 -> mostrarJornais();
                case 4 -> removerJornais();
                case 5 -> procurarJornalPorISSN();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
    private void adicionarJornais() {
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

        Jornal jornal = new Jornal(titulo, editora, categoria, ISSN, dataPublicacao);
        this.biblioteca.getJornais().add(jornal);
    }
    private void editarJornais() {
        mostrarJornais();
        if (!this.biblioteca.getJornais().isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Título do Jornal a editar: ");
            String titulo = scanner.nextLine();
            Jornal jornalEditado = null;
            for (Jornal jornal : this.biblioteca.getJornais()) {
                if (jornal.getTitulo().equals(titulo)) {
                    jornalEditado = jornal;
                    break;
                }
            }
            if (jornalEditado != null) {
                System.out.println("\n--- Editar Jornal ---");
                System.out.print("Novo Título: ");
                String novoTitulo = scanner.nextLine();
                if (!novoTitulo.isEmpty()) {
                    jornalEditado.setTitulo(novoTitulo);
                } else {
                    System.out.println("Título não pode estar vazio.");
                }
                System.out.print("Nova Editora: ");
                String novaEditora = scanner.nextLine();
                if (!novaEditora.isEmpty()) {
                    jornalEditado.setEditora(novaEditora);
                } else {
                    System.out.println("Editora não pode estar vazia.");
                }
                System.out.print("Nova Categoria: ");
                String novaCategoria = scanner.nextLine();
                if (!novaCategoria.isEmpty()) {
                    jornalEditado.setCategoria(novaCategoria);
                } else {
                    System.out.println("Categoria não pode estar vazia.");
                }
                System.out.print("Novo ISSN: ");
                String novoISSN = Leitores.lerISSN(scanner);
                jornalEditado.setISSN(novoISSN);

                System.out.print("Nova data de publicação (dd-MM-yyyy): ");
                LocalDate novaDataPublicacao = Leitores.lerData(scanner);
                jornalEditado.setDataPublicacao(novaDataPublicacao);
                System.out.println("Jornal editado com sucesso");
            } else {
                System.out.println("Jornal não encontrado.");
            }
        }

    }
    private void mostrarJornais() {
        if (this.biblioteca.getJornais().isEmpty()) {
            System.out.println("Não existem jornais registados.");
        }else{
            System.out.println("\n--- Lista de Jornais ---");
            for (Jornal jornal : this.biblioteca.getJornais()) {
                System.out.println(jornal);
            }
        }
    }
    private void removerJornais() {
        mostrarJornais();
        if (!this.biblioteca.getJornais().isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("ISSN do Jornal a remover: ");
            String issn = scanner.nextLine();
            boolean jornalFoiRemovido = this.biblioteca.removerJornalPorIssn(issn);
            if (jornalFoiRemovido) {
                System.out.println("Jornal removido com sucesso.");
            } else {
                System.out.println("Jornal não pode ser removido. Pode não existir ou estar associado a uma reserva ou empréstimo.");
            }
        }
    }

    private void procurarJornalPorISSN(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduza o ISSN do jornal:");
        String issn = scanner.nextLine();
        Jornal jornal = this.biblioteca.getJornalPorISSN(issn);
        if (jornal != null) {
            System.out.println(jornal);
        } else {
            System.out.println("ISSN inválido");
        }
    }
}
