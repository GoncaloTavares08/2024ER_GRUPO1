package menus.reservas;

import menus.Menu;
import modelos.Biblioteca;

public class MenuRemoverReserva extends Menu {
    public MenuRemoverReserva(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        System.out.println(this.name);
    }
}
