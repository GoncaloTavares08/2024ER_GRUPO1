package menus;

import modelos.Biblioteca;
import modelos.Documento;
import modelos.Emprestimo;
import modelos.Transacao;
import utilitarios.Leitores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe responsável pela gestão das estatísticas da biblioteca.
 * Permite ao utilizador visualizar informações sobre empréstimos e documentos requisitados.
 * @author [João Teixeira]
 * @version 1.0
 */
public class MenuEstatisticas {
    private Biblioteca biblioteca;
    /**
     * Construtor da classe MenuEstatisticas.
     *
     * @param biblioteca A biblioteca que contém os dados a serem geridos.
     */
    public MenuEstatisticas(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }
    /**
     * Metodo que inicia o menu de gestão de estatísticas.
     * Permite ao utilizador escolher entre várias opções de visualização de dados.
     */
    protected void gerirEstatisticas() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n--- Menu Gestão Emprestimos ---");
            System.out.println("1. Mostrar Documento mais Requisitado num Intervalo de Datas");
            System.out.println("2. Mostrar Tempo Médio de Empréstimos num Intervalo de Datas");
            System.out.println("3. Mostrar Total Empréstimos num Intervalo de Datas");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = Leitores.lerNumeroInteiro(scanner);
            System.out.println("");
            switch (opcao) {
                case 1 -> documentoMaisRequisitado();
                case 2 -> mostrarTempoMedioEmprestimos();
                case 3 -> mostrarTotalEmprestimos();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
    /**
     * Metodo que exibe o documento mais requisitado dentro de um intervalo de datas especificado pelo utilizador.
     * O utilizador deve introduzir uma data de início e uma data de fim.
     */
    private void documentoMaisRequisitado() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduza a data de início:");
        LocalDate dataInicio = Leitores.lerData(scanner);
        System.out.print("Introduza a data de fim:");
        LocalDate dataFim = Leitores.lerData(scanner);

        List<String> idList = new ArrayList<>();
        List<Integer> freqList = new ArrayList<>();

        for (Transacao transacao : this.biblioteca.getTransacoes()) {
            if (dataInicio.isBefore(transacao.getDataInicio()) && dataFim.isAfter(transacao.getDataInicio())) {
                for (Documento documento : transacao.getDocumentos()) {
                    String id = documento.getIdentificadorDocumento();
                    if (idList.contains(id)) {
                        int index = idList.indexOf(id);
                        freqList.set(index, freqList.get(index) + 1);
                    } else {
                        idList.add(id);
                        freqList.add(1);
                    }
                }
            }
        }

        int maxFrequencia = 0;
        List<String> maisRequisitados = new ArrayList<>();
        for (int i = 0; i < idList.size(); i++) {
            if (freqList.get(i) > maxFrequencia) {
                maxFrequencia = freqList.get(i);
                maisRequisitados.clear();
                maisRequisitados.add(idList.get(i));
            } else if (freqList.get(i) == maxFrequencia) {
                maisRequisitados.add(idList.get(i));
            }
        }

        // Apresentar os resultados
        if (!maisRequisitados.isEmpty()) {
            System.out.println("Os itens mais requisitados no intervalo de datas são:");
            for (String id : maisRequisitados) {
                System.out.println("ID: " + id + " com " + maxFrequencia + " requisições.");
            }
        } else {
            System.out.println("Nenhum item foi requisitado no intervalo de datas especificado.");
        }
    }
    /**
     * Metodo que calcula e exibe o tempo médio de empréstimos dentro de um intervalo de datas especificado pelo utilizador.
     * O utilizador deve introduzir uma data de início e uma data de fim.
     */
    private void mostrarTempoMedioEmprestimos() {
        int count = 0;
        long somaDias = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduza a data de início : ");
        LocalDate dataInicio = Leitores.lerData(scanner);
        System.out.print("Introduza a data de fim: ");
        LocalDate dataFim = Leitores.lerData(scanner);
        for (Emprestimo emprestimo : this.biblioteca.getEmprestimos()) {
            if (dataInicio.isBefore(emprestimo.getDataInicio()) && dataFim.isAfter(emprestimo.getDataInicio())) {
                count++;
                somaDias += ChronoUnit.DAYS.between(emprestimo.getDataInicio(), emprestimo.getDataPrevistaDevolucao());
            }
        }
        if (count == 0) {
            System.out.println("Não existem empréstimos nas datas indicadas.");
        } else {
            System.out.println("A média em dias dos empréstimos entre as datas indicadas é de " + somaDias / count + " dias.");
        }
    }
    /**
     * Metodo que calcula e exibe o total de empréstimos realizados dentro de um intervalo de datas especificado pelo utilizador.
     * O utilizador deve introduzir uma data de início e uma data de fim.
     */
    private void mostrarTotalEmprestimos() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduza a data de início: ");
        LocalDate dataInicio = Leitores.lerData(scanner);
        System.out.print("Introduza a data de fim: ");
        LocalDate dataFim = Leitores.lerData(scanner);

        int totalEmprestimos = 0;
        for (Emprestimo emprestimo : this.biblioteca.getEmprestimos()) {
            if (dataInicio.isBefore(emprestimo.getDataInicio()) && dataFim.isAfter(emprestimo.getDataInicio())) {
                totalEmprestimos++;
            }
        }
        System.out.println("Total de empréstimos para o intervalo " + dataInicio.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + " e " + dataFim.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ": " + totalEmprestimos);
    }
}