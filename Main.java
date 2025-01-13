import menus.Menu;
import menus.MenuBiblioteca;
import modelos.Biblioteca;
import utilitarios.Memoria;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca("Biblioteca1");
        Menu menu = new MenuBiblioteca(biblioteca, "Biblioteca1");
        menu.mostrarMenu();
        Memoria.guardaDados(biblioteca);
    }
}
