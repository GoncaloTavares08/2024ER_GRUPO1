public class Main {
    public static void main(String[] args) {
        Memoria.carregarDados();
        SistemaGestaoBiblioteca.transformarReservasParaEmprestimos();
        SistemaGestaoBiblioteca.menu();
    }
}
