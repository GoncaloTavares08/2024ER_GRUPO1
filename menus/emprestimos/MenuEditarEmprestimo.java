package menus.emprestimos;

import menus.Menu;
import modelos.Biblioteca;

public class MenuEditarEmprestimo extends Menu {
    public MenuEditarEmprestimo(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        System.out.println(this.name);
    }
}
