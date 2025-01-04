package menus.emprestimos;

import menus.Menu;
import modelos.Biblioteca;

public class MenuAdicionarEmprestimo extends Menu {
    public MenuAdicionarEmprestimo(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        System.out.println(this.name);
    }
}
