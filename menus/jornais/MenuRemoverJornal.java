package menus.jornais;

import menus.Menu;
import modelos.Biblioteca;
import modelos.Jornal;

import java.util.Scanner;

public class MenuRemoverJornal extends Menu {
    public MenuRemoverJornal(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        Menu menuJornais = new MenuListarJornais(biblioteca, "Lista de Jornais");
        menuJornais.mostrarMenu();
        if (!this.biblioteca.getJornais().isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Título do Jornal a remover: ");
            String titulo = scanner.nextLine();
            Jornal jornalRemovido = null;
            for (Jornal jornal : this.biblioteca.getJornais()) {
                if (jornal.getTitulo().equals(titulo)) {
                    jornalRemovido = jornal;
                    break;
                }
            }
            if (jornalRemovido!= null) {
                this.biblioteca.getJornais().remove(jornalRemovido);
                System.out.println("Jornal removido com sucesso.");
            } else {
                System.out.println("Jornal não encontrado.");
            }
        }
    }
}
