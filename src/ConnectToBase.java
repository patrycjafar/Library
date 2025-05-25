import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa do łączenia się z bazą danych i pobierania danych z tabeli books.
 */
public class ConnectToBase {

    /**
     * Łączy się z bazą danych i pobiera listę tytułów książek.
     *
     * @return lista tytułów książek dostępnych w bazie danych
     */
    public static List<String> connect() {
        List<String> titles = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library", "root", "")) {

            String query = "SELECT title FROM books";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                titles.add(resultSet.getString("title"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return titles;
    }
}
