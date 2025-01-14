package menus;

import modelos.Biblioteca;

import java.util.Scanner;

public class MenuBiblioteca extends Menu {

    public MenuBiblioteca(Biblioteca biblioteca,String name) {
        super(biblioteca, name);
        gerirGeral();
    }

    public void gerirGeral(){
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n--- Menu Gestão ---");
            System.out.println("1. Livros");
            System.out.println("2. Jornais");
            System.out.println("3. Revistas");
            System.out.println("4. Utentes");
            System.out.println("5. Reservas");
            System.out.println("6. Empréstimos");
            System.out.println("7. Estatísticas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            System.out.println("");
            switch (opcao) {
                case 1 -> new MenuLivros(biblioteca, "Livros").gerirLivros();
                case 2 -> new MenuJornais(biblioteca, "Jornais").gerirJornais();
                case 3 -> new MenuRevistas(biblioteca, "Revistas").gerirRevistas();
                case 4 -> new MenuUtentes(biblioteca, "Utentes").gerirUtentes();
                case 5 -> new MenuReservas(biblioteca, "Reservas").gerirReservas();
                case 6 -> new MenuEmprestimos(biblioteca, "Empréstimos").gerirEmprestimos();
                case 7 -> new MenuEstatisticas(biblioteca, "Estatísticas").gerirEstatisticas();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

    }

}
