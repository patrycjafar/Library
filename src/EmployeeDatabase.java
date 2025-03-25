import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDatabase {

    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static long addEmployee(String name, String surname) {
        String query = "INSERT INTO employees (w_name, w_surname) VALUES (?, ?)";
        long generatedId = -1;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.executeUpdate(); // Wykonanie zapytania

            // Pobranie wygenerowanego ID
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getLong(1); // Pobranie ID
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generatedId; // Zwróć wygenerowane ID
    }
}