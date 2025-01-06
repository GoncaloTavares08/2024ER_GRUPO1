import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class SistemaGestaoBiblioteca {

    public static ArrayList<Livro> livros = new ArrayList<>();
    public static ArrayList<Jornal> jornais = new ArrayList<>();
    public static ArrayList<Revista> revistas = new ArrayList<>();
    public static ArrayList<Utente> utentes = new ArrayList<>();
    public static ArrayList<Reserva> reservas = new ArrayList<>();
    public static ArrayList<Emprestimo> emprestimos = new ArrayList<>();

    public static void menu(){
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n--- Sistema de Gestão de Biblioteca ---");
            System.out.println("1. Gerir");
            System.out.println("2. Pesquisar");
            System.out.println("3. Mostrar");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1 -> gerirGeral();
                case 2 -> pesquisarGeral();
                case 3 -> mostrarGeral();
                case 0 -> {
                    Memoria.guardarDados();
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
        System.exit(0);
    }


    private static void gerirGeral(){
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n--- Menu Gestão ---");
            System.out.println("1. Gerir Livros");
            System.out.println("2. Gerir Jornais");
            System.out.println("3. Gerir Revistas");
            System.out.println("4. Gerir Utentes");
            System.out.println("5. Gerir Reservas");
            System.out.println("6. Gerir Empréstimos");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            System.out.println("");
            switch (opcao) {
                case 1 -> gerirLivros();
                case 2 -> gerirJornais();
                case 3 -> gerirRevistas();
                case 4 -> gerirUtentes();
                case 5 -> gerirReservas();
                case 6 -> gerirEmprestimos();
                case 0 -> {
                    menu();
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

    }

    private static void gerirLivros() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n--- Menu Gestão Livros ---");
            System.out.println("1. Adicionar Livros");
            System.out.println("2. Editar Livros");
            System.out.println("3. Mostrar Livros");
            System.out.println("4. Remover Livros");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            System.out.println("");
            switch (opcao) {
                case 1 -> adicionarLivros();
                case 2 -> editarLivros();
                case 3 -> mostrarLivros();
                case 4 -> removerLivros();
                case 0 -> {
                    menu();
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
    private static void adicionarLivros() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Editora: ");
        String editora = scanner.nextLine();
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        System.out.print("Ano de Edição: ");
        String anoEdicao = scanner.nextLine();
        System.out.print("ISBN: ");
        String ISBN = scanner.nextLine();
        System.out.print("Número de Autores: ");
        int numAutores = scanner.nextInt();
        ArrayList<String> autores = new ArrayList<>();
        scanner.nextLine(); // consumir o newline do scanner
        for (int i = 0; i < numAutores; i++) {
            System.out.print("Nome do " + (i + 1) + " Autor: ");
            String autor = scanner.nextLine();
            autores.add(autor);
        }
        Livro livro = new Livro(titulo, editora, categoria, anoEdicao, ISBN, autores);
        livros.add(livro);

    }
    private static void editarLivros() {
        mostrarLivros();
        if (!livros.isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Título do Livro a editar: ");
            String titulo = scanner.nextLine();
            Livro livroEditado = null;
            for (Livro livro : livros) {
                if (livro.getTitulo().equals(titulo)) {
                    livroEditado = livro;
                    break;
                }
            }
            if (livroEditado!= null) {
                System.out.println("\n--- Editar Livro ---");
                System.out.print("Novo Título: ");
                String novoTitulo = scanner.nextLine();
                if (!novoTitulo.isEmpty()) {
                    livroEditado.setTitulo(novoTitulo);
                } else {
                    System.out.println("Título não pode estar vazio.");
                }
                System.out.print("Nova Editora: ");
                String novoEditora = scanner.nextLine();
                if (!novoEditora.isEmpty()) {
                    livroEditado.setEditora(novoEditora);
                } else {
                    System.out.println("Editora não pode estar vazia.");
                }
                System.out.print("Nova Categoria: ");
                String novaCategoria = scanner.nextLine();
                if (!novaCategoria.isEmpty()) {
                    livroEditado.setCategoria(novaCategoria);
                } else {
                    System.out.println("Categoria não pode estar vazia.");
                }
                System.out.print("Novo Ano de Edição: ");
                String novoAnoEdicao = scanner.nextLine();
                if (!novoAnoEdicao.isEmpty()) {
                    livroEditado.setAnoEdicao(novoAnoEdicao);
                } else {
                    System.out.println("Ano de Edição não pode estar vazio.");
                }
                System.out.print("Novo ISBN: ");
                String novoISBN = scanner.nextLine();
                if (!novoISBN.isEmpty()) {
                    livroEditado.setISBN(novoISBN);
                } else {
                    System.out.println("ISBN não pode estar vazio.");
                }
                System.out.print("Novo Número de Autores: ");
                int novoNumAutores = scanner.nextInt();
                if (novoNumAutores > 0) {
                    ArrayList<String> autores = new ArrayList<>();
                    scanner.nextLine(); // consumir o newline do scanner
                    autores.clear();
                    for (int i = 0; i < novoNumAutores; i++) {
                        System.out.print("Nome do " + (i + 1) + "º Autor: ");
                        String novoAutor = scanner.nextLine();
                        autores.add(novoAutor);
                    }
                    livroEditado.setAutores(autores);
                    System.out.println("Livro editado com sucesso.");
                } else {
                    System.out.println("Número de Autores não pode estar vazio.");
                }
            }
        }
    }
    private static void mostrarLivros() {
        if (livros.isEmpty()) {
            System.out.println("Não existem livros registados.");
        }else{
            System.out.println("\n--- Lista de Livros ---");
            for (Livro livro : livros) {
                System.out.println(livro);
            }
        }
    }
    private static void removerLivros() {
        mostrarLivros();
        if (!livros.isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Título do Livro a remover: ");
            String titulo = scanner.nextLine();
            Livro livroRemovido = null;

            for (Livro livro : livros) {
                if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                    livroRemovido = livro;
                    break;
                }
            }

            // Verifica se o livro está em reservas ou empréstimos
            ArrayList<Livro> livrosAtivos = listarLivrosAtivosLista();
            if (livroRemovido != null) {
                if (livrosAtivos.contains(livroRemovido)) {
                    System.out.println("Não é possível remover o livro. Ele está associado a uma reserva ou empréstimo.");
                } else {
                    livros.remove(livroRemovido);
                    System.out.println("Livro removido com sucesso.");
                }
            } else {
                System.out.println("Livro não encontrado.");
            }
        }
    }


    private static void gerirJornais() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n--- Menu Gestão Jornais ---");
            System.out.println("1. Adicionar Jornais");
            System.out.println("2. Editar Jornais");
            System.out.println("3. Mostrar Jornais");
            System.out.println("4. Remover Jornais");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            System.out.println("");
            switch (opcao) {
                case 1 -> adicionarJornais();
                case 2 -> editarJornais();
                case 3 -> mostrarJornais();
                case 4 -> removerJornais();
                case 0 -> {
                    menu();
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
    private static void adicionarJornais() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Editora: ");
        String editora = scanner.nextLine();
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        System.out.print("ISSN: ");
        String ISSN = scanner.nextLine();
        System.out.print("Data de publicação (dd-MM-yyyy): ");
        String dataPublicacao = scanner.nextLine();

        Jornal jornal = new Jornal(titulo, editora, categoria, ISSN, dataPublicacao);
        jornais.add(jornal);
    }
    private static void editarJornais() {
        mostrarJornais();
        if (!jornais.isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Título do Jornal a editar: ");
            String titulo = scanner.nextLine();
            Jornal jornalEditado = null;
            for (Jornal jornal : jornais) {
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
                String novadataPublicacao = scanner.nextLine();
                if (!novadataPublicacao.isEmpty()) {
                    jornalEditado.setDataPublicacao(novadataPublicacao);
                } else {
                    System.out.println("Data de publicação não pode estar vazia.");
                }
                System.out.println("Jornal editado com sucesso");
            }
        }

    }
    private static void mostrarJornais() {
        if (jornais.isEmpty()) {
            System.out.println("Não existem jornais registados.");
        }else{
            System.out.println("\n--- Lista de Jornais ---");
            for (Jornal jornal : jornais) {
                System.out.println(jornal);
            }
        }
    }
    private static void removerJornais() {
        mostrarJornais();
        if (!jornais.isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Título do Jornal a remover: ");
            String titulo = scanner.nextLine();
            Jornal jornalRemovido = null;
            for (Jornal jornal : jornais) {
                if (jornal.getTitulo().equals(titulo)) {
                    jornalRemovido = jornal;
                    break;
                }
            }
            if (jornalRemovido!= null) {
                jornais.remove(jornalRemovido);
                System.out.println("Jornal removido com sucesso.");
            } else {
                System.out.println("Jornal não encontrado.");
                removerJornais();
            }
        }
    }

    private static void gerirRevistas() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n--- Menu Gestão Revistas ---");
            System.out.println("1. Adicionar Revistas");
            System.out.println("2. Editar Revistas");
            System.out.println("3. Mostrar Revistas");
            System.out.println("4. Remover Revistas");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            System.out.println("");
            switch (opcao) {
                case 1 -> adicionarRevistas();
                case 2 -> editarRevistas();
                case 3 -> mostrarRevistas();
                case 4 -> removerRevistas();
                case 0 -> {
                    menu();
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
    private static void adicionarRevistas() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Editora: ");
        String editora = scanner.nextLine();
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        System.out.print("ISSN: ");
        String ISSN = scanner.nextLine();
        System.out.print("Data de publicação (dd-MM-yyyy): ");
        String dataPublicacao = scanner.nextLine();

        Revista revista = new Revista(titulo, editora, categoria, ISSN, dataPublicacao);
        revistas.add(revista);
    }
    private static void editarRevistas() {
        mostrarRevistas();
        if (!revistas.isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Título da Revista a editar: ");
            String titulo = scanner.nextLine();
            Revista revistaEditado = null;
            for (Revista revista : revistas) {
                if (revista.getTitulo().equals(titulo)) {
                    revistaEditado = revista;
                    break;
                }
            }
            if (revistaEditado!= null) {
                System.out.println("\n--- Editar Revista ---");
                System.out.print("Novo Título: ");
                String novoTitulo = scanner.nextLine();
                if (!novoTitulo.isEmpty()) {
                    revistaEditado.setTitulo(novoTitulo);
                } else {
                    System.out.println("Título não pode estar vazio.");
                }
                System.out.print("Nova Editora: ");
                String novoEditora = scanner.nextLine();
                if (!novoEditora.isEmpty()) {
                    revistaEditado.setEditora(novoEditora);
                } else {
                    System.out.println("Editora não pode estar vazia.");
                }
                System.out.print("Nova Categoria: ");
                String novaCategoria = scanner.nextLine();
                if (!novaCategoria.isEmpty()) {
                    revistaEditado.setCategoria(novaCategoria);
                } else {
                    System.out.println("Categoria não pode estar vazia.");
                }
                System.out.print("Novo ISSN: ");
                String novoISSN = scanner.nextLine();
                if (!novoISSN.isEmpty()) {
                    revistaEditado.setISSN(novoISSN);
                } else {
                    System.out.println("ISSN não pode estar vazio.");
                }
                System.out.print("Nova data de publicação (dd-MM-yyyy): ");
                String novadataPublicacao = scanner.nextLine();
                if (!novadataPublicacao.isEmpty()) {
                    revistaEditado.setDataPublicacao(novadataPublicacao);
                } else {
                    System.out.println("Data de publicação não pode estar vazia.");
                }
                System.out.println("Revista editada com sucesso");
            }
        }

    }
    private static void mostrarRevistas() {
        if (revistas.isEmpty()) {
            System.out.println("Não existem revistas registadas.");
        }else{
            System.out.println("\n--- Lista de Revistas ---");
            for (Revista revista : revistas) {
                System.out.println(revista);
            }
        }
    }
    private static void removerRevistas() {
        mostrarRevistas();
        if (!revistas.isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Título da Revista a remover: ");
            String titulo = scanner.nextLine();
            Revista revistaRemovido = null;
            for (Revista revista : revistas) {
                if (revista.getTitulo().equals(titulo)) {
                    revistaRemovido = revista;
                    break;
                }
            }
            if (revistaRemovido!= null) {
                revistas.remove(revistaRemovido);
                System.out.println("Revista removida com sucesso.");
            } else {
                System.out.println("Revista não encontrada.");
                removerRevistas();
            }
        }
    }

    private static void gerirUtentes() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n--- Menu Gestão Utentes ---");
            System.out.println("1. Adicionar Utentes");
            System.out.println("2. Editar Utentes");
            System.out.println("3. Mostrar Utentes");
            System.out.println("4. Remover Utentes");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            System.out.println("");
            switch (opcao) {
                case 1 -> adicionarUtentes();
                case 2 -> editarUtentes();
                case 3 -> mostrarUtentes();
                case 4 -> removerUtentes();
                case 0 -> {
                    menu();
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
    private static void adicionarUtentes() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("NIF: ");
        String nif = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Género (M/F): ");
        String generoInput = scanner.nextLine().trim().toUpperCase();
        char genero;
        if (generoInput.length() == 1 && (generoInput.charAt(0) == 'M' || generoInput.charAt(0) == 'F')) {
            genero = generoInput.charAt(0);
        } else {
            System.out.println("Erro: O género deve ser apenas 'M' ou 'F'.");
            return;
        }
        System.out.print("Contacto: ");
        String contacto = scanner.nextLine();

        Utente utente = new Utente(nif, nome, genero, contacto);
        utentes.add(utente);
    }
    private static void editarUtentes() {
        mostrarUtentes();
        if (!utentes.isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("NIF do Utente a editar: ");
            String nif = scanner.nextLine();
            Utente utenteEditado = null;
            for (Utente utente : utentes) {
                if (utente.getNif().equals(nif)) {
                    utenteEditado = utente;
                    break;
                }
            }
            if (utenteEditado!= null) {
                System.out.println("\n--- Editar Utente ---");
                System.out.print("Novo NIF: ");
                String novoNif = scanner.nextLine();
                if (!novoNif.isEmpty()) {
                    utenteEditado.setNif(novoNif);
                } else {
                    System.out.println("NIF não pode estar vazio.");
                }
                System.out.print("Novo Nome: ");
                String novoNome = scanner.nextLine();
                if (!novoNome.isEmpty()) {
                    utenteEditado.setNome(novoNome);
                } else {
                    System.out.println("Nome não pode estar vazio.");
                }
                System.out.print("Novo Género (M/F): ");
                String novoGenero = scanner.nextLine();
                if (!novoGenero.isEmpty() && novoGenero.length() == 1) {
                    char genero = novoGenero.charAt(0);
                    if (genero == 'M' || genero == 'F') {
                        utenteEditado.setGenero(genero);
                    } else {
                        System.out.println("Género inválido. Deve ser 'M' ou 'F'.");
                    }
                } else {
                    System.out.println("Género não pode estar vazio.");
                }
                System.out.print("Novo Contacto: ");
                String novoContacto = scanner.nextLine();
                if (!novoContacto.isEmpty()) {
                    utenteEditado.setContacto(novoContacto);
                } else {
                    System.out.println("Contacto não pode estar vazio.");
                }
                System.out.println("Utente editado com sucesso");
            }
        }

    }
    private static void mostrarUtentes() {
        if (utentes.isEmpty()) {
            System.out.println("Não existem utentes registados.");
        }else{
            System.out.println("\n--- Lista de Utentes ---");
            for (Utente utente : utentes) {
                System.out.println(utente);
            }
        }
    }
    private static void removerUtentes() {
        mostrarUtentes();
        if (!utentes.isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("NIF do Utente a remover: ");
            String nif = scanner.nextLine();

            Utente utenteRemovido = null;
            for (Utente utente : utentes) {
                if (utente.getNif().equals(nif)) {
                    utenteRemovido = utente;
                    break;
                }
            }

            // Verifica se o utente está em reservas ou empréstimos
            ArrayList<Utente> utentesAtivos = listarUtentesAtivosLista();
            if (utenteRemovido != null) {
                if (utentesAtivos.contains(utenteRemovido)) {
                    System.out.println("Não é possível remover o utente, o mesmo possui reservas ou empréstimos.");
                } else {
                    utentes.remove(utenteRemovido);
                    System.out.println("Utente removido com sucesso.");
                }
            } else {
                System.out.println("Utente não encontrado.");
            }
        }
    }


    private static void gerirReservas() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n--- Menu Gestão Reservas ---");
            System.out.println("1. Adicionar Reservas");
            System.out.println("2. Editar Reservas");
            System.out.println("3. Mostrar Reservas");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            System.out.println("");
            switch (opcao) {
                case 1 -> adicionarReservas();
                case 2 -> editarReservas();
                case 3 -> mostrarReservas();
                case 0 -> {
                    menu();
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
    private static void adicionarReservas() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Número da Reserva: ");
        String numero = scanner.nextLine();
        mostrarUtentes();
        System.out.print("NIF do Utente (0 para sair): ");
        String nifUtente = scanner.nextLine();
        Utente utente = procurarUtentePorNIF(nifUtente);
        if (utente == null) {
            System.out.println("Operação cancelada.");
            return;
        }
        System.out.print("Quantos livros tem a reserva? ");
        int quantidadeLivros = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        ArrayList<Livro> livrosReserva = new ArrayList<>();
        mostrarLivros();
        for (int i = 0; i < quantidadeLivros; i++) {
            System.out.print("ISBN do " + (i + 1) + "º livro (0 para sair): ");
            String isbnLivro = scanner.nextLine();
            Livro livro = procurarLivroPorISBN(isbnLivro);
            if (livro == null) {
                System.out.println("Operação cancelada.");
                return;
            }
            livrosReserva.add(livro);
        }
        System.out.print("Data de Registo (dd-MM-yyyy): ");
        String dataRegisto = scanner.nextLine();
        System.out.print("Data de Início (dd-MM-yyyy): ");
        String dataInicio = scanner.nextLine();
        System.out.print("Data de Fim (dd-MM-yyyy): ");
        String dataFim = scanner.nextLine();

        Reserva reserva = new Reserva(numero, utente, livrosReserva, dataInicio, dataRegisto, dataFim);
        reservas.add(reserva);

        System.out.println("Reserva adicionada com sucesso!");
    }
    private static void editarReservas() {
        mostrarReservas();
        if (!reservas.isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Número da Reserva a editar: ");
            String numero = scanner.nextLine();
            Reserva reservaEditada = null;
            for (Reserva reserva : reservas) {
                if (reserva.getNumero().equals(numero)) {
                    reservaEditada = reserva;
                    break;
                }
            }
            if (reservaEditada!= null) {
                System.out.println("\n--- Editar Reserva ---");
                System.out.print("Novo NIF: ");
                String novoNif = scanner.nextLine();
                if (!novoNif.isEmpty()) {
                    reservaEditada.setUtente(procurarUtentePorNIF(novoNif));
                } else {
                    System.out.println("NIF não pode estar vazio.");
                }
                System.out.print("Novo Número de Livros: ");
                int novoNumLivros = scanner.nextInt();
                if (novoNumLivros > 0) {
                    ArrayList<Livro> livros = new ArrayList<>();
                    scanner.nextLine(); // consumir o newline do scanner
                    livros.clear();
                    for (int i = 0; i < novoNumLivros; i++) {
                        System.out.print("ISBN do " + (i + 1) + "º Livro: ");
                        String novoLivro = scanner.nextLine();
                        livros.add(procurarLivroPorISBN(novoLivro));
                    }
                    reservaEditada.setLivros(livros);
                } else {
                    System.out.println("Número de Livros não pode estar vazio.");
                }
                System.out.print("Nova Data de Início (dd-MM-yyyy): ");
                String novaDataInicio = scanner.nextLine();
                reservaEditada.setDataInicio(novaDataInicio);
                System.out.print("Nova Data de Fim (dd-MM-yyyy): ");
                String novaDataFim = scanner.nextLine();
                reservaEditada.setDataFim(novaDataFim);

                System.out.println("Reserva editada com sucesso");
            }
        }

    }
    private static void mostrarReservas() {
        transformarReservasParaEmprestimos();
        if (reservas.isEmpty()) {
            System.out.println("Não existem reservas registados.");
        }else{
            System.out.println("\n--- Lista de Reservas ---");
            for (Reserva reserva : reservas) {
                System.out.println(reserva);
            }
        }
    }

    private static void gerirEmprestimos() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n--- Menu Gestão Emprestimos ---");
            System.out.println("1. Adicionar Emprestimos");
            System.out.println("2. Editar Emprestimos");
            System.out.println("3. Mostrar Emprestimos");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            System.out.println("");
            switch (opcao) {
                case 1 -> adicionarEmprestimos();
                case 2 -> editarEmprestimos();
                case 3 -> mostrarEmprestimos();
                case 0 -> {
                    menu();
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void adicionarEmprestimos() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Número do Empréstimo: ");
        String numero = scanner.nextLine();
        mostrarUtentes();
        System.out.print("NIF do Utente (0 para sair): ");
        String nifUtente = scanner.nextLine();
        Utente utente = procurarUtentePorNIF(nifUtente);
        if (utente == null) {
            System.out.println("Operação cancelada.");
            return;
        }
        System.out.print("Quantos livros tem o Empréstimo? ");
        int quantidadeLivros = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        ArrayList<Livro> livrosEmprestimo = new ArrayList<>();
        mostrarLivros();
        for (int i = 0; i < quantidadeLivros; i++) {
            System.out.print("ISBN do " + (i + 1) + "º livro (0 para sair): ");
            String isbnLivro = scanner.nextLine();
            Livro livro = procurarLivroPorISBN(isbnLivro);
            if (livro == null) {
                System.out.println("Operação cancelada.");
                return;
            }
            livrosEmprestimo.add(livro);
        }
        System.out.print("Data de Início (dd-MM-yyyy): ");
        String dataInicio = scanner.nextLine();
        System.out.print("Data Prevista de Devolução (dd-MM-yyyy): ");
        String dataPrevistaDevolucao = scanner.nextLine();

        Emprestimo emprestimo = new Emprestimo(numero, utente, livrosEmprestimo, dataInicio, dataPrevistaDevolucao);
        emprestimos.add(emprestimo);

        System.out.println("Empréstimo adicionado com sucesso!");
    }
    private static void editarEmprestimos() {
        mostrarEmprestimos();
        if (!emprestimos.isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Número do Empréstimo a editar: ");
            String numero = scanner.nextLine();
            Emprestimo emprestimoEditado = null;
            for (Emprestimo emprestimo : emprestimos) {
                if (emprestimo.getNumero().equals(numero)) {
                    emprestimoEditado = emprestimo;
                    break;
                }
            }
            if (emprestimoEditado!= null) {
                System.out.println("\n--- Editar Empréstimo ---");
                System.out.print("Novo NIF: ");
                String novoNif = scanner.nextLine();
                if (!novoNif.isEmpty()) {
                    emprestimoEditado.setUtente(procurarUtentePorNIF(novoNif));
                } else {
                    System.out.println("NIF não pode estar vazio.");
                }
                System.out.print("Novo Número de Livros: ");
                int novoNumLivros = scanner.nextInt();
                if (novoNumLivros > 0) {
                    ArrayList<Livro> livros = new ArrayList<>();
                    scanner.nextLine(); // consumir o newline do scanner
                    livros.clear();
                    for (int i = 0; i < novoNumLivros; i++) {
                        System.out.print("ISBN do " + (i + 1) + "º Livro: ");
                        String novoLivro = scanner.nextLine();
                        livros.add(procurarLivroPorISBN(novoLivro));
                    }
                    emprestimoEditado.setLivros(livros);
                } else {
                    System.out.println("Número de Livros não pode estar vazio.");
                }
                System.out.print("Nova Data de Início (dd-MM-yyyy): ");
                String novaDataInicio = scanner.nextLine();
                emprestimoEditado.setDataInicio(novaDataInicio);
                System.out.print("Nova Data Prevista de Devolução (dd-MM-yyyy): ");
                String novaDataPrevistaDevolucao = scanner.nextLine();
                emprestimoEditado.setDataPrevistaDevolucao(novaDataPrevistaDevolucao);
                System.out.print("Nova Data Efetiva de Devolução (dd-MM-yyyy): ");
                String novaDataEfetivaDevolucao = scanner.nextLine();
                emprestimoEditado.setDataEfetivaDevolucao(novaDataEfetivaDevolucao);

                System.out.println("Empréstimo editado com sucesso");
            }
        }

    }
    private static void mostrarEmprestimos() {
        if (emprestimos.isEmpty()) {
            System.out.println("Não existem empréstimos registados.");
        }else{
            System.out.println("\n--- Lista de Empréstimos ---");
            for (Emprestimo emprestimo : emprestimos) {
                System.out.println(emprestimo);
            }
        }
    }


    private static void pesquisarGeral() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Pesquisa ---");
        System.out.println("1. Pesquisar por NIF de Utente");
        System.out.println("2. Pesquisar por ISBN de Livro");
        System.out.println("3. Pesquisar por ISSN de Jornal");
        System.out.println("0. Cancelar");
        System.out.print("Opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // consumir a quebra de linha
        switch (opcao) {
            case 1:
                System.out.print("Insira o NIF a procurar: ");
                String nif = scanner.nextLine();
                System.out.println(procurarUtentePorNIF(nif));
                break;
            case 2:
                System.out.print("Insira o ISBN a procurar: ");
                String isbn = scanner.nextLine();
                System.out.println(procurarLivroPorISBN(isbn));
                break;
            case 3:
                System.out.print("Insira o ISSN a procurar: ");
                String issn = scanner.nextLine();
                System.out.println(procurarJornalPorISSN(issn));
                break;
            case 0:
                menu();
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void mostrarGeral() {
        String dataInicial;
        String dataFinal;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- Mostrar ---");
        System.out.println("1. Mostrar Utentes com Reservas/Empréstimos");
        System.out.println("2. Mostrar Utentes com Devolucão com atraso superior a X dias");
        System.out.println("3. Mostrar Emprestimos entre Data X e Data Y");
        System.out.println("4. Mostrar Emprestimos entre Data X e Data Y por Utente");
        System.out.println("5. Mostrar Tempo Medio em dias de Emprestimos entre Data X e Data Y");
        System.out.println("6. Mostrar Item mais Requesitado entre Data X e Data Y");
        System.out.println("0. Cancelar");
        System.out.print("Opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // consumir a quebra de linha
        switch (opcao) {
            case 1:
                listarUtentesAtivos();
                break;
            case 2:
                System.out.print("Insira o número de dias de atraso minimo: ");
                int dias = scanner.nextInt();
                scanner.nextLine(); // consumir a quebra de linha
                listarUtentesDevolucaoAtrasada(dias);
                break;
            case 3:
                System.out.println("Insira a data inicial (dd-MM-yyyy):");
                dataInicial = scanner.nextLine();
                System.out.println("Insira a data final (dd-MM-yyyy):");
                dataFinal = scanner.nextLine();
                listarTotalEmprestimosIntervaloDatas(dataInicial, dataFinal);
                break;
            case 4:
                System.out.println("Insira o nif do Utente a procurar: ");
                String nifUtente = scanner.nextLine();
                while (true) {
                    for (Utente utente : utentes) {
                        if (utente.getNif().equals(nifUtente)) {
                            System.out.println("Insira a data inicial (dd-MM-yyyy):");
                            dataInicial = scanner.nextLine();
                            System.out.println("Insira a data final (dd-MM-yyyy):");
                            dataFinal = scanner.nextLine();
                            listarTotalEmprestimosIntervaloDatas(dataInicial, dataFinal, nifUtente);
                            return;
                        }
                    }
                    System.out.println("NIF não encontrado. Tente novamente ou digite '0' para cancelar.");
                    System.out.print("NIF do Utente (0 para sair): ");
                    nifUtente = scanner.nextLine();
                    if (nifUtente.equals("0")) {
                        mostrarGeral();
                    }
                }
            case 5:
                System.out.println("Insira a data inicial (dd-MM-yyyy):");
                dataInicial = scanner.nextLine();
                System.out.println("Insira a data final (dd-MM-yyyy):");
                dataFinal = scanner.nextLine();
                listarMediaDiasEmprestimosIntervaloDatas(dataInicial, dataFinal);
                break;
            case 6:
                System.out.println("Insira a data inicial (dd-MM-yyyy):");
                dataInicial = scanner.nextLine();
                System.out.println("Insira a data final (dd-MM-yyyy):");
                dataFinal = scanner.nextLine();
                itemMaisRequisitado(dataInicial, dataFinal);
                break;
            case 0:
                menu();
            default:
                System.out.println("Opção inválida.");
        }
    }


    public static Utente procurarUtentePorNIF(String nif) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            for (Utente utente : utentes) {
                if (utente.getNif().equals(nif)) {
                    return utente;
                }
            }
            System.out.println("NIF não encontrado. Tente novamente ou digite '0' para cancelar.");
            System.out.print("NIF do Utente (0 para sair): ");
            nif = scanner.nextLine();
            if (nif.equals("0")) {
                return null;
            }
        }
    }
    public static Livro procurarLivroPorISBN(String isbn) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            for (Livro livro : livros) {
                if (livro.getISBN().equals(isbn)) {
                    return livro;
                }
            }
            System.out.println("ISBN não encontrado. Tente novamente ou digite '0' para cancelar.");
            System.out.print("ISBN do Livro (0 para sair): ");
            isbn = scanner.nextLine();
            if (isbn.equals("0")) {
                return null;
            }
        }
    }

    public static Jornal procurarJornalPorISSN(String issn) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            for (Jornal jornal : jornais) {
                if (jornal.getISSN().equals(issn)) {
                    return jornal;
                }
            }
            System.out.println("ISSN não encontrado. Tente novamente ou digite '0' para cancelar.");
            System.out.print("ISSN do Jornal (0 para sair): ");
            issn = scanner.nextLine();
            if (issn.equals("0")) {
                return null;
            }
        }
    }

    public static void transformarReservasParaEmprestimos() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataAtual = LocalDate.now();

        LocalDate dataInicioFormatada;

        ArrayList<Reserva> reservasParaRemover = new ArrayList<>();
        for (Reserva reserva : reservas) {
            try {
                dataInicioFormatada = LocalDate.parse(reserva.getDataInicio(), formatter);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("A data de início fornecida não está no formato correto 'dd-MM-yyyy'.", e);
            }
            if (!dataInicioFormatada.isAfter(dataAtual)) {
                Emprestimo emprestimo = new Emprestimo(
                        String.valueOf(emprestimos.size() + 1),
                        reserva.getUtente(),
                        reserva.getLivros(),
                        reserva.getDataInicio(),
                        reserva.getDataFim()
                );
                emprestimos.add(emprestimo);
                reservasParaRemover.add(reserva);
            }
        }

        reservas.removeAll(reservasParaRemover);
    }

    public static void listarUtentesAtivos() {
        ArrayList<Utente> utentesAtivos = new ArrayList<>();

        // Adicionar utentes das reservas
        for (Reserva reserva : reservas) {
            if (!utentesAtivos.contains(reserva.getUtente())) {
                utentesAtivos.add(reserva.getUtente());
            }
        }

        // Adicionar utentes dos empréstimos
        for (Emprestimo emprestimo : emprestimos) {
            if (!utentesAtivos.contains(emprestimo.getUtente())) {
                utentesAtivos.add(emprestimo.getUtente());
            }
        }

        // Mostrar utentes
        if (utentesAtivos.isEmpty()) {
            System.out.println("Não há utentes com reservas ou empréstimos.");
        } else {
            System.out.println("\n--- Lista de Utentes Ativos ---");
            for (Utente utente : utentesAtivos) {
                System.out.println(utente);
            }
        }
    }
    public static ArrayList<Utente> listarUtentesAtivosLista() {
        ArrayList<Utente> utentesAtivos = new ArrayList<>();

        for (Reserva reserva : reservas) {
            if (!utentesAtivos.contains(reserva.getUtente())) {
                utentesAtivos.add(reserva.getUtente());
            }
        }

        for (Emprestimo emprestimo : emprestimos) {
            if (!utentesAtivos.contains(emprestimo.getUtente())) {
                utentesAtivos.add(emprestimo.getUtente());
            }
        }

        return utentesAtivos;
    }
    public static ArrayList<Livro> listarLivrosAtivosLista() {
        ArrayList<Livro> livrosAtivos = new ArrayList<>();

        for (Reserva reserva : reservas) {
            for (Livro livro : reserva.getLivros()) {
                if (!livrosAtivos.contains(livro)) {
                    livrosAtivos.add(livro);
                }
            }
        }

        for (Emprestimo emprestimo : emprestimos) {
            for (Livro livro : emprestimo.getLivros()) {
                if (!livrosAtivos.contains(livro)) {
                    livrosAtivos.add(livro);
                }
            }
        }

        return livrosAtivos;
    }

    public static void listarUtentesDevolucaoAtrasada(int dias) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataAtual = LocalDate.now();
        for (Reserva reserva : reservas){
            LocalDate dataFimFormatada = LocalDate.parse(reserva.getDataFim(), formatter);
            Period periodo = dataFimFormatada.until(dataAtual);
            if (periodo.getDays() >= dias){
                System.out.println(reserva.getUtente());
            }
        }
        for (Emprestimo emprestimo : emprestimos){
            LocalDate dataPrevistaDevolucaoFormatada = LocalDate.parse(emprestimo.getDataPrevistaDevolucao(), formatter);
            Period periodo = dataPrevistaDevolucaoFormatada.until(dataAtual);
            if (periodo.getDays() >= dias){
                System.out.println(emprestimo.getUtente());
            }
        }
    }
    public static void listarTotalEmprestimosIntervaloDatas(String dataInicial, String dataFinal){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataInicialInseridaFormatada = LocalDate.parse(dataInicial, formatter);
        LocalDate dataFinalInseridaFormatada = LocalDate.parse(dataFinal, formatter);
        ArrayList<Emprestimo> emprestimosIntervaloDatas = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos){
            LocalDate dataInicioFormatada = LocalDate.parse(emprestimo.getDataInicio(), formatter);
            LocalDate dataFimFormatada = LocalDate.parse(emprestimo.getDataPrevistaDevolucao(), formatter);
            if(dataInicioFormatada.isAfter(dataInicialInseridaFormatada) && dataFimFormatada.isBefore(dataFinalInseridaFormatada)){
                emprestimosIntervaloDatas.add(emprestimo);
            }
        }
        System.out.println("Existem " + emprestimosIntervaloDatas.size() + " emprestimos feitos dentro desse intevalo de datas.");
    }

    public static void listarMediaDiasEmprestimosIntervaloDatas(String dataInicial, String dataFinal){
        long count = 0;
        long somaDias = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataInicialInseridaFormatada = LocalDate.parse(dataInicial, formatter);
        LocalDate dataFinalInseridaFormatada = LocalDate.parse(dataFinal, formatter);
        ArrayList<Emprestimo> emprestimosIntervaloDatas = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos){
            LocalDate dataInicioFormatada = LocalDate.parse(emprestimo.getDataInicio(), formatter);
            LocalDate dataFimFormatada = LocalDate.parse(emprestimo.getDataPrevistaDevolucao(), formatter);
            if(dataInicioFormatada.isAfter(dataInicialInseridaFormatada) && dataFimFormatada.isBefore(dataFinalInseridaFormatada)){
                count++;
                somaDias += ChronoUnit.DAYS.between(dataInicioFormatada, dataFimFormatada);
                emprestimosIntervaloDatas.add(emprestimo);
            }
        }
        System.out.println("A media em dias dos emprestimos entre as data indicadas é de " + somaDias/count + " dias.");
    }

    public static void listarTotalEmprestimosIntervaloDatas(String dataInicial, String dataFinal, String nif){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataInicialInseridaFormatada = LocalDate.parse(dataInicial, formatter);
        LocalDate dataFinalInseridaFormatada = LocalDate.parse(dataFinal, formatter);
        ArrayList<Emprestimo> emprestimosIntervaloDatas = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos){
            if(emprestimo.getUtente().getNif().equals(nif)){
                LocalDate dataInicioFormatada = LocalDate.parse(emprestimo.getDataInicio(), formatter);
                LocalDate dataFimFormatada = LocalDate.parse(emprestimo.getDataPrevistaDevolucao(), formatter);
                if(dataInicioFormatada.isAfter(dataInicialInseridaFormatada) && dataFimFormatada.isBefore(dataFinalInseridaFormatada)){
                    emprestimosIntervaloDatas.add(emprestimo);
                }
            }
        }
        System.out.println("Existem " + emprestimosIntervaloDatas.size() + " emprestimos feitos dentro desse intevalo de datas para o utente com o nif " + nif + ".");
    }

    public static void itemMaisRequisitado(String dataInicial, String dataFinal) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataInicialInserida = LocalDate.parse(dataInicial, formatter);
        LocalDate dataFinalInserida = LocalDate.parse(dataFinal, formatter);

        // Encontrar o item mais requisitado usando ArrayList
        ArrayList<String> isbnList = new ArrayList<>();
        ArrayList<Integer> freqList = new ArrayList<>();

        // Filtrar e processar os empréstimos dentro do intervalo de datas
        for (Emprestimo emprestimo : emprestimos) {
            LocalDate dataInicioEmprestimo = LocalDate.parse(emprestimo.getDataInicio(), formatter);
            if (!dataInicioEmprestimo.isBefore(dataInicialInserida) && !dataInicioEmprestimo.isAfter(dataFinalInserida)) {
                for (Livro livro : emprestimo.getLivros()) {
                    String isbn = livro.getISBN();
                    if (isbnList.contains(isbn)) {
                        int index = isbnList.indexOf(isbn);
                        freqList.set(index, freqList.get(index) + 1);
                    } else {
                        isbnList.add(isbn);
                        freqList.add(1);
                    }
                }
            }
        }

        // Filtrar e processar as reservas dentro do intervalo de datas
        for (Reserva reserva : reservas) {
            LocalDate dataRegistroReserva = LocalDate.parse(reserva.getDataRegisto(), formatter);
            if (!dataRegistroReserva.isBefore(dataInicialInserida) && !dataRegistroReserva.isAfter(dataFinalInserida)) {
                for (Livro livro : reserva.getLivros()) {
                    String isbn = livro.getISBN();
                    if (isbnList.contains(isbn)) {
                        int index = isbnList.indexOf(isbn);
                        freqList.set(index, freqList.get(index) + 1);
                    } else {
                        isbnList.add(isbn);
                        freqList.add(1);
                    }
                }
            }
        }

        // Encontrar os ISBNs mais requisitados
        int maxFrequencia = 0;
        ArrayList<String> maisRequisitados = new ArrayList<>();
        for (int i = 0; i < isbnList.size(); i++) {
            if (freqList.get(i) > maxFrequencia) {
                maxFrequencia = freqList.get(i);
                maisRequisitados.clear();
                maisRequisitados.add(isbnList.get(i));
            } else if (freqList.get(i) == maxFrequencia) {
                maisRequisitados.add(isbnList.get(i));
            }
        }

        // Apresentar os resultados
        if (!maisRequisitados.isEmpty()) {
            System.out.println("Os itens mais requisitados no intervalo de datas são:");
            for (String isbn : maisRequisitados) {
                System.out.println("ISBN: " + isbn + " com " + maxFrequencia + " requisições.");
            }
        } else {
            System.out.println("Nenhum item foi requisitado no intervalo de datas especificado.");
        }
    }

}
