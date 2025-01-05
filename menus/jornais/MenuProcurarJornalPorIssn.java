package menus.jornais;

import menus.Menu;
import modelos.Biblioteca;
import modelos.Jornal;

import java.util.Scanner;

public class MenuProcurarJornalPorIssn extends Menu {


    public MenuProcurarJornalPorIssn(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduza o ISSN do jornal:");
        String issn = scanner.nextLine();
        Jornal jornal = this.biblioteca.getJornalPorISSN(issn);
        if (jornal != null) {
            System.out.println(jornal);
        } else {
            System.out.println("ISSN inválido");
        }
    }
}