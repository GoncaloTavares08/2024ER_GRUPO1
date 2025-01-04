import menus.Menu;
import menus.MenuBiblioteca;
import modelos.Biblioteca;

public class Main {
    public static void main(String[] args) {
//        Memoria.carregarDados();
//        SistemaGestaoBiblioteca.transformarReservasParaEmprestimos();
//        SistemaGestaoBiblioteca.menu();
        Biblioteca biblioteca = new Biblioteca();
        Menu menu = new MenuBiblioteca(biblioteca, "Biblioteca1");
        menu.mostrarMenu();
    }
}
