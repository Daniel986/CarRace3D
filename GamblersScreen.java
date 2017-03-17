package CarRace3D;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GamblersScreen extends Application implements Runnable, CarRaceConstants {

	//Fields
	static ArrayList<Gambler> gamblers = new ArrayList<>();
	static ArrayList<Race> races = new ArrayList<>();
	private Button btnAddGambler, btnGamble, btnRaceHistory, btnGamblerHistory;
	private Statement stmt;
	private static Connection conn;
	private Server server = new Server();
	// Host name or ip
	private String host = "localhost";
	// Socket
	private Socket socketRace1, socketRace2, socketRace3;

	@Override
	public void start(Stage primaryStage) {
		BorderPane pane = new BorderPane();
		pane.setStyle("-fx-background-color: darkblue");
		HBox hbButtons = initTopButtons();
		stylizeButtons();
		Scene scene = new Scene(pane, 900, 520);
		TableView<Race> table = initTable();
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
		btnGamble.setOnAction(e -> {gambleScreen();});
		btnRaceHistory.setOnAction(e -> {raceHistoryScreen();});
		btnGamblerHistory.setOnAction(e -> {gamblerHistoryScreen();});
		primaryStage.show(); 
		primaryStage.setAlwaysOnTop(true);
		CreateSQL();
		connectSQL();
		new Thread(server).start();
		getSockets();
		handleRaces();
	}

	private void gamblerHistoryScreen() {
		new RaceHistoryScreen().raceHistoryScreen(races);
	}

	private void raceHistoryScreen() {
		new RaceHistoryScreen().raceHistoryScreen(races);
	}

	private void handleRaces() {
		try {
			DataInputStream fromRace1 = new DataInputStream(socketRace1.getInputStream());
			DataOutputStream toRace1 = new DataOutputStream(socketRace1.getOutputStream());
			DataInputStream fromRace2 = new DataInputStream(socketRace2.getInputStream());
			DataOutputStream toRace2 = new DataOutputStream(socketRace2.getOutputStream());
			DataInputStream fromRace3 = new DataInputStream(socketRace3.getInputStream());
			DataOutputStream toRace3 = new DataOutputStream(socketRace3.getOutputStream());
			
			System.out.println(fromRace1.readInt());
			System.out.println(fromRace2.readInt());
			System.out.println(fromRace3.readInt());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getSockets() {
		ArrayList<Socket> sockets = server.getSockets();
		this.socketRace1 = sockets.get(0);
		this.socketRace2 = sockets.get(1);
		this.socketRace3 = sockets.get(2);
	}

	private void connectSQL() {
		try {
			// Load the JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			// Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver loaded");
			// Establish a connection
			conn = DriverManager.getConnection
					("jdbc:mysql://localhost/CarRace", "scott", "tiger");
			System.out.println("Database connected");
			// Create a statement
			stmt = conn.createStatement();
			loadGamblers();
			loadRaces();
		} catch(Exception ex) {
			ex.printStackTrace();
		}

	}

	private void loadRaces() {
		///////// load only finished races
		///////// query or querys that fill all relevant race class fields: raceID, name, date, time, gambles, cars
	}

	private void loadGamblers() {
		String queryString = "select gamblerId, name, wallet from gambler";
		ResultSet rset;
		try {
			rset = stmt.executeQuery(queryString);
			while (rset.next()) { 
				int id = rset.getInt(1);
				String name = rset.getString(2);
				float wallet = rset.getFloat(3);
				gamblers.add(new Gambler(id, name, wallet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void CreateSQL() {
		new CreateRaceSQL();
	}

	private void gambleScreen() {
		new GambleScreen().gambleScreen();
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
	private TableView<Race> initTable() {
		TableView<Race> table = new TableView<Race>();
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

	public static void addGambler(Gambler gambler) {
		if(gambler != null)
			gamblers.add(gambler);
		// the mysql insert statement
		String insertGambler = " insert into gambler (gamblerid, name, wallet)"
				+ " values (NULL, ?, ?)";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.prepareStatement(insertGambler);
			preparedStmt.setString (1, gambler.getName());
			preparedStmt.setFloat(2, gambler.getWallet());
			preparedStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


}
