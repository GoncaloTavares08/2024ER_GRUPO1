package menus.emprestimos;

import menus.Menu;
import modelos.Biblioteca;
import modelos.Emprestimo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MenuDevolverEmprestimo extends Menu {

    protected MenuDevolverEmprestimo(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        if (!this.biblioteca.getEmprestimos().isEmpty()){
            Menu menuEmprestimos = new MenuListarEmprestimos(biblioteca, "Lista de Empréstimos");
            menuEmprestimos.mostrarMenu();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Insira o número do empréstimo:");
            String numero = scanner.nextLine();
            Emprestimo emprestimoDevolvido = null;
            for (Emprestimo emprestimo : this.biblioteca.getEmprestimos()){
                if(emprestimo.getNumero().equals(numero)){
                    emprestimoDevolvido = emprestimo;
                    break;
                }
            }
            if (emprestimoDevolvido != null){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate atual = LocalDate.now();
                String dataAtual = atual.format(formatter);
                LocalDate atualFormatada = LocalDate.parse(dataAtual, formatter);

                emprestimoDevolvido.setDataEfetivaDevolucao(atualFormatada);
                System.out.println("Empréstimo devolvido com sucesso!");
            }
        }
    }
}
