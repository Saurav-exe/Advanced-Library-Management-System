package models;

public class User {
    private int userId;
    private String name;
    private String role; // "Student" or "Librarian"

    public User(int userId, String name, String role) {
        this.userId = userId;
        this.name = name;
        this.role = role;
    }

    // Getters
    public int getUserId() { return userId; }
    public String getName() { return name; }
    public String getRole() { return role; }

    @Override
    public String toString() {
        return name + " (" + role + ")";
    }
}
