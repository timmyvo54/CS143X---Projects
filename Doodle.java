// Timmy Vo
// 10/6/21
// CSE143X
// TA: Andrew Frazier
// Assignment #2
//
// This program will output a figure using the drawing panel class

import java.awt.*;

public class Doodle {

	public static void main(String[] args) {
		DrawingPanel panel = new DrawingPanel(500, 500);
		Graphics g = panel.getGraphics();
		panel.setBackground(new Color(51, 0, 111));
		g.setColor(Color.WHITE);
		g.fillOval(50, 50, 400, 400);
		g.setColor(new Color(51, 0, 111));
		g.fillOval(75, 75, 350, 350);
		g.setColor(Color.WHITE);
		uShape(g, 200, 75);
		uShape(g, 300, 50);
		uShape(g, 350, 50);
	}
	
  // Takes x, the 
	public static void uShape(Graphics g, int x, int width) {
		g.fillRect(x, 180, 20, 150);
		g.fillRect(x - width, 310, width, 20);
		g.fillRect(x - width, 180, 20, 150);
	}
	

}
