package MainApp;
	import javafx.application.Application;
	import javafx.beans.value.ChangeListener;
	import javafx.beans.value.ObservableValue;
	import javafx.collections.FXCollections;
	import javafx.collections.ObservableList;
	import javafx.event.ActionEvent;
	import javafx.event.EventHandler;
	import javafx.geometry.Insets;
	import javafx.scene.Scene;
	import javafx.scene.control.Button;
	import javafx.scene.control.TableColumn;
	import javafx.scene.control.TableView;
	import javafx.scene.control.TextField;
	import javafx.scene.control.cell.PropertyValueFactory;
	import javafx.scene.layout.VBox;
	import javafx.stage.Stage;

	public class Test extends Application {

	    public static void main(String[] args) {
	        launch(args);
	    }

	    @Override
	    public void start(Stage primaryStage) throws Exception {

	        TableView tableView = new TableView();

	        TableColumn nombresTableColum = new TableColumn("Nombres");
	        TableColumn apellidosTableColumn = new TableColumn("Apellidos");
	        TableColumn correoTableColumn = new TableColumn("Correo");

	        nombresTableColum.setCellValueFactory(new PropertyValueFactory<>("nombres"));
	        apellidosTableColumn.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
	        correoTableColumn.setCellValueFactory(new PropertyValueFactory<>("correo"));

	        tableView.getColumns().addAll(nombresTableColum, apellidosTableColumn, correoTableColumn);

	        ObservableList<Personne> personas = FXCollections.observableArrayList();
	        tableView.setItems(personas);

	        TextField nombresTextField = new TextField();
	        TextField apellidosTextField = new TextField();
	        TextField correoTextField = new TextField();

	        nombresTextField.setPromptText("Nombres");
	        apellidosTextField.setPromptText("Apellidos");
	        correoTextField.setPromptText("Correo");

	        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
	            @Override
	            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
	              Personne p = (Personne) newValue;

	                if (p != null) {
	                    nombresTextField.setText(p.getNombres());
	                    apellidosTextField.setText(p.getApellidos());
	                    correoTextField.setText(p.getCorreo());
	                }
	            }
	        });

	        Button adicionarButton = new Button("Adicionar");
	        adicionarButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                String nombres = nombresTextField.getText();
	                String apellidos = apellidosTextField.getText();
	                String correo = correoTextField.getText();

	                personas.add(new Personne(nombres, apellidos, correo));

	                nombresTextField.setText(null);
	                apellidosTextField.setText(null);
	                correoTextField.setText(null);
	            }
	        });

	        Button eliminarButton = new Button("Eliminar");
	        eliminarButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                Personne p = (Personne) tableView.getSelectionModel().getSelectedItem();
	                personas.remove(p);
	            }
	        });

	        VBox vBox = new VBox();
	        vBox.getChildren().addAll(tableView, nombresTextField, apellidosTextField, correoTextField, adicionarButton, eliminarButton);
	        vBox.setPadding(new Insets(20));
	        vBox.setSpacing(20);

	        Scene scene = new Scene(vBox, 400, 400);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }
	}

