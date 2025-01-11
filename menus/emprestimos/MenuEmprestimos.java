package menus.emprestimos;

import menus.Menu;
import modelos.Biblioteca;

public class MenuEmprestimos extends Menu {
    public MenuEmprestimos(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
        this.menus.add(new MenuListarEmprestimos(biblioteca, "Listar Emprestimos"));
        this.menus.add(new MenuAdicionarEmprestimo(biblioteca, "Adicionar Emprestimo"));
        this.menus.add(new MenuEditarEmprestimo(biblioteca, "Editar Emprestimo"));
    }
}
