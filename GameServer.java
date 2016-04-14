package jrJava.networkPingpong;

import java.awt.Color;
import java.awt.Graphics2D;
import resources.*;

public class GameServer {
	
	public static int SCREEN_WIDTH = 500;
	public static int SCREEN_HEIGHT = 300;
	
	
	public static void main(String[] args) throws Exception{
	
		DrawingBoard board = new DrawingBoard(SCREEN_WIDTH, SCREEN_HEIGHT);
		board.getJFrame().setSize(SCREEN_WIDTH+15, SCREEN_HEIGHT+38);
		
		Graphics2D g = board.getCanvas();
		Timer timer = new Timer();
		
		Ball ball = new Ball();
		
		Deflector serverDeflector = new Deflector(0, 50, Color.RED);
		Deflector clientDeflector = new Deflector(SCREEN_WIDTH - Deflector.width , 150, Color.BLUE);
		
		board.addMouseListener(serverDeflector);
		board.addMouseMotionListener(serverDeflector);
		
		ball.setDeflectors(clientDeflector, serverDeflector);
		
		ConnectionServer connectionServer = new ConnectionServer();
		connectionServer.connectAndFormStreams();
		connectionServer.setGameComponents(ball, serverDeflector, clientDeflector);
		Thread communicationThread = new Thread(connectionServer);
		communicationThread.start();
		
		while(true){
			
			board.clear();
			
			g.setColor(Color.BLACK);
			g.drawLine(0, SCREEN_HEIGHT/2, SCREEN_WIDTH, SCREEN_HEIGHT/2);
			g.drawLine(0, SCREEN_HEIGHT, SCREEN_WIDTH, SCREEN_HEIGHT);
			g.drawLine(SCREEN_WIDTH/2, 0, SCREEN_WIDTH/2, SCREEN_HEIGHT);
			
			ball.update();
			
			ball.draw(board);
			clientDeflector.draw(board);
			serverDeflector.draw(board);
			
			board.repaint();
			
			timer.pause(50);
		}
		
	}
	
}
