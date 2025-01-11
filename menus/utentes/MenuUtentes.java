package menus.utentes;

import menus.Menu;
import modelos.Biblioteca;

public class MenuUtentes extends Menu {
    public MenuUtentes(Biblioteca biblioteca, String name) {
        super(biblioteca,name);
        this.menus.add(new MenuListarUtentes(biblioteca, "Listar Utentes"));
        this.menus.add(new MenuListarUtentesAtivos(biblioteca, "Listar Utentes Ativos"));
        this.menus.add(new MenuAdicionarUtente(biblioteca, "Adicionar Utente"));
        this.menus.add(new MenuProcurarUtentePorNif(biblioteca, "Procurar Utente por NIF"));
        this.menus.add(new MenuListarTransacoesPorUtentePorDatas(biblioteca, "Procurar Transações por Utente e data"));
        this.menus.add(new MenuEditarUtente(biblioteca, "Editar Utente"));
        this.menus.add(new MenuRemoverUtente(biblioteca, "Remover Utente"));
    }
}
