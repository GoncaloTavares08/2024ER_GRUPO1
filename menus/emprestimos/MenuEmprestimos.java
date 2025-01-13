package menus.emprestimos;

import menus.Menu;
import modelos.Biblioteca;

public class MenuEmprestimos extends Menu {
    public MenuEmprestimos(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
        this.menus.add(new MenuListarEmprestimos(biblioteca, "Listar Empréstimos"));
        this.menus.add(new MenuTotalEmprestimosPorData(biblioteca, "Total de Empréstimos por Data"));
        this.menus.add(new MenuMostrarTempoMedioDuracaoEmprestimosPorData(biblioteca, "Média de duração de Empréstimos por Data"));
        this.menus.add(new MenuAdicionarEmprestimo(biblioteca, "Adicionar Empréstimo"));
        this.menus.add(new MenuEditarEmprestimo(biblioteca, "Editar Empréstimo"));
        this.menus.add(new MenuDevolverEmprestimo(biblioteca, "Devolver Empréstimo"));
    }
}
