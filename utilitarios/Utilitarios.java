package utilitarios;
/**
 * A classe Utilitarios fornece métodos utilitários para validação de códigos ISBN-10/13 e ISSN.
 * @author [Grupo1]
 * @version 1.0
 */
public class Utilitarios {
    /**
     * Verifica se um ISBN é válido.
     *
     * @param isbn O ISBN a ser verificado. Pode ser um ISBN-10 ou ISBN-13.
     * @return True se o ISBN for válido, false caso contrário.
     */
    public static boolean eValidoISBN(String isbn) {
        if (isbn == null) {
            return false;
        }

        isbn = isbn.replace("-", "");

        if (isbn.length() == 10) {
            return eValidoISBN10(isbn);
        } else if (isbn.length() == 13) {
            return isValidISBN13(isbn);
        }

        return false;
    }
    /**
     * Verifica se um ISBN-10 é válido.
     *
     * @param isbn O ISBN-10 a ser verificado.
     * @return True se o ISBN-10 for válido, false caso contrário.
     */
    private static boolean eValidoISBN10(String isbn) {
        if (!isbn.matches("\\d{9}[\\dX]")) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (isbn.charAt(i) - '0') * (10 - i);
        }

        char checkDigit = isbn.charAt(9);
        sum += (checkDigit == 'X') ? 10 : (checkDigit - '0');

        return sum % 11 == 0;
    }
    /**
     * Verifica se um ISBN-13 é válido.
     *
     * @param isbn O ISBN-13 a ser verificado.
     * @return True se o ISBN-13 for válido, false caso contrário.
     */
    private static boolean isValidISBN13(String isbn) {
        if (!isbn.matches("\\d{13}")) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int digit = isbn.charAt(i) - '0';
            sum += (i % 2 == 0) ? digit : digit * 3;
        }

        int checkDigit = 10 - (sum % 10);
        if (checkDigit == 10) {
            checkDigit = 0;
        }

        return checkDigit == (isbn.charAt(12) - '0');
    }
    /**
     * Verifica se um ISSN é válido.
     *
     * @param issn O ISSN a ser verificado.
     * @return True se o ISSN for válido, false caso contrário.
     */
    public static boolean eValidoISSN(String issn) {
        if (issn == null) {
            return false;
        }

        issn = issn.replace("-", "");

        if (!issn.matches("\\d{7}[\\dX]")) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 7; i++) {
            sum += (8 - i) * (issn.charAt(i) - '0');
        }

        char checkDigit = issn.charAt(7);
        int expectedCheckDigit = (11 - (sum % 11)) % 11;
        int actualCheckDigit = (checkDigit == 'X') ? 10 : (checkDigit - '0');

        return expectedCheckDigit == actualCheckDigit;
    }

}
