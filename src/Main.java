import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Log Log = new Log();
        System.out.println("Welcome to the online library");
        System.out.println("Do you want to log in or create an account?");

        String logOrCreate = scanner.next();
        Log.welcome(logOrCreate);


    }
}