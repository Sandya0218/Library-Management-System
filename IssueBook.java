import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class IssueBook {

    public static void issueBook() {

        Scanner sc = new Scanner(System.in);

        try {

            Connection con = DBConnection.getConnection();

            System.out.print("Enter Student ID: ");
            String studentId = sc.nextLine();

            System.out.print("Enter Book ID: ");
            int bookId = sc.nextInt();

            String issueQuery = "INSERT INTO issued_books(student_id, book_id, issue_date, due_date, fine) VALUES (?, ?, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 7 DAY), 0)";

            PreparedStatement ps = con.prepareStatement(issueQuery);

            ps.setString(1, studentId);
            ps.setInt(2, bookId);

            int rows = ps.executeUpdate();

System.out.println("Rows inserted = " + rows);
System.out.println("Student ID = " + studentId);
System.out.println("Book ID = " + bookId);
            if (rows > 0) {

                String updateQuery = "UPDATE books SET status='Issued', quantity=quantity-1 WHERE book_id=?";

                PreparedStatement ps2 = con.prepareStatement(updateQuery);

                ps2.setInt(1, bookId);

                ps2.executeUpdate();

                ps2.close();

                System.out.println("Book Issued Successfully!");

            } else {

                System.out.println("Issue Failed!");

            }

            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
