package MainApp;

import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrentUserTest extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Login");
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        TextField textField = new TextField();
        textField.setPromptText("Username");
        gridPane.add(textField, 0, 0, 1, 1);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        gridPane.add(passwordField, 0, 1, 1, 1);

        Button loginButton = new Button("Login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String email = textField.getText();
                String password = passwordField.getText();

                // Connect to the database
                Connection connection = connectToDatabase();

                // Check if the username and password are correct
                if (checkCredentials(connection, email, password)) {
                    // Display the user's information
                    Label label = new Label("Welcome, " + email + "!");
                    label.setStyle("-fx-font-size: 24; -fx-font-family: 'Arial';");
                    gridPane.add(label, 0, 2, 1, 1);

                    // Add a button to logout
                    Button logoutButton = new Button("Logout");
                    logoutButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            // Close the connection
                            try {
								connection.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

                            // Reset the interface
                            gridPane.getChildren().clear();
                            gridPane.add(textField, 0, 0, 1, 1);
                            gridPane.add(passwordField, 0, 1, 1, 1);
                            gridPane.add(loginButton, 0, 2, 1, 1);
                        }
                    });
                    gridPane.add(logoutButton, 0, 3, 1, 1);
                } else {
                    Label errorLabel = new Label("Invalid username or password");
                    errorLabel.setStyle("-fx-font-size: 24; -fx-font-family: 'Arial'; -fx-text-fill: red;");
                    gridPane.add(errorLabel, 0, 2, 1, 1);
                }
            }

			
        });
        gridPane.add(loginButton, 0, 2, 1, 1);

        primaryStage.setScene(new Scene(gridPane, 400, 300));
        primaryStage.show();
    }

    // Connect to the database
    private Connection connectToDatabase() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/passwordmanager", "root", "Root@2003");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Check if the username and password are correct
    private boolean checkCredentials(Connection connection, String email, String password) {
        boolean valid = false;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM utilisateurs WHERE email = ? AND password = ?");
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                valid = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return valid;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
