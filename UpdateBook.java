import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class UpdateBook {

    public static void updateBook() {

        Scanner sc = new Scanner(System.in);

        try {

            Connection con = DBConnection.getConnection();

            System.out.print("Enter Book ID to Update: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter New Book Title: ");
            String title = sc.nextLine();

            System.out.print("Enter New Author Name: ");
            String author = sc.nextLine();

            System.out.print("Enter New Quantity: ");
            int quantity = sc.nextInt();

            String query = "UPDATE books SET title=?, author=?, quantity=? WHERE book_id=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, title);
            ps.setString(2, author);
            ps.setInt(3, quantity);
            ps.setInt(4, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Book Updated Successfully!");
            } else {
                System.out.println("Book ID Not Found!");
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
