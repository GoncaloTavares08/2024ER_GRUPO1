package menus.jornais;

import menus.Menu;
import modelos.Biblioteca;
import modelos.Jornal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MenuAdicionarJornal extends Menu {
    public MenuAdicionarJornal(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Editora: ");
        String editora = scanner.nextLine();
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        System.out.print("ISSN: ");
        String ISSN = scanner.nextLine();
        System.out.print("Data de publicação (dd-MM-yyyy): ");
        String dataString = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataPublicacao = LocalDate.parse(dataString, formatter);

        Jornal jornal = new Jornal(titulo, editora, categoria, ISSN, dataPublicacao);
        this.biblioteca.getJornais().add(jornal);
    }
}
