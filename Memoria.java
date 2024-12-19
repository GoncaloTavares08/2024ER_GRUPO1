import java.io.*;

public class Memoria {
    private static final String FICHEIRO_LIVROS = "ficheiros/livros.txt";
    private static final String FICHEIRO_JORNAIS = "ficheiros/jornais.txt";
    private static final String FICHEIRO_REVISTAS = "ficheiros/revistas.txt";
    private static final String FICHEIRO_UTENTES = "ficheiros/utentes.txt";
    private static final String FICHEIRO_RESERVAS = "ficheiros/reservas.txt";
    private static final String FICHEIRO_EMPRESTIMOS = "ficheiros/emprestimos.txt";

    public static void guardarDados() {
        guardarLivros();
        guardarJornais();
        guardarRevistas();
        guardarUtentes();
        guardarReservas();
    }

    private static void guardarLivros(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHEIRO_LIVROS))) {
            for (Livro livro : SistemaGestaoBiblioteca.livros) {
                writer.write(livro.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao guardar os livros: " + e.getMessage());
        }
    }

    private static void guardarJornais() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHEIRO_JORNAIS))) {
            for (Jornal jornal : SistemaGestaoBiblioteca.jornais) {
                writer.write(jornal.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao guardar os jornais: " + e.getMessage());
        }
    }

    private static void guardarRevistas() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHEIRO_REVISTAS))) {
            for (Revista revista : SistemaGestaoBiblioteca.revistas) {
                writer.write(revista.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao guardar as revistas: " + e.getMessage());
        }
    }

    private static void guardarUtentes() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHEIRO_UTENTES))) {
            for (Utente utente : SistemaGestaoBiblioteca.utentes) {
                writer.write(utente.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao guardar os utentes: " + e.getMessage());
        }
    }

    private static void guardarReservas(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHEIRO_RESERVAS))) {
            for (Reserva reserva : SistemaGestaoBiblioteca.reservas) {
                writer.write(reserva.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao guardar as reservas: " + e.getMessage());
        }
    }

    public static void carregarDados() {
        carregarLivros();
        carregarJornais();
        carregarRevistas();
        carregarUtentes();
        carregarReservas();
    }

    private static void carregarLivros(){
        try (BufferedReader reader = new BufferedReader(new FileReader(FICHEIRO_LIVROS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Livro livro = Livro.fromString(linha);
                SistemaGestaoBiblioteca.livros.add(livro);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar os livros: " + e.getMessage());
        }
    }

    private static void carregarJornais() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FICHEIRO_JORNAIS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Jornal jornal = Jornal.fromString(linha);
                SistemaGestaoBiblioteca.jornais.add(jornal);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar os jornais: " + e.getMessage());
        }
    }

    private static void carregarRevistas() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FICHEIRO_REVISTAS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Revista revista = Revista.fromString(linha);
                SistemaGestaoBiblioteca.revistas.add(revista);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar as revistas: " + e.getMessage());
        }
    }

    private static void carregarUtentes() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FICHEIRO_UTENTES))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Utente utente = Utente.fromString(linha);
                SistemaGestaoBiblioteca.utentes.add(utente);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar os utentes: " + e.getMessage());
        }
    }

    private static void carregarReservas() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FICHEIRO_RESERVAS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Reserva reserva = Reserva.fromString(linha);
                SistemaGestaoBiblioteca.reservas.add(reserva);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar as reservas: " + e.getMessage());
        }
    }
}
