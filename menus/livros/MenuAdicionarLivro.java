package menus.livros;

import menus.Menu;
import modelos.Biblioteca;
import modelos.Livro;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuAdicionarLivro extends Menu {
    public MenuAdicionarLivro(Biblioteca biblioteca, String name) {
        super(biblioteca,name);
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
        System.out.print("Ano de Edição: ");
        int anoEdicao = scanner.nextInt();
        scanner.nextLine();
        System.out.print("ISBN: ");
        String ISBN = scanner.nextLine();
        System.out.print("Número de Autores: ");
        int numAutores = scanner.nextInt();
        List<String> autores = new ArrayList<>();
        scanner.nextLine();
        for (int i = 0; i < numAutores; i++) {
            System.out.print("Nome do " + (i + 1) + " Autor: ");
            String autor = scanner.nextLine();
            autores.add(autor);
        }
        Livro livro = new Livro(titulo, editora, categoria, anoEdicao, ISBN, autores);
        this.biblioteca.adicionarLivro(livro);
    }
}
