
import java.sql.*;
import java.util.*;

public class BookRecommendationSystem03 {
    // MySQL connection details
    private static final String URL = "jdbc:mysql://localhost:3306/OOPS"; // Change to your database
    private static final String USER = "root"; // Change to your username
    private static final String PASSWORD = "Password"; // Change to your password

    // Graph representation using an adjacency list
    private static Map<Integer, List<Integer>> graph = new HashMap<>();
    private static Map<Integer, String> bookTitles = new HashMap<>();

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Scanner scanner = new Scanner(System.in)) {
            System.out.println("Connected to the database!");

            // Load books into the graph
            loadBooks(conn);

            // Ask user for previously issued book IDs
            System.out.print("Enter previously issued book IDs (comma-separated): ");
            String input = scanner.nextLine();
            List<Integer> issuedBooks = parseInput(input);

            // Get recommendations
            List<Integer> recommendations = recommendBooks(issuedBooks);

            // Display recommendations
            if (recommendations.isEmpty()) {
                System.out.println("No recommendations found.");
            } else {
                System.out.println("\nTop 5 Recommended Books:");
                for (int bookId : recommendations) {
                    System.out.println("- " + bookTitles.get(bookId));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Load books and build the adjacency list
    private static void loadBooks(Connection conn) throws SQLException {
        String query = "SELECT BookID, Title, Author, Genre, Publication FROM books";
        Map<Integer, String> bookAuthors = new HashMap<>();
        Map<Integer, String> bookGenres = new HashMap<>();
        Map<Integer, Integer> bookYears = new HashMap<>();

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int bookId = rs.getInt("BookID");
                String title = rs.getString("Title");
                String author = rs.getString("Author");
                String genre = rs.getString("Genre");
                int year = rs.getInt("Publication");

                bookTitles.put(bookId, title);
                bookAuthors.put(bookId, author);
                bookGenres.put(bookId, genre);
                bookYears.put(bookId, year);
                graph.putIfAbsent(bookId, new ArrayList<>());
            }
        }

        // Build  graph
        for (int book1 : bookTitles.keySet()) {
            for (int book2 : bookTitles.keySet()) {
                if (book1 != book2) {
                    boolean isConnected = bookAuthors.get(book1).equals(bookAuthors.get(book2)) ||
                                          bookGenres.get(book1).equals(bookGenres.get(book2)) ||
                                          Math.abs(bookYears.get(book1) - bookYears.get(book2)) <= 5;
                    if (isConnected) {
                        graph.get(book1).add(book2);
                        graph.get(book2).add(book1);
                    }
                }
            }
        }
    }

    // Recommend books based on previously issued ones
    private static List<Integer> recommendBooks(List<Integer> issuedBooks) {
        Map<Integer, Integer> frequency = new HashMap<>();
        Set<Integer> visited = new HashSet<>(issuedBooks);
        Queue<Integer> queue = new LinkedList<>(issuedBooks);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int neighbor : graph.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    frequency.put(neighbor, frequency.getOrDefault(neighbor, 0) + 1);
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        // Sort books by highest connection frequency and return the top 5
        return frequency.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue())) // Sort in descending order
                .limit(5)
                .map(Map.Entry::getKey)
                .toList();
    }

    // Parse user input into a list of book IDs
    private static List<Integer> parseInput(String input) {
        List<Integer> bookIds = new ArrayList<>();
        String[] tokens = input.split(",");
        for (String token : tokens) {
            try {
                bookIds.add(Integer.parseInt(token.trim()));
            } catch (NumberFormatException e) {
                System.out.println("Invalid book ID: " + token.trim());
            }
        }
        return bookIds;
    }
}

    

