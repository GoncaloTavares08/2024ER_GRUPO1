package menus.emprestimos;

import menus.Menu;
import modelos.Biblioteca;
import modelos.Emprestimo;
import utilitarios.Leitores;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class MenuMostrarTempoMedioDuracaoEmprestimosPorData extends Menu {

    public MenuMostrarTempoMedioDuracaoEmprestimosPorData(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        int count = 0;
        long somaDias = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduza a data de início:");
        LocalDate dataInicio = Leitores.lerData(scanner);
        System.out.print("Introduza a data de fim:");
        LocalDate dataFim = Leitores.lerData(scanner);
        for (Emprestimo emprestimo : this.biblioteca.getEmprestimos()) {
            if(dataInicio.isBefore(emprestimo.getDataInicio()) && dataFim.isAfter(emprestimo.getDataInicio())){
                count++;
                somaDias += ChronoUnit.DAYS.between(emprestimo.getDataInicio(), emprestimo.getDataPrevistaDevolucao());
            }
        }
        System.out.println("A média em dias dos empréstimos entre as datas indicadas é de " + somaDias/count + " dias.");
    }
}
