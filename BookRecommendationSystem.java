import java.sql.*;
import java.util.*;

public class BookRecommendationSystem {
    // MySQL Credentials
    private static final String URL = "jdbc:mysql://localhost:3306/OOPS"; // Change to your DB
    private static final String USER = "root"; // Change to your username
    private static final String PASSWORD = "Password"; // Change to your password

    // Graph Representation (Adjacency List)
    private static Map<String, List<String>> adjacencyList = new HashMap<>();

    public static void main(String[] args) {
        loadBooksFromDatabase();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter previously issued book title:");
        String issuedBook = scanner.nextLine();
        
        List<String> recommendations = getRecommendations(issuedBook);
        
        System.out.println("\nRecommended Books:");
        if (recommendations.isEmpty()) {
            System.out.println("No recommendations found.");
        } else {
            for (String book : recommendations) {
                System.out.println("- " + book);
            }
        }
        
        scanner.close();
    }

    // Load books and build adjacency list (Graph)
    private static void loadBooksFromDatabase() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT Title, Author, Genre, PublicationYear FROM books")) {

            // Store books in adjacency list
            List<String[]> books = new ArrayList<>();
            while (rs.next()) {
                String title = rs.getString("Title");
                String author = rs.getString("Author");
                String genre = rs.getString("Genre");
                int year = rs.getInt("PublicationYear");

                books.add(new String[]{title, author, genre, String.valueOf(year)});
            }

            // Build adjacency list
            for (String[] book1 : books) {
                String title1 = book1[0];
                for (String[] book2 : books) {
                    String title2 = book2[0];

                    if (!title1.equals(title2)) {
                        if (book1[1].equals(book2[1]) || book1[2].equals(book2[2]) || Math.abs(Integer.parseInt(book1[3]) - Integer.parseInt(book2[3])) <= 5) {
                            adjacencyList.computeIfAbsent(title1, k -> new ArrayList<>()).add(title2);
                        }
                    }
                }
            }

            System.out.println("Book graph built successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Recommend books based on issued book
    private static List<String> getRecommendations(String issuedBook) {
        return adjacencyList.getOrDefault(issuedBook, new ArrayList<>());
    }
}
