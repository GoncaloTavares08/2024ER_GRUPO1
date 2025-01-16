package menus;

import modelos.Biblioteca;
import utilitarios.Leitores;

import java.util.Scanner;
/**
 * Classe que representa o menu de gestão da biblioteca.
 * Permite ao utilizador gerir diferentes aspectos da biblioteca, como livros, jornais, revistas, utentes, reservas, empréstimos e estatísticas.
 * @author [João Teixeira]
 * @version 1.0
 */
public class MenuBiblioteca {
    private Biblioteca biblioteca;
    /**
     * Construtor da classe MenuBiblioteca.
     *
     * @param biblioteca A instância da biblioteca que será gerida pelo menu.
     */
    public MenuBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }
    /**
     * Metodo que inicia o menu de gestão geral da biblioteca.
     * Apresenta as opções disponíveis ao utilizador e executa a ação correspondente à opção escolhida.
     * O menu continua a ser apresentado até que o utilizador escolha a opção de sair.
     */
    public void gerirGeral() {
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
            opcao = Leitores.lerNumeroInteiro(scanner);
            System.out.println("");
            switch (opcao) {
                case 1 -> new MenuLivros(biblioteca).gerirLivros();
                case 2 -> new MenuJornais(biblioteca).gerirJornais();
                case 3 -> new MenuRevistas(biblioteca).gerirRevistas();
                case 4 -> new MenuUtentes(biblioteca).gerirUtentes();
                case 5 -> new MenuReservas(biblioteca).gerirReservas();
                case 6 -> new MenuEmprestimos(biblioteca).gerirEmprestimos();
                case 7 -> new MenuEstatisticas(biblioteca).gerirEstatisticas();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
}