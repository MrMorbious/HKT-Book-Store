
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
    public static boolean checkISBNOfBookExist(String ISBN) {
        for (Book bookInformation : listBook) {
            if (bookInformation.getISBN().equals(ISBN))
                return true;
        }
        return false;
    }
    
    public static Book findBookByID(String bookID) {
        for (Book bookInformation : listBook) {
            if (bookInformation.getBookID().equals(bookID))
                return bookInformation;
        }
        return null;
    }
    public static boolean checkIdOfBookExist(String bookID) {
        for (Book bookInformation : listBook) {
            if (bookInformation.getBookID().equals(bookID))
                return true;
        }
        return false;
    }
    
    public void updateBookByID(String bookID) {
        Scanner sc = new Scanner(System.in);
        boolean stillAdd = true;
        Author author = new Author();
        System.out.println("\n-----> Leave blank to keep current <-----");
        if (findBookByID(bookID) == null)
            System.out.println("Book does not exist!\n");
        else {
            Book bookWillUpdate = findBookByID(bookID);
            System.out.print("Update ISBN: ");
            do {
                try {
                    sc = new Scanner(System.in);
                    String newISBN = sc.nextLine();
                    if (!newISBN.isEmpty()) {
                        if (!newISBN.equals(bookWillUpdate.getISBN()) && checkISBNOfBookExist(newISBN)) {
                            throw new Exception("Book's ISBN already exists!");
                        }
                        bookWillUpdate.setISBN(newISBN);
                    }
                    stillAdd = false;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.print("Enter another book's ISBN: ");
                }
            } while (stillAdd);

            System.out.print("Update ID: ");
            do {
                try {
                    sc = new Scanner(System.in);
                    String newID = sc.nextLine();
                    if (!newID.isEmpty()) {
                        if (!newID.equals(bookWillUpdate.getBookID()) && checkIdOfBookExist(newID)) {
                            throw new Exception("Book's ID already exists!");
                        }
                        bookWillUpdate.setBookID(newID);
                    }
                    stillAdd = false;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.print("Enter another book's ID: ");
                    stillAdd = true;
                }
            } while (stillAdd);

            System.out.print("Update title: ");
            do {
                try {
                    sc = new Scanner(System.in);
                    String newTitle = sc.nextLine();
                    if (!newTitle.isEmpty()) {
                        bookWillUpdate.setTitle(newTitle);
                    }
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
                    String newPriceInput = sc.nextLine();
                    if (!newPriceInput.isEmpty()){
                        try {
                            int newPrice = Integer.parseInt(newPriceInput);
                            if (newPrice <= 0) {
                                throw new Exception("Price must be a positive integer!");
                            }
                            bookWillUpdate.setPrice(newPrice);
                        } catch (NumberFormatException e) {
                            throw new Exception("Invalid price format!");
                        }
                    }
                    stillAdd = false;
                } catch (Exception e) {
                    System.out.print("Enter another price: ");
                    stillAdd = true;
                }
            } while (stillAdd);

            System.out.print("Update name author: ");
            String authorInput = sc.nextLine();
            if (!authorInput.isEmpty()) {
                Author newAuthor = new Author();
                newAuthor.addAuthorInformation();
                bookWillUpdate.setAuthor(newAuthor);
            } else {
                bookWillUpdate.setAuthor(bookWillUpdate.getAuthor());
            }
            System.out.println("");
            System.out.println("Book's information after updated are: ");
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
        } catch (Exception ignored) {
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
                if (id.isEmpty())
                    throw new Exception();
                stillAdd = false;
            } catch (Exception e) {
                System.out.print("Enter another ID: ");
            }
        } while (stillAdd);
        return id;
    }
}
