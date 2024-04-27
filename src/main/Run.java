
package main;

import data.Book;
import java.sql.ResultSet;
import java.util.Scanner;
import manager.ListOfAuthor;
import manager.ListOfBook;


/**
 *
 * @author PHI
 */
public class Run {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        boolean stillAdd = true;
        
        ListOfBook list = new ListOfBook();
        ListOfAuthor list1 = new ListOfAuthor();
        list1.loadAuthorInformationFromFile();
        list.loadDataFromFile();
        do {            
            System.out.println("\n");
            System.out.println("============Welcome to HKT Book Store============");
            System.out.println("1. Print out all of book");
            System.out.println("2. Add new book");
            System.out.println("3. Update book");
            System.out.println("4. Delete book");
            System.out.println("5. Search book");
            System.out.println("6. Store data to file");
            System.out.println("7. Quit");
            System.out.println("=================================================");
            System.out.print("\nYour choice is: ");
            
            do {                
                try {
                    sc = new Scanner(System.in);
                    choice = sc.nextInt();
                    if(choice <= 0 || choice > 7)
                        throw new Exception();
                    stillAdd = false;
                } catch (Exception e) {
                    System.out.print("Enter another choice: ");
                    stillAdd = true;
                }
            } while (stillAdd);
            
            switch(choice) {
                case 1: // Show data from file to screen
                    if (list.checkListEmpty()) {
                    } else list.printListOfBook();
                    break;
                case 2: //Add new book
                    int statusYesNo;
                    
                    Book bookInformation = new Book();
                    bookInformation.addBookInf(list1);
                    
                    if(list.addNewBook(bookInformation)){
                        System.out.println("Added success !");
                        System.out.println("Continue adding another book ?");
                        statusYesNo = list.addStatusYesNo();
                        
                        while (statusYesNo == 1){
                            Book bookInformation1 = new Book();
                            bookInformation1.addBookInf(list1);
                            if(list.addNewBook(bookInformation1)){
                                System.out.println("Added success !");
                                System.out.println("Continue adding another book ?");
                                statusYesNo = list.addStatusYesNo();
                            }
                        }
                    } else System.out.println("Added fail !");
                    break;
                case 3: //Update book
                    int statusYesNo1;

                    if(list.checkListEmpty()){
                        
                    } else {
                        String IDWantUpdate = "";
                        
                        System.out.print("Enter ID of book that you want to update: ");
                        IDWantUpdate = list.addId();

                        list.updateBookByID(IDWantUpdate);
                        
                        System.out.println("Continue updating another book ?");
                        statusYesNo1 = list.addStatusYesNo();
                        
                        while (statusYesNo1 == 1) {                            
                            System.out.print("Enter ID of book that you want to update: ");
                            IDWantUpdate = list.addId();

                            list.updateBookByID(IDWantUpdate);

                            System.out.println("Continue updating another book?");
                            statusYesNo1 = list.addStatusYesNo();
                        }
                    }
                    break;
                case 4: //Delete book
                    int statusYesNo2;
                    
                    if (list.checkListEmpty()) {
                    } else {
                        String IDWantDelete = "";
                        System.out.print("Enter ID of book that you want to delete: ");
                        IDWantDelete = list.addId();
                        
                        if (list.deleteBookByID(IDWantDelete)) {
                            System.out.println("Deleted success!");
                            
                            System.out.println("Continue deleting another book?");
                            statusYesNo2 =  list.addStatusYesNo();

                            if (list.checkListEmpty()) {
                            } else {
                                while (statusYesNo2 == 1) {                            
                                    System.out.print("Enter ID of book that you want to delete: ");
                                    IDWantDelete = list.addId();

                                    if (list.deleteBookByID(IDWantDelete)) {
                                        System.out.println("Deleted success!");
                                        System.out.println("Continue deleting another book?");
                                        statusYesNo2 = list.addStatusYesNo();
                                    } else System.out.println("Deleted fail!");
                                }
                            }
                        }
                        else System.out.println("Not Found book's ID in list.\n---->>> Deleted fail!");
                    }
                    break;
                case 5: //Search book
                    if (list.checkListEmpty()) {
                    } else {
                        String text = "";
                        int statusYesNo3;

                        System.out.print("Enter text you want to search from the name of the book that contains: ");
                        do {
                            try {
                                sc = new Scanner(System.in);
                                text = sc.nextLine();
                                if (text.isEmpty())
                                    throw new Exception();
                                stillAdd = false;
                            } catch (Exception e) {
                                System.out.print("Enter another text: ");
                                stillAdd = true;
                            }
                        } while (stillAdd);

                        list.searchAllBookByText(text);
                        
                        System.out.println("Continue searching another book?");
                        statusYesNo3 = list.addStatusYesNo();
                        
                        while (statusYesNo3  == 1) {                            
                            System.out.print("Enter text you want to search from the name of the book that contains: ");
                            do {
                                try {
                                    sc = new Scanner(System.in);
                                    text = sc.nextLine();
                                    if (text.isEmpty())
                                        throw new Exception();
                                    stillAdd = false;
                                } catch (Exception e) {
                                    System.out.print("Enter another text: ");
                                    stillAdd = true;
                                }
                            } while (stillAdd);

                            list.searchAllBookByText(text);
        
                            System.out.println("Continue searching another book?");
                            statusYesNo3 = list.addStatusYesNo();
                        }
                    }
                    break;
                    
                case 6: //Store data to file
                    if (list.checkListEmpty()) {
                    } else {
                        list.storeDataToFile();
                        list1.storeDataToFile();
                    }
                    break;
                    
                case 7: //Quit
                    System.out.println("Quit menu. See you later !");
                    break;
                    
            }
            
        } while (choice > 0 && choice < 7);
    }
    
}
