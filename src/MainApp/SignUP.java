package MainApp;

import java.io.IOException;
import java.sql.SQLException;

import DBconnection.connection;
import MainApp.HomePage;
import MainApp.Passwords;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SignUP extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		connection conx = new connection(); //DB connection
        
        
		TextField Name = new TextField ();
		Name.setStyle("-fx-min-height: 35px ;-fx-min-width: 240px;");
		Name.setPromptText("User Name");
		
        Button CloseButton = new Button("X");
        CloseButton.setOnAction(e -> primaryStage.close());
        CloseButton.setStyle("-fx-min-width: 13px;-fx-min-height: 13px;-fx-font: bold 12 Consolas; -fx-text-fill: white; -fx-base: red;-fx-border-color:red;");
	
        Hyperlink RetourButton = new Hyperlink("←");
        RetourButton.setStyle("-fx-font: bold 19 Consolas; -fx-text-fill:#3A94D6;-fx-border:none");
        RetourButton.setOnAction(e->Home(primaryStage));
        
		Image image = new Image("file:/C:/Users/pc/Desktop/PasswordManager_JavaFX/login11.png"); 
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(195);  
        imageView.setFitHeight(145);
        
		TextField Email = new TextField ();
		Email.setStyle("-fx-min-height: 35px ;-fx-min-width: 240px;");
		Email.setPromptText("Email");
		
		PasswordField pass =new PasswordField();
		pass.setStyle("-fx-min-height: 35px ;-fx-min-width: 240px;");
		pass.setPromptText("Password");
		
		Button btn=new Button("Create Account");
		btn.setLayoutX(120);
		btn.setLayoutY(400);
		btn.setStyle("-fx-min-width: 240px;-fx-min-height: 50px; -fx-font: bold 15 Tahoma; -fx-text-fill: white; -fx-base: #3A94D6;fx-border-color:#0A67F4;");
		btn.setOnAction(e -> {
			if ((Email.getText().isEmpty() && pass.getText().isEmpty()) || (Email.getText().isEmpty() || pass.getText().isEmpty()))
			{
				Alert fail= new Alert(AlertType.ERROR);
		        fail.setHeaderText("Error");
		        fail.setContentText("The field cannot be left blank.");
		        fail.showAndWait();
				//labelresponse.setText("The field cannot be left blank. You must enter in a name");
				//Email.setStyle("-fx-min-height: 35px ;-fx-min-width: 240px;-fx-border-color:red;");
				//pass.setStyle("-fx-min-height: 35px ;-fx-min-width: 240px;-fx-border-color:red;");
			}
			
			else {
				Alert Conf= new Alert(AlertType.CONFIRMATION);
				Conf.setHeaderText("Welcome "+Name.getText()+" !");
				Conf.setContentText("Your account has been created successfully.");
				Conf.showAndWait();
	        
				//Connect with database
				try {
					try {
						conx.CreateAccount(Name.getText(),Email.getText(),pass.getText());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			
				//Afficher les passwords
				PasswordsList(primaryStage,Email.getText());
		}
		});
		
		
		Pane root = new Pane();
        
        CloseButton.setLayoutX(380);
        CloseButton.setLayoutY(6);
        
        RetourButton.setLayoutX(6);
        RetourButton.setLayoutY(0);
        
        imageView.setLayoutX(106);
        imageView.setLayoutY(70);
        
        Name.setLayoutX(85);
        Name.setLayoutY(223);
        
        Email.setLayoutX(85);
        Email.setLayoutY(263);
        
        pass.setLayoutX(85);
        pass.setLayoutY(305);
        
        btn.setLayoutX(85);
        btn.setLayoutY(375);

		root.getChildren().addAll(CloseButton ,RetourButton ,imageView,Name,Email ,pass ,btn);
		
		
		Scene scene=new Scene(root);
		primaryStage.setScene(scene);
		
		
		
		BackgroundFill background_fill = new BackgroundFill(Color.WHITE,CornerRadii.EMPTY, Insets.EMPTY); 
		Background background = new Background(background_fill); 
		root.setBackground(background); 
		
		primaryStage.setTitle("Sign In");
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setWidth(410);
		primaryStage.setHeight(500);
		primaryStage.setResizable(false);
		primaryStage.show(); 
		primaryStage.centerOnScreen();
		
		
	}
	
	private void Home(Stage firstStage) {
		
		   firstStage.close();
	       HomePage home = new HomePage();
	       try {
	       	home.start(new Stage());
			} catch (Exception e) {
				e.printStackTrace();
			}
	   }
	
	private void PasswordsList(Stage firstStage,String email) {
		
		  firstStage.close();
	      Passwords passwordsList = new Passwords(email);
	      // Affichez l'interface sign up
	      try {
	      	passwordsList.start(new Stage());
			} catch (Exception e) {
				e.printStackTrace();
			}
	  }
	
	
	public static void main(String[] args) {
		launch(args);
	}

}

