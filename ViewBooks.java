import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewBooks {

    public static void viewBooks() {

        try {

            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM books";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n========== BOOK DETAILS ==========\n");

            while (rs.next()) {

                System.out.println("Book ID      : " + rs.getInt("book_id"));
                System.out.println("Title        : " + rs.getString("title"));
                System.out.println("Author       : " + rs.getString("author"));
                System.out.println("Price        : " + rs.getDouble("price"));
                System.out.println("Quantity     : " + rs.getInt("quantity"));
                System.out.println("Status       : " + rs.getString("status"));
                System.out.println("---------------------------------------");

            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
