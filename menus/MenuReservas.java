package menus;

import modelos.Biblioteca;
import modelos.Documento;
import modelos.Reserva;
import modelos.Utente;
import utilitarios.Leitores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuReservas extends Menu {
    public MenuReservas(Biblioteca biblioteca, String name) {
        super(biblioteca,name);
    }

    protected void gerirReservas() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n--- Menu Gestão Reservas ---");
            System.out.println("1. Adicionar Reservas");
            System.out.println("2. Editar Reservas");
            System.out.println("3. Mostrar Reservas");
            System.out.println("4. Remover Reservas");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            System.out.println("");
            switch (opcao) {
                case 1 -> adicionarReservas();
                case 2 -> editarReservas();
                case 3 -> mostrarReservas();
                case 4 -> removerReservas();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
    private void adicionarReservas() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("NIF do Utente:");
        String nif = scanner.nextLine();
        Utente utente = this.biblioteca.getUtentePorNif(nif);
        if (utente == null) {
            System.out.println("Utente não existe.");
            return;
        }
        LocalDate dataRegisto = LocalDate.now();

        System.out.print("Data de Início (dd-MM-yyyy): ");
        LocalDate dataInicio = Leitores.lerData(scanner);

        System.out.print("Data de Fim (dd-MM-yyyy): ");
        LocalDate dataFim = Leitores.lerData(scanner);

        System.out.print("Quantos documentos tem a reserva? ");
        int quantidadeDocumentos = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        List<Documento> documentosParaReserva = new ArrayList<>();
        for (int i = 0; i < quantidadeDocumentos; i++) {
            System.out.print("ID do " + (i + 1) + "º documento:");
            String id = scanner.nextLine();
            Documento documento = this.biblioteca.getDocumentoPorIdentificador(id);
            if (documento == null) {
                System.out.println("Operação cancelada.");
                return;
            }
            if (this.biblioteca.documentoEstaLivreNoPeriodo(documento, dataInicio, dataFim)) {
                documentosParaReserva.add(documento);
            } else {
                System.out.println("Documento não está disponivel para a data selecionada.");
                return;
            }
        }
        Reserva reserva = new Reserva(String.valueOf(this.biblioteca.getReservas().size()+1), utente, documentosParaReserva, dataInicio, dataRegisto, dataFim);
        this.biblioteca.getReservas().add(reserva);

        System.out.println("Reserva adicionada com sucesso!");
    }

    private void editarReservas() {
        mostrarReservas();
        if (!this.biblioteca.getReservas().isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Número da Reserva a editar: ");
            String numero = scanner.nextLine();
            Reserva reservaEditada = null;
            for (Reserva reserva : this.biblioteca.getReservas()) {
                if (reserva.getNumero().equals(numero)) {
                    reservaEditada = reserva;
                    break;
                }
            }
            if (reservaEditada != null) {
                System.out.println("\n--- Editar Reserva ---");
                System.out.print("Novo NIF: ");
                String novoNif = scanner.nextLine();
                if (!novoNif.isEmpty()) {
                    reservaEditada.setUtente(this.biblioteca.getUtentePorNif(novoNif));
                } else {
                    System.out.println("NIF não pode estar vazio.");
                }

                System.out.print("Nova Data de Início (dd-MM-yyyy): ");
                LocalDate novaDataInicio = Leitores.lerData(scanner);
                reservaEditada.setDataInicio(novaDataInicio);

                System.out.print("Nova Data de Fim (dd-MM-yyyy): ");
                LocalDate novaDataFim = Leitores.lerData(scanner);
                reservaEditada.setDataFim(novaDataFim);


                System.out.print("Novo Número de Documentos: ");
                int novoNumDocumentos = scanner.nextInt();
                if (novoNumDocumentos > 0) {
                    List<Documento> documentos = new ArrayList<>();
                    scanner.nextLine(); // consumir o newline do scanner
                    reservaEditada.getDocumentos().clear();
                    for (int i = 0; i < novoNumDocumentos; i++) {
                        System.out.print("ID do " + (i + 1) + "º documento:");
                        String id = scanner.nextLine();
                        Documento documento = this.biblioteca.getDocumentoPorIdentificador(id);
                        if (this.biblioteca.documentoEstaLivreNoPeriodo(documento, novaDataInicio, novaDataFim)) {
                            documentos.add(documento);
                        } else {
                            System.out.println("Documento não está disponivel para a data selecionada.");
                            return;
                        }
                    }
                    reservaEditada.setDocumentos(documentos);
                } else {
                    System.out.println("Número de documentos não pode estar vazio.");
                }


                System.out.println("Reserva editada com sucesso");
            }
        }
    }
    private void mostrarReservas() {
        if (this.biblioteca.getReservas().isEmpty()) {
            System.out.println("Não existem reservas registadas.");
        }else{
            System.out.println("\n--- Lista de Reservas ---");
            for (Reserva reserva : this.biblioteca.getReservas()) {
                System.out.println(reserva);
            }
        }

    }

    private void removerReservas(){
        mostrarReservas();
        if (this.biblioteca.getReservas().isEmpty()) {
            System.out.println("Não existem reservas no sistema");
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Número da Reserva a remover: ");
            String numeroReserva = scanner.nextLine();
            boolean reservaFoiRemovida = this.biblioteca.removerReservaPorNumero(numeroReserva);
            if (reservaFoiRemovida) {
                System.out.println("Reserva removida com sucesso.");
            } else {
                System.out.println("Reserva não encontrada.");
            }
        }
    }

}
