package menus.revistas;

import menus.Menu;
import modelos.Biblioteca;

public class MenuAdicionarRevista extends Menu {
    public MenuAdicionarRevista(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        System.out.println(this.name);
    }
}
