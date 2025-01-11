package menus.utentes;

import menus.Menu;
import modelos.Biblioteca;
import modelos.Emprestimo;
import modelos.Reserva;
import utilitarios.Leitores;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuListarTransacoesPorUtentePorDatas extends Menu {

    public MenuListarTransacoesPorUtentePorDatas(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduza o NIF do utente:");
        String nif = scanner.nextLine();
        System.out.print("Introduza a data de início:");
        LocalDate dataInicio = Leitores.lerData(scanner);
        System.out.print("Introduza a data de fim:");
        LocalDate dataFim = Leitores.lerData(scanner);

        List<Emprestimo> emprestimos = this.biblioteca.getEmprestimos();
        for(Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getUtente().getNif().equals(nif) && emprestimo.getDataInicio().isAfter(dataInicio) && emprestimo.getDataInicio().isBefore(dataFim)) {
                System.out.println("Empréstimo: " + emprestimo);
            }
        }
        List<Reserva> reservas = this.biblioteca.getReservas();
        for(Reserva reserva : reservas) {
            if (reserva.getUtente().getNif().equals(nif) && reserva.getDataInicio().isAfter(dataInicio) && reserva.getDataInicio().isBefore(dataFim)) {
                System.out.println("Reserva: " + reserva);
            }

        }
    }
}
