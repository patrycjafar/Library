public class Employee {
    private String name;
    private String surname;
    private String position;

    public Employee(String name, String surname, String position) {
        this.name = name;
        this.surname = surname;
        this.position = position;
    }

    // Gettery
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPosition() {
        return position;
    }
}

