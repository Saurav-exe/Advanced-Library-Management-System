import pymysql
import csv

# Database connection details
DB_HOST = "localhost"
DB_USER = "root"
DB_PASSWORD = " " #Enter Your password here
DB_NAME = "OOPS" #Create a Database called OOPS or Change DB_NAME to desired 
TABLE_NAME = "books" #Create a Table called books or Change Table_name to desired
CSV_FILE = r" " #Enter the path of your file


connection = pymysql.connect(
    host=DB_HOST,
    user=DB_USER,
    password=DB_PASSWORD,
    database=DB_NAME
)

cursor = connection.cursor()


with open(CSV_FILE, "r", encoding="utf-8") as file:
    csv_reader = csv.reader(file)
    headers = next(csv_reader)  

    # Construct the INSERT query dynamically
    placeholders = ", ".join(["%s"] * len(headers))
    columns = ", ".join(headers)
    insert_query = f"INSERT INTO {TABLE_NAME} (BookID, Title, Author, Genre, Publication, Total_Copies, Available_Copies) VALUES ({placeholders})"



    for row in csv_reader:
        cursor.execute(insert_query, row)


connection.commit()
cursor.close()
connection.close()

print("CSV file uploaded successfully!")
