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
}
