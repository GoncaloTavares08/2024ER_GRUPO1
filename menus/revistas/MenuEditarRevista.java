package menus.revistas;

import menus.Menu;
import modelos.Biblioteca;

public class MenuEditarRevista extends Menu {
    public MenuEditarRevista(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        System.out.println(this.name);
    }
}
