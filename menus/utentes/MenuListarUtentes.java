package menus.utentes;

import menus.Menu;
import modelos.Biblioteca;
import modelos.Utente;

public class MenuListarUtentes extends Menu {
    public MenuListarUtentes(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        if (this.biblioteca.getUtentes().isEmpty()) {
            System.out.println("Não existem utentes registados.");
        }else{
            System.out.println("\n--- Lista de Utentes ---");
            for (Utente utente : this.biblioteca.getUtentes()) {
                System.out.println(utente);
            }
        }
    }
}
