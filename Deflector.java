package jrJava.networkPingpong;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import resources.DrawingBoard;



public class Deflector implements MouseListener, MouseMotionListener{

	public static int width = 30;
	public static int height = 80;
	
	public int x;
	public int y;
	
	private int lastY;
	
	private Color color;
	
	
	public Deflector(int _x, int _y, Color _color){
		x = _x;
		y = _y;
		color = _color;
	}
	
	
	public void draw(DrawingBoard board){
		Graphics2D canvas = board.getCanvas();
		canvas.setColor(color);
		canvas.fillRect(x, y, width, height);
	}

	
	public void mouseDragged(MouseEvent e) {
		int currentY = e.getY();
		y = y + (currentY-lastY);
				
		if(y<0){
			y = 0;
		}
		else if(y+height>GameClient.SCREEN_HEIGHT){
			y = GameClient.SCREEN_HEIGHT-height;
		}
		lastY = currentY;
	}

	
	public void mousePressed(MouseEvent e) {
		lastY = e.getY();
	}
	
	
	public void mouseMoved(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
}

