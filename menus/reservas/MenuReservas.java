package menus.reservas;

import menus.Menu;
import modelos.Biblioteca;

public class MenuReservas extends Menu {
    public MenuReservas(Biblioteca biblioteca, String name) {
        super(biblioteca,name);
        this.menus.add(new MenuListarReservas(biblioteca, "Listar Reservas"));
        this.menus.add(new MenuAdicionarReserva(biblioteca, "Adicionar Reserva"));
        this.menus.add(new MenuAdicionarReserva(biblioteca, "Editar Reserva"));
        this.menus.add(new MenuAdicionarReserva(biblioteca, "Remover Reserva"));
    }

}
