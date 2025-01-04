package menus.livros;

import menus.Menu;
import modelos.Biblioteca;
import modelos.Livro;

public class MenuListarLivros extends Menu {

    public MenuListarLivros(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        if (!this.biblioteca.temLivros()) {
            System.out.println("Não existem livros no sistema");
        } else {
            for (Livro livro : this.biblioteca.getLivros()) {
                System.out.println(livro);
            }
        }
    }
}
