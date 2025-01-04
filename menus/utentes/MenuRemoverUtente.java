package menus.utentes;

import menus.Menu;
import menus.revistas.MenuListarRevistas;
import modelos.Biblioteca;
import modelos.Utente;

import java.util.Scanner;

public class MenuRemoverUtente extends Menu {
    public MenuRemoverUtente(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

//    @Override
//    public void mostrarMenu() {
//        Menu menuUtentes = new MenuListarUtentes(biblioteca, "Lista de Utentes");
//        menuUtentes.mostrarMenu();
//        if (!this.biblioteca.getUtentes().isEmpty()) {
//            Scanner scanner = new Scanner(System.in);
//            System.out.print("NIF do Utente a remover: ");
//            String nif = scanner.nextLine();
//
//        }
//    }
}
