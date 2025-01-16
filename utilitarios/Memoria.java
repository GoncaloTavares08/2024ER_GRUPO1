package utilitarios;

import modelos.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Memoria {
    private static final String FICHEIROS_BASE = "ficheiros/";
    private static final String FICHEIRO_LIVROS = "livros.txt";
    private static final String FICHEIRO_JORNAIS = "jornais.txt";
    private static final String FICHEIRO_REVISTAS = "revistas.txt";
    private static final String FICHEIRO_UTENTES = "utentes.txt";
    private static final String FICHEIRO_RESERVAS = "reservas.txt";
    private static final String FICHEIRO_EMPRESTIMOS = "emprestimos.txt";

    private static final String[] FICHEIROS_OBRIGATORIOS = {
            FICHEIRO_LIVROS, FICHEIRO_JORNAIS, FICHEIRO_REVISTAS,
            FICHEIRO_UTENTES, FICHEIRO_RESERVAS, FICHEIRO_EMPRESTIMOS
    };

    public static void garantirDiretorioEficheirosExistem(String diretorio) {
        try {
            // Garante que o diretório existe
            Files.createDirectories(Paths.get(FICHEIROS_BASE + diretorio));
            // Garante que os ficheiros obrigatórios existem
            for (String ficheiro : FICHEIROS_OBRIGATORIOS) {
                File file = new File(FICHEIROS_BASE + diretorio + "/" + ficheiro);
                if (!file.exists()) {
                    file.createNewFile(); // Cria um ficheiro vazio
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao criar diretório ou ficheiros: " + e.getMessage());
        }
    }

    public static List<Biblioteca> carregarBibliotecas() {
        List<Biblioteca> bibliotecas = new ArrayList<>();
        File diretorioBibliotecas = new File(FICHEIROS_BASE);
        if (diretorioBibliotecas.exists()) {
            for (File arquivo : diretorioBibliotecas.listFiles()) {
                if (arquivo.isDirectory()) {
                    bibliotecas.add(new Biblioteca(arquivo.getName()));
                }
            }
        }
        return bibliotecas;
    }

    public static void criarBiblioteca(String nomeBiblioteca) {
        File diretorioBiblioteca = new File(FICHEIROS_BASE + nomeBiblioteca);
        if (!diretorioBiblioteca.exists()) {
            diretorioBiblioteca.mkdir();
        }
    }

    public static List<Livro> carregarLivros(String diretorio) {
        List<Livro> livros = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FICHEIROS_BASE + diretorio + "/" + FICHEIRO_LIVROS))) {
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

    public static List<Jornal> carregarJornais(String diretorio) {
        List<Jornal> jornais = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FICHEIROS_BASE + diretorio + "/" + FICHEIRO_JORNAIS))) {
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

    public static List<Revista> carregarRevistas(String diretorio) {
        List<Revista> revistas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FICHEIROS_BASE + diretorio + "/" + FICHEIRO_REVISTAS))) {
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

    public static List<Utente> carregarUtentes(String diretorio) {
        List<Utente> utentes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FICHEIROS_BASE + diretorio + "/" + FICHEIRO_UTENTES))) {
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
        try (BufferedReader reader = new BufferedReader(new FileReader(FICHEIROS_BASE + biblioteca.getDiretorio() + "/" + FICHEIRO_EMPRESTIMOS))) {
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

    private static Emprestimo carregarEmprestimo(String dados, Biblioteca biblioteca) {
        String[] partes = dados.split("\\|");
        String numero = partes[0];
        String nif = partes[1];
        Utente utente = biblioteca.getUtentePorNif(nif);
        if (utente == null) {
            throw new IllegalArgumentException("Utente com NIF " + nif + " não encontrado.");
        }
        List<Documento> documentos = new ArrayList<>();
        for (String id : partes[2].split(",")) {
            Documento documento = biblioteca.getDocumentoPorIdentificador(id.trim());
            if (documento == null) {
                throw new IllegalArgumentException("Documento com id " + id + " não encontrado.");
            }
            documentos.add(documento);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataInicio = LocalDate.parse(partes[3], formatter);
        LocalDate dataPrevistaDevolucao = LocalDate.parse(partes[4], formatter);


        if (partes[5].equals("Nao Devolvido")) {
            return new Emprestimo(numero, utente, documentos, dataInicio, dataPrevistaDevolucao);
        } else {
            LocalDate dataEfetivaDevolucao = LocalDate.parse(partes[5], formatter);
            return new Emprestimo(numero, utente, documentos, dataInicio, dataPrevistaDevolucao, dataEfetivaDevolucao);
        }
    }

    public static List<Reserva> carregarReservas(Biblioteca biblioteca) {
        List<Reserva> reservas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FICHEIROS_BASE + biblioteca.getDiretorio() + "/" + FICHEIRO_RESERVAS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Reserva reserva = carregarReserva(linha, biblioteca);
                reservas.add(reserva);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar as reservas: " + e.getMessage());
        }
        return reservas;
    }

    private static Reserva carregarReserva(String dados, Biblioteca biblioteca) {
        String[] partes = dados.split("\\|");
        String numero = partes[0];
        String nif = partes[1];
        Utente utente = biblioteca.getUtentePorNif(nif);
        if (utente == null) {
            throw new IllegalArgumentException("Utente com NIF " + nif + " não encontrado.");
        }
        List<Documento> documentos = new ArrayList<>();
        for (String id : partes[2].split(",")) {
            Documento documento = biblioteca.getDocumentoPorIdentificador(id.trim());
            if (documento == null) {
                throw new IllegalArgumentException("Documento com ID " + id + " não encontrado.");
            }
            documentos.add(documento);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataRegisto = LocalDate.parse(partes[3], formatter);
        LocalDate dataInicio = LocalDate.parse(partes[4], formatter);
        LocalDate dataFim = LocalDate.parse(partes[5], formatter);

        return new Reserva(numero, utente, documentos, dataRegisto, dataInicio, dataFim);
    }

    public static void guardaDados(Biblioteca biblioteca) {
        guardarLivros(biblioteca);
        guardarJornais(biblioteca);
        guardarRevistas(biblioteca);
        guardarUtentes(biblioteca);
        guardarEmprestimos(biblioteca);
        guardarReservas(biblioteca);
    }

    private static void guardarLivros(Biblioteca biblioteca) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHEIROS_BASE + biblioteca.getDiretorio() + "/" + FICHEIRO_LIVROS))) {
            for (Livro livro : biblioteca.getLivros()) {
                writer.write(livro.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao guardar os livros: " + e.getMessage());
        }
    }

    private static void guardarJornais(Biblioteca biblioteca) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHEIROS_BASE + biblioteca.getDiretorio() + "/" + FICHEIRO_JORNAIS))) {
            for (Jornal jornal : biblioteca.getJornais()) {
                writer.write(jornal.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao guardar os jornais: " + e.getMessage());
        }
    }

    private static void guardarRevistas(Biblioteca biblioteca) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHEIROS_BASE + biblioteca.getDiretorio() + "/" + FICHEIRO_REVISTAS))) {
            for (Revista revista : biblioteca.getRevistas()) {
                writer.write(revista.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao guardar as revistas: " + e.getMessage());
        }
    }

    private static void guardarUtentes(Biblioteca biblioteca) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHEIROS_BASE + biblioteca.getDiretorio() + "/" + FICHEIRO_UTENTES))) {
            for (Utente utente : biblioteca.getUtentes()) {
                writer.write(utente.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao guardar utentes: " + e.getMessage());
        }
    }

    private static void guardarEmprestimos(Biblioteca biblioteca) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHEIROS_BASE + biblioteca.getDiretorio() + "/" + FICHEIRO_EMPRESTIMOS))) {
            for (Emprestimo emprestimo : biblioteca.getEmprestimos()) {
                writer.write(emprestimo.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao guardar emprestimos: " + e.getMessage());
        }
    }

    private static void guardarReservas(Biblioteca biblioteca) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHEIROS_BASE + biblioteca.getDiretorio() + "/" + FICHEIRO_RESERVAS))) {
            for (Reserva reserva : biblioteca.getReservas()) {
                writer.write(reserva.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao guardar reservas: " + e.getMessage());
        }
    }
}
