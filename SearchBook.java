
import java.sql.*;
import java.util.Scanner;

public class SearchBook {

    public static void searchBook() {

        Scanner sc = new Scanner(System.in);

        try {

            Connection con = DBConnection.getConnection();

            System.out.print("Enter Book ID: ");
            int id = sc.nextInt();

            String query = "SELECT * FROM books WHERE book_id=?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("\n===== BOOK FOUND =====");
                System.out.println("Book ID   : " + rs.getInt("book_id"));
                System.out.println("Title     : " + rs.getString("title"));
                System.out.println("Author    : " + rs.getString("author"));
                System.out.println("Price     : " + rs.getDouble("price"));
                System.out.println("Quantity  : " + rs.getInt("quantity"));
                System.out.println("Status    : " + rs.getString("status"));

            } else {

                System.out.println("Book not found.");

            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}