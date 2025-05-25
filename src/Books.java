import java.util.List;
import java.util.Scanner;
public class Books {

    public static void displayBooks() {
        List<String> books = ConnectToBase.connect();
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            System.out.println("Available books:");
            for (String title : books) {
                System.out.println("- " + title);
            }
        }
    }
}
