import menus.Menu;
import menus.MenuBiblioteca;
import menus.MenuEscolhaBibliotecas;
import modelos.Biblioteca;
import utilitarios.Memoria;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = MenuEscolhaBibliotecas.escolherOuCriarBiblioteca();
        biblioteca.transformarReservasParaEmprestimos();
        Menu menu = new MenuBiblioteca(biblioteca, biblioteca.getDiretorio());
        menu.mostrarMenu();
        Memoria.guardaDados(biblioteca);
    }
}
