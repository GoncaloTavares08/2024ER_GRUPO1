package menus.reservas;

import menus.Menu;
import modelos.Biblioteca;

public class MenuEditarReserva extends Menu {
    public MenuEditarReserva(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        System.out.println(this.name);
    }
}
