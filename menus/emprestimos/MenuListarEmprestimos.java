package menus.emprestimos;

import menus.Menu;
import modelos.Biblioteca;
import modelos.Emprestimo;

public class MenuListarEmprestimos extends Menu {
    public MenuListarEmprestimos(Biblioteca biblioteca, String nome) {
        super(biblioteca, nome);
    }

    @Override
    public void mostrarMenu() {
        if (this.biblioteca.getEmprestimos().isEmpty()) {
            System.out.println("Não existem empréstimos registados.");
        }else{
            System.out.println("\n--- Lista de Empréstimos ---");
            for (Emprestimo emprestimo : this.biblioteca.getEmprestimos()) {
                System.out.println(emprestimo);
            }
        }
    }
}
