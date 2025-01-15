package menus;

import modelos.Biblioteca;
import modelos.Documento;
import modelos.Transacao;
import utilitarios.Leitores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuEstatisticas{
    private Biblioteca biblioteca;

    public MenuEstatisticas(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    protected void gerirEstatisticas(){
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n--- Menu Gestão Emprestimos ---");
            System.out.println("1. Mostrar Documento mais Requisitado");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            System.out.println("");
            switch (opcao) {
                case 1 -> documentoMaisRequisitado();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

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
}
