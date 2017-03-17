package CarRace3D;


import javafx.stage.Stage;

public class Controller {

	  private Stage stg;
	  private Model model;
	  private View view;
	  private final int CAR1_ID = 0;
	  private final int CAR2_ID = 1;
	  private final int CAR3_ID = 2;
	  private final int CAR4_ID = 3;
	  private final int CAR5_ID = 4;
	  
	  
	  public Controller(Model model, View view)
	  {	
		  this.model = model;
		  this.view = view;
	  }
	
}
