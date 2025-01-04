package menus.livros;

import menus.Menu;
import modelos.Biblioteca;
import modelos.Livro;
import modelos.Utente;

import java.util.Scanner;

public class MenuProcurarLivroPorIsbn extends Menu {

    public MenuProcurarLivroPorIsbn(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduza o ISBN do livro:");
        String isbn = scanner.nextLine();
        Livro livro = this.biblioteca.getLivroPorIsbn(isbn);
        if (livro != null) {
            System.out.println(livro);
        } else {
            System.out.println("ISBN inválido");
        }
    }
}
