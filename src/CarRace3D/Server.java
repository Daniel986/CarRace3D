package CarRace3D;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;

import javafx.application.Platform;

public class Server implements Runnable, CarRaceConstants{

	private ServerSocket serverSocket;
	private static Socket socketRace1, socketRace2, socketRace3;

	public Server() {
		new Thread( () -> { 
			try { 
				// Create a server socket
			serverSocket = new ServerSocket(8000);
			// Ready to create a session for every two players
				socketRace1 = serverSocket.accept();
				new DataOutputStream(socketRace1.getOutputStream()).writeInt(CONNECT_TO_SERVER);
				socketRace2 = serverSocket.accept();
				new DataOutputStream(socketRace2.getOutputStream()).writeInt(CONNECT_TO_SERVER);
				socketRace3 = serverSocket.accept();
				new DataOutputStream(socketRace3.getOutputStream()).writeInt(CONNECT_TO_SERVER);
				// Launch a new thread for this session of two players
				//Runnable hr = new HandleRaces(socketRace1, socketRace2, socketRace3);
				//new Thread(hr).start();
		} catch(SocketException ex) { 
		} 
		catch(IOException ex) { 
		}
		}).start();
	}

	@Override
	public void run() {
		new Server();
	}

	public ArrayList<Socket> getSockets() {
		ArrayList<Socket> sockets = new ArrayList<>();
		sockets.add(socketRace1);
		sockets.add(socketRace2);
		sockets.add(socketRace3);
		return sockets;
	}

}
