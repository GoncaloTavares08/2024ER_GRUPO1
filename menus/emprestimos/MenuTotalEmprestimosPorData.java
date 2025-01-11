package menus.emprestimos;

import menus.Menu;
import modelos.Biblioteca;
import modelos.Emprestimo;
import utilitarios.Leitores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuTotalEmprestimosPorData extends Menu {
    public MenuTotalEmprestimosPorData(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduza a data de início:");
        LocalDate dataInicio = Leitores.lerData(scanner);
        System.out.print("Introduza a data de fim:");
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
