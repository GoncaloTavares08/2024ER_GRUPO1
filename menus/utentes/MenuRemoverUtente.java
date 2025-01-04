package menus.utentes;

import menus.Menu;
import modelos.Biblioteca;

public class MenuRemoverUtente extends Menu {
    public MenuRemoverUtente(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        System.out.println(this.name);
    }
}
