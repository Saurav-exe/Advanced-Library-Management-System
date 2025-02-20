# Advanced Library Management System

This project implements an advanced Library Management System using Java, Java Swing for the GUI, and MySQL for database persistence.  It incorporates features like book recommendations based on user borrowing history, fine calculation, and efficient data management.

## Features

* **User Authentication:** Secure login for librarians and members.
* **Book Management:** Add, update, delete, and search books.
* **Member Management:** Add, update, and delete members.
* **Borrowing and Returning:** Track book borrowing and return transactions.
* **Book Recommendations:** Suggest books to users based on their past borrowing history using a graph-based recommendation algorithm.
* **Fine Calculation:** Automatically calculate fines for overdue books.
* **Search Functionality:** Search books by title, author, ISBN, or other criteria.
* **Reporting:** Generate reports on book availability, borrowing trends, and overdue books (future implementation).
* **User-Friendly GUI:**  Intuitive interface for easy navigation and interaction.

## File Structure

*LibraryManagementSystem/
*├── src/
*│   ├── models/           # Entity classes (Book, User, Transaction)
*│   │   ├── Book.java
*│   │   ├── User.java
*│   │   ├── Transaction.java
*│   ├── services/         # Business logic (GraphRecommendation, FineCalculation, etc.)
*│   │   ├── GraphRecommendation.java
*│   │   ├── FineCalculator.java
*│   │   ├── BookService.java
*│   │   ├── UserService.java
*│   ├── database/         # Database connection and queries
*│   │   ├── DatabaseConnection.java
*│   │   ├── BookDAO.java
*│   │   ├── UserDAO.java
*│   │   ├── TransactionDAO.java
*│   ├── gui/              # Java Swing UI components
*│   │   ├── MainFrame.java
*│   │   ├── LoginPanel.java
*│   │   ├── DashboardPanel.java
*│   │   ├── SearchBookPanel.java
*│   │   ├── RecommendBookPanel.java
*│   ├── main/             # Main application entry point
*│   │   ├── LibraryApp.java
*├── resources/
*│   ├── schema.sql        # MySQL schema for database setup
*│   ├── technical_books.csv # Book data file (example)
*├── README.md*/
