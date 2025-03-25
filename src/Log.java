import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
     * Zbiera dane, takie jak imię, nazwisko i hasło, a następnie dodaje użytkownika do bazy danych.
     */
    public static void create() {
        System.out.println("What's your name?");
        String cusName = scanner.next(); // Odczytanie imienia użytkownika

        System.out.println("What's your surname?");
        String cusSurname = scanner.next(); // Odczytanie nazwiska użytkownika

        System.out.println("Create a password (at least 7 characters)");
        String password = scanner.next(); // Odczytanie hasła użytkownika

        // Dodawanie rekordu do bazy danych
        long id = addCustomerToDatabase(cusName, cusSurname, password);
        if (id != -1) {
            System.out.println("Customer added to the database successfully.");
        } else {
            System.out.println("Failed to add customer.");
        }
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

    private static long addCustomerToDatabase(String name, String surname, String password) {
        long generatedId = -1;

        // Ustanawianie połączenia z bazą danych
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library", // URL bazy danych
                "root", // Użytkownik
                "" // Hasło
        )) {
            // Zapytanie SQL do dodania klienta
            String query = "INSERT INTO customers (c_name, c_surname, password, number_of_books_borrowed) VALUES (?, ?, ?, 0)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, surname);
                preparedStatement.setString(3, password);
                preparedStatement.executeUpdate();

                // Pobranie wygenerowanego ID
                try (var generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getLong(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generatedId;
    }
}