import menus.MenuBiblioteca;
import menus.MenuEscolhaBibliotecas;
import modelos.Biblioteca;
import utilitarios.Memoria;
/**
 * Classe que inicia a aplicação de gestão de bibliotecas.
 * Esta classe é responsável por criar e instanciar o menu, permitindo entrar no sistema.
 * @author [Grupo1]
 * @version 1.0
 */
public class Main {
    /**
     * Metodo principal que é o ponto de entrada da aplicação.
     *
     * Este metodo realiza as seguintes operações:
     * 1. Cria uma instancia biblioteca através do Menu Escolha Bibliotecas.
     * 2. Transforma as reservas existentes em empréstimos.
     * 3. Cria um menu para gerir a biblioteca.
     * 4. Guarda os dados da biblioteca na memória.
     *
     */
    public static void main(String[] args) {
        Biblioteca biblioteca = MenuEscolhaBibliotecas.escolherOuCriarBiblioteca();
        biblioteca.transformarReservasParaEmprestimos();
        MenuBiblioteca menuBiblioteca = new MenuBiblioteca(biblioteca);
        menuBiblioteca.gerirGeral();
        Memoria.guardaDados(biblioteca);
    }
}
