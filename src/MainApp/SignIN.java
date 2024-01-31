package MainApp;

import java.sql.SQLException;
import DBconnection.connection;
import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class SignIN extends Application{
		

		@Override
		public void start(Stage primaryStage) throws Exception {
			
			/*Image Icon = new Image("file:C:\\Users\\pc\\Desktop\\Password_Python\\icon.ico");
	        primaryStage.getIcons().add(Icon);*/
	        
			
			connection conx = new connection(); //DB connection
			
			
	        Button CloseButton = new Button("X");
	        CloseButton.setOnAction(e -> primaryStage.close());
	        CloseButton.setStyle("-fx-min-width: 13px;-fx-min-height: 13px;-fx-font: bold 12 Consolas; -fx-text-fill: white; -fx-base: red;-fx-border-color:red;");
	        
	        Hyperlink RetourButton = new Hyperlink("←");
	        RetourButton.setStyle("-fx-font: bold 19 Consolas; -fx-text-fill:#3A94D6;");
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
			
			Button btn=new Button("Log In");
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
					//Email.setStyle("-fx-min-height: 35px ;-fx-min-width: 240px;-fx-border-color:red;");
					//pass.setStyle("-fx-min-height: 35px ;-fx-min-width: 240px;-fx-border-color:red;");
				}
				
				else {
					try {
						conx.VerifyUser(Email.getText(),pass.getText());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					PasswordsList(primaryStage,Email.getText());
			}
			});
		

			Pane root = new Pane();
	        
	        CloseButton.setLayoutX(379);
	        CloseButton.setLayoutY(6);
	        
	        RetourButton.setLayoutX(6);
	        RetourButton.setLayoutY(0);
	        
	        imageView.setLayoutX(106);
	        imageView.setLayoutY(83);
	       
	        Email.setLayoutX(85);
	        Email.setLayoutY(248);
	        
	        pass.setLayoutX(85);
	        pass.setLayoutY(290);
	        
	        btn.setLayoutX(85);
	        btn.setLayoutY(370);
			
	
			root.getChildren().addAll(CloseButton,RetourButton,imageView,Email ,pass ,btn);
			
			
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
		private void PasswordsList(Stage firstStage,String emailuser) {
			
		  firstStage.close();
	      Passwords passwordsList = new Passwords(emailuser);
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



