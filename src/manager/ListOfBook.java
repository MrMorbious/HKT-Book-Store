
package manager;

import data.Author;
import data.Book;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ListOfBook {
    public static ArrayList<Book> listBook = new ArrayList<>();
    
    public boolean addNewBook(Book bookInformation) {
        return listBook.add(bookInformation);
    }
    
    public void printListOfBook() {
        for (Book book : listBook) 
            book.printBookInformation();
    }
    
    public static Book findBookByISBN(String ISBN) {
        for (Book bookInformation : listBook) {
            if (bookInformation.getISBN().equals(ISBN))
                return bookInformation;
        }
        return null;
    }
    
    public static Book findBookByID(String bookID) {
        for (Book bookInformation : listBook) {
            if (bookInformation.getBookID().equals(bookID))
                return bookInformation;
        }
        return null;
    }
    
    public void updateBookByID(String bookID) {
        Scanner sc = new Scanner(System.in);
        boolean stillAdd = true;
        Author author = new Author();
        
        if (findBookByID(bookID) == null)  
            System.out.println("Book does not exist!\n");
        else {
            Book bookWillUpdate = findBookByID(bookID);
            
            System.out.print("Update ISBN: ");
            do {
                try {
                    sc = new Scanner(System.in);
                    bookWillUpdate.setISBN(sc.nextLine());
                    if (bookWillUpdate.getISBN().equals("") && findBookByISBN(bookWillUpdate.getISBN()) != null)
                        throw new Exception();
                    stillAdd = false;
                } catch (Exception e) {
                    System.out.print("Enter another book's ISBN: ");
                    stillAdd = true;
                }
            } while (stillAdd);
            
            System.out.print("Update ID: ");
            do {
                try {
                    sc = new Scanner(System.in);
                    bookWillUpdate.setBookID(sc.nextLine());
                    if (bookWillUpdate.getBookID().equals("") && findBookByID(bookWillUpdate.getBookID()) != null)
                        throw new Exception();
                    stillAdd = false;
                } catch (Exception e) {
                    System.out.print("Enter another book's ID: ");
                    stillAdd = true;
                }
            } while (stillAdd);
            
            System.out.print("Update title: ");
            do {
                try {
                    sc = new Scanner(System.in);
                    bookWillUpdate.setTitle(sc.nextLine());
                    if (bookWillUpdate.getTitle().equals(""))
                        throw new Exception();
                    stillAdd = false;
                } catch (Exception e) {
                    System.out.print("Enter another title: ");
                    stillAdd = true;
                }
            } while (stillAdd);
            
            System.out.print("Update price: ");
            do {
                try {
                    sc = new Scanner(System.in);
                    bookWillUpdate.setPrice(sc.nextInt());
                    if (bookWillUpdate.getPrice() <= 0)
                        throw new Exception();
                    stillAdd = false;
                } catch (Exception e) {
                    System.out.print("Enter another price: ");
                    stillAdd = true;
                }
            } while (stillAdd);
            
            System.out.println("Update author: ");
            author.addAuthorInformation();
            bookWillUpdate.setAuthor(author);
            System.out.println("");
            System.out.println("Book's informations after updated are: ");
            bookWillUpdate.printBookInformation();
            System.out.println("Updated success!");
        }
    }
    
    public boolean deleteBookByID(String bookID) {
        int statusYesNo;
        Book bookInformation = findBookByID(bookID);
        
        if (bookInformation != null) {
            System.out.println("Found book's ID in list.");
            System.out.println("Are you sure to remove the book with this ID?");
            statusYesNo = addStatusYesNo();
            
            if (statusYesNo == 1) {
                listBook.remove(bookInformation);
            return true;
            } else return false;
        } else return false;
    }
    
    public Book findBookByText(String text) {
        for (Book bookInformation : listBook) {
            if (bookInformation.getTitle().contains(text))
                return bookInformation;
        } 
        return null;
    }
    
    public void searchAllBookByText(String text) {
        if (findBookByText(text) == null) System.out.println("No book is matched!");
        else {
            System.out.println("Book that have the text are:");
            for (Book bookInformation : listBook) {
                if (bookInformation.getTitle().contains(text))
                    bookInformation.printBookInformation();
            }
        }
    }
    
    public Book findBookByPrice(int price) {
        if (checkListEmpty()) return null;
        for (Book bookInformation : listBook) {
            if (bookInformation.getPrice() <= price)
                return bookInformation;
        }
        return null;
    }
    
    public boolean checkListEmpty() {
        if (listBook.isEmpty()) {
            System.out.println("No element!");
        return true;
        } else return false;
    }
    
    public void loadDataFromFile() {
        String fileNameOfBook = "book.dat";
        try {
            File f = new File(fileNameOfBook); 
            if (!f.exists()){
                f.createNewFile();
            }
            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream fo = new ObjectInputStream(fi);
            Book b;
            while ((b = (Book)fo.readObject()) != null) {                
                addNewBook(b);
            }
            fo.close();
            fi.close();
        } catch (Exception e) {
        }
    }
    
     public void storeDataToFile() {
        String fileNameOfBook = "book.dat";
        try {
            FileOutputStream f = new FileOutputStream(fileNameOfBook);
            ObjectOutputStream fo = new ObjectOutputStream(f);
            for (Book bookInformation : listBook){
                fo.writeObject(bookInformation);
            }
            fo.close(); 
            f.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Store data to file success!");
    }
    
    public void storeAuthorInformationToFile() {
        String fileNameOfAuthor = "author.dat";
        try {
            FileOutputStream f = new FileOutputStream(fileNameOfAuthor);
            ObjectOutputStream fo = new ObjectOutputStream(f);
            for (Book bookInformation : listBook) 
                fo.writeObject(bookInformation.getAuthor());
            fo.close(); 
            f.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Store author information to file success!");
    }
    
    public int addStatusYesNo() {
        int statusYesNo = 0;
        boolean stillAdd = true;
        Scanner sc = new Scanner(System.in);
        
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.print("Your choice: ");
        do {
            try {
                sc = new Scanner(System.in);
                statusYesNo = sc.nextInt();
                if (statusYesNo != 1 && statusYesNo != 2)
                    throw new Exception();
                stillAdd = false;
            } catch (Exception e) {
                System.out.print("Just choose 1 or 2: ");
                stillAdd = true;
            }
        } while (stillAdd);
        return statusYesNo;
    }
    
    public String addId() {
        boolean stillAdd = true;
        String id = "";
        Scanner sc = new Scanner(System.in);
        do {
            try {
                sc = new Scanner(System.in);
                id = sc.nextLine();
                if (id.equals(""))
                    throw new Exception();
                stillAdd = false;
            } catch (Exception e) {
                System.out.print("Enter another ID: ");
                stillAdd = true;
            }
        } while (stillAdd);
        return id;
    }
}
