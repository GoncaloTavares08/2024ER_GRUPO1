package menus.reservas;

import menus.Menu;
import modelos.Biblioteca;
import modelos.Documento;
import modelos.Reserva;
import utilitarios.Leitores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuEditarReserva extends Menu {
    public MenuEditarReserva(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        Menu menuReservas = new MenuListarReservas(biblioteca, "Lista de Reservas");
        menuReservas.mostrarMenu();
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
                        System.out.print("Título do " + (i + 1) + "º documento:");
                        String titulo = scanner.nextLine();
                        Documento documento = this.biblioteca.getDocumentoPorTitulo(titulo);
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
}
