package menus.reservas;

import menus.Menu;
import menus.livros.MenuListarLivros;
import modelos.Biblioteca;

import java.util.Scanner;

public class MenuRemoverReserva extends Menu {
    public MenuRemoverReserva(Biblioteca biblioteca, String nome) {
        super(biblioteca, nome);
    }

    @Override
    public void mostrarMenu() {
        Menu menuReservas = new MenuListarReservas(biblioteca, "Lista de Reservas");
        menuReservas.mostrarMenu();
        if (this.biblioteca.getReservas().isEmpty()) {
            System.out.println("Não existem reservas no sistema");
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Número da Reserva a remover: ");
            String numeroReserva = scanner.nextLine();
            boolean reservaFoiRemovida = this.biblioteca.removerReservaPorNumero(numeroReserva);
            if (reservaFoiRemovida) {
                System.out.println("Reserva removida com sucesso.");
            } else {
                System.out.println("Reserva não encontrada.");
            }
        }
    }
}
