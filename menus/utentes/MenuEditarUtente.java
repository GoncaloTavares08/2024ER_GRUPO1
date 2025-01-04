package menus.utentes;

import menus.Menu;
import modelos.Biblioteca;

public class MenuEditarUtente extends Menu {
    public MenuEditarUtente(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        System.out.println(this.name);
    }
}
