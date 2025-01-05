package menus.utentes;

import menus.Menu;
import modelos.Biblioteca;
import modelos.Utente;

public class MenuListarUtentesAtivos extends Menu {

    public MenuListarUtentesAtivos(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        if (this.biblioteca.getUtentesAtivos().isEmpty()) {
            System.out.println("Não existem utentes ativos.");
        }else{
            System.out.println("\n--- Lista de Utentes ---");
            for (Utente utente : this.biblioteca.getUtentesAtivos()) {
                System.out.println(utente);
            }
        }
    }
}

