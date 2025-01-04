package menus;

import menus.emprestimos.MenuEmprestimos;
import menus.jornais.MenuJornais;
import menus.livros.MenuLivros;
import menus.reservas.MenuReservas;
import menus.revistas.MenuRevistas;
import menus.utentes.MenuUtentes;
import modelos.Biblioteca;

public class MenuBiblioteca extends Menu {

    public MenuBiblioteca(Biblioteca biblioteca,String name) {
        super(biblioteca, name);
        menus.add(new MenuLivros(biblioteca,"Livros"));
        menus.add(new MenuJornais(biblioteca,"Jornais"));
        menus.add(new MenuRevistas(biblioteca,"Revistas"));
        menus.add(new MenuUtentes(biblioteca,"Utentes"));
        menus.add(new MenuReservas(biblioteca,"Reservas"));
        menus.add(new MenuEmprestimos(biblioteca,"Empréstimos"));
    }
}
