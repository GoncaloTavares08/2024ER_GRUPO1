package menus.emprestimos;

import menus.Menu;
import modelos.Biblioteca;

public class MenuListarEmprestimos extends Menu {
    public MenuListarEmprestimos(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        System.out.println(this.name);
    }
}
