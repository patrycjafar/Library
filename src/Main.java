import java.util.Scanner;
/**
 * Główna klasa aplikacji
 */
public class Main {
/**
 * Główna metoda programu.
 */
 public static void main(String[] args) {
     // Tworzenie obiektu scanner do odczytu danych wejściowych
     Scanner scanner = new Scanner(System.in);

     // Tworzenie obiektu Log, który obsługuje logowanie i tworzenie konta
     Log Log = new Log();

     // Powitanie użytkownika
     System.out.println("Welcome to the online library");

     // Zapytanie użytkownika o wybór akcji
     System.out.println("Do you want to log in or create an account?");

     // Odczytanie odpowiedzi użytkownika
     String logOrCreate = scanner.next();

     // Wywołanie metody welcome obiektu Log na podstawie odpowiedzi użytkownika
     Log.welcome(logOrCreate);

    }
}