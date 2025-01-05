package menus.emprestimos;

import menus.Menu;
import modelos.Biblioteca;

public class MenuRemoverEmprestimo extends Menu {
    public MenuRemoverEmprestimo(Biblioteca biblioteca, String nome) {
        super(biblioteca, nome);
    }

    @Override
    public void mostrarMenu() {
        System.out.println(this.nome);
    }
}
