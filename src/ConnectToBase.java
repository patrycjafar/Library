import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa ConnectToBase zarządza połączeniem z bazą danych oraz (na razie) pobieraniem tytułów książek.
 */
public class ConnectToBase {

    /**
     * Łączy się z bazą danych i pobiera listę tytułów książek.
     *
     * @return Lista tytułów książek znajdujących się w bazie danych
     */
    public static List<String> connect() {
        // Lista na przechowywanie tytułów książek
        List<String> bookTitles = new ArrayList<>();

        try {
            // Ustanawianie połączenia z bazą danych
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/library", // URL bazy danyc
                    "root", // Użytkownik
                    ""); // Hasło

            // Zapytanie SQL do pobrania książek
            String query = "SELECT * FROM books";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Pobieranie wyników i dodawanie tytułów książek do listy
            while (resultSet.next()) {
                bookTitles.add(resultSet.getString("title"));
            }

            // Zamknięcie zasobów
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            // Obsługa błędów SQL
            e.printStackTrace();
        }

        // Zwracanie listy tytułów książek
        return bookTitles;
    }
}
