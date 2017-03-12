package CarRace3D;

import java.util.ArrayList;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class UserScreen extends Stage{

	//////// Fields

	private Button btnRegister = new Button("Register");
	private Label lblName = new Label("Please enter your name:");
	private Label lblDeposit = new Label("Please enter sum of money to deposit:");
	private TextField tfName = new TextField();
	private TextField tfDeposit = new TextField("Sum > 0");
	private Gambler gambler = null;
	private String name;
	private float deposit;
	
	//////// Methods

	public void gamblerRegistrationScreen() {
		minWidthProperty().set(300);
		minHeightProperty().set(300);
		GridPane gridPane = initGrid();
		Scene scene = new Scene(gridPane, 500, 200);
		setScene(scene);
		setTitle("Gambler registration panel");
		setAlwaysOnTop(true);
		show();
		btnRegister.setOnAction(e -> {
			name = tfName.getText();
			if(name.length() == 0) {
				tfName.clear();
				tfName.appendText("Name cannot be empty");
			}
			else if(name.length() > 24) {
				tfName.clear();
				tfName.appendText("25 Characters max!");
			}
			else if(checkIsDuplicate(name)){
				tfName.clear();
				tfName.appendText("Name is already taken. Please enter another name");
			}
			else{
				if (tfDeposit.getText().matches("[0-9]+")){
					deposit = Float.parseFloat(tfDeposit.getText());
					if(deposit <=0){
						tfDeposit.clear();
						tfDeposit.appendText("Please deposit a positive amount");
					}
					else{
						gambler = new Gambler(name, deposit);
						GamblersScreen.addGamblerToList(gambler);
						btnRegister.setDisable(true);
						this.close();
					}
				}
				else{
					tfDeposit.clear();
					tfDeposit.appendText("Enter numbers only");
				}
			}
		});
	}

	private boolean checkIsDuplicate(String name) { 
		ArrayList<Gambler> list = GamblersScreen.getGamblers();
		for(Gambler d : list){
			if(d.getName() != null && d.getName().contains(name))
				return true;
		}
		return false;
	}

	private GridPane initGrid() {
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10));
		// Divide the panel to 3 rows X 2 columns
		for (int i = 0; i < 2; i++) {
			ColumnConstraints column = new ColumnConstraints();
			column.setPercentWidth(50); // Each column is 50% of panels width
			gridPane.getColumnConstraints().add(column);
		}
		for (int i = 0; i < 3; i++) {
			RowConstraints row = new RowConstraints();
			row.setPercentHeight(33); // Each row is 33% of panels height
			gridPane.getRowConstraints().add(row);
		}

		// Add objects to grid
		gridPane.add(lblName, 0, 0);
		gridPane.add(lblDeposit, 0, 1);
		gridPane.add(tfName, 1, 0);
		gridPane.add(tfDeposit, 1, 1);
		gridPane.add(btnRegister, 1, 2);

		// Set text related characteristics
		lblName.setFont(new Font(16));
		lblName.setWrapText(true);
		lblDeposit.setFont(new Font(16));
		lblDeposit.setWrapText(true);
		tfName.setFont(new Font(16));
		tfDeposit.setFont(new Font(16));
		btnRegister.setTextFill(Color.LIMEGREEN);
		btnRegister.setFont(new Font(25));

		// Align grid button object
		GridPane.setHalignment(btnRegister, HPos.CENTER);

		return gridPane;
	}

}