package utilitarios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Leitores {
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

    public static char lerGenero(Scanner sc) {
        char genero = ' ';
        boolean eValido = false;
        while (!eValido) {
            genero = sc.nextLine().charAt(0);
            if (genero == 'M' || genero == 'F') {
                eValido = true;
            } else {
                System.out.println("Erro! O género deve ser apenas 'M' ou 'F'.");
            }
        }
        return genero;
    }
}
