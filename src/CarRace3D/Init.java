package CarRace3D;

public class Init {

	public static void main(String[] args) {
		GamblersScreen gs = new GamblersScreen();
		new Thread(gs).start();
		for(int i = 0; i < 3; i++) {
			Thread client = new Thread(new RaceDUMMY()); // check it
			//Thread client = new Thread(new CarRace3D());
		}

	}

}
