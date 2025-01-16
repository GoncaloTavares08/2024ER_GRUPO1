package menus;

import modelos.Biblioteca;
import modelos.Emprestimo;
import modelos.Reserva;
import modelos.Utente;
import utilitarios.Leitores;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
/**
 * Classe responsável pela gestão de utentes na biblioteca.
 * @author [Grupo1]
 * @version 1.0
 */
public class MenuUtentes {
    private Biblioteca biblioteca;
    /**
     * Construtor da classe MenuUtentes.
     *
     * @param biblioteca A instância da biblioteca onde os utentes serão geridos.
     */
    public MenuUtentes(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }
    /**
     * Metodo que apresenta o menu de gestão de utentes e processa as opções escolhidas pelo utilizador.
     */
    protected void gerirUtentes() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n--- Menu Gestão Utentes ---");
            System.out.println("1. Adicionar Utentes");
            System.out.println("2. Editar Utentes");
            System.out.println("3. Mostrar Utentes");
            System.out.println("4. Mostrar Utentes Ativos");
            System.out.println("5. Mostrar Utentes com Devolução Atrasada");
            System.out.println("6. Remover Utentes");
            System.out.println("7. Procurar Utente por NIF");
            System.out.println("8. Procurar Transações por Utente e Data");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = Leitores.lerNumeroInteiro(scanner);
            System.out.println("");
            switch (opcao) {
                case 1 -> adicionarUtentes();
                case 2 -> editarUtentes();
                case 3 -> mostrarUtentes();
                case 4 -> listarUtentesAtivos();
                case 5 -> listarUtentesDevolucaoAtrasada();
                case 6 -> removerUtentes();
                case 7 -> procurarUtentePorNIF();
                case 8 -> listarTransacoesPorUtentePorDatas();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
    /**
     * Metodo que permite adicionar um novo utente à biblioteca.
     */
    private void adicionarUtentes() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("NIF: ");
        String nif = Leitores.lerStringNaoVazia(scanner);
        System.out.print("Nome: ");
        String nome = Leitores.lerStringNaoVazia(scanner);
        System.out.print("Género (M/F): ");
        char genero = Leitores.lerGenero(scanner);
        System.out.print("Contacto: ");
        String contacto = Leitores.lerStringNaoVazia(scanner);

        Utente utente = new Utente(nif, nome, genero, contacto);
        this.biblioteca.getUtentes().add(utente);
    }
    /**
     * Metodo que permite editar os dados de um utente existente.
     */
    private void editarUtentes() {
        mostrarUtentes();
        if (!this.biblioteca.getUtentes().isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("NIF do Utente a editar: ");
            String nif = Leitores.lerStringNaoVazia(scanner);
            Utente utenteEditado = null;
            for (Utente utente : this.biblioteca.getUtentes()) {
                if (utente.getNif().equals(nif)) {
                    utenteEditado = utente;
                    break;
                }
            }
            if (utenteEditado != null) {
                System.out.println("\n--- Editar Utente ---");
                System.out.print("Novo NIF: ");
                String novoNif = Leitores.lerStringNaoVazia(scanner);
                utenteEditado.setNif(novoNif);
                System.out.print("Novo Nome: ");
                String novoNome = Leitores.lerStringNaoVazia(scanner);
                utenteEditado.setNome(novoNome);
                System.out.print("Novo Género (M/F): ");
                char novoGenero = Leitores.lerGenero(scanner);
                utenteEditado.setGenero(novoGenero);
                System.out.print("Novo Contacto: ");
                String novoContacto = Leitores.lerStringNaoVazia(scanner);
                utenteEditado.setContacto(novoContacto);

                System.out.println("Utente editado com sucesso");
            }
        }

    }
    /**
     * Metodo que mostra todos os utentes registados na biblioteca, se não exister nenhum, informa o utilizador.
     */
    public void mostrarUtentes() {
        if (this.biblioteca.getUtentes().isEmpty()) {
            System.out.println("Não existem utentes registados.");
        } else {
            System.out.println("\n--- Lista de Utentes ---");
            for (Utente utente : this.biblioteca.getUtentes()) {
                System.out.println(utente);
            }
        }
    }
    /**
     * Metodo que permite remover um utente da biblioteca.
     */
    private void removerUtentes() {
        mostrarUtentes();
        if (!this.biblioteca.getUtentes().isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("NIF do Utente a remover: ");
            String nif = Leitores.lerStringNaoVazia(scanner);

            Utente utenteRemovido = null;
            for (Utente utente : this.biblioteca.getUtentes()) {
                if (utente.getNif().equals(nif)) {
                    utenteRemovido = utente;
                    break;
                }
            }

            // Verifica se o utente está em reservas ou empréstimos
            List<Utente> utentesAtivos = this.biblioteca.getUtentesAtivos();
            if (utenteRemovido != null) {
                if (utentesAtivos.contains(utenteRemovido)) {
                    System.out.println("Não é possível remover o utente, o mesmo possui reservas ou empréstimos.");
                } else {
                    this.biblioteca.getUtentes().remove(utenteRemovido);
                    System.out.println("Utente removido com sucesso.");
                }
            } else {
                System.out.println("Utente não encontrado.");
            }
        }
    }
    /**
     * Metodo que lista todos os utentes que tenham reservas ou emprestimos no momento, na biblioteca.
     */
    private void listarUtentesAtivos() {
        if (this.biblioteca.getUtentesAtivos().isEmpty()) {
            System.out.println("Não existem utentes ativos.");
        } else {
            System.out.println("\n--- Lista de Utentes ---");
            for (Utente utente : this.biblioteca.getUtentesAtivos()) {
                System.out.println(utente);
            }
        }
    }
    /**
     * Metodo que lista os utentes com devoluções atrasadas superior à quantidade de dias inserida pelo utilizador.
     */
    private void listarUtentesDevolucaoAtrasada() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quantos dias a devolução está atrasada?");
        int diasAtrasados = Leitores.lerNumeroInteiro(scanner);
        scanner.nextLine();
        List<Emprestimo> emprestimos = this.biblioteca.getEmprestimos();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.estaAtrasado(diasAtrasados)) {
                System.out.println(emprestimo.getUtente());
            }
        }
    }
    /**
     * Metodo que procura um utente pelo seu NIF.
     */
    private void procurarUtentePorNIF() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduza o NIF do utente");
        String nif = Leitores.lerStringNaoVazia(scanner);
        Utente utente = this.biblioteca.getUtentePorNif(nif);
        if (utente != null) {
            System.out.println(utente);
        } else {
            System.out.println("NIF inválido");
        }
    }
    /**
     * Metodo que lista as transações (reservas e/ou emprestimos) de um utente entre duas datas inseridas pelo utilizador.
     */
    private void listarTransacoesPorUtentePorDatas() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduza o NIF do utente:");
        String nif = Leitores.lerStringNaoVazia(scanner);
        System.out.print("Introduza a data de início:");
        LocalDate dataInicio = Leitores.lerData(scanner);
        System.out.print("Introduza a data de fim:");
        LocalDate dataFim = Leitores.lerData(scanner);

        List<Emprestimo> emprestimos = this.biblioteca.getEmprestimos();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getUtente().getNif().equals(nif) && emprestimo.getDataInicio().isAfter(dataInicio) && emprestimo.getDataInicio().isBefore(dataFim)) {
                System.out.println("Empréstimo: " + emprestimo);
            }
        }
        List<Reserva> reservas = this.biblioteca.getReservas();
        for (Reserva reserva : reservas) {
            if (reserva.getUtente().getNif().equals(nif) && reserva.getDataInicio().isAfter(dataInicio) && reserva.getDataInicio().isBefore(dataFim)) {
                System.out.println("Reserva: " + reserva);
            }

        }
    }
}