/*
package Passwords_Carnet;

import java.sql.SQLException;
import DBconnection.CreateAccount;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SignUP extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        TextField Name = new TextField();
        Name.setStyle("-fx-min-height: 35px ;-fx-min-width: 240px;");
        Name.setPromptText("User Name");

        Button CloseButton = new Button("X");
        CloseButton.setOnAction(e -> primaryStage.close());
        CloseButton.setStyle("-fx-min-width: 10px;-fx-min-height: 10px;-fx-font: bold 9 Consolas; -fx-text-fill: white; -fx-base: red;-fx-border-color:red;");

        Hyperlink RetourButton = new Hyperlink("←");
        RetourButton.setStyle("-fx-font: bold 19 Consolas; -fx-text-fill:#3A94D6;-fx-border:none");
        RetourButton.setOnAction(e -> Home(primaryStage));

        Image image = new Image("file:/C:/Users/pc/Desktop/login11.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(195);
        imageView.setFitHeight(145);

        TextField Email = new TextField();
        Email.setStyle("-fx-min-height: 35px ;-fx-min-width: 240px;");
        Email.setPromptText("Email");

        PasswordField pass = new PasswordField();
        pass.setStyle("-fx-min-height: 35px ;-fx-min-width: 240px;");
        pass.setPromptText("Password");

        Button btn = new Button("Create Account");
        btn.setLayoutX(120);
        btn.setLayoutY(400);
        btn.setStyle("-fx-min-width: 240px;-fx-min-height: 50px; -fx-font: bold 15 Tahoma; -fx-text-fill: white; -fx-base: #3A94D6;fx-border-color:#0A67F4;");

        Pane root = new Pane();

        CloseButton.setLayoutX(382);
        CloseButton.setLayoutY(6);
        RetourButton.setLayoutX(6);
        RetourButton.setLayoutY(0);
        imageView.setLayoutX(106);
        imageView.setLayoutY(70);
        Name.setLayoutX(85);
        Name.setLayoutY(223);
        Email.setLayoutX(85);
        Email.setLayoutY(263);
        pass.setLayoutX(85);
        pass.setLayoutY(305);
        btn.setLayoutX(85);
        btn.setLayoutY(375);

        root.getChildren().addAll(CloseButton, RetourButton, imageView, Name, Email, pass, btn);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        btn.setOnAction(e -> {
            if (Email.getText().isEmpty() || pass.getText().isEmpty()) {
                Alert fail = new Alert(AlertType.ERROR);
                fail.setHeaderText("Error");
                fail.setContentText("The field cannot be left blank.");
                fail.showAndWait();
            } else {
                // Enregistrement des données de l'utilisateur dans la base de données
                CreateAccount createAccount = new CreateAccount();
                try {
                    createAccount.insertUser(Name.getText(), Email.getText(), pass.getText());
                    Alert success = new Alert(AlertType.CONFIRMATION);
                    success.setHeaderText("Welcome " + Name.getText() + " !");
                    success.setContentText("Your account has been created successfully.");
                    success.showAndWait();

                    // ... Reste du code
                } catch (SQLException ex) {
                    Alert error = new Alert(AlertType.ERROR);
                    error.setHeaderText("Error");
                    error.setContentText("Failed to create account. Please try again.");
                    error.showAndWait();
                    ex.printStackTrace();
                }
            }
        });

        BackgroundFill background_fill = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        root.setBackground(background);

        primaryStage.setTitle("Sign In");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setWidth(410);
        primaryStage.setHeight(500);
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }

    private void Home(Stage firstStage) {
        firstStage.close();
        HomePage home = new HomePage();
        try {
            Stage stage = new Stage();
            home.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

*/





