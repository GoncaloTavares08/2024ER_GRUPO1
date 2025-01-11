package menus.utentes;

import menus.Menu;
import modelos.Biblioteca;
import modelos.Emprestimo;

import java.util.List;
import java.util.Scanner;

public class ListarUtentesDevolucaoAtrasada extends Menu {
    public ListarUtentesDevolucaoAtrasada(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quantos dias a devolução está atrasada?");
        int diasAtrasados = scanner.nextInt();
        scanner.nextLine();
        List<Emprestimo> emprestimos = this.biblioteca.getEmprestimos();
        for (Emprestimo emprestimo : emprestimos) {
           if (emprestimo.estaAtrasado(diasAtrasados)) {
               System.out.println(emprestimo.getUtente());
            }
        }
    }
}

