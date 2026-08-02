import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class AddBook {

    public static void addBook() {

        Scanner sc = new Scanner(System.in);

        try {

            Connection con = DBConnection.getConnection();

            System.out.print("Enter Book ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Book Title: ");
            String title = sc.nextLine();

            System.out.print("Enter Author Name: ");
            String author = sc.nextLine();

            System.out.print("Enter Quantity: ");
            int quantity = sc.nextInt();
            sc.nextLine();

            String status = "Available";

            String query = "INSERT INTO books(book_id,title,author,quantity,status) VALUES(?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);
            ps.setString(2, title);
            ps.setString(3, author);
            ps.setInt(4, quantity);
            ps.setString(5, status);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Book Added Successfully!");
            } else {
                System.out.println("Failed to Add Book.");
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
