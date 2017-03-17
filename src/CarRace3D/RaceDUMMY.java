package CarRace3D;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class RaceDUMMY implements Runnable {
	  private DataInputStream fromServer;
	  private DataOutputStream toServer;
	  private String host = "localhost";
	  // Socket
	  private Socket socket;

	public RaceDUMMY() {
		   try { // Create a socket to connect to the server
			      socket = new Socket(host, 8000);
			      // Create an input stream to receive data from the server
			      fromServer = new DataInputStream(socket.getInputStream());
			      // Create an output stream to send data to the server
			      toServer = new DataOutputStream(socket.getOutputStream());
			      int player = fromServer.readInt(); // reading from server
			      System.out.println(player);
			      toServer.writeInt(player); // sending to server
			    }
			    catch (Exception ex)
			    { ex.printStackTrace();
			    }
	}

	@Override
	public void run() {
		new RaceDUMMY();
	}
}
