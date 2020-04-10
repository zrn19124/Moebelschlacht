package Control.ClientServer;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;


public abstract class Client {

	private final String IP_ADDRESS;//The IP address of the Server to reach
	private final int PORT_NUMBER;//The Port number of Server to reach

	/**
	 * Establishes a connection to the localhost on Port 65535
	 *
	 */
	public Client(){
		IP_ADDRESS = "127.0.0.1";
		PORT_NUMBER = 65535;
		Client c = this;
		new Thread(() -> {
			new Server(c, 65535);
		}).start();
	}

	/**
	 * Starts a Network.Server on your own PC
	 *
	 * @param ip_address The IP_ADDRESS of the Network.Server to reach
	 * @param serverPort  The PORT_NUMBER of the Network.Server to reach
	 */
	public Client(String ip_address, int serverPort) {
		IP_ADDRESS = ip_address;
		PORT_NUMBER = serverPort;
		Client c = this;
		new Thread(() -> {
			new Server(c, 65535);
		}).start();
	}

	/**
	 * Starts a Network.Server on your own PC with the given Port
	 *
	 * @param ip_address The IP_ADDRESS of the Network.Server to reach
	 * @param localPort  The PORT_NUMBER for your Network.Server to run on
	 * @param serverPort The PORT_NUMBER of the Network.Server to reach
	 */
	public Client(String ip_address, int localPort, int serverPort) {
		IP_ADDRESS = ip_address;
		PORT_NUMBER = serverPort;
		Client c = this;
		new Thread(() -> {
			new Server(c, localPort);
		}).start();
	}

	/**
	 * Sends a message to the Network.Server to reach
	 *
	 * @param message The message to send
	 */
	public void sendMessage(String message) {
		try {
			Socket socket = new Socket(IP_ADDRESS, PORT_NUMBER);
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			bufferedWriter.write(message);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			bufferedWriter.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This Method is called when the Network.Server receives a Message
	 *
	 * @param s The String that is received from the Network.Server
	 */
	public abstract void receiveMessage(String s);

	/**
	 * Tries to connect to the Network.Server
	 *
	 * @return True - if you can connect to the Network.Server<br>False - if the Network.Server is not reachable
	 */
	public boolean testConnection() {
		try {
			Socket socket = new Socket(IP_ADDRESS, PORT_NUMBER);
			return socket.isConnected();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Connects to the Network.Server and returns it InetAddress
	 *
	 * @return The InetAddress of the Network.Server
	 */
	public InetAddress getOpponentsAddress() {
		try {
			Socket socket = new Socket(IP_ADDRESS, PORT_NUMBER);
			return socket.getInetAddress();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Connects to the Network.Server and returns its Port-number
	 *
	 * @return <b>-1</b> - if an exception occurs<br><b>A positive Integer</b> - if the Network.Server is reachable
	 */
	public int getOpponentsPortNumber() {
		try {
			Socket socket = new Socket(IP_ADDRESS, PORT_NUMBER);
			return socket.getPort();
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Connects to the Network.Server and returns its InputStream
	 *
	 * @return The Input Stream of the Network.Server
	 */
	public InputStream getInputStream() {
		try {
			Socket socket = new Socket(IP_ADDRESS, PORT_NUMBER);
			return socket.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Connects to the Network.Server and retuns its OutputStream
	 *
	 * @return The OutputStream of the Network.Server
	 */
	public OutputStream getOutputStream() {
		try {
			Socket socket = new Socket(IP_ADDRESS, PORT_NUMBER);
			return socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * This method is called when the first character of a received message contains a backslash( \ )
	 *
	 * @param message - The command that is received
	 */
	public abstract void receiveCommand(String message);

	@Override
	public String toString() {
		return "Client{" +
				"IP_ADDRESS='" + IP_ADDRESS + '\'' +
				", PORT_NUMBER=" + PORT_NUMBER +
				'}';
	}
}
