package bookstore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author Anuj
 */
public class Books {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void addBook(Connection con) throws SQLException, InputMismatchException {
        String sql = "INSERT INTO BOOKS (name, author, price)"
                + "VALUES (?, ?, ?)";
        System.out.println("Name of book: ");
        String name = scanner.nextLine();        
        System.out.println("Author of book: ");
        String author = scanner.nextLine();
        System.out.println("Price of book: ");
        double price = scanner.nextDouble();
        
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setString(2, author);
        stmt.setDouble(3, price);
        stmt.execute();
    }
    
    public static void deleteBook(Connection con) throws SQLException {
        String sql = "DELETE FROM BOOKS WHERE name = ?";
                
        System.out.println("Which book would you like to delete?");
        String name = scanner.nextLine();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.execute();
        System.out.println("Deleted " + name);
    }
    
    public static void getBooks(ResultSet rs) throws SQLException {
        while (rs.next()) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("Name: " + rs.getString("name") + "  ");
            buffer.append("Price: " + rs.getDouble("price") + "  ");
            buffer.append("Author: " + rs.getString("author"));
            System.out.println(buffer.toString());
        }
        rs.beforeFirst();
    }
    
    public static void filterByPrice(Connection con) throws SQLException, InputMismatchException {
        String sql = "SELECT * FROM Books WHERE price <= ?";
        
        System.out.println("What is the maximum price?");        
        double price = scanner.nextDouble();
        PreparedStatement stmt = con.prepareStatement(sql, 
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt.setDouble(1, price);
        stmt.execute();
        ResultSet rs = stmt.executeQuery();
        getBooks(rs);        
    }
    
    // use UPDATE to rename tags
}
