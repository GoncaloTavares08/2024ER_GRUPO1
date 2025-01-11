package utilitarios;

import modelos.*;

import javax.print.Doc;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Memoria {
    private static final String FICHEIRO_LIVROS = "ficheiros/livros.txt";
    private static final String FICHEIRO_JORNAIS = "ficheiros/jornais.txt";
    private static final String FICHEIRO_REVISTAS = "ficheiros/revistas.txt";
    private static final String FICHEIRO_UTENTES = "ficheiros/utentes.txt";
    private static final String FICHEIRO_RESERVAS = "ficheiros/reservas.txt";
    private static final String FICHEIRO_EMPRESTIMOS = "ficheiros/emprestimos.txt";


    public static List<Livro> carregarLivros() {
        List<Livro> livros = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FICHEIRO_LIVROS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Livro livro = Livro.fromString(linha);
                livros.add(livro);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar os livros: " + e.getMessage());
        }
        return livros;
    }

    public static List<Jornal> carregarJornais() {
        List<Jornal> jornais = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FICHEIRO_JORNAIS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Jornal jornal = Jornal.fromString(linha);
                jornais.add(jornal);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar os jornais: " + e.getMessage());
        }
        return jornais;
    }

    public static List<Revista> carregarRevistas() {
        List<Revista> revistas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FICHEIRO_REVISTAS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Revista revista = Revista.fromString(linha);
                revistas.add(revista);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar revistas: " + e.getMessage());
        }
        return revistas;
    }

    public static List<Utente> carregarUtentes() {
        List<Utente> utentes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FICHEIRO_UTENTES))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Utente utente = Utente.fromString(linha);
                utentes.add(utente);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar os utentes: " + e.getMessage());
        }
        return utentes;
    }

    public static List<Emprestimo> carregarEmprestimos(Biblioteca biblioteca) {
        List<Emprestimo> emprestimos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FICHEIRO_EMPRESTIMOS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Emprestimo emprestimo = carregarEmprestimo(linha, biblioteca);
                emprestimos.add(emprestimo);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar os emprestimos: " + e.getMessage());
        }
        return emprestimos;
    }

    private static Emprestimo carregarEmprestimo(String dados, Biblioteca biblioteca){
        String[] partes = dados.split("\\|");
        String numero = partes[0];
        String nif = partes[1];
        Utente utente = biblioteca.getUtentePorNif(nif);
        if (utente == null) {
            throw new IllegalArgumentException("Utente com NIF " + nif + " não encontrado.");
        }
        List<Documento> documentos = new ArrayList<>();
        for (String titulo : partes[2].split(",")) {
            Documento documento = biblioteca.getDocumentoPorTitulo(titulo.trim());
            if (documento == null) {
                throw new IllegalArgumentException("Documento com titulo " + titulo + " não encontrado.");
            }
            documentos.add(documento);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataInicio = LocalDate.parse(partes[3], formatter);
        LocalDate dataPrevistaDevolucao = LocalDate.parse(partes[4], formatter);


        if (partes[5].equals("Nao Devolvido")) {
            return new Emprestimo(numero, utente, documentos, dataInicio, dataPrevistaDevolucao);
        }else {
            LocalDate dataEfetivaDevolucao = LocalDate.parse(partes[5], formatter);
            return new Emprestimo(numero, utente, documentos, dataInicio, dataPrevistaDevolucao, dataEfetivaDevolucao);
        }
    }

//    public static List<Reserva> carregarReservas(Biblioteca biblioteca) {
//
//    }

    public static void guardaDados(Biblioteca biblioteca) {
        guardarLivros(biblioteca);
        guardarJornais(biblioteca);
        guardarRevistas(biblioteca);
        guardarUtentes(biblioteca);
        guardarEmprestimos(biblioteca);
        guardarReservas(biblioteca);
    }

    private static void guardarLivros(Biblioteca biblioteca) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHEIRO_LIVROS))) {
            for (Livro livro : biblioteca.getLivros()) {
                writer.write(livro.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao guardar os livros: " + e.getMessage());
        }
    }

    private static void guardarJornais(Biblioteca biblioteca) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHEIRO_JORNAIS))) {
            for (Jornal jornal : biblioteca.getJornais()) {
                writer.write(jornal.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao guardar os jornais: " + e.getMessage());
        }
    }

    private static void guardarRevistas(Biblioteca biblioteca) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHEIRO_REVISTAS))) {
            for (Revista revista : biblioteca.getRevistas()) {
                writer.write(revista.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao guardar as revistas: " + e.getMessage());
        }
    }

    private static void guardarUtentes(Biblioteca biblioteca) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHEIRO_UTENTES))) {
            for (Utente utente : biblioteca.getUtentes()) {
                writer.write(utente.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao guardar utentes: " + e.getMessage());
        }
    }

    private static void guardarEmprestimos(Biblioteca biblioteca) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHEIRO_EMPRESTIMOS))) {
            for (Emprestimo emprestimo : biblioteca.getEmprestimos()) {
                writer.write(emprestimo.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao guardar emprestimos: " + e.getMessage());
        }
    }

    private static void guardarReservas(Biblioteca biblioteca) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHEIRO_RESERVAS))) {
            for (Reserva reserva : biblioteca.getReservas()) {
                writer.write(reserva.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao guardar reservas: " + e.getMessage());
        }
    }
}
