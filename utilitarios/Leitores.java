package utilitarios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
/**
 * A classe Leitores fornece métodos utilitários para ler diferentes tipos de dados
 * a partir da entrada do utilizador, garantindo que os dados são válidos.
 * @author [João Teixeira]
 * @version 1.0
 */
public class Leitores {
    /**
     * Lê uma data a partir da entrada do utilizador.
     * O formato esperado é "dd-MM-yyyy".
     *
     * @param sc o Scanner utilizado para ler a entrada do utilizador
     * @return a data lida como um objeto LocalDate
     */
    public static LocalDate lerData(Scanner sc) {
        LocalDate data = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        while (data == null) {
            try {
                String dataString = sc.nextLine();
                data = LocalDate.parse(dataString, formatter);
            } catch (Exception e) {
                System.out.print("Data inválida. Insira novamente:");
            }
        }
        return data;
    }
    /**
     * Lê um ISBN a partir da entrada do utilizador.
     * O metodo valida se o ISBN introduzido é válido.
     *
     * @param sc o Scanner utilizado para ler a entrada do utilizador
     * @return o ISBN lido como uma String
     */
    public static String lerISBN(Scanner sc) {
        String isbn = "";
        boolean eValido = false;
        while (!eValido) {
            isbn = sc.nextLine();
            if (Utilitarios.eValidoISBN(isbn)) {
                eValido = true;
            } else {
                System.out.print("ISBN inválido, introduza novamente:");
            }
        }
        return isbn;
    }
    /**
     * Lê um ISSN a partir da entrada do utilizador.
     * O metodo valida se o ISSN introduzido é válido.
     *
     * @param sc o Scanner utilizado para ler a entrada do utilizador
     * @return o ISSN lido como uma String
     */
    public static String lerISSN(Scanner sc) {
        String issn = "";
        boolean eValido = false;
        while (!eValido) {
            issn = sc.nextLine();
            if (Utilitarios.eValidoISSN(issn)) {
                eValido = true;
            } else {
                System.out.print("ISSN inválido, introduza novamente:");
            }
        }
        return issn;
    }
    /**
     * Lê uma string não vazia a partir da entrada do utilizador.
     * O metodo garante que a string não é apenas espaços em branco.
     *
     * @param sc o Scanner utilizado para ler a entrada do utilizador
     * @return a string lida como uma String
     */
    public static String lerStringNaoVazia(Scanner sc) {
        String string = "";
        boolean eValido = false;
        while (!eValido) {
            string = sc.nextLine();
            if (!string.trim().isEmpty()) {
                eValido = true;
            } else {
                System.out.print("Campo inválido, introduza novamente:");
            }
        }
        return string;
    }
    /**
     * Lê um número inteiro a partir da entrada do utilizador.
     * O metodo garante que o valor introduzido é um número inteiro válido.
     *
     * @param sc o Scanner utilizado para ler a entrada do utilizador
     * @return o número inteiro lido
     */
    public static int lerNumeroInteiro(Scanner sc) {
        int numero = 0;
        boolean eValido = false;
        while (!eValido) {
            try {
                numero = sc.nextInt();
                eValido = true;
            } catch (Exception e) {
                System.out.print("Valor inválido, introduza novamente:");
            }
            sc.nextLine();
        }
        return numero;
    }
    /**
     * Lê um género a partir da entrada do utilizador.
     * O metodo valida se o género introduzido é 'M' (masculino) ou 'F' (feminino).
     *
     * @param sc o Scanner utilizado para ler a entrada do utilizador
     * @return o género lido como um carácter
     */
    public static char lerGenero(Scanner sc) {
        char genero = ' ';
        boolean eValido = false;
        while (!eValido) {
            genero = sc.nextLine().toUpperCase().charAt(0);
            if (genero == 'M' || genero == 'F') {
                eValido = true;
            } else {
                System.out.print("Género inválido, introduza novamente:");
            }
        }
        return genero;
    }
}