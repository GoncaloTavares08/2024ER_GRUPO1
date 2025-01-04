package menus.livros;

import menus.Menu;
import menus.utentes.MenuProcurarUtentePorNif;
import modelos.Biblioteca;

public class MenuLivros extends Menu {
    public MenuLivros(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
        this.menus.add(new MenuListarLivros(biblioteca, "Listar livros"));
        this.menus.add(new MenuAdicionarLivro(biblioteca, "Adicionar livro"));
        this.menus.add(new MenuProcurarLivroPorIsbn(biblioteca, "Procurar livro por ISBN"));
        this.menus.add(new MenuEditarLivro(biblioteca, "Editar livro"));
        this.menus.add(new MenuRemoverLivro(biblioteca, "Remover livro"));
    }

}
