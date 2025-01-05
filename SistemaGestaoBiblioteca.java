import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class SistemaGestaoBiblioteca {

//    public static void transformarReservasParaEmprestimos() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        LocalDate dataAtual = LocalDate.now();
//
//        LocalDate dataInicioFormatada;
//
//        ArrayList<Reserva> reservasParaRemover = new ArrayList<>();
//        for (Reserva reserva : reservas) {
//            try {
//                dataInicioFormatada = LocalDate.parse(reserva.getDataInicio(), formatter);
//            } catch (DateTimeParseException e) {
//                throw new IllegalArgumentException("A data de início fornecida não está no formato correto 'dd-MM-yyyy'.", e);
//            }
//            if (!dataInicioFormatada.isAfter(dataAtual)) {
//                Emprestimo emprestimo = new Emprestimo(
//                        String.valueOf(emprestimos.size() + 1),
//                        reserva.getUtente(),
//                        reserva.getLivros(),
//                        reserva.getDataInicio(),
//                        reserva.getDataFim()
//                );
//                emprestimos.add(emprestimo);
//                reservasParaRemover.add(reserva);
//            }
//        }
//
//        reservas.removeAll(reservasParaRemover);
//    }
//
//    public static void listarTotalEmprestimosIntervaloDatas(String dataInicial, String dataFinal){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        LocalDate dataInicialInseridaFormatada = LocalDate.parse(dataInicial, formatter);
//        LocalDate dataFinalInseridaFormatada = LocalDate.parse(dataFinal, formatter);
//        ArrayList<Emprestimo> emprestimosIntervaloDatas = new ArrayList<>();
//        for (Emprestimo emprestimo : emprestimos){
//            LocalDate dataInicioFormatada = LocalDate.parse(emprestimo.getDataInicio(), formatter);
//            LocalDate dataFimFormatada = LocalDate.parse(emprestimo.getDataPrevistaDevolucao(), formatter);
//            if(dataInicioFormatada.isAfter(dataInicialInseridaFormatada) && dataFimFormatada.isBefore(dataFinalInseridaFormatada)){
//                emprestimosIntervaloDatas.add(emprestimo);
//            }
//        }
//        System.out.println("Existem " + emprestimosIntervaloDatas.size() + " emprestimos feitos dentro desse intevalo de datas.");
//    }
//
//    public static void listarMediaDiasEmprestimosIntervaloDatas(String dataInicial, String dataFinal){
//        long count = 0;
//        long somaDias = 0;
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        LocalDate dataInicialInseridaFormatada = LocalDate.parse(dataInicial, formatter);
//        LocalDate dataFinalInseridaFormatada = LocalDate.parse(dataFinal, formatter);
//        ArrayList<Emprestimo> emprestimosIntervaloDatas = new ArrayList<>();
//        for (Emprestimo emprestimo : emprestimos){
//            LocalDate dataInicioFormatada = LocalDate.parse(emprestimo.getDataInicio(), formatter);
//            LocalDate dataFimFormatada = LocalDate.parse(emprestimo.getDataPrevistaDevolucao(), formatter);
//            if(dataInicioFormatada.isAfter(dataInicialInseridaFormatada) && dataFimFormatada.isBefore(dataFinalInseridaFormatada)){
//                count++;
//                somaDias += ChronoUnit.DAYS.between(dataInicioFormatada, dataFimFormatada);
//                emprestimosIntervaloDatas.add(emprestimo);
//            }
//        }
//        System.out.println("A media em dias dos emprestimos entre as data indicadas é de " + somaDias/count + " dias.");
//    }
//
//    public static void listarTotalEmprestimosIntervaloDatas(String dataInicial, String dataFinal, String nif){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        LocalDate dataInicialInseridaFormatada = LocalDate.parse(dataInicial, formatter);
//        LocalDate dataFinalInseridaFormatada = LocalDate.parse(dataFinal, formatter);
//        ArrayList<Emprestimo> emprestimosIntervaloDatas = new ArrayList<>();
//        for (Emprestimo emprestimo : emprestimos){
//            if(emprestimo.getUtente().getNif().equals(nif)){
//                LocalDate dataInicioFormatada = LocalDate.parse(emprestimo.getDataInicio(), formatter);
//                LocalDate dataFimFormatada = LocalDate.parse(emprestimo.getDataPrevistaDevolucao(), formatter);
//                if(dataInicioFormatada.isAfter(dataInicialInseridaFormatada) && dataFimFormatada.isBefore(dataFinalInseridaFormatada)){
//                    emprestimosIntervaloDatas.add(emprestimo);
//                }
//            }
//        }
//        System.out.println("Existem " + emprestimosIntervaloDatas.size() + " emprestimos feitos dentro desse intevalo de datas para o utente com o nif " + nif + ".");
//    }


}
