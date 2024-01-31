package MainApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import DBconnection.connection;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Passwords extends Application{
	
	String emailB;
	
	public Passwords() {}
	public Passwords(String eml) {
		this.emailB=eml;
	}

	connection conx = new connection();

	private TableView<PasswordW> table = new TableView<>();
    private final ObservableList<PasswordW> data = FXCollections.observableArrayList();


	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println(emailB);
		
		initializeDataFromDatabase(emailB);

	    TableColumn<Passwords.PasswordW, String> websiteCol = new TableColumn<>("Website");
	    websiteCol.setMinWidth(240);
	    websiteCol.setCellValueFactory(new PropertyValueFactory<>("Website"));
	    websiteCol.setStyle("-fx-min-height: 28px;");

	    TableColumn<Passwords.PasswordW, String> emailCol = new TableColumn<>("Email");
	    emailCol.setMinWidth(305);
	    emailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
	    emailCol.setStyle("-fx-min-height: 28px;");
	    
	    TableColumn<Passwords.PasswordW, String> passwordCol = new TableColumn<>("Password");
	    passwordCol.setMinWidth(240);
	    passwordCol.setCellValueFactory(new PropertyValueFactory<>("Password"));
	    passwordCol.setStyle("-fx-min-height: 28px;");
	    
	    table.setItems(data);
	    table.getColumns().addAll(websiteCol, emailCol, passwordCol);
	    
	
	
        Image image = new Image("file:/C:/Users/pc/Desktop/Profile1.png"); 
        ImageView Profile = new ImageView(image);
        Profile.setFitWidth(50);  
        Profile.setFitHeight(55);
        Profile.setOnMouseClicked(event -> {
            //System.out.println("Image cliquée !");
            Profile(primaryStage);
        });
        
        
	
        FilteredList<PasswordW> flPassword = new FilteredList(data, p -> true);//Pass the data to a filtered list
        table.setItems(flPassword);//Set the table's items using the filtered list
        //table.getColumns().addAll(websiteCol,emailCol,passwordCol);*/

        //Adding ChoiceBox and TextField here!
        ChoiceBox<String> choiceBox = new ChoiceBox();
        choiceBox.getItems().addAll("Website", "Email");
        choiceBox.setValue("Website");
        choiceBox.setStyle("-fx-min-height: 24px;-fx-font: bold 12 Consolas;");
        //choiceBox.getStyleClass().add("custom-choice-box");
        
        
        TextField textField = new TextField();
        textField.setPromptText("Search here!");
        textField.textProperty().addListener((obs, oldValue, newValue) -> {
           
                   flPassword.setPredicate(p -> p.getWebsite().toLowerCase().contains(newValue.toLowerCase().trim()));//filter table by first name
        });

        choiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)
                -> {//reset table and textfield when new choice is selected
            if (newVal != null) {
                textField.setText("");
            }
        });

        
        TextField Wbsite = new TextField();
        TextField email = new TextField();
        TextField passW = new TextField();

        Wbsite.setPromptText("Website");
        Wbsite.setStyle("-fx-min-height: 35px;");
        email.setPromptText("Email");
        email.setStyle("-fx-min-height: 35px;");
        passW.setPromptText("Password");
        passW.setStyle("-fx-min-height: 35px;");

        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            	PasswordW p = (PasswordW)newValue;

                if (p != null) {
                    Wbsite.setText(p.getWebsite());
                    email.setText(p.getEmail());
                    passW.setText(p.getPassword());
                }
            }
        });
        
        Button addButton = new Button("+Add");
        addButton.setStyle("-fx-min-width: 100px;-fx-min-height: 35px; -fx-font: bold 14 Impact; -fx-text-fill: white; -fx-base: green;-fx-border-radius: 50;");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String Website = Wbsite.getText();
                String Email = email.getText();
                String Pass = passW.getText();
                data.add(new PasswordW(Website,Email,Pass));                            
                try {
                	conx.AddPassword(Website,Email,Pass,emailB);
                } catch (SQLException e1) {
                	e1.printStackTrace();
                }
                Alert Conf= new Alert(AlertType.CONFIRMATION);
                Conf.setContentText("Your Password has been Added successfully.");
                Conf.showAndWait();
                Wbsite.setText(null);
                email.setText(null);
                passW.setText(null); 
            }
    		
          });

        Button deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-min-width: 100px;-fx-min-height: 35px; -fx-font: bold 14 Impact; -fx-text-fill: white; -fx-base:red;-fx-border-radius: 50;");
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PasswordW p = (PasswordW) table.getSelectionModel().getSelectedItem();
                String Website = Wbsite.getText();
                Alert warn= new Alert(AlertType.WARNING);
                warn.setHeaderText("Warning");
                warn.setContentText("Are you certain about deleting this password?");
                warn.showAndWait();
                data.remove(p);
                conx.deletePassword(Website);
                Alert Conf= new Alert(AlertType.CONFIRMATION);
                Conf.setContentText("Your password has been successfully deleted.");
                Conf.showAndWait();
                Wbsite.setText(null);
                email.setText(null);
                passW.setText(null); 
                
            }
        });

        
        Button editButton = new Button("Update");
        editButton.setStyle("-fx-min-width: 100px;-fx-min-height: 35px; -fx-font: bold 14 Impact; -fx-text-fill: white; -fx-base:#3A94D6;-fx-border-radius: 50;");
        editButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	PasswordW selectedPassword = table.getSelectionModel().getSelectedItem();

                if (selectedPassword != null) {
                    String updatedWebsite = Wbsite.getText();
                    String updatedEmail = email.getText();
                    String updatedPassword = passW.getText();

                    selectedPassword.setWebsite(updatedWebsite);
                    selectedPassword.setEmail(updatedEmail);
                    selectedPassword.setPassword(updatedPassword);
                    data.set(data.indexOf(selectedPassword),selectedPassword);
                    try {
                    	System.out.println(emailB);
                        conx.updatePassword(updatedWebsite, updatedPassword);
                        
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    Wbsite.setText(null);
                    email.setText(null);
                    passW.setText(null);

                    Alert confirmation = new Alert(AlertType.CONFIRMATION);
                    confirmation.setContentText("Your password has been successfully updated.");
                    confirmation.showAndWait();
                }
            }
        });
        
        	
        Pane List= new Pane();
        
        List.getChildren().addAll(choiceBox,textField,Profile,table,Wbsite,email,passW,addButton,deleteButton,editButton);
		
		BackgroundFill background_fill = new BackgroundFill(Color.WHITE,CornerRadii.EMPTY, Insets.EMPTY); 
		Background background = new Background(background_fill); 
		List.setBackground(background); 
		
		/*CloseButton.setLayoutX(770);
        CloseButton.setLayoutY(6);
		
        title.setLayoutX(7);
        title.setLayoutY(6);*/
        
        table.setLayoutX(14);
        table.setLayoutY(140);
        
        Wbsite.setLayoutX(14);
        Wbsite.setLayoutY(90);
        
        email.setLayoutX(167);
        email.setLayoutY(90);
        
        passW.setLayoutX(320);
        passW.setLayoutY(90);
        
        addButton.setLayoutX(490);
        addButton.setLayoutY(90);
        
        deleteButton.setLayoutX(595);
        deleteButton.setLayoutY(90);
        
        editButton.setLayoutX(700);
        editButton.setLayoutY(90);
        
        choiceBox.setLayoutX(14);
        choiceBox.setLayoutY(20);
        
        textField.setLayoutX(99);
        textField.setLayoutY(20);
        
        Profile.setLayoutX(755);
        Profile.setLayoutY(5);
        
		Scene scene=new Scene(List);
		primaryStage.setScene(scene);
		//scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		primaryStage.getIcons().add(new Image("file:C:/Users/pc/Desktop/pass.png"));
		//primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setTitle("Password Manager");
		primaryStage.setWidth(830);
		primaryStage.setHeight(590);
		primaryStage.setResizable(false);
		primaryStage.show(); 
		primaryStage.centerOnScreen();
	}
	
	
	
	public static class PasswordW
	{
	    private final SimpleStringProperty Website = new SimpleStringProperty();
	    private final SimpleStringProperty EmailW = new SimpleStringProperty();
	    private final SimpleStringProperty passwordW = new SimpleStringProperty();

	    public PasswordW(String WBsite, String EmlW, String PassW)
	    {
	        this.Website.setValue(WBsite);
	        this.EmailW.setValue(EmlW);
	        this.passwordW.setValue(PassW);
	    }

	    public String getWebsite()
	    {
	        return Website.get();
	    }

	    public void setWebsite(String WBsite)
	    {
	    	Website.set(WBsite);
	    }
	    
	    public SimpleStringProperty getWebsiteProperty()
	    {
	        return Website;
	    }
	  
	    public String getEmail()
	    {
	        return EmailW.get();
	    }

	    public void setEmail(String Eml)
	    {
	    	EmailW.set(Eml);
	    }
	    
	    public SimpleStringProperty getEmailProperty()
	    {
	        return EmailW;
	    }
	    
	    public String getPassword()
	    {
	        return passwordW.get();
	    }

	    public void setPassword(String passW)
	    {
	    	passwordW.set(passW);
	    }
	    
	    public SimpleStringProperty getPasswordProperty()
	    {
	        return passwordW;
	    }
	}


	
	private void initializeDataFromDatabase(String email) throws ClassNotFoundException {  
        Class.forName("com.mysql.cj.jdbc.Driver"); //charge dynamiquement la classe JDBC du pilote MySQL (com.mysql.jdbc.Driver) dans la mémoire de la JVM.

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/passwordmanager","root","Root@2003")) {
            String query = "SELECT * FROM passwords where email=?";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            	preparedStatement.setString(1, email);
                 ResultSet resultSet = preparedStatement.executeQuery(); 
            	
                while (resultSet.next()) {
                    String Website = resultSet.getString("Website");
                    String Email = resultSet.getString("emailused");
                    String Password = resultSet.getString("passwordused");

                    data.addAll(new PasswordW(Website, Email, Password));
                }
            }
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
	
	private void Profile(Stage firstStage) {
		
       Profile prof = new Profile();
       try {
       	prof.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
   }
}
