package brickBracker;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame win = new JFrame();
		Gameplay game = new Gameplay();
		win.setBounds(10, 10, 700, 600);
		win.setTitle("Breakout Ball");
		win.setResizable(false);
		win.add(game);
		win.setVisible(true);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
 
}
