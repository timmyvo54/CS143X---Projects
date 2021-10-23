// Timmy Vo
// 10/6/21
// CSE143X
// TA: Andrew Frazier
// Assignment #2
//
// This program will output a Cafe Wall illusion image using drawing panel

import java.awt.*;

public class CafeWall {
    
    public static final int MORTAR = 2;

    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel(650, 400);
        Graphics picaso = panel.getGraphics();
        panel.setBackground(Color.GRAY);
        row(picaso, 0, 0, 4, 20);
        row(picaso, 50, 70, 5, 30);
        grid(picaso, 10, 150, 4, 8, 25, 0);
        grid(picaso, 250, 200, 3, 6, 25, 10);
        grid(picaso, 425, 180, 5, 10, 20, 10);
        grid(picaso, 400, 20, 2, 4, 35, 35);
    }
    
    // Takes six integers: x-coordinate, y-coordinate, number of pairs,
    // number of rows, size of squares, and the offset as parameters.
    // Creates a grid of black tiles with a blue x and white tiles.
    public static void grid(Graphics picaso, int x, int y, int pairs, int rows, int size, int offset) {
        for (int i = 0; i < rows; i += 2 ) {
            row(picaso, x, y + (i * size) + (i * MORTAR), pairs, size);
            row(picaso, x + offset, y + (i * size) + (i * MORTAR) + (size + MORTAR), pairs, size);
        }
    }
    
    // Takes four integers: x-coordinate, y-coordinate, number of pairs,
    // and size of squares as parameters.
    // Creates a row of black tiles with a blue x and white tiles
    // based on the number of pairs of tiles and the size of the tiles. 
    public static void row(Graphics picaso, int x, int y, int pairs, int size) {
        for (int i = 0; i < pairs; i++) {
            picaso.setColor(Color.BLACK);
            picaso.fillRect(x + (i * (2 * size)), y, size, size);
            picaso.setColor(Color.WHITE);
            picaso.fillRect(x + size + (i * (2 * size)), y, size, size);
            picaso.setColor(Color.BLUE);
            picaso.drawLine(x + (i * (2 * size)), y, x + size + (i * (2 * size)), y + size);
            picaso.drawLine(x + (i * (2 * size)), y + size, x + size + (i * (2 * size)), y);
        }
    }
    
}