package models;

import java.util.Date;

public class Transaction {
    private int transactionId;
    private int userId;
    private int bookId;
    private Date issueDate;
    private Date returnDate;
    private double fine;

    public Transaction(int transactionId, int userId, int bookId, Date issueDate, Date returnDate, double fine) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.bookId = bookId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.fine = fine;
    }

    // Getters
    public int getTransactionId() { return transactionId; }
    public int getUserId() { return userId; }
    public int getBookId() { return bookId; }
    public Date getIssueDate() { return issueDate; }
    public Date getReturnDate() { return returnDate; }
    public double getFine() { return fine; }

    // Setters
    public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }
    public void setFine(double fine) { this.fine = fine; }

    @Override
    public String toString() {
        return "Transaction ID: " + transactionId + ", Book ID: " + bookId + ", User ID: " + userId;
    }
}
