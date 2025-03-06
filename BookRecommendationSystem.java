import java.sql.*;
import java.util.*;

public class BookRecommendationSystem02 {
    // MySQL connection details
    private static final String URL = "jdbc:mysql://localhost:3306/"; // Change to your database
    private static final String USER = "    "; // Change to your username
    private static final String PASSWORD = "     "; // Change to your password

    // Graph representation using an adjacency list
    private static Map<Integer, List<Integer>> graph = new HashMap<>();
    private static Map<Integer, String> bookTitles = new HashMap<>();

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected to the database!");

            // Load books into graph
            loadBooks(conn);

            // Example: User's previously issued books (Book IDs)
            List<Integer> issuedBooks = Arrays.asList(1, 3); // Change with real issued book IDs

            // Get recommendations
            Set<Integer> recommendations = recommendBooks(issuedBooks);

            System.out.println("Recommended Books:");
            for (int bookId : recommendations) {
                System.out.println(bookTitles.get(bookId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void loadBooks(Connection conn) throws SQLException {
        String query = "SELECT BookID, Title, Author, Genre, Publication FROM books";
        Map<Integer, Integer> bookYears = new HashMap<>(); // Store book years separately
    
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int bookId = rs.getInt("BookID");
                String title = rs.getString("Title");
                String author = rs.getString("Author");
                String genre = rs.getString("Genre");
                int year = rs.getInt("Publication");  // Directly store the year
    
                bookTitles.put(bookId, title);
                bookYears.put(bookId, year);
                graph.putIfAbsent(bookId, new ArrayList<>());
    
                // Connect books based on authors, genre, and publication year
                for (int otherBookId : bookYears.keySet()) {
                    if (bookId != otherBookId) {
                        boolean sameAuthor = bookTitles.get(otherBookId).contains(author);
                        boolean sameGenre = bookTitles.get(otherBookId).contains(genre);
                        boolean closeYear = Math.abs(year - bookYears.get(otherBookId)) <= 5;
    
                        if (sameAuthor || sameGenre || closeYear) {
                            graph.get(bookId).add(otherBookId);
                            graph.get(otherBookId).add(bookId);
                        }
                    }
                }
            }
        }
    }
    
    private static Set<Integer> recommendBooks(List<Integer> issuedBooks) {
        Set<Integer> visited = new HashSet<>(issuedBooks);
        Queue<Integer> queue = new LinkedList<>(issuedBooks);
        Set<Integer> recommendations = new HashSet<>();

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int neighbor : graph.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    recommendations.add(neighbor);
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        return recommendations;
    }
}
