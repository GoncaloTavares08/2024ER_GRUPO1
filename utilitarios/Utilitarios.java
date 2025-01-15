package utilitarios;

public class Utilitarios {

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

    public static boolean eValidoISSN(String issn) {
        if (issn == null) {
            return false;
        }

        issn = issn.replace("-", "");

        if (!issn.matches("\\d{7}[\\dX]")) {
            return false;
        }

        // Cálculo do check-digit para o ISSN
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
