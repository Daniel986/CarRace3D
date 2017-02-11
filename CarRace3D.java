package CarRace3D;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CarRace3D {

	private ArrayList<Controller> controllerList;
	private ArrayList<View> viewList;
	private ArrayList<Model> modelList;

	public static void main(String[] args) throws Exception {
		// Start 3 Race screens(threads)
		// Start Gamblers screen
		Thread gs = new Thread(new GamblersScreen());
        gs.start();
		for(int i = 0; i < 3 ; i++) {
		}
	}

}
