
package data;

import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;
import manager.ListOfAuthor;
/**
 *
 * @author PHI
 */
public class Author implements Serializable{
    private String authorID;
    private String name;
    
    public Author(){
        authorID = "";
        name = "";
    }

    public Author(String authorID, String name) {
        this.authorID = authorID;
        this.name = name;
    }

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void addAuthorInformation(){
        Scanner sc = new Scanner(System.in);
        boolean stillAdd = true;
        String nameAuthor = "";
        System.out.print("Add author's ID: ");
        do {            
            try {
                sc = new Scanner(System.in);
                authorID = sc.nextLine();
                if(authorID.isEmpty()) // Luu y
                    throw new Exception("Please enter author's ID.");
                if (ListOfAuthor.findAuthorByID(authorID)){
                    nameAuthor = Objects.requireNonNull(ListOfAuthor.getAuthorByID(authorID)).getName();
                }
                stillAdd = false;
            } catch (Exception e) {
                System.out.println("Add another author's ID: ");
            }
        } while (stillAdd);

        if (!nameAuthor.isEmpty()){
            name = nameAuthor;
        } else {
            System.out.print("Add author's name: ");
            do {
                try {
                    sc = new Scanner(System.in);
                    name = sc.nextLine();
                    if (name.isEmpty()) // Luu y
                        throw new Exception("Please enter author's name");
                    stillAdd = false;
                } catch (Exception e) {
                    System.out.print("Add another name: ");
                    stillAdd = true;
                }
            } while (stillAdd);
        }
    }
    public void printAuthorInformation() {
        System.out.println("Author ID: " + authorID);
        System.out.println("Author name: " + name);
    }
}
