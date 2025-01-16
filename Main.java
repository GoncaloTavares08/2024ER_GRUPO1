import menus.MenuBiblioteca;
import menus.MenuEscolhaBibliotecas;
import modelos.Biblioteca;
import utilitarios.Memoria;
/**
 * Classe principal que inicia a aplicação de gestão de bibliotecas.
 * Esta classe é responsável por escolher ou criar uma biblioteca,
 * transformar reservas em empréstimos, gerir a biblioteca e guardar os dados.
 */
public class Main {
    /**
     * Metodo principal que é o ponto de entrada da aplicação.
     *
     * Este metodo realiza as seguintes operações:
     * 1. Escolhe ou cria uma nova biblioteca através do menu de escolha.
     * 2. Transforma as reservas existentes em empréstimos.
     * 3. Cria um menu para gerir a biblioteca.
     * 4. Guarda os dados da biblioteca na memória.
     *
     * @param args Argumentos de linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        Biblioteca biblioteca = MenuEscolhaBibliotecas.escolherOuCriarBiblioteca();
        biblioteca.transformarReservasParaEmprestimos();
        MenuBiblioteca menuBiblioteca = new MenuBiblioteca(biblioteca);
        menuBiblioteca.gerirGeral();
        Memoria.guardaDados(biblioteca);
    }
}
