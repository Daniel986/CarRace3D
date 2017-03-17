package CarRace3D;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RaceHistoryScreen extends Stage {

	private Button btnShowRace;
	private ComboBox<Race> cbRaces;
	private ArrayList<Race> races;

	public void raceHistoryScreen(ArrayList<Race> races) {
		this.races = races;
		if(!races.isEmpty())
			cbRaces.getItems().addAll(races);
		minWidthProperty().set(300);
		minHeightProperty().set(300);
		BorderPane pane = new BorderPane();
		TableView<Car> carTable = initCarTable();
		TableView<Gamble> gambleTable = initGambleTable();
		HBox hbox = initTop();
		VBox vbox = new VBox();
		vbox.getChildren().add(carTable);
		vbox.getChildren().add(gambleTable);
		pane.setTop(hbox);
		pane.setCenter(vbox);
		Scene scene = new Scene(pane, 600, 400);
		setScene(scene);
		setTitle("Race History panel");
		setAlwaysOnTop(true);
		show();
		btnShowRace.setOnAction(e -> {showRace();});
	}

	private TableView<Gamble> initGambleTable() {
		TableView<Gamble> table = new TableView<Gamble>();
		TableColumn idCol = new TableColumn("Gamble ID");
		TableColumn nameCol = new TableColumn("Gambler Name");
		TableColumn colorCol = new TableColumn("Car");
		TableColumn gamblerCol = new TableColumn("Amount");
		table.getColumns().addAll(idCol, nameCol, colorCol, gamblerCol);


		return table;
	}

	private void showRace() {
		// TODO Auto-generated method stub
		//////////Update Gamble db
		//////////Update Race db
		//////////Update Gambler db
	}

	private HBox initTop() {
		HBox hbox = new HBox();
		hbox.setSpacing(10);
		hbox.setPadding(new Insets(0, 20, 10, 20)); 
		btnShowRace = new Button("Show race");
		Text txtRace = new Text("Race: ");
		cbRaces = new ComboBox<>();  ////////////Connect to all races
		hbox.getChildren().addAll(txtRace, cbRaces, btnShowRace);
		return hbox;
	}


	private TableView<Car> initCarTable() {
		TableView<Car> table = new TableView<Car>();
		TableColumn idCol = new TableColumn("Car ID");
		TableColumn nameCol = new TableColumn("Car Name");
		TableColumn colorCol = new TableColumn("Car Color");
		TableColumn gambleCol = new TableColumn("Gambles");
		TableColumn winnerCol = new TableColumn("Winner");
		gambleCol.setMinWidth(300);
		table.getColumns().addAll(idCol, nameCol, colorCol, gambleCol, winnerCol);

		///////////////// Add table update, connect to Races

		return table;
	}

}
