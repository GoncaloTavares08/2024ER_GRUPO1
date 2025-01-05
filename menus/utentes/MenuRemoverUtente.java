package menus.utentes;

import menus.Menu;
import modelos.Biblioteca;

import java.util.Scanner;

public class MenuRemoverUtente extends Menu {
    public MenuRemoverUtente(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        Menu menuUtentes = new MenuListarUtentes(biblioteca, "Lista de Utentes");
        menuUtentes.mostrarMenu();
        if (!this.biblioteca.getUtentes().isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("NIF do Utente a remover: ");
            String nif = scanner.nextLine();
            boolean utenteFoiRemovido = this.biblioteca.removerUtentePorNif(nif);
            if (utenteFoiRemovido) {
                System.out.println("Utente removido com sucesso.");
            } else {
                System.out.println("Utente não pode ser removido. Pode não existir ou estar associado a uma reserva ou empréstimo.");
            }

        }
    }
}
