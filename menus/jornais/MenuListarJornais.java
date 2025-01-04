package menus.jornais;

import menus.Menu;
import modelos.Biblioteca;
import modelos.Jornal;

public class MenuListarJornais extends Menu {
    public MenuListarJornais(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        if (this.biblioteca.getJornais().isEmpty()) {
            System.out.println("Não existem jornais registados.");
        }else{
            System.out.println("\n--- Lista de Jornais ---");
            for (Jornal jornal : this.biblioteca.getJornais()) {
                System.out.println(jornal);
            }
        }
    }
}
