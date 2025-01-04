package menus.utentes;

import menus.Menu;
import modelos.Biblioteca;

public class MenuAdicionarUtente extends Menu {
    public MenuAdicionarUtente(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        System.out.println(this.name);
    }
}
