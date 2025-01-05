package menus.reservas;

import menus.Menu;
import modelos.Biblioteca;

public class MenuRemoverReserva extends Menu {
    public MenuRemoverReserva(Biblioteca biblioteca, String nome) {
        super(biblioteca, nome);
    }

    @Override
    public void mostrarMenu() {
        System.out.println(this.nome);
    }
}
