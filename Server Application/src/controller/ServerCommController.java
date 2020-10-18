package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class represents the server, which accepts input from the client and calls the appropriate functionality.
 * 
 * @author Jens Varughese: 30061226
 * @author Zachary Lancaster: 30077019
 * @author Salma Salhi: 30064196
 *
 */
public class ServerCommController {

	/** 
	 * A variable to represent the socket used to communicate with the client.
	 * */
	private Socket aSocket; 
	
	/** 
	 * A PrintWriter variable to write to the socket.
	 * */
	private PrintWriter socketOut; 
	
	/** 
	 * A BufferedReader variable to read from the socket.
	 * */
	private BufferedReader socketIn; 
	
	/** 
	 * A ServerSocket.
	 * */
	private ServerSocket serverSocket; 
	
	/** 
	 * A thread pool.
	 * */
	private ExecutorService pool;
	
	/** 
	 * The constructor for this class, which initalizes serverSocket with the port number and initializes the thread pool. 
	 * 
	 * @param port, the port number.
	 * */
	public ServerCommController(int port) {
		try {
			serverSocket = new ServerSocket(port);
			pool = Executors.newFixedThreadPool(5);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * This function communicates with the client by accepting a connection, initializing socketIn and socketOut, and executing the pool using an 
	 * instance of ModelCommController.
	 * */
	public void runServer () {
		try {
			while (true) {
				aSocket = serverSocket.accept();
				System.out.println("Connection accepted by server!");
			    socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
				socketOut = new PrintWriter((aSocket.getOutputStream()), true);
				ModelCommController instance = new ModelCommController(socketIn, socketOut);

				pool.execute(instance);
			}
		} catch (IOException e) {
			e.getStackTrace();
		}
		closeConnection();	
	}
	
	/** 
	 * This function closes the connection by closing socketIn and socketOut.
	 * */
	private void closeConnection() {
		try {
			socketIn.close();
			socketOut.close();
		} catch (IOException e) {
			e.getStackTrace();
		}
	}

	/** 
	 * The main function, which initializes the server with the port number and runs it.
	 * 
	 * @param args, the system arguments. 
	 * @throws IOException if there is a problem initializing the server.
	 * */
	public static void main(String [] args) throws IOException{		
		ServerCommController myServer = new ServerCommController(9898);
		myServer.runServer();
	}

}
