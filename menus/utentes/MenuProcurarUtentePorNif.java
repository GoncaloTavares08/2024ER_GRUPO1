package menus.utentes;

import menus.Menu;
import modelos.Biblioteca;
import modelos.Utente;

import java.util.Scanner;

public class MenuProcurarUtentePorNif extends Menu {

    public MenuProcurarUtentePorNif(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduza o NIF do utente");
        String nif = scanner.nextLine();
        Utente utente = this.biblioteca.getUtentePorNif(nif);
        if (utente != null) {
            System.out.println(utente);
        } else {
            System.out.println("NIF inválido");
        }
    }
}
