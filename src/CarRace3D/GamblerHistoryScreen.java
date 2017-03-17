package CarRace3D;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GamblerHistoryScreen extends Stage{


	private Button btnShowGambler;
	private ComboBox<Gambler> cbGamblers;
	private ComboBox<Race> cbRaces;
	private ArrayList<Gambler> gamblers;
	private ObservableList<Gambler> options;

	public void gamblerHistoryScreen(ArrayList<Gambler> gamblers) {
		this.gamblers = gamblers;
		minWidthProperty().set(300);
		minHeightProperty().set(300);
		BorderPane pane = new BorderPane();
		TableView<Gambler> table = initTable();
		HBox hbox = initTop();
		pane.setTop(hbox);
		pane.setCenter(table);
		Scene scene = new Scene(pane, 600, 400);
		setScene(scene);
		setTitle("Gambler History panel");
		setAlwaysOnTop(true);
		show();
		btnShowGambler.setOnAction(e -> {showGambler();});
	}

	private void showGambler() { 
		// import chosen gambler's details
	}

	private HBox initTop() {
		HBox hbox = new HBox();
		hbox.setSpacing(10);
		hbox.setPadding(new Insets(0, 20, 10, 20)); 
		btnShowGambler = new Button("Show gambler");
		Text txtGambler = new Text("Gambler: ");
		if (!gamblers.isEmpty()) {
			options = FXCollections.observableArrayList(gamblers);
		}
		cbGamblers = new ComboBox<>(options); /////////Connect to registered gamblers
		hbox.getChildren().addAll(txtGambler, cbGamblers, btnShowGambler);
		return hbox;
	}


	private TableView<Gambler> initTable() {
		TableView<Gambler> table = new TableView<Gambler>();
		TableColumn idCol = new TableColumn("Gambler ID");
		TableColumn nameCol = new TableColumn("Gambler Name");
		TableColumn walletCol = new TableColumn("Gambler Wallet");
		TableColumn amountCol = new TableColumn("Amount");
		table.getColumns().addAll(idCol, nameCol, walletCol, amountCol);

		///////////////// Add table update, connect to Races

		return table;
	}


}
