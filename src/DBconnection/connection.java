package DBconnection;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class connection {

	private static final String INSERT_USER_SQL = "INSERT INTO utilisateurs (UserName, email, password) VALUES (?, ?, ?)";
	private static final String Verify_USER_SQL = "SELECT * FROM utilisateurs WHERE email = ? AND password = ?";
	private static final String Drop_USER_SQL = "INSERT INTO utilisateurs (UserName, email, password) VALUES (?, ?, ?)";
	
	private static final String Modify_Password_SQL = "UPDATE passwords SET passwordused=? WHERE website=?";
	private static final String Add_Password_SQL = "INSERT INTO passwords (Website, emailused ,passwordused,email) VALUES (?, ?, ?,?)";
	private static final String Delet_Password_SQL = "DELETE FROM passwords WHERE Website = ?";
	
	 Connection Conx = null;
     PreparedStatement stmt = null;

    public void CreateAccount(String UserName, String email, String password) throws SQLException, IOException {
       
        try {
        	Properties inf = new Properties();
        	//FileReader reader = new FileReader("app.properties");
        	//InputStream reader = getClass().getResourceAsStream("app.properties");
        	//FileInputStream reader = new FileInputStream(new File("/Passwords_Manager/resources/app.properties"));
        	inf.load(connection.class.getClassLoader().getResourceAsStream("app.properties"));
        	
        	
            //Conx = DriverManager.getConnection("jdbc:mysql://localhost:3306/passwordmanager","root","Root@2003");
        	Conx = DriverManager.getConnection(inf.getProperty("mysql"),inf.getProperty("user"),inf.getProperty("password"));
        	
            stmt = Conx.prepareStatement(INSERT_USER_SQL);
            stmt.setString(1, UserName);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.executeUpdate();
            System.out.println("Données insérées avec succès");
            //System.out.println("La la mabritch :)");
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            // Fermer les ressources JDBC
            if (stmt != null) {
                stmt.close();
            }
            if (Conx != null) {
                Conx.close();
            }
        }
    }
    
    
    public void VerifyUser(String email, String password) throws SQLException {

        try {
            Conx = DriverManager.getConnection("jdbc:mysql://localhost:3306/passwordmanager","root","Root@2003");
            stmt = Conx.prepareStatement(Verify_USER_SQL);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                //Compare the provided password with the hashed password stored in the database
                String hashedPassword = rs.getString("password"); // Assuming the password is stored as a hash in the database   
                String hashedEmail = rs.getString("email");
              
                if ((password.equals(hashedPassword)) && (email.equals(hashedEmail))) {
                	Alert succ= new Alert(AlertType.CONFIRMATION);
			        succ.setContentText("Successful sign-in!"); 
			        succ.showAndWait();
			        
                } else if(((password.equals(hashedPassword)==false)&&(email.equals(hashedEmail))) || ((password.equals(hashedPassword)) && (email.equals(hashedEmail)==false))) { 
                	Alert fail= new Alert(AlertType.ERROR);
                	fail.setHeaderText("Error");
			        fail.setContentText("Invalid information!");
			        fail.showAndWait();
                }
            } else {
            	Alert fail= new Alert(AlertType.ERROR);
            	fail.setHeaderText("Error");
		        fail.setContentText("User does not exist! ,If you don't have an account, go back to the home page and choose Sign Up to create your account.");
		        fail.showAndWait();
            }
            
            rs.close();
            stmt.close();
            Conx.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    
    
    public void deletePassword(String Website) { 
    	try {
    		Connection Conx = DriverManager.getConnection("jdbc:mysql://localhost:3306/passwordmanager","root","Root@2003");
    		PreparedStatement stmt = Conx.prepareStatement(Delet_Password_SQL);
    		stmt.setString(1, Website);
    		stmt.executeUpdate();
    		System.out.println("Password supprimée avec succè");
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
   
 }

    
    public void AddPassword(String Website, String Emailused, String passused ,String email) throws SQLException {
        try {
            Conx = DriverManager.getConnection("jdbc:mysql://localhost:3306/passwordmanager","root","Root@2003");
            stmt = Conx.prepareStatement(Add_Password_SQL);
            stmt.setString(1, Website);
            stmt.setString(2, Emailused);
            stmt.setString(3, passused);
            stmt.setString(4, email);
            stmt.executeUpdate();
            System.out.println("Password insérée avec succè");
            //System.out.println("La la mabritch :)");
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            // Fermer les ressources JDBC
            if (stmt != null) {
                stmt.close();
            }
           
        }
    }
    
    
    public void updatePassword(String WebsiteN,String passusedN) throws SQLException {
        try {
            Conx = DriverManager.getConnection("jdbc:mysql://localhost:3306/passwordmanager","root","Root@2003");
            stmt = Conx.prepareStatement(Modify_Password_SQL);
         // Assigner les valeurs paramétrées
            stmt.setString(1, WebsiteN);
            stmt.setString(2, passusedN);
          
            // Exécuter la requête UPDATE
            int rowsAffected =  stmt.executeUpdate();

            System.out.println(rowsAffected + " enregistrement(s) modifié(s).");
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } 
    }
    
    
    
}
