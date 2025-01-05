package menus.emprestimos;

import menus.Menu;
import modelos.Biblioteca;
import modelos.Documento;
import modelos.Emprestimo;
import modelos.Utente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class MenuAdicionarEmprestimo extends Menu {
    public MenuAdicionarEmprestimo(Biblioteca biblioteca, String nome) {
        super(biblioteca, nome);
    }

    @Override
    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("NIF do Utente: ");
        String nif = scanner.nextLine();
        Utente utente = this.biblioteca.getUtentePorNif(nif);
        if (utente == null) {
            System.out.println("Utente não existe.");
            return;
        }
        System.out.print("Quantos documentos tem o Empréstimo? ");
        int quantidadeDocumentos = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        List<Documento> documentosParaEmprestimo = new ArrayList<>();
        for (int i = 0; i < quantidadeDocumentos; i++) {
            System.out.print("Título do " + (i + 1) + "º documento:");
            String titulo = scanner.nextLine();
            Documento documento = this.biblioteca.getDocumentoPorTitulo(titulo);
            if (documento == null) {
                System.out.println("Operação cancelada.");
                return;
            }
            documentosParaEmprestimo.add(documento);
        }
        System.out.print("Data de Início (dd-MM-yyyy): ");
        String dataInicioString = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataInicio = LocalDate.parse(dataInicioString, formatter);

        System.out.print("Data Prevista de Devolução (dd-MM-yyyy): ");
        String dataPrevistaDevolucaoString = scanner.nextLine();
        LocalDate dataPrevistaDevolucao = LocalDate.parse(dataPrevistaDevolucaoString, formatter);

        Emprestimo emprestimo = new Emprestimo(UUID.randomUUID().toString(), utente, documentosParaEmprestimo, dataInicio, dataPrevistaDevolucao);
        this.biblioteca.getEmprestimos().add(emprestimo);

        System.out.println("Empréstimo adicionado com sucesso!");
    }
}
