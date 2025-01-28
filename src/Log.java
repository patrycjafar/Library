import java.util.Scanner;

/**
 * Klasa Log dziedziczy po klasie LogLibrarian i zarządza procesami logowania oraz tworzenia konta.
 */
public class Log extends LogLibrarian {

    // Statyczne pole klasy Main do uruchamiania głównej logiki programu
    static Main Main = new Main();

    // Statyczne pole Scanner umożliwiające wczytywanie danych od użytkownika
    static Scanner scanner = new Scanner(System.in);

    /**
     * Wita użytkownika i przekierowuje do odpowiedniej funkcji w zależności od wyboru.
     *
     * @param logOrCreate Określa, czy użytkownik chce się zalogować, czy stworzyć konto
     */
    public static void welcome(String logOrCreate) {
        switch (logOrCreate) {
            case "create":
                Log.create();
                break; // W przypadku tworzenia konta wywołaj metodę create()
            case "logIn":
                Log.logIn();
                break; // W przypadku logowania wywołaj metodę logIn()
            case "logInLib":
                logInLib();
                break; // W przypadku logowania jako bibliotekarz wywołaj metodę logInLib()
        }
    }

    /**
     * Proces tworzenia konta użytkownika.
     * Zbiera dane, takie jak imię, nazwisko i hasło, a następnie generuje identyfikator.
     */
    public static void create() {
        System.out.println("What's your name?");
        String cusName = scanner.next(); // Odczytanie imienia użytkownika

        System.out.println("What's your surname?");
        String cusSurname = scanner.next(); // Odczytanie nazwiska użytkownika

        System.out.println("Create a password (at least 7 characters)");
        String password = scanner.next(); // Odczytanie hasła użytkownika

        // Tworzenie pełnego imienia i nazwiska
        String fullName = cusName + cusSurname;

        // Generowanie unikalnego identyfikatora na podstawie hasła
        int hash = Math.abs(fullName.hashCode()); // Pobierz wartość dodatnią z hasha pełnego imienia i nazwiska
        String id = String.valueOf(hash).substring(0, Math.min(5, String.valueOf(hash).length())); // Maksymalnie 5 znaków z hasha

        System.out.println("Your ID will be: " + id); // Wyświetlenie identyfikatora użytkownika
    }

    /**
     * Proces logowania użytkownika na podstawie jego ID i hasła.
     */
    public static void logIn() {
        System.out.println("Enter your account ID: ");
        String accountId = scanner.next(); // Odczytanie ID konta użytkownika

        System.out.println("Enter password: ");
        String password = scanner.next(); // Odczytanie hasła użytkownika
    }
}
