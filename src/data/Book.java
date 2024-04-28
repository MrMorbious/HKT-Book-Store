
package data;

import java.io.Serializable;
import java.util.Scanner;

import manager.ListOfAuthor;
import manager.ListOfBook;

public class Book implements Serializable{
    private String ISBN;
    private String bookID;
    private String title;
    private int price;
    private Author author;
    
    public Book() {
        ISBN = "";
        bookID = "";
        title = "";
        price = 0;
        author = new Author();
    }

    public Book(String ISBN, String bookID, String title, int price, Author author) {
        this.ISBN = ISBN;
        this.bookID = bookID;
        this.title = title;
        this.price = price;
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
    
    public void addBookInf(ListOfAuthor author_external) {
        Scanner sc = new Scanner(System.in);
        boolean stillAdd = true;
        
        System.out.print("Enter book's ISBN: ");
        do {            
            try {
                sc = new Scanner(System.in);
                ISBN = sc.nextLine();
                if (ISBN.isEmpty() || ListOfBook.findBookByISBN(ISBN) != null)
                    throw new Exception();
                stillAdd = false;
            } catch (Exception e) {
                System.out.print("Enter another ISBN: ");
            }
        } while (stillAdd);
        
        System.out.print("Enter book's ID: ");
        do {            
            try {
                sc = new Scanner(System.in);
                bookID = sc.nextLine();
                if (ListOfBook.findBookByID(bookID) != null)
                    throw new Exception("Book's ID already exist!");
                if(bookID.isEmpty())
                    throw new Exception("Book's ID not accept empty!");
                stillAdd = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.print("Enter another book's ID: ");
                stillAdd = true;
            }
        } while (stillAdd);
        
        System.out.print("Enter title: ");
        sc = new Scanner(System.in);
                title = sc.nextLine();
                if (title.isEmpty()) {
                    title = getTitle();
                }
        
        System.out.print("Enter price: ");
        do {
            try {
                sc = new Scanner(System.in);
                price = sc.nextInt();
                if (price <= 0)
                    throw new Exception();
                stillAdd = false;
            } catch (Exception e) {
                System.out.print("Enter another price: ");
                stillAdd = true;
            }
        } while (stillAdd);
        
        author.addAuthorInformation();
        author_external.listAuthor.add(author);
    }

    
    public void printBookInformation() {
        System.out.println("----------------");
        System.out.println("ISBN: " + ISBN);
        System.out.println("ID: " + bookID);
        System.out.println("Title: " + title);
        System.out.println("Price: " + price);
        author.printAuthorInformation();
        System.out.println("----------------");
    }
}
