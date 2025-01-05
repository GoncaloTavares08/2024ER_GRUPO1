package menus.jornais;

import menus.Menu;
import modelos.Biblioteca;

import java.util.Scanner;

public class MenuRemoverJornal extends Menu {
    public MenuRemoverJornal(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        Menu menuJornais = new MenuListarJornais(biblioteca, "Lista de Jornais");
        menuJornais.mostrarMenu();
        if (!this.biblioteca.getJornais().isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("ISSN do Jornal a remover: ");
            String issn = scanner.nextLine();
            boolean jornalFoiRemovido = this.biblioteca.removerJornalPorIssn(issn);
            if (jornalFoiRemovido) {
                System.out.println("Jornal removido com sucesso.");
            } else {
                System.out.println("Jornal não pode ser removido. Pode não existir ou estar associado a uma reserva ou empréstimo.");
            }
        }
    }
}
