package MainApp;

import MainApp.SignIN;
import MainApp.SignUP;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Profile extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
        
		Label User =new Label("Welcome to Password Manager!");
		User.setStyle("-fx-font: bold 16px Impact;-fx-text-fill:#3A94D6;");
	
		Label Email =new Label("  for the security of your account ,\nplease select one of the functions");
		Email.setStyle("-fx-font: bold 12px Arial;-fx-text-fill:#A6A9AF;");
		
		Button close=new Button("Login In"); //connect
		close.setStyle("-fx-min-width: 200px;-fx-min-height: 50px; -fx-font: bold 15 Consolas; -fx-text-fill: white; -fx-base:#3A94D6;-fx-border-color:#3A94D6;");
		//SignIN.setOnAction(event -> Login(primaryStage));
		
		Button Delete=new Button("Sign Up");  //create in account
		Delete.setStyle("-fx-min-width: 200px;-fx-min-height: 50px; -fx-font: bold 15 Consolas; -fx-text-fill: white; -fx-base: red;");
		//SignUP.setOnAction(event -> Signup(primaryStage));
		
		Image image = new Image("file:/C:/Users/pc/Desktop/Home.png"); 
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);  
        imageView.setFitHeight(100);
        
        
        Pane Home = new Pane();
        
        imageView.setLayoutX(103);
        imageView.setLayoutY(30);
        
        User.setLayoutX(90);
        User.setLayoutY(208);
        
        Email.setLayoutX(96);
        Email.setLayoutY(242);
        
        Delete.setLayoutX(94);
        Delete.setLayoutY(310);
        
        close.setLayoutX(94);
        close.setLayoutY(370);


        Home.getChildren().addAll(imageView);
		
		BackgroundFill background_fill = new BackgroundFill(Color.WHITE,CornerRadii.EMPTY, Insets.EMPTY); 
		Background background = new Background(background_fill); 
		Home.setBackground(background); 
		
		
		Scene scene=new Scene(Home);
		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setTitle("Password Manager");
		primaryStage.setWidth(300);
		primaryStage.setHeight(300);
		primaryStage.setX(1000); // Set the x-coordinate of the window's top left corner
		primaryStage.setY(180);
		primaryStage.setResizable(false);
		primaryStage.show(); 
		
	}

}
