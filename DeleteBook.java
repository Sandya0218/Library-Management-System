import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class DeleteBook {

    public static void deleteBook() {

        Scanner sc = new Scanner(System.in);

        try {

            Connection con = DBConnection.getConnection();

            System.out.print("Enter Book ID to Delete: ");
            int id = sc.nextInt();

            String query = "DELETE FROM books WHERE book_id=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Book Deleted Successfully!");
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
