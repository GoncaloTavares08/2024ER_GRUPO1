package menus.revistas;

import menus.Menu;
import menus.jornais.MenuListarJornais;
import modelos.Biblioteca;
import modelos.Jornal;
import modelos.Revista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MenuEditarRevista extends Menu {
    public MenuEditarRevista(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        Menu menuRevistas = new MenuListarRevistas(biblioteca, "Lista de Revistas");
        menuRevistas.mostrarMenu();
        if (!this.biblioteca.getRevistas().isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Título da Revista a editar: ");
            String titulo = scanner.nextLine();
            Revista revistaEditada = null;
            for (Revista revista : this.biblioteca.getRevistas()) {
                if (revista.getTitulo().equals(titulo)) {
                    revistaEditada = revista;
                    break;
                }
            }
            if (revistaEditada!= null) {
                System.out.println("\n--- Editar Revista ---");
                System.out.print("Novo Título: ");
                String novoTitulo = scanner.nextLine();
                if (!novoTitulo.isEmpty()) {
                    revistaEditada.setTitulo(novoTitulo);
                } else {
                    System.out.println("Título não pode estar vazio.");
                }
                System.out.print("Nova Editora: ");
                String novaEditora = scanner.nextLine();
                if (!novaEditora.isEmpty()) {
                    revistaEditada.setEditora(novaEditora);
                } else {
                    System.out.println("Editora não pode estar vazia.");
                }
                System.out.print("Nova Categoria: ");
                String novaCategoria = scanner.nextLine();
                if (!novaCategoria.isEmpty()) {
                    revistaEditada.setCategoria(novaCategoria);
                } else {
                    System.out.println("Categoria não pode estar vazia.");
                }
                System.out.print("Novo ISSN: ");
                String novoISSN = scanner.nextLine();
                if (!novoISSN.isEmpty()) {
                    revistaEditada.setISSN(novoISSN);
                } else {
                    System.out.println("ISSN não pode estar vazio.");
                }
                System.out.print("Nova data de publicação (dd-MM-yyyy): ");

                try {
                    String novaDataPublicacaoString = scanner.nextLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate novaDataPublicacao = LocalDate.parse(novaDataPublicacaoString, formatter);
                    revistaEditada.setDataPublicacao(novaDataPublicacao);
                } catch (Exception e) {
                    System.out.println("Data de publicação inválida.");
                }
                System.out.println("Revista editada com sucesso");
            } else {
                System.out.println("Revista não encontrada.");
            }
        }
    }
}
