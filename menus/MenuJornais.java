package menus;

import modelos.Biblioteca;
import modelos.Jornal;
import utilitarios.Leitores;

import java.time.LocalDate;
import java.util.Scanner;
/**
 * Classe responsável pela gestão de jornais na biblioteca.
 * Permite adicionar, editar, mostrar, remover e procurar jornais.
 * @author [João Teixeira]
 * @version 1.0
 */
public class MenuJornais {
    private Biblioteca biblioteca;
    /**
     * Construtor da classe MenuJornais.
     *
     * @param biblioteca A biblioteca onde os jornais serão geridos.
     */
    public MenuJornais(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }
    /**
     * Metodo que inicia o menu de gestão de jornais.
     * Permite ao utilizador escolher entre várias opções de gestão de jornais.
     */
    protected void gerirJornais() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n--- Menu Gestão Jornais ---");
            System.out.println("1. Adicionar Jornais");
            System.out.println("2. Editar Jornais");
            System.out.println("3. Mostrar Jornais");
            System.out.println("4. Remover Jornais");
            System.out.println("5. Procurar Jornal por ISSN");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = Leitores.lerNumeroInteiro(scanner);
            System.out.println("");
            switch (opcao) {
                case 1 -> adicionarJornais();
                case 2 -> editarJornais();
                case 3 -> mostrarJornais();
                case 4 -> removerJornais();
                case 5 -> procurarJornalPorISSN();
                case 0 -> {
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
    /**
     * Metodo que permite adicionar um novo jornal à biblioteca.
     * Solicita ao utilizador os dados do jornal e cria um novo objeto Jornal.
     */
    private void adicionarJornais() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Título: ");
        String titulo = Leitores.lerStringNaoVazia(scanner);
        System.out.print("Editora: ");
        String editora = Leitores.lerStringNaoVazia(scanner);
        System.out.print("Categoria: ");
        String categoria = Leitores.lerStringNaoVazia(scanner);
        System.out.print("ISSN: ");
        String ISSN = Leitores.lerISSN(scanner);
        System.out.print("Data de publicação (dd-MM-yyyy): ");
        LocalDate dataPublicacao = Leitores.lerData(scanner);

        Jornal jornal = new Jornal(titulo, editora, categoria, ISSN, dataPublicacao);
        this.biblioteca.getJornais().add(jornal);
    }
    /**
     * Metodo que permite editar os dados de um jornal existente.
     * Solicita ao utilizador o título do jornal a editar e atualiza os seus dados.
     */
    private void editarJornais() {
        mostrarJornais();
        if (!this.biblioteca.getJornais().isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Título do Jornal a editar: ");
            String titulo = Leitores.lerStringNaoVazia(scanner);
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
                String novoTitulo = Leitores.lerStringNaoVazia(scanner);
                jornalEditado.setTitulo(novoTitulo);
                System.out.print("Nova Editora: ");
                String novaEditora = Leitores.lerStringNaoVazia(scanner);
                jornalEditado.setEditora(novaEditora);
                System.out.print("Nova Categoria: ");
                String novaCategoria = Leitores.lerStringNaoVazia(scanner);
                jornalEditado.setCategoria(novaCategoria);
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
    /**
     * Metodo que mostra todos os jornais registados /**
     * Metodo que mostra todos os jornais registados na biblioteca.
     * Se não houver jornais, informa o utilizador que não existem jornais registados.
     */
    private void mostrarJornais() {
        if (this.biblioteca.getJornais().isEmpty()) {
            System.out.println("Não existem jornais registados.");
        } else {
            System.out.println("\n--- Lista de Jornais ---");
            for (Jornal jornal : this.biblioteca.getJornais()) {
                System.out.println(jornal);
            }
        }
    }
    /**
     * Metodo que permite remover um jornal da biblioteca.
     * Solicita ao utilizador o ISSN do jornal a remover e tenta removê-lo.
     */
    private void removerJornais() {
        mostrarJornais();
        if (!this.biblioteca.getJornais().isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("ISSN do Jornal a remover: ");
            String issn = Leitores.lerISSN(scanner);
            boolean jornalFoiRemovido = this.biblioteca.removerJornalPorIssn(issn);
            if (jornalFoiRemovido) {
                System.out.println("Jornal removido com sucesso.");
            } else {
                System.out.println("Jornal não pode ser removido. Pode não existir ou estar associado a uma reserva ou empréstimo.");
            }
        }
    }
    /**
     * Metodo que procura um jornal pelo seu ISSN.
     * Solicita ao utilizador o ISSN e mostra os detalhes do jornal se encontrado.
     */
    private void procurarJornalPorISSN() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduza o ISSN do jornal: ");
        String issn = Leitores.lerStringNaoVazia(scanner);
        Jornal jornal = this.biblioteca.getJornalPorISSN(issn);
        if (jornal != null) {
            System.out.println(jornal);
        } else {
            System.out.println("ISSN inválido");
        }
    }
}