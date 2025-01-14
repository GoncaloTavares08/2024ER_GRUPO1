package menus;
import utilitarios.Memoria;
import modelos.Biblioteca;
import java.util.Scanner;
import java.util.List;

public class MenuEscolhaBibliotecas {
    public static Biblioteca escolherOuCriarBiblioteca() {
        Scanner scanner = new Scanner(System.in);
        List<Biblioteca> bibliotecasExistentes = Memoria.carregarBibliotecas();

        while (true) {
            System.out.println("--- Bibliotecas ---");
            System.out.println("1. Criar nova biblioteca");
            System.out.println("2. Escolher biblioteca existente");
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            if (opcao == 1) {
                System.out.print("Digite o nome da nova biblioteca: ");
                String nomeBiblioteca = scanner.nextLine();
                Memoria.criarBiblioteca(nomeBiblioteca);
                return new Biblioteca(nomeBiblioteca);
            } else if (opcao == 2) {
                if (bibliotecasExistentes.isEmpty()) {
                    System.out.println("Não há bibliotecas existentes. Crie uma nova.");
                    continue;
                }
                System.out.println("Bibliotecas existentes:");
                for (int i = 0; i < bibliotecasExistentes.size(); i++) {
                    System.out.println((i + 1) + ". " + bibliotecasExistentes.get(i).getDiretorio());
                }
                System.out.print("Escolha o número da biblioteca: ");
                int escolha = scanner.nextInt();
                if (escolha > 0 && escolha <= bibliotecasExistentes.size()) {
                    return bibliotecasExistentes.get(escolha - 1);
                } else {
                    System.out.println("Escolha inválida. Tente novamente.");
                }
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}