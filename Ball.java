package jrJava.networkPingpong;

import java.awt.Color;
import java.awt.Graphics2D;
import resources.DrawingBoard;

public class Ball {

	public int x = 100;
	public int y = 200;
	
	private int dx = 10;
	private int dy = 3;
	
	private int radius = 25;
	private Color color = Color.GREEN;
	private Deflector clientDeflector, serverDeflector;
	
	
	public Ball(){}
	
	
	public Ball(int _x, int _y, int _dx, int _dy, int _radius, Color _color){
		x = _x;
		y = _y;
		dx = _dx;
		dy = _dy;
		radius = _radius;
		color = _color;
	}
	
	
	public void setDeflectors(Deflector _clientDeflector, Deflector _serverDeflector){
		clientDeflector = _clientDeflector;
		serverDeflector = _serverDeflector;
	}
	
	
	public void update(){
		x = x + dx;
		y = y + dy;
		
		if(x-radius<serverDeflector.x+Deflector.width && x-radius>serverDeflector.x &&
		   y>serverDeflector.y && y<serverDeflector.y+Deflector.height){
			x = serverDeflector.x + Deflector.width + radius;
			dx = -dx;
		}
		if(x+radius>clientDeflector.x && x+radius<clientDeflector.x+clientDeflector.width &&
		   y>clientDeflector.y && y<clientDeflector.y+Deflector.height){
			x = clientDeflector.x - radius;
			dx = -dx;
		}
		
		
		if(y-radius<0){
			y = radius;
			dy = -dy;
		}
		if(y+radius> GameClient.SCREEN_HEIGHT){
			y = GameClient.SCREEN_HEIGHT - radius;
			dy = -dy;
		}
	}
	
	
	public void draw(DrawingBoard board){
		Graphics2D canvas = board.getCanvas();
		canvas.setColor(color);
		canvas.fillOval(x-radius, y-radius, 2*radius, 2*radius);
	}
	
}

