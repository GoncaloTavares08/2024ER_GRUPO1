package menus.revistas;

import menus.Menu;
import modelos.Biblioteca;

public class MenuRemoverRevista extends Menu {
    public MenuRemoverRevista(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        System.out.println(this.name);
    }
}
