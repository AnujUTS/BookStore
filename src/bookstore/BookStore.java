package bookstore;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

/**
 *
 * @author Anuj
 */

public class BookStore {    
    private static Scanner scanner = new Scanner(System.in);
    private static String task;
    public static void main(String[] args) {
       
        try (
                Connection con = DBConnection.getConnection();
                Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stmt.executeQuery("SELECT * FROM BOOKS;");
            ){                                 
            
            System.out.println("To use this Book Store Application, enter one of the following: ");
            System.out.println("A - to add a book");
            System.out.println("D - to delete a book");
            System.out.println("F - to filter by price");
            System.out.println("S - to show all books");           
            System.out.println("Q - to quit");
            
            task = scanner.next().toLowerCase();
            
            while (!"q".equals(task)) {
                switch(task) {
                    case "a":
                        Books.addBook(con);
                        askAgain();
                        break;
                    case "d":
                        Books.deleteBook(con);
                        askAgain();
                        break;
                    case "f":
                        Books.filterByPrice(con);
                        askAgain();
                        break;
                    case "s":
                        Books.getBooks(rs);
                        askAgain();
                        break;
                    default:
                        System.out.println("Invalid Input");
                        askAgain();
                        break;
                }
            }
            System.exit(0);            
                        
        }   catch (SQLException ex) {
                System.err.println(ex);
        }   catch (InputMismatchException e){
                System.out.print("Please enter valid input");
                askAgain();
        }
    }
    
    private static void askAgain(){
        System.out.println("What would you like to do next?"); 
        task = scanner.next().toLowerCase();
    }
}
