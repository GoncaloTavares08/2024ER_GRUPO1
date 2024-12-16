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
    }

    private static void guardarLivros(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHEIRO_LIVROS))) {
            for (Livro livro : SistemaGestaoBiblioteca.livros) {
                writer.write(livro.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao guardar livros: " + e.getMessage());
        }
    }

    private static void guardarJornais() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHEIRO_JORNAIS))) {
            for (Jornal jornal : SistemaGestaoBiblioteca.jornais) {
                writer.write(jornal.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao guardar jornais: " + e.getMessage());
        }
    }

    public static void carregarDados() {
        carregarLivros();
        carregarJornais();
    }

    private static void carregarLivros(){
        try (BufferedReader reader = new BufferedReader(new FileReader(FICHEIRO_LIVROS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Livro livro = Livro.fromString(linha);
                SistemaGestaoBiblioteca.livros.add(livro);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar livros: " + e.getMessage());
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
            System.out.println("Erro ao carregar jornais: " + e.getMessage());
        }
    }
}
