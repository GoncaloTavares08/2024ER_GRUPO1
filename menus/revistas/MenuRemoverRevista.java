package menus.revistas;

import menus.Menu;
import modelos.Biblioteca;

import java.util.Scanner;

public class MenuRemoverRevista extends Menu {
    public MenuRemoverRevista(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        Menu menuRevistas = new MenuListarRevistas(biblioteca, "Lista de Revistas");
        menuRevistas.mostrarMenu();
        if (!this.biblioteca.getRevistas().isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("ISSN da Revista a remover: ");
            String issn = scanner.nextLine();
            boolean revistaFoiRemovida = this.biblioteca.removerRevistaPorIssn(issn);
            if (revistaFoiRemovida) {
                System.out.println("Revista removida com sucesso.");
            } else {
                System.out.println("Revista não pode ser removida. Pode não existir ou estar associada a uma reserva ou empréstimo.");
            }
        }
    }
}
