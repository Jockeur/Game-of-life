package fr.jockeur.gol;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {
	
private final JFrame frame;
private final int width, height;
private boolean[][] cells;
private int round = 0;

	
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
		double tick = 1000000000.0 / 5.0;
		
		int tps = 0, fps = 0;
		
		long lastTime = System.currentTimeMillis();
		
		while (true) 
		{
			if(System.nanoTime() - nanoSecond > tick) 
			{
				nanoSecond += tick;
				tps++;
				round++;
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
		boolean[][] newCells = new boolean[width][height];
		
		for(int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				
				int count = 0;

				for(int xo = -1; xo < 2; xo++) {
					for (int yo =-1; yo < 2; yo++) {
						
						if (xo == 0 || yo == 0) continue;
						int nx = x + xo;
						int ny = y + yo;
								
						count += (nx >= 0 && ny > 0 && nx < width && ny < height && cells[nx][ny]) ? 1 : 0;
					}
				}
				newCells[x][y] = cells[x][y] ? (count == 2 || count == 3) : count == 3;
			}
		}
		
		cells = newCells;
		
	}
	
	protected void paintComponent(Graphics g) 
	{
		int xOffset = 720/width;
		int yOffset = 480/height;
		g.setColor(Color.RED);
		
		g.drawString("" + round, 10, 10);
		
		g.setColor(Color.BLACK);
		
		for(int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if(cells[x][y]) {
					g.fillRect(x * xOffset, y * yOffset, xOffset, yOffset);
				}
			}
		}
		
	}

	public static void main(String... args) 
	{
		new Main(50, 50, 200);
	}
	
	private static class Mouse implements MouseMotionListener, MouseListener{
		
		private final Main main;
		// private final random Random = new Random();
		
		private Mouse(Main main) {
			this.main = main;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// main.cells[random.nextInt]
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

}









