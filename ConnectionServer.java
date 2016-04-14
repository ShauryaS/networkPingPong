package jrJava.networkPingpong;


import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

import resources.Timer;


public class ConnectionServer implements Runnable {

	private int listeningPortNumber = 5454;
	
	private InputStream is;
	private OutputStream os;
	
	private BufferedReader br;
	private PrintWriter pw;
	
	private Ball ball;
	private Deflector serverDeflector, clientDeflector;
	
	
	
	public void connectAndFormStreams() throws Exception{

		ServerSocket serverSocket = new ServerSocket(listeningPortNumber);
		Socket s = serverSocket.accept();
		
		is = s.getInputStream();
		os = s.getOutputStream();
		
		InputStreamReader isr = new InputStreamReader(is);
		br = new BufferedReader(isr);
		
		OutputStreamWriter osw = new OutputStreamWriter(os);
		pw = new PrintWriter(osw);
	}
	
	
	public void setGameComponents(Ball _ball, Deflector _serverDeflector, Deflector _clientDeflector) {
		ball = _ball;
		serverDeflector = _serverDeflector;
		clientDeflector = _clientDeflector;
	}
	
	
	public void run() {

		while(true){
			try{
				// Receiver clientDeflector data from client
				String msgFromClient = br.readLine();
				clientDeflector.y = Integer.parseInt(msgFromClient);
				
				// Send serverDeflectoy y and Ball's x and y
				String msgToClient = ball.x+","+ball.y+","+serverDeflector.y;
				pw.println(msgToClient);
				pw.flush();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	
	}
}










/*
//Receive client deflector info from client data
String clientData = br.readLine();
int clientDeflectorY = Integer.parseInt(clientData);
clientDeflector.y = clientDeflectorY;

//Send ball position and deflector position to client
pw.println(ball.x + " " + ball.y + " " + serverDeflector.y);
pw.flush();

*/