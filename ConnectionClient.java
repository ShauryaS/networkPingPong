package jrJava.networkPingpong;

import java.io.*;
import java.net.*;
import resources.*;
import java.util.StringTokenizer;


public class ConnectionClient implements Runnable {

	private String serverIpAddress = "localhost";
	private int serverPortNumber = 5454;
	
	private InputStream is;
	private OutputStream os;
	
	private BufferedReader br;
	private PrintWriter pw;
	
	private Ball ball;
	private Deflector serverDeflector, clientDeflector;
	
	private Timer timer = new Timer();
	
	
	public void connectAndFormStreams() throws Exception{

		Socket s = new Socket(serverIpAddress, serverPortNumber);
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
	
	
	public void run(){
		
		while(true){
			try{
				// Send clientDeflector's y value to Server.
				pw.println(clientDeflector.y);
				pw.flush();
				
				// Receive the ball x and y and serverDefelctor y from Server
				String msgFromServer = br.readLine();
				StringTokenizer st = new StringTokenizer(msgFromServer, ",");
				ball.x = Integer.parseInt(st.nextToken());
				ball.y = Integer.parseInt(st.nextToken());
				serverDeflector.y = Integer.parseInt(st.nextToken());
				
				timer.pause(20);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	
}






/*
 * // Send client deflector position to server.
				pw.println(clientDeflector.y);
				pw.flush();
				
				String serverData = br.readLine();  
				StringTokenizer st = new StringTokenizer(serverData, " ");
				ball.x = Integer.parseInt(st.nextToken());
				ball.y = Integer.parseInt(st.nextToken());
				serverDeflector.y = Integer.parseInt(st.nextToken());
				
				timer.pause(20);
*/
