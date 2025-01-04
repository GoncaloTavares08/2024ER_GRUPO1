package menus.livros;

import menus.Menu;
import modelos.Biblioteca;

import java.util.Scanner;

public class MenuRemoverLivro extends Menu {
    protected MenuRemoverLivro(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        if (!this.biblioteca.temLivros()) {
            System.out.println("Não existem livros no sistema");
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Título do Livro a remover: ");
            String titulo = scanner.nextLine();
            boolean livroFoiRemovido = this.biblioteca.removerLivroPorTitulo(titulo);
            if (livroFoiRemovido) {
                System.out.println("Livro removido com sucesso.");
            } else {
                System.out.println("Livro não encontrado.");
            }
        }
    }
}
