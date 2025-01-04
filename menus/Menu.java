package menus;

import modelos.Biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Menu {
    protected Biblioteca biblioteca;
    protected String name;
    protected List<Menu> menus;

    protected Menu(Biblioteca biblioteca, String name) {
        this.biblioteca = biblioteca;
        this.name = name;
        this.menus = new ArrayList<>();
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            this.desenhaMenu();
            opcao = scanner.nextInt();
            System.out.println();
            if (opcao > 0 && opcao <= this.menus.size()) {
                int indexOpcao = opcao - 1;
                menus.get(indexOpcao).mostrarMenu();
            } else if (opcao != 0) {
                System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);
    }

    public String getName() {
        return name;
    }

    private void desenhaMenu() {
        System.out.println("---" + name + "---");
        for (int index = 0; index < this.menus.size(); index++) {
            int indexOpcao = index + 1;
            Menu menu = this.menus.get(index);
            System.out.println(indexOpcao + ". " + menu.getName());
        }
        System.out.println("0. Sair");
        System.out.print("Selecione uma opção:");

    }
}
