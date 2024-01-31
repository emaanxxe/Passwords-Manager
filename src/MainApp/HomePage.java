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

public class HomePage extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		Button CloseButton = new Button("X");
        CloseButton.setOnAction(e -> primaryStage.close());
        CloseButton.setStyle("-fx-min-width: 13px;-fx-min-height: 13px;-fx-font: bold 12 Consolas; -fx-text-fill: white; -fx-base: red;-fx-border-color:red;");
	
		Label title =new Label("Welcome to Password Manager!");
		title.setStyle("-fx-font: bold 16px Impact;-fx-text-fill:#3A94D6;");
		
		
		Label para =new Label("  for the security of your account ,\nplease select one of the functions");
		para.setStyle("-fx-font: bold 12px Arial;-fx-text-fill:#A6A9AF;");
		
		Button SignIN=new Button("Sign In"); //connect
		SignIN.setStyle("-fx-min-width: 200px;-fx-min-height: 50px; -fx-font: bold 15 Consolas; -fx-text-fill: white; -fx-base:#3A94D6;-fx-border-color:#3A94D6;");
		SignIN.setOnAction(event -> Login(primaryStage));
       
		/*SignIN.setOnAction(e -> {
			System.out.println("Connect with Succes");
			System.out.println(Email.getText());
			System.out.println(pass.getText());
			//btn.setText("Hello Again :)");
		});*/
		
		
		Button SignUP=new Button("Sign Up");  //create in account
		SignUP.setStyle("-fx-min-width: 200px;-fx-min-height: 50px; -fx-font: bold 15 Consolas; -fx-text-fill: white; -fx-base: #3A94D6;-fx-border-color:#3A94D6;");
		SignUP.setOnAction(event -> Signup(primaryStage));
		
		Image image = new Image("file:/C:/Users/pc/Desktop/PasswordManager_JavaFX/Home.png"); 
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(195);  
        imageView.setFitHeight(195);
        
        
        Pane Home = new Pane();
        
        CloseButton.setLayoutX(360);
        CloseButton.setLayoutY(6);
        
        imageView.setLayoutX(103);
        imageView.setLayoutY(30);
        
        title.setLayoutX(90);
        title.setLayoutY(208);
        
        para.setLayoutX(96);
        para.setLayoutY(242);
        
        SignIN.setLayoutX(94);
        SignIN.setLayoutY(310);
        
        SignUP.setLayoutX(94);
        SignUP.setLayoutY(370);


        Home.getChildren().addAll(CloseButton,imageView,title,para,SignIN,SignUP);
		
		BackgroundFill background_fill = new BackgroundFill(Color.WHITE,CornerRadii.EMPTY, Insets.EMPTY); 
		Background background = new Background(background_fill); 
		Home.setBackground(background); 
		
		
		Scene scene=new Scene(Home);
		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setTitle("Password Manager");
		primaryStage.setWidth(390);
		primaryStage.setHeight(500);
		primaryStage.setResizable(false);
		primaryStage.show(); 
		primaryStage.centerOnScreen();
	}

	private void Login(Stage firstStage) {
		
		 firstStage.close();
        // Créez une instance de la deuxième interface
        SignIN SignInInterface = new SignIN();
        // Affichez l'interface sign in
        try {
        	SignInInterface.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	private void Signup(Stage firstStage) {
		
	   firstStage.close();
       // Créez une instance de l'interface sign up
       SignUP SignUpInterface = new SignUP();
       // Affichez l'interface sign up
       try {
       	SignUpInterface.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
   }

}



