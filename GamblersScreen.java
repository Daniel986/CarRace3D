package CarRace3D;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GamblersScreen extends Application implements Runnable{

	//Fields
	static ArrayList<Gambler> gamblers = new ArrayList<>();
	static ArrayList<Gambler> races = new ArrayList<>();
	Button btnAddGambler, btnGamble, btnRaceHistory, btnGamblerHistory;
	//Methods

	@SuppressWarnings("rawtypes")
	@Override
	public void start(Stage primaryStage) {
		BorderPane pane = new BorderPane();
		pane.setStyle("-fx-background-color: darkblue");
		HBox hbButtons = initTopButtons();
		stylizeButtons();
		Scene scene = new Scene(pane, 900, 520);
		TableView table = initTable();
		pane.setTop(hbButtons);
		pane.setCenter(table);
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.setTitle("Gamblers Screen"); // Set the stage title
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				try {
					Platform.exit();
					System.exit(0);
				} catch (Exception e) { 
					e.printStackTrace();
				}
			}
		});
		btnAddGambler.setOnAction(e -> {registerNewGambler();});
		primaryStage.show(); 
		primaryStage.setAlwaysOnTop(true);
	}

	@Override
	public void run() {
		launch(this.getClass());
	}

	private HBox initTopButtons() {
		HBox hbButtons = new HBox();
		hbButtons.setSpacing(10);
		hbButtons.setPadding(new Insets(0, 20, 10, 20)); 
		btnAddGambler = new Button("Add Gambler");
		btnRaceHistory = new Button("Race History");
		btnGamblerHistory = new Button("Gambler History");
		btnGamble = new Button("Gamble");
		hbButtons.getChildren().addAll(btnAddGambler, btnRaceHistory, btnGamblerHistory, btnGamble);
		return hbButtons;
	}

	private void stylizeButtons() {
		btnGamble.setFont(new Font(20));
		btnAddGambler.setFont(new Font(20));
		btnRaceHistory.setFont(new Font(20));
		btnGamblerHistory.setFont(new Font(20));
		btnGamble.setTextFill(Color.GOLD);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private TableView initTable() {
		TableView table = new TableView();
		TableColumn idCol = new TableColumn("Race ID");
		TableColumn nameCol = new TableColumn("Race Name");
		TableColumn partCol = new TableColumn("Particaipating Gamblers");
		TableColumn carCol = new TableColumn("Cars Racing");
		TableColumn statusCol = new TableColumn("Status");

		nameCol.setMinWidth(150);
		partCol.setMinWidth(300);
		carCol.setMinWidth(288);

		table.getColumns().addAll(idCol, nameCol, partCol, carCol, statusCol);

		return table;
	}

	public void registerNewGambler() {
		new UserScreen().gamblerRegistrationScreen();
	}

	public static ArrayList<Gambler> getGamblers() {
		return gamblers;
	}

	public static void addGamblerToList(Gambler gambler) {
		if(gambler != null)
			gamblers.add(gambler);
		for(Gambler g : gamblers){
			System.out.println(g.getName() + " " + g.getWallet());
		}

	}


}
