import java.util.Scanner;

public class SistemaGestaoBiblioteca {
    public static void main(String[] args) {

    }

    private static void menu(){
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n--- Sistema de Gestão de Biblioteca ---");
            System.out.println("1. Gerir");
            System.out.println("2. Pesquisar");
            System.out.println("3. Mostrar");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1 -> gerirGeral();
                case 2 -> pesquisarGeral();
                case 3 -> mostrarGeral();
                case 0 -> {
                    //guardarDados();
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }


    private static void gerirGeral(){
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n--- Menu Gestão ---");
            System.out.println("1. Gerir Livros");
            System.out.println("2. Gerir Jornais");
            System.out.println("3. Gerir Utentes");
            System.out.println("4. Gerir Reservas");
            System.out.println("5. Gerir Empréstimos");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            switch (opcao) {
                /*case 1 -> gerirGeral();
                case 2 -> pesquisarGeral();
                case 3 -> mostrarGeral();
                case 4 -> pesquisarGeral();
                case 5 -> mostrarGeral();
                case 0 -> {
                    menu();
                }*/
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

    }

    private static void pesquisarGeral() {
    }

    private static void mostrarGeral() {
    }






}
