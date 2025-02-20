### Advanced-Library-Management-System

##FILE STRUCTURE
LibraryManagementSystem/
│── src/
│   ├── models/              # Entity classes (Book, User, Transaction)
│   │   ├── Book.java
│   │   ├── User.java
│   │   ├── Transaction.java
│   ├── services/            # Business logic (GraphRecommendation, FineCalculation, etc.)
│   │   ├── GraphRecommendation.java
│   │   ├── FineCalculator.java
│   │   ├── BookService.java
│   │   ├── UserService.java
│   ├── database/            # Database connection and queries
│   │   ├── DatabaseConnection.java
│   │   ├── BookDAO.java
│   │   ├── UserDAO.java
│   │   ├── TransactionDAO.java
│   ├── gui/                 # Java Swing UI components
│   │   ├── MainFrame.java
│   │   ├── LoginPanel.java
│   │   ├── DashboardPanel.java
│   │   ├── SearchBookPanel.java
│   │   ├── RecommendBookPanel.java
│   ├── main/                # Main application entry point
│   │   ├── LibraryApp.java
│── resources/
│   ├── schema.sql           # MySQL schema for database setup
│   ├── technical_books.csv  # Book data file
│── README.md
