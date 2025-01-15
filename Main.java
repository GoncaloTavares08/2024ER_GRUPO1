import menus.MenuBiblioteca;
import menus.MenuEscolhaBibliotecas;
import modelos.Biblioteca;
import utilitarios.Memoria;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = MenuEscolhaBibliotecas.escolherOuCriarBiblioteca();
        biblioteca.transformarReservasParaEmprestimos();
        MenuBiblioteca menuBiblioteca = new MenuBiblioteca(biblioteca);
        menuBiblioteca.gerirGeral();
        Memoria.guardaDados(biblioteca);
    }
}
