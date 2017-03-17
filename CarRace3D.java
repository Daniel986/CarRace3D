package CarRace3D;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CarRace3D {

	public static void main(String[] args) throws Exception {
		// Start Gamblers screen
		//Thread server = new Thread(new Server());
		//server.start();
		GamblersScreen gs = new GamblersScreen();
		new Thread(gs).start();
		for(int i = 0; i < 3; i++) {
			Thread client = new Thread(new RaceDUMMY());
		}
			
	}

}
