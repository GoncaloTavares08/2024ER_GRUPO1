package menus.jornais;

import menus.Menu;
import modelos.Biblioteca;

public class MenuJornais extends Menu {

    public MenuJornais(Biblioteca biblioteca, String name) {
        super(biblioteca,name);
        this.menus.add(new MenuListarJornais(biblioteca, "Listar Jornal"));
        this.menus.add(new MenuAdicionarJornal(biblioteca, "Adicionar Jornal"));
        this.menus.add(new MenuProcurarJornalPorIssn(biblioteca, "Procurar Jornal por ISSN"));
        this.menus.add(new MenuEditarJornal(biblioteca, "Editar Jornal"));
        this.menus.add(new MenuRemoverJornal(biblioteca, "Remover Jornal"));
    }
}
