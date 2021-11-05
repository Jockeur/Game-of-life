package fr.jockeur.gol;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {
	
private final JFrame frame;
	
	public Main() 
	{
		frame = new JFrame("Game Of Life");
		frame.setSize(720, 480);
		frame.setResizable(false);
		frame.setUndecorated(true);
		
		frame.setLocationRelativeTo(null);
		
		frame.setContentPane(this);
		frame.setBackground(Color.blue);
		
		frame.setVisible(true);
	}
	
	private void run() 
	{
		long nanoSecond = System.nanoTime();
		double tick = 1000000000.0 / 20.0;
		
		int tps = 0, fps = 0;
		
		long lastTime = System.currentTimeMillis();
		
		while (true) 
		{
			if(System.nanoTime() - nanoSecond > tick) 
			{
				tps++;
				update();
			}
			else 
			{
				fps++;
				frame.repaint();
			}
			
			if(System.currentTimeMillis() - lastTime >= 1000)
			{
				System.out.println(tps+" TPS - "+fps+" FPS");
				tps = 0;
				fps = 0;
				
				System.gc();
			}
			
		}
	}
	
	private void update() 
	{
		
	}
	
	protected void paintComponent(Graphics g) 
	{
		
	}

	public static void main(String... args) 
	{
		new Main();
	}

}
