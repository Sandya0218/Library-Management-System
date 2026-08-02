public class Book {

    int bookId;
    String title;
    String author;
    int quantity;
    String status;

    public Book(int bookId, String title, String author, int quantity, String status) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
        this.status = status;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }
}
