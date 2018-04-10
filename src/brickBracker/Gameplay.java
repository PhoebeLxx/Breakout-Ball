package brickBracker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener{
	private boolean player = false;
	private int score = 0;
	
	private Timer time;
	private int delay = 8;
	
	private int playerX = 310;
	private Brisk brisk;
	private Rectangle[][] block;
	
	private int ballPosX = 120;
	private int ballPosY = 350;
	private int ballDirX = -1;
	private int ballDirY = -2;
	
	public Gameplay() {
		brisk = new Brisk(10, 2, 100, 100, 500, 100);
		block = new Rectangle[10][2];
		createBlock();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		time = new Timer(delay, this);
		time.start();
	}
	
	public void paint(Graphics g) {
		//background
		g.setColor(Color.BLACK);
		g.fillRect(1, 1, 692, 592);
		
		//borders
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
		//paddle
		g.setColor(Color.GREEN);
		g.fillRect(playerX, 550, 100, 8);
		
		//ball
		g.setColor(Color.ORANGE);
		g.fillOval(ballPosX, ballPosY, 20, 20);
		
		//brisk
		brisk.paint(g);
		if(brisk.remain == 0) {
			g.setColor(Color.WHITE);
			g.setFont( new Font("Osaka", 10, 30));
			g.drawString("You Win!", 270, 270);
			g.drawString("You Score: " + score, 240, 320);
		}
		
		if(brisk.remain != 0 && ballPosY >= 600) {
			g.setColor(Color.WHITE);
			g.setFont( new Font("Osaka", 10, 30));
			g.drawString("(╬~皿~)ヽ(o`皿o)ノ", 190, 270);
			g.drawString("You Score: " + score, 240, 320);
		}
		
		g.dispose();
	}
	
	public void moveRight() {
		 player = true;
		 playerX += 30;
	}
	
	public void moveLeft() {
		 player = true;
		 playerX -= 30;
	}
	
	
	public void createBlock() {
		for(int i = 0; i < block.length; i++)
			for(int j = 0; j < block[i].length; j++)
				block[i][j] = new Rectangle(brisk.startX + brisk.briskWid * i, brisk.startY + brisk.briskHei * j, brisk.briskWid, brisk.briskHei);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		time.start();
		
		if(player) {
			ballPosX += ballDirX;
			ballPosY += ballDirY;
			if(ballPosX <= 0)
				ballDirX = -ballDirX;
			if(ballPosY <= 0)
				ballDirY = -ballDirY;
			if(ballPosX >= 670)
				ballDirX = -ballDirX;
			if((new Rectangle(playerX, 550, 100, 8)).intersects(new Rectangle(ballPosX, ballPosY, 20, 20)))
				ballDirY = -ballDirY;

			for(int i = 0; i < block.length; i++)
				for(int j = 0; j < block[i].length; j++)
					if(block[i][j] != null)
						if(block[i][j].intersects(new Rectangle(ballPosX, ballPosY, 20, 20))) {
							brisk.disappear(i, j);
							brisk.remain--;
							score += 10;
							block[i][j] = null;
							ballDirX = -ballDirX;
						}
		}
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerX >= 600) {
				playerX = 600;
			}else {
				moveRight();
			}
				
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerX <= 10) {
				playerX = 10;
			}else {
				moveLeft();
			}
				
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
	

}
