package menus.reservas;

import menus.Menu;
import modelos.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class MenuAdicionarReserva extends Menu {
    public MenuAdicionarReserva(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("NIF do Utente:");
        String nif = scanner.nextLine();
        Utente utente = this.biblioteca.getUtentePorNif(nif);
        if (utente == null) {
            System.out.println("Utente não existe.");
            return;
        }
        System.out.print("Quantos documentos tem a reserva? ");
        int quantidadeDocumentos = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        List<Documento> documentosParaReserva = new ArrayList<>();
        for (int i = 0; i < quantidadeDocumentos; i++) {
            System.out.print("Título do " + (i + 1) + "º documento:");
            String titulo = scanner.nextLine();
            Documento documento = this.biblioteca.getDocumentoPorTitulo(titulo);
            if (documento == null) {
                System.out.println("Operação cancelada.");
                return;
            }
            documentosParaReserva.add(documento);
            //FALTA VER SE O DOCUMENTO ESTÁ OU NÃO DISPONIVEL PARA RESERVA
        }

        System.out.print("Data de Registo (dd-MM-yyyy): ");
        String dataRegistoString = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataRegisto = LocalDate.parse(dataRegistoString, formatter);

        System.out.print("Data de Início (dd-MM-yyyy): ");
        String dataInicioString = scanner.nextLine();
        LocalDate dataInicio = LocalDate.parse(dataInicioString, formatter);

        System.out.print("Data de Fim (dd-MM-yyyy): ");
        String dataFimString = scanner.nextLine();
        LocalDate dataFim = LocalDate.parse(dataFimString, formatter);

        Reserva reserva = new Reserva(UUID.randomUUID().toString(), utente, documentosParaReserva, dataInicio, dataRegisto, dataFim);
        this.biblioteca.getReservas().add(reserva);

        System.out.println("Reserva adicionada com sucesso!");
    }
}
