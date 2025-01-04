package menus.revistas;

import menus.Menu;
import modelos.Biblioteca;
import modelos.Revista;

public class MenuListarRevistas extends Menu {
    public MenuListarRevistas(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        if (this.biblioteca.getRevistas().isEmpty()) {
            System.out.println("Não existem revistas registadas.");
        }else{
            System.out.println("--- Lista de Revistas ---");
            for (Revista revista : this.biblioteca.getRevistas()) {
                System.out.println(revista);
            }
        }
    }
}
