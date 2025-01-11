package menus.emprestimos;

import menus.Menu;
import modelos.Biblioteca;
import modelos.Documento;
import modelos.Emprestimo;
import utilitarios.Leitores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuEditarEmprestimo extends Menu {
    public MenuEditarEmprestimo(Biblioteca biblioteca, String nome) {
        super(biblioteca, nome);
    }

    @Override
    public void mostrarMenu() {
        Menu menuEmprestimos = new MenuListarEmprestimos(biblioteca, "Lista de Empréstimos");
        menuEmprestimos.mostrarMenu();
        if (!this.biblioteca.getEmprestimos().isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Número do Empréstimo a editar: ");
            String numero = scanner.nextLine();
            Emprestimo emprestimoEditado = null;
            for (Emprestimo emprestimo : this.biblioteca.getEmprestimos()) {
                if (emprestimo.getNumero().equals(numero)) {
                    emprestimoEditado = emprestimo;
                    break;
                }
            }
            if (emprestimoEditado != null) {
                System.out.println("\n--- Editar Empréstimo ---");
                System.out.print("Novo NIF: ");
                String novoNif = scanner.nextLine();
                if (!novoNif.isEmpty()) {
                    emprestimoEditado.setUtente(this.biblioteca.getUtentePorNif(novoNif));
                } else {
                    System.out.println("NIF não pode estar vazio.");
                }

                int novoNumDocumentos = scanner.nextInt();
                if (novoNumDocumentos > 0) {
                    List<Documento> documentos = new ArrayList<>();
                    scanner.nextLine(); // consumir o newline do scanner
                    documentos.clear();
                    for (int i = 0; i < novoNumDocumentos; i++) {
                        System.out.print("Título do " + (i + 1) + "º documento:");
                        String titulo = scanner.nextLine();
                        Documento documento = this.biblioteca.getDocumentoPorTitulo(titulo);
                        documentos.add(documento);
                    }
                    emprestimoEditado.setDocumentos(documentos);
                } else {
                    System.out.println("Número de documentos não pode estar vazio.");
                }
                System.out.print("Nova Data de Início (dd-MM-yyyy): ");
                LocalDate novaDataInicio = Leitores.lerData(scanner);
                emprestimoEditado.setDataInicio(novaDataInicio);

                System.out.print("Nova Data Prevista de Devolução (dd-MM-yyyy): ");
                LocalDate novaDataPrevistaDevolucao = Leitores.lerData(scanner);
                emprestimoEditado.setDataPrevistaDevolucao(novaDataPrevistaDevolucao);

                System.out.print("Nova Data Efetiva de Devolução (dd-MM-yyyy): ");
                LocalDate novaDataEfetivaDevolucao = Leitores.lerData(scanner);
                emprestimoEditado.setDataEfetivaDevolucao(novaDataEfetivaDevolucao);
                System.out.println("Empréstimo editado com sucesso");
            }
        }
    }
}
