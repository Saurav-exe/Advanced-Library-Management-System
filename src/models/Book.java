package models;

public class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private int publicationYear;
    private int totalCopies;
    private int availableCopies;

    public Book(int id, String title, String author, String genre, int publicationYear, int totalCopies, int availableCopies) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public int getPublicationYear() { return publicationYear; }
    public int getTotalCopies() { return totalCopies; }
    public int getAvailableCopies() { return availableCopies; }

    // Setters
    public void setAvailableCopies(int availableCopies) { this.availableCopies = availableCopies; }

    @Override
    public String toString() {
        return title + " by " + author + " (" + genre + ")";
    }
}
