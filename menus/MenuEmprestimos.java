package menus;

import modelos.Biblioteca;
import modelos.Documento;
import modelos.Emprestimo;
import modelos.Utente;
import utilitarios.Leitores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuEmprestimos {
    private Biblioteca biblioteca;

    public MenuEmprestimos(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    protected void gerirEmprestimos() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n--- Menu Gestão Emprestimos ---");
            System.out.println("1. Adicionar Emprestimos");
            System.out.println("2. Editar Emprestimos");
            System.out.println("3. Mostrar Emprestimos");
            System.out.println("4. Devolver Emprestimos");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = Leitores.lerNumeroInteiro(scanner);
            System.out.println("");
            switch (opcao) {
                case 1 -> adicionarEmprestimos();
                case 2 -> editarEmprestimos();
                case 3 -> mostrarEmprestimos();
                case 4 -> devolverEmprestimos();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void adicionarEmprestimos() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("NIF do Utente: ");
        String nif = Leitores.lerStringNaoVazia(scanner);
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
        int quantidadeDocumentos = Leitores.lerNumeroInteiro(scanner);
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

        Emprestimo emprestimo = new Emprestimo(String.valueOf(this.biblioteca.getEmprestimos().size() + 1), utente, documentosParaEmprestimo, dataInicio, dataPrevistaDevolucao);
        this.biblioteca.getEmprestimos().add(emprestimo);

        System.out.println("Empréstimo adicionado com sucesso!");
    }

    private void editarEmprestimos() {
        mostrarEmprestimos();
        if (!this.biblioteca.getEmprestimos().isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Número do Empréstimo a editar: ");
            String numero = Leitores.lerStringNaoVazia(scanner);
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
                String novoNif = Leitores.lerStringNaoVazia(scanner);
                emprestimoEditado.setUtente(this.biblioteca.getUtentePorNif(novoNif));
                System.out.print("Nova Data de Início (dd-MM-yyyy): ");
                LocalDate novaDataInicio = Leitores.lerData(scanner);
                emprestimoEditado.setDataInicio(novaDataInicio);

                System.out.print("Nova Data Prevista de Devolução (dd-MM-yyyy): ");
                LocalDate novaDataPrevistaDevolucao = Leitores.lerData(scanner);
                emprestimoEditado.setDataPrevistaDevolucao(novaDataPrevistaDevolucao);

                System.out.print("Nova Data Efetiva de Devolução (dd-MM-yyyy): ");
                LocalDate novaDataEfetivaDevolucao = Leitores.lerData(scanner);
                emprestimoEditado.setDataEfetivaDevolucao(novaDataEfetivaDevolucao);

                int novoNumDocumentos = Leitores.lerNumeroInteiro(scanner);
                if (novoNumDocumentos > 0) {
                    List<Documento> documentos = new ArrayList<>();
                    for (int i = 0; i < novoNumDocumentos; i++) {
                        System.out.print("ID do " + (i + 1) + "º documento:");
                        String id = Leitores.lerStringNaoVazia(scanner);
                        Documento documento = this.biblioteca.getDocumentoPorIdentificador(id);
                        if (this.biblioteca.documentoEstaLivreNoPeriodo(documento, novaDataInicio, novaDataPrevistaDevolucao)) {
                            documentos.add(documento);
                        } else {
                            System.out.println("Documento não está disponivel para a data selecionada.");
                            return;
                        }
                    }
                    emprestimoEditado.setDocumentos(documentos);
                } else {
                    System.out.println("Número de documentos não pode estar vazio.");
                }
                System.out.println("Empréstimo editado com sucesso");
            }
        }
    }

    private void mostrarEmprestimos() {
        if (this.biblioteca.getEmprestimos().isEmpty()) {
            System.out.println("Não existem empréstimos registados.");
        } else {
            System.out.println("\n--- Lista de Empréstimos ---");
            for (Emprestimo emprestimo : this.biblioteca.getEmprestimos()) {
                System.out.println(emprestimo);
            }
        }
    }

    private void devolverEmprestimos() {
        if (!this.biblioteca.getEmprestimos().isEmpty()) {
            mostrarEmprestimos();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Insira o número do empréstimo:");
            String numero = Leitores.lerStringNaoVazia(scanner);
            Emprestimo emprestimoDevolvido = null;
            for (Emprestimo emprestimo : this.biblioteca.getEmprestimos()) {
                if (emprestimo.getNumero().equals(numero)) {
                    emprestimoDevolvido = emprestimo;
                    break;
                }
            }
            if (emprestimoDevolvido != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate atual = LocalDate.now();
                String dataAtual = atual.format(formatter);
                LocalDate atualFormatada = LocalDate.parse(dataAtual, formatter);

                emprestimoDevolvido.setDataEfetivaDevolucao(atualFormatada);
                System.out.println("Empréstimo devolvido com sucesso!");
            }
        }
    }

}
