package fr.jockeur.gol;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {
	
private final JFrame frame;
private final int width, height;
private boolean[][] cells;

	
	public Main(int width, int height, int n) 
	{
		
		this.width = width;
		this.height = height;
		
		cells = new boolean[width][height];
		 generateRandomCells(n);
		
		frame = new JFrame("Game Of Life");
		frame.setSize(720, 480);
		frame.setResizable(false);
		frame.setUndecorated(true);
		
		frame.setLocationRelativeTo(null);
		
		frame.setContentPane(this);
		frame.setBackground(Color.blue);
		
		frame.setVisible(true);
		
		run();
	}
	
	private void generateRandomCells(int x) {
		Random random = new Random();
		
		List<String> cantPlace = new ArrayList<>();
		
		while(x > 0) {
			int rx = random.nextInt(width);
			int ry = random.nextInt(height);
			
			if(cantPlace.contains(rx+"-"+"ry")) continue;
			
			cantPlace.add(rx+"-"+ry);
			
			cells[rx][ry] = true;
			
			x--;
		}
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
				nanoSecond += tick;
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
				lastTime = System.currentTimeMillis();
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
		int xOffset = 720/width;
		int yOffset = 480/height;
		
		for(int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if(cells[x][y]) {
					g.drawRect(x * xOffset, y * yOffset, xOffset, yOffset);
				}
			}
		}
		
	}

	public static void main(String... args) 
	{
		new Main(9, 9, 10);
	}

}
