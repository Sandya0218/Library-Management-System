import java.util.Scanner;

public class Menu {

    public static void showMenu() {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n==================================");
            System.out.println("     LIBRARY MANAGEMENT SYSTEM");
            System.out.println("==================================");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Update Book");
            System.out.println("5. Delete Book");
            System.out.println("6. Issue Book");
            System.out.println("7. Return Book");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    AddBook.addBook();
                    break;

                case 2:
                    ViewBooks.viewBooks();
                    break;

                case 3:
                    SearchBook.searchBook();
                    break;

                case 4:
                    UpdateBook.updateBook();
                    break;

                case 5:
                    DeleteBook.deleteBook();
                    break;

                case 6:
                    IssueBook.issueBook();
                    break;

                case 7:
                    ReturnBook.returnBook();
                    break;

                case 8:
                    System.out.println("Thank you for using Library Management System!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}
