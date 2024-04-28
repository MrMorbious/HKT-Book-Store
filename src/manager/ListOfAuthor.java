
package manager;

import data.Author;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ListOfAuthor {
    public static ArrayList<Author> listAuthor = new ArrayList<>();
    
    public void loadAuthorInformationFromFile() {
        String fileNameOfBook = "author.dat";
        try {
            File f = new File(fileNameOfBook);
            if (!f.exists()) return;
            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream fo = new ObjectInputStream(fi);
            Author a;
            while ((a = (Author)fo.readObject()) != null) {                
                listAuthor.add(a);
            }
            fo.close(); 
            fi.close();
        } catch (Exception ignored) {
        }
    }
    
    public static boolean findAuthorByID(String authorID) {
        for (Author authorInformation : listAuthor) {
            if (authorInformation.getAuthorID().equals(authorID))
                return true;
        }
        return false;
    }

    public static Author getAuthorByID(String authorID) {
        for (Author authorInformation : listAuthor) {
            if (authorInformation.getAuthorID().equals(authorID))
                return authorInformation;
        }
        return null;
    }
    public void storeDataToFile() {
        String fileNameOfAuthor = "author.dat";
        try {
            FileOutputStream f = new FileOutputStream(fileNameOfAuthor);
            ObjectOutputStream fo = new ObjectOutputStream(f);
            
            for (Author authorInformation : listAuthor) 
                fo.writeObject(authorInformation);
            
            fo.close(); 
            f.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
