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
/**
 * Classe responsável pela gestão de reservas na biblioteca.
 * Permite adicionar, editar, mostrar e remover reservas.
 * @author [Grupo1]
 * @version 1.0
 */
public class MenuReservas {
    private Biblioteca biblioteca;
    /**
     * Construtor da classe MenuReservas.
     *
     * @param biblioteca A instância da biblioteca onde as reservas serão geridas.
     */
    public MenuReservas(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }
    /**
     * Metodo principal para gerir reservas.
     * Apresenta um menu com opções para adicionar, editar, mostrar e remover reservas.
     */
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
            opcao = Leitores.lerNumeroInteiro(scanner);
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
    /**
     * Metodo para adicionar uma nova reserva.
     * Solicita ao utilizador o NIF do utente, as datas de início e fim,
     * e os documentos a serem reservados.
     */
    private void adicionarReservas() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("NIF do Utente:");
        String nif = Leitores.lerStringNaoVazia(scanner);
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
        int quantidadeDocumentos = Leitores.lerNumeroInteiro(scanner);
        List<Documento> documentosParaReserva = new ArrayList<>();
        for (int i = 0; i < quantidadeDocumentos; i++) {
            System.out.print("ID do " + (i + 1) + "º documento:");
            String id = Leitores.lerStringNaoVazia(scanner);
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
        Reserva reserva = new Reserva(String.valueOf(this.biblioteca.getReservas().size() + 1), utente, documentosParaReserva, dataInicio, dataRegisto, dataFim);
        this.biblioteca.getReservas().add(reserva);

        System.out.println("Reserva adicionada com sucesso!");
    }
    /**
     * Metodo para editar uma reserva existente.
     * Permite ao utilizador alterar o NIF do utente, as datas e os documentos da reserva.
     */
    private void editarReservas() {
        mostrarReservas();
        if (!this.biblioteca.getReservas().isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Número da Reserva a editar: ");
            String numero = Leitores.lerStringNaoVazia(scanner);
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
                String novoNif = Leitores.lerStringNaoVazia(scanner);
                reservaEditada.setUtente(this.biblioteca.getUtentePorNif(novoNif));

                System.out.print("Nova Data de Início (dd-MM-yyyy): ");
                LocalDate novaDataInicio = Leitores.lerData(scanner);
                reservaEditada.setDataInicio(novaDataInicio);

                System.out.print("Nova Data de Fim (dd-MM-yyyy): ");
                LocalDate novaDataFim = Leitores.lerData(scanner);
                reservaEditada.setDataFim(novaDataFim);


                System.out.print("Novo Número de Documentos: ");
                int novoNumDocumentos = Leitores.lerNumeroInteiro(scanner);
                if (novoNumDocumentos > 0) {
                    List<Documento> documentos = new ArrayList<>();
                    reservaEditada.getDocumentos().clear();
                    for (int i = 0; i < novoNumDocumentos; i++) {
                        System.out.print("ID do " + (i + 1) + "º documento:");
                        String id = Leitores.lerStringNaoVazia(scanner);
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
    /**
     * Metodo para mostrar todas as reservas registadas.
     * Exibe uma lista de reservas ou uma mensagem se não houver reservas.
     */
    private void mostrarReservas() {
        if (this.biblioteca.getReservas().isEmpty()) {
            System.out.println("Não existem reservas registadas.");
        } else {
            System.out.println("\n--- Lista de Reservas ---");
            for (Reserva reserva : this.biblioteca.getReservas()) {
                System.out.println(reserva);
            }
        }

    }
    /**
     * Metodo para remover uma reserva existente.
     * Solicita ao utilizador o número da reserva a ser removida.
     */
    private void removerReservas() {
        mostrarReservas();
        if (this.biblioteca.getReservas().isEmpty()) {
            System.out.println("Não existem reservas no sistema");
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Número da Reserva a remover: ");
            String numeroReserva = Leitores.lerStringNaoVazia(scanner);
            boolean reservaFoiRemovida = this.biblioteca.removerReservaPorNumero(numeroReserva);
            if (reservaFoiRemovida) {
                System.out.println("Reserva removida com sucesso.");
            } else {
                System.out.println("Reserva não encontrada.");
            }
        }
    }

}
