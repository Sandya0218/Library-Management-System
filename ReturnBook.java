import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Scanner;

public class ReturnBook {

    public static void returnBook() {

        Scanner sc = new Scanner(System.in);

        try {

            Connection con = DBConnection.getConnection();

            System.out.print("Enter Book ID: ");
            int bookId = sc.nextInt();

            // Get due date
            String selectQuery = "SELECT due_date FROM issued_books WHERE book_id=?";
            PreparedStatement ps1 = con.prepareStatement(selectQuery);
            ps1.setInt(1, bookId);

            ResultSet rs = ps1.executeQuery();

            if (rs.next()) {

                LocalDate dueDate = rs.getDate("due_date").toLocalDate();

                // Calculate fine
                double fine = FineCalculator.calculateFine(dueDate);

                // Update books table
                String updateQuery = "UPDATE books SET quantity=quantity+1, status='Available' WHERE book_id=?";
                PreparedStatement ps2 = con.prepareStatement(updateQuery);
                ps2.setInt(1, bookId);
                ps2.executeUpdate();

                // Delete issued record
                String deleteQuery = "DELETE FROM issued_books WHERE book_id=?";
                PreparedStatement ps3 = con.prepareStatement(deleteQuery);
                ps3.setInt(1, bookId);
                ps3.executeUpdate();

                System.out.println("\n==============================");
                System.out.println(" Book Returned Successfully!");
                System.out.println(" Fine Amount : ₹" + fine);
                System.out.println("==============================");

                ps2.close();
                ps3.close();

            } else {

                System.out.println("Book was not issued!");

            }

            rs.close();
            ps1.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}