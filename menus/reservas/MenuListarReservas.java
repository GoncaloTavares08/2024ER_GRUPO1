package menus.reservas;

import menus.Menu;
import modelos.Biblioteca;
import modelos.Reserva;

public class MenuListarReservas extends Menu {
    public MenuListarReservas(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        if (this.biblioteca.getReservas().isEmpty()) {
            System.out.println("Não existem reservas registadas.");
        }else{
            System.out.println("\n--- Lista de Reservas ---");
            for (Reserva reserva : this.biblioteca.getReservas()) {
                System.out.println(reserva);
            }
        }
    }
}
