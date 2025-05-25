import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
                break;
            case "logIn":
                Log.logIn();
                break;
            case "logInLib":
                logInLib();
                break;
        }
    }

    /**
     * Proces tworzenia konta użytkownika.
     * Zbiera dane, takie jak imię, nazwisko i hasło, a następnie dodaje użytkownika do bazy danych.
     */
    public static void create() {
        System.out.println("What's your name?");
        String cusName = scanner.next();

        System.out.println("What's your surname?");
        String cusSurname = scanner.next();

        System.out.println("Create a password (at least 7 characters)");
        String password = scanner.next();

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
        long accountId = scanner.nextLong();

        System.out.println("Enter password: ");
        String password = scanner.next();

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library", "root", "")) {

            String query = "SELECT * FROM customers WHERE c_id = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, accountId);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Logged in successfully!");
                userMenu(accountId);
            } else {
                System.out.println("Invalid credentials.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Menu użytkownika po zalogowaniu
     */
    public static void userMenu(long userId) {
        while (true) {
            System.out.println("\n[1] View books [2] Extend return date [0] Logout");
            String option = scanner.next();

            switch (option) {
                case "1":
                    Books.displayBooks();
                    break;
                case "2":
                    System.out.println("Enter book ID to extend return date:");
                    long bookId = scanner.nextLong();
                    extendReturnDate(userId, bookId);
                    break;
                case "0":
                    System.out.println("Logged out.");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    /**
     * Przedłużenie terminu zwrotu książki
     */
    private static void extendReturnDate(long userId, long bookId) {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library", "root", "")) {

            String query = "UPDATE borrows SET return_date = DATE_ADD(return_date, INTERVAL 7 DAY) WHERE customer_id = ? AND book_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            statement.setLong(2, bookId);

            int updated = statement.executeUpdate();

            if (updated > 0) {
                System.out.println("Return date extended by 7 days.");
            } else {
                System.out.println("No matching borrow record found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Dodaje klienta do bazy danych
     */
    private static long addCustomerToDatabase(String name, String surname, String password) {
        long generatedId = -1;

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library", "root", "")) {

            String query = "INSERT INTO customers (c_name, c_surname, password, number_of_books_borrowed) VALUES (?, ?, ?, 0)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, surname);
                preparedStatement.setString(3, password);
                preparedStatement.executeUpdate();

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
