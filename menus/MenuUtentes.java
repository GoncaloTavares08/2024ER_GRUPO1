package menus;

import modelos.Biblioteca;
import modelos.Emprestimo;
import modelos.Reserva;
import modelos.Utente;
import utilitarios.Leitores;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuUtentes{
    private Biblioteca biblioteca;
    public MenuUtentes(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
        gerirUtentes();
    }

    protected void gerirUtentes() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n--- Menu Gestão Utentes ---");
            System.out.println("1. Adicionar Utentes");
            System.out.println("2. Editar Utentes");
            System.out.println("3. Mostrar Utentes");
            System.out.println("4. Mostrar Utentes Ativos");
            System.out.println("5. Mostrar com Devolução Atrasada");
            System.out.println("6. Remover Utentes");
            System.out.println("7. Procurar Utente por NIF");
            System.out.println("8. Procurar Transações por Utente e Data");


            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
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
    private void adicionarUtentes() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("NIF: ");
        String nif = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Género (M/F): ");
        char genero = Leitores.lerGenero(scanner);
        System.out.print("Contacto: ");
        String contacto = scanner.nextLine();

        Utente utente = new Utente(nif, nome, genero, contacto);
        this.biblioteca.getUtentes().add(utente);
    }
    private void editarUtentes() {
        mostrarUtentes();
        if (!this.biblioteca.getUtentes().isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("NIF do Utente a editar: ");
            String nif = scanner.nextLine();
            Utente utenteEditado = null;
            for (Utente utente : this.biblioteca.getUtentes()) {
                if (utente.getNif().equals(nif)) {
                    utenteEditado = utente;
                    break;
                }
            }
            if (utenteEditado!= null) {
                System.out.println("\n--- Editar Utente ---");
                System.out.print("Novo NIF: ");
                String novoNif = scanner.nextLine();
                if (!novoNif.isEmpty()) {
                    utenteEditado.setNif(novoNif);
                } else {
                    System.out.println("NIF não pode estar vazio.");
                }
                System.out.print("Novo Nome: ");
                String novoNome = scanner.nextLine();
                if (!novoNome.isEmpty()) {
                    utenteEditado.setNome(novoNome);
                } else {
                    System.out.println("Nome não pode estar vazio.");
                }
                System.out.print("Novo Género (M/F): ");
                char novoGenero = Leitores.lerGenero(scanner);
                utenteEditado.setGenero(novoGenero);

                System.out.print("Novo Contacto: ");
                String novoContacto = scanner.nextLine();
                if (!novoContacto.isEmpty()) {
                    utenteEditado.setContacto(novoContacto);
                } else {
                    System.out.println("Contacto não pode estar vazio.");
                }
                System.out.println("Utente editado com sucesso");
            }
        }

    }
    public void mostrarUtentes() {
        if (this.biblioteca.getUtentes().isEmpty()) {
            System.out.println("Não existem utentes registados.");
        }else{
            System.out.println("\n--- Lista de Utentes ---");
            for (Utente utente : this.biblioteca.getUtentes()) {
                System.out.println(utente);
            }
        }
    }
    private void removerUtentes() {
        mostrarUtentes();
        if (!this.biblioteca.getUtentes().isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("NIF do Utente a remover: ");
            String nif = scanner.nextLine();

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

    private void listarUtentesAtivos(){
        if (this.biblioteca.getUtentesAtivos().isEmpty()) {
            System.out.println("Não existem utentes ativos.");
        }else{
            System.out.println("\n--- Lista de Utentes ---");
            for (Utente utente : this.biblioteca.getUtentesAtivos()) {
                System.out.println(utente);
            }
        }
    }

    private void listarUtentesDevolucaoAtrasada(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quantos dias a devolução está atrasada?");
        int diasAtrasados = scanner.nextInt();
        scanner.nextLine();
        List<Emprestimo> emprestimos = this.biblioteca.getEmprestimos();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.estaAtrasado(diasAtrasados)) {
                System.out.println(emprestimo.getUtente());
            }
        }
    }

    private void procurarUtentePorNIF(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduza o NIF do utente");
        String nif = scanner.nextLine();
        Utente utente = this.biblioteca.getUtentePorNif(nif);
        if (utente != null) {
            System.out.println(utente);
        } else {
            System.out.println("NIF inválido");
        }
    }

    private void listarTransacoesPorUtentePorDatas(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduza o NIF do utente:");
        String nif = scanner.nextLine();
        System.out.print("Introduza a data de início:");
        LocalDate dataInicio = Leitores.lerData(scanner);
        System.out.print("Introduza a data de fim:");
        LocalDate dataFim = Leitores.lerData(scanner);

        List<Emprestimo> emprestimos = this.biblioteca.getEmprestimos();
        for(Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getUtente().getNif().equals(nif) && emprestimo.getDataInicio().isAfter(dataInicio) && emprestimo.getDataInicio().isBefore(dataFim)) {
                System.out.println("Empréstimo: " + emprestimo);
            }
        }
        List<Reserva> reservas = this.biblioteca.getReservas();
        for(Reserva reserva : reservas) {
            if (reserva.getUtente().getNif().equals(nif) && reserva.getDataInicio().isAfter(dataInicio) && reserva.getDataInicio().isBefore(dataFim)) {
                System.out.println("Reserva: " + reserva);
            }

        }
    }
}
