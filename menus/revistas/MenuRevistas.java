package menus.revistas;

import menus.Menu;
import modelos.Biblioteca;

public class MenuRevistas extends Menu {
    public MenuRevistas(Biblioteca biblioteca, String name) {
        super(biblioteca,name);
        this.menus.add(new MenuListarRevistas(biblioteca, "Listar Revistas"));
        this.menus.add(new MenuAdicionarRevista(biblioteca, "Adicionar Revista"));
        this.menus.add(new MenuAdicionarRevista(biblioteca, "Editar Revista"));
        this.menus.add(new MenuAdicionarRevista(biblioteca, "Remover Revista"));
    }

}
