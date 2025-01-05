package menus.revistas;

import menus.Menu;
import modelos.Biblioteca;
import modelos.Revista;

import java.util.Scanner;

public class MenuProcurarRevistaPorIssn extends Menu {
    public MenuProcurarRevistaPorIssn(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduza o ISSN da revista:");
        String issn = scanner.nextLine();
        Revista revista = this.biblioteca.getRevistaPorISSN(issn);
        if (revista != null) {
            System.out.println(revista);
        } else {
            System.out.println("ISSN inválido");
        }
    }
}
