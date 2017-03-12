package CarRace3D;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GambleScreen extends Stage{
	
	private Button btnAddGamble;
	private ComboBox<Gambler> cbGamblers;
	private ComboBox<Race> cbRaces;
	
	public void gambleScreen() {
		minWidthProperty().set(300);
		minHeightProperty().set(300);
		BorderPane pane = new BorderPane();
		TableView table = initTable();
		HBox hbox = initTop();
		pane.setTop(hbox);
		pane.setCenter(table);
		Scene scene = new Scene(pane, 600, 400);
		setScene(scene);
		setTitle("Gambling panel");
		setAlwaysOnTop(true);
		show();
		btnAddGamble.setOnAction(e -> {addGamble();});
	}
	
	private void addGamble() {
		// TODO Auto-generated method stub
		//////////Update Gamble db
		//////////Update Race db
		//////////Update Gambler db
	}

	private HBox initTop() {
		HBox hbox = new HBox();
		hbox.setSpacing(10);
		hbox.setPadding(new Insets(0, 20, 10, 20)); 
		btnAddGamble = new Button("Add Gamble");
		Text txtGambler = new Text("Gambler: ");
		cbGamblers = new ComboBox<>(); /////////Connect to registered gamblers
		Text txtRace = new Text("Race: ");
		cbRaces = new ComboBox<>();  ////////////Connect to active races
		hbox.getChildren().addAll(txtGambler, cbGamblers,txtRace, cbRaces, btnAddGamble);
		return hbox;
	}

	
	private TableView initTable() {
		TableView table = new TableView();
		TableColumn idCol = new TableColumn("Car ID");
		TableColumn nameCol = new TableColumn("Car Name");
		TableColumn colorCol = new TableColumn("Car Color");
		TableColumn amountCol = new TableColumn("Amount");
		table.getColumns().addAll(idCol, nameCol, colorCol, amountCol);

		///////////////// Add table update, connect to Races
		
		return table;
	}
	
	
}
