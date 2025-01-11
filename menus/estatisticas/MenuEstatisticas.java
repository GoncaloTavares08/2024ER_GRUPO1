package menus.estatisticas;

import menus.Menu;
import modelos.Biblioteca;

public class MenuEstatisticas extends Menu {
    public MenuEstatisticas(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
        this.menus.add(new MenuItemMaisRequisitado(biblioteca, "Item mais requisitado por data"));
    }
}
