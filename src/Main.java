import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Library System");
        while (true) {
            System.out.println("Choose: [1] Create account [2] Log in as user [3] Log in as employee [0] Exit");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    Log.welcome("create");
                    break;
                case "2":
                    Log.welcome("logIn");
                    break;
                case "3":
                    Log.welcome("logInLib");
                    break;
                case "0":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
