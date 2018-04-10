package brickBracker;

import java.awt.Color;
import java.awt.Graphics;

public class Brisk {
	
	protected int[][] brisk;
	protected int remain;
	protected int row;
	protected int col;
	protected int startX;
	protected int startY;
	
	protected int briskWid;
	protected int briskHei;
	
	public Brisk(int row, int col, int x, int y, int rowLen, int colLen) {
		this.row = row;
		this.col = col;
		startX = x;
		startY = y;
		briskWid = rowLen / row;
		briskHei = colLen / col;
		brisk = new int[row][col];
		remain = row * col;
		
		for(int i = 0; i < brisk.length; i++)
			for(int j = 0; j < brisk[i].length; j++)
				brisk[i][j] = 1;
	}
	
	public void paint(Graphics g) {
		for (int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++)
				if(brisk[i][j] == 1) {
					g.setColor(Color.WHITE);
					g.fillRect(startX + briskWid * i, startY + briskHei * j, briskWid, briskHei);
					g.setColor(Color.BLACK);
					g.drawRect(startX + briskWid * i, startY + briskHei * j, briskWid, briskHei);
				}
		}
	}
	
	public void disappear(int i, int j) {
		this.brisk[i][j] = 0;
	}
	

}
