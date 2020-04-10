package Control.ClientServer;

import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	/**
	 * Starts the Network.Server and listens for new connections
	 *
	 * @param client the Network.Client class for the Network.Server to send messages to
	 * @param port   the Port for the Network.Server to run on
	 */
	public Server(Client client, int port) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
			while (true) {
				Socket s = serverSocket.accept();
				new Thread(() -> {
					try {
						BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
						String message = bufferedReader.readLine();
						if(message.indexOf("\\")==0){
							client.receiveCommand(message);
						}else {
							System.out.println("Nachricht vom Client: " + message);
							client.receiveMessage(message);
						}
						bufferedReader.close();
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							s.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				serverSocket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
