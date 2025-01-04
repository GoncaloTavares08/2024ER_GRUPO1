package menus.jornais;

import menus.Menu;
import modelos.Biblioteca;
import modelos.Jornal;

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
            if (jornalEditado!= null) {
                System.out.println("\n--- Editar Jornal ---");
                System.out.print("Novo Título: ");
                String novoTitulo = scanner.nextLine();
                if (!novoTitulo.isEmpty()) {
                    jornalEditado.setTitulo(novoTitulo);
                } else {
                    System.out.println("Título não pode estar vazio.");
                }
                System.out.print("Nova Editora: ");
                String novoEditora = scanner.nextLine();
                if (!novoEditora.isEmpty()) {
                    jornalEditado.setEditora(novoEditora);
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

                try {
                    String novaDataPublicacaoString = scanner.nextLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate novaDataPublicacao = LocalDate.parse(novaDataPublicacaoString, formatter);
                    jornalEditado.setDataPublicacao(novaDataPublicacao);
                } catch (Exception e) {
                    System.out.println("Data de publicação inválida.");
                }
                System.out.println("Jornal editado com sucesso");
            } else {
                System.out.println("Jornal não encontrado.");
            }
        }
    }
}
