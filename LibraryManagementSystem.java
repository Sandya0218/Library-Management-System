import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LibraryManagementSystem extends JFrame {
    // JDBC variables
    private Connection con;
    private PreparedStatement ps;

    // GUI Components
    private JTextField tfBookID, tfTitle, tfAuthor, tfPrice, tfQuantity;
    private JButton btnAdd, btnView, btnUpdate, btnDelete;

    public LibraryManagementSystem() {
        // Frame settings
        setTitle("Library Management System");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 10, 10));

        // Labels & TextFields
        add(new JLabel("Book ID:"));
        tfBookID = new JTextField();
        add(tfBookID);

        add(new JLabel("Title:"));
        tfTitle = new JTextField();
        add(tfTitle);

        add(new JLabel("Author:"));
        tfAuthor = new JTextField();
        add(tfAuthor);

        add(new JLabel("Price:"));
        tfPrice = new JTextField();
        add(tfPrice);

        add(new JLabel("Quantity:"));
        tfQuantity = new JTextField();
        add(tfQuantity);

        // Buttons
        btnAdd = new JButton("Add Book");
        btnView = new JButton("View Books");
        btnUpdate = new JButton("Update Book");
        btnDelete = new JButton("Delete Book");

        add(btnAdd);
        add(btnView);
        add(btnUpdate);
        add(btnDelete);

        // JDBC Connection
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/XEPDB1",
                    "system",
                    "Oracle@123"  
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "DB Connection Failed: " + e.getMessage());
        }

        // Button Actions
        btnAdd.addActionListener(e -> addBook());
        btnView.addActionListener(e -> viewBooks());
        btnUpdate.addActionListener(e -> updateBook());
        btnDelete.addActionListener(e -> deleteBook());

        setVisible(true);
    }

    // Add Book
    private void addBook() {   
        try {
            String sql = "INSERT INTO books VALUES (?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(tfBookID.getText()));
            ps.setString(2, tfTitle.getText());
            ps.setString(3, tfAuthor.getText());
            ps.setDouble(4, Double.parseDouble(tfPrice.getText()));
            ps.setInt(5, Integer.parseInt(tfQuantity.getText()));

            int rows = ps.executeUpdate();
            if (rows > 0) JOptionPane.showMessageDialog(this, "Book Added Successfully!");
        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(this, "Book ID already exists!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    // View Books
    private void viewBooks() {
        try {
            String sql = "SELECT * FROM books";
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("ID: ").append(rs.getInt("book_id"))
                        .append(", Title: ").append(rs.getString("title"))
                        .append(", Author: ").append(rs.getString("author"))
                        .append(", Price: ").append(rs.getDouble("price"))
                        .append(", Quantity: ").append(rs.getInt("quantity"))
                        .append("\n");
            }

            JOptionPane.showMessageDialog(this, sb.length() > 0 ? sb.toString() : "No Books Found");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    // Update Book
    private void updateBook() {
        try {
            String sql = "UPDATE books SET title=?, author=?, price=?, quantity=? WHERE book_id=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, tfTitle.getText());
            ps.setString(2, tfAuthor.getText());
            ps.setDouble(3, Double.parseDouble(tfPrice.getText()));
            ps.setInt(4, Integer.parseInt(tfQuantity.getText()));
            ps.setInt(5, Integer.parseInt(tfBookID.getText()));

            int rows = ps.executeUpdate();
            if (rows > 0) JOptionPane.showMessageDialog(this, "Book Updated Successfully!");
            else JOptionPane.showMessageDialog(this, "Book ID not found!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    // Delete Book
    private void deleteBook() {
        try {
            String sql = "DELETE FROM books WHERE book_id=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(tfBookID.getText()));

            int rows = ps.executeUpdate();
            if (rows > 0) JOptionPane.showMessageDialog(this, "Book Deleted Successfully!");
            else JOptionPane.showMessageDialog(this, "Book ID not found!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    // Main Method
    public static void main(String[] args) {
        new LibraryManagementSystem();
    }
}