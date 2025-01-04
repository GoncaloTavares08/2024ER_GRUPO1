package menus.livros;

import menus.Menu;
import modelos.Biblioteca;
import modelos.Livro;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuEditarLivro extends Menu {

    public MenuEditarLivro(Biblioteca biblioteca, String name) {
        super(biblioteca, name);
    }

    @Override
    public void mostrarMenu() {
        if (!this.biblioteca.temLivros()) {
            System.out.println("Não existem livros no sistema");
        } else {
            this.editarLivro();
        }
    }

    private void editarLivro(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Título do Livro a editar: ");
        String titulo = scanner.nextLine();

        Livro livroParaEditar = this.biblioteca.getLivroPorTitulo(titulo);

        if (livroParaEditar != null) {
            System.out.println("\n--- Editar Livro ---");
            System.out.print("Novo Título: ");
            String novoTitulo = scanner.nextLine();
            if (!novoTitulo.isEmpty()) {
                livroParaEditar.setTitulo(novoTitulo);
            } else {
                System.out.println("Título não pode estar vazio.");
            }
            System.out.print("Nova Editora: ");
            String novoEditora = scanner.nextLine();
            if (!novoEditora.isEmpty()) {
                livroParaEditar.setEditora(novoEditora);
            } else {
                System.out.println("Editora não pode estar vazia.");
            }
            System.out.print("Nova Categoria: ");
            String novaCategoria = scanner.nextLine();
            if (!novaCategoria.isEmpty()) {
                livroParaEditar.setCategoria(novaCategoria);
            } else {
                System.out.println("Categoria não pode estar vazia.");
            }
            System.out.print("Novo Ano de Edição: ");
            int novoAnoEdicao = scanner.nextInt();
            scanner.nextLine();
            if (novoAnoEdicao>0) {
                livroParaEditar.setAnoEdicao(novoAnoEdicao);
            } else {
                System.out.println("Ano de Edição não pode estar vazio.");
            }
            System.out.print("Novo ISBN: ");
            String novoISBN = scanner.nextLine();
            if (!novoISBN.isEmpty()) {
                livroParaEditar.setISBN(novoISBN);
            } else {
                System.out.println("ISBN não pode estar vazio.");
            }
            System.out.print("Novo Número de Autores: ");
            int novoNumAutores = scanner.nextInt();
            if (novoNumAutores > 0) {
                List<String> autores = new ArrayList<>();
                scanner.nextLine(); // consumir o newline do scanner
                autores.clear();
                for (int i = 0; i < novoNumAutores; i++) {
                    System.out.print("Nome do " + (i + 1) + "º Autor: ");
                    String novoAutor = scanner.nextLine();
                    autores.add(novoAutor);
                }
                livroParaEditar.setAutores(autores);
                System.out.println("Livro editado com sucesso.");
            } else {
                System.out.println("Número de Autores não pode estar vazio.");
            }
        }
    }
}
