import java.sql.*;
import java.util.Scanner;

public class LogLibrarian {

    static Scanner scanner = new Scanner(System.in);

    public static void logInLib() {
        System.out.println("Hello employee :)");
        System.out.println("Enter your employee ID: ");
        long empId = scanner.nextLong();

        System.out.println("Enter password: ");
        String password = scanner.next();

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library", "root", "")) {

            String query = "SELECT * FROM employees WHERE id = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, empId);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                System.out.println("Logged in as employee.");
                employeeMenu();
            } else {
                System.out.println("Invalid employee credentials.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void employeeMenu() {
        while (true) {
            System.out.println("\n[1] View user data [0] Logout");
            String option = scanner.next();

            if (option.equals("0")) break;
            if (option.equals("1")) {
                System.out.println("Enter user ID: ");
                long userId = scanner.nextLong();
                displayUserInfo(userId);
            }
        }
    }

    private static void displayUserInfo(long userId) {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library", "root", "")) {

            String userQuery = "SELECT * FROM customers WHERE id = ?";
            PreparedStatement userStmt = connection.prepareStatement(userQuery);
            userStmt.setLong(1, userId);
            ResultSet userResult = userStmt.executeQuery();

            if (userResult.next()) {
                System.out.println("User: " + userResult.getString("c_name") + " " + userResult.getString("c_surname"));
                System.out.println("Borrowed books: " + userResult.getInt("number_of_books_borrowed"));

                String bookQuery = "SELECT b.title FROM books b JOIN borrows br ON b.id = br.book_id WHERE br.customer_id = ?";
                PreparedStatement bookStmt = connection.prepareStatement(bookQuery);
                bookStmt.setLong(1, userId);
                ResultSet books = bookStmt.executeQuery();

                System.out.println("Books:");
                while (books.next()) {
                    System.out.println("- " + books.getString("title"));
                }
            } else {
                System.out.println("User not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
