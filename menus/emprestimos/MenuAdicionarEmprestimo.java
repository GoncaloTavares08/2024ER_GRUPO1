package menus.emprestimos;

import menus.Menu;
import modelos.Biblioteca;
import modelos.Documento;
import modelos.Emprestimo;
import modelos.Utente;
import utilitarios.Leitores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        System.out.print("Data de Início (dd-MM-yyyy): ");
        LocalDate dataInicio = Leitores.lerData(scanner);

        System.out.print("Data Prevista de Devolução (dd-MM-yyyy): ");
        LocalDate dataPrevistaDevolucao = Leitores.lerData(scanner);

        System.out.print("Quantos documentos tem o Empréstimo? ");
        int quantidadeDocumentos = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        List<Documento> documentosParaEmprestimo = new ArrayList<>();
        for (int i = 0; i < quantidadeDocumentos; i++) {
            System.out.print("ID do " + (i + 1) + "º documento:");
            String id = scanner.nextLine();
            Documento documento = this.biblioteca.getDocumentoPorIdentificador(id);
            if (documento == null) {
                System.out.println("Operação cancelada.");
                return;
            }
            if (this.biblioteca.documentoEstaLivreNoPeriodo(documento, dataInicio, dataPrevistaDevolucao)) {
                documentosParaEmprestimo.add(documento);
            } else {
                System.out.println("Documento não está disponivel para a data selecionada.");
                return;
            }
        }

        Emprestimo emprestimo = new Emprestimo(String.valueOf(this.biblioteca.getEmprestimos().size()+1), utente, documentosParaEmprestimo, dataInicio, dataPrevistaDevolucao);
        this.biblioteca.getEmprestimos().add(emprestimo);

        System.out.println("Empréstimo adicionado com sucesso!");
    }
}
