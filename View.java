package CarRace3D;

import java.util.List;

import com.sun.prism.paint.Color;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
public class View {

	private Model model ;
	private BorderPane border_pane ;
	private GridPane raceDetails_grid;
	private ObservableList<String>  items_car;
	private TableView rankTable;
	private TableColumn carRank ;
    private TableColumn carName ;
    private TableColumn carId ;
    private List<Xform> carGroup;
    private final ObservableList<Car> data =
		        FXCollections.observableArrayList();
	 private VBox tableBox;
	 private Label timeLbl;
	public View(Model model)
	{
		this.model = model;
		border_pane = new BorderPane();
		createDeailtsGrid();
		raceDetails_grid = new GridPane();
	
		border_pane.setTop(raceDetails_grid);
		border_pane.setScaleX(30);
		border_pane.setScaleY(15);
		border_pane.setScaleZ(30);
		border_pane.setRotationAxis(Rotate.Y_AXIS);
		border_pane.setRotate(90);
		border_pane.setTranslateZ(300);
		border_pane.setTranslateY(-100);
		border_pane.setTranslateX(3000);

	}
	
	private void createDeailtsGrid()
	{
		rankTable = new TableView();
		raceDetails_grid = new GridPane();
		carRank = new TableColumn("Rank");
        carName = new TableColumn("Name");
        carId = new TableColumn("ID");
        fillTable();
        rankTable.getColumns().addAll(carRank,carName,carId);
        tableBox = new VBox();
        tableBox.setSpacing(5);
        tableBox.setPadding(new Insets(10,5,5,10));
        tableBox.getChildren().add(rankTable);
        
        timeLbl = new Label();
        timeLbl.setText(model.getTimeOfRace()+""); 
        raceDetails_grid.getChildren().addAll(timeLbl);
     //   border_pane.setBackground(Color.GREEN);
        border_pane.getChildren().add(raceDetails_grid);
	}
	
	private void fillTable()
	{
	       
		for (Car car : model.getCarList()) 
		{
			if(car != null)
			{
				data.add(car);
			}
			
		}
		rankTable.setItems(data);
	}
	
	public BorderPane getPane()
	{
		return this.border_pane;
	}
}
