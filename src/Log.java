public class Log extends LogLibrarian{
    static Main Main = new Main();
    public static void welcome(String logOrCreate){

        switch(logOrCreate){
            case "create":
                Log.create();
                break;//inaczej
            case "logIn":
                Log.logIn();
                break;//inaczej
            case "logInLib":
                logInLib();
                break;
        }
    }
    public static void create(){
        System.out.println("What's your name?");
        System.out.println("What's your surname?");
        System.out.println("Create a password (at least 7 characters)");
        System.out.println("Your id will be: ");
    }
    public static void logIn(){
        System.out.println("Enter your account ID: ");
        System.out.println("Enter password: ");

    }
}
