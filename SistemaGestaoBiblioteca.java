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

    private static void removerUtentes() {
        //mostrarUtentes();
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

    private static void adicionarReservas() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Número da Reserva: ");
        String numero = scanner.nextLine();
        //mostrarUtentes();
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
        //mostrarLivros();
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

    private static void adicionarEmprestimos() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Número do Empréstimo: ");
        String numero = scanner.nextLine();
        //mostrarUtentes();
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
        //mostrarLivros();
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

    public static Utente procurarUtentePorNIF(String nif) {
        return null;
    }
    public static Livro procurarLivroPorISBN(String isbn) {
        return null;
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


}
