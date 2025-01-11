package menus.jornais;

import menus.Menu;
import modelos.Biblioteca;
import modelos.Jornal;
import utilitarios.Leitores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MenuEditarJornal extends Menu {
    public MenuEditarJornal(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        Menu menuJornais = new MenuListarJornais(biblioteca, "Lista de Jornais");
        menuJornais.mostrarMenu();
        if (!this.biblioteca.getJornais().isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Título do Jornal a editar: ");
            String titulo = scanner.nextLine();
            Jornal jornalEditado = null;
            for (Jornal jornal : this.biblioteca.getJornais()) {
                if (jornal.getTitulo().equals(titulo)) {
                    jornalEditado = jornal;
                    break;
                }
            }
            if (jornalEditado != null) {
                System.out.println("\n--- Editar Jornal ---");
                System.out.print("Novo Título: ");
                String novoTitulo = scanner.nextLine();
                if (!novoTitulo.isEmpty()) {
                    jornalEditado.setTitulo(novoTitulo);
                } else {
                    System.out.println("Título não pode estar vazio.");
                }
                System.out.print("Nova Editora: ");
                String novaEditora = scanner.nextLine();
                if (!novaEditora.isEmpty()) {
                    jornalEditado.setEditora(novaEditora);
                } else {
                    System.out.println("Editora não pode estar vazia.");
                }
                System.out.print("Nova Categoria: ");
                String novaCategoria = scanner.nextLine();
                if (!novaCategoria.isEmpty()) {
                    jornalEditado.setCategoria(novaCategoria);
                } else {
                    System.out.println("Categoria não pode estar vazia.");
                }
                System.out.print("Novo ISSN: ");
                String novoISSN = scanner.nextLine();
                if (!novoISSN.isEmpty()) {
                    jornalEditado.setISSN(novoISSN);
                } else {
                    System.out.println("ISSN não pode estar vazio.");
                }
                System.out.print("Nova data de publicação (dd-MM-yyyy): ");
                LocalDate novaDataPublicacao = Leitores.lerData(scanner);
                jornalEditado.setDataPublicacao(novaDataPublicacao);
                System.out.println("Jornal editado com sucesso");
            } else {
                System.out.println("Jornal não encontrado.");
            }
        }
    }
}
