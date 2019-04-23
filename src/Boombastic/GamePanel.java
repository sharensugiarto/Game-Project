package Boombastic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class GamePanel extends JPanel {

	private Image background;
	private Plane player = new Plane(new Point(400, 380));
	private int live = 6;
	private Vector<Bullet> bullet = new Vector<Bullet>();
	private Vector<Enemy> enemies = new Vector<Enemy>();
	private boolean isInGame = true;

	Thread playerControl = new Thread(new Runnable() {

		@Override
		public void run() {
			while (isInGame) {
				player.setIndex(player.getIndex()+1);;
				try {
					Iterator<Bullet> it1 = bullet.iterator();
					for (int i = bullet.size() - 1; i >= 0; i--) {
						Bullet tembak1 = bullet.get(i);
						tembak1.setPoint(new Point(tembak1.getPoint().x, tembak1.getPoint().y-20));
						for (Enemy f : enemies) {
							if (tembak1.getPoint().y + tembak1.getHeight() >= f.getPoint().y
									&& tembak1.getPoint().x <= f.getPoint().x + f.getWidth()
									&& tembak1.getPoint().x + tembak1.getWidth() >= f.getPoint().x
									&& tembak1.getPoint().y <= f.getPoint().y + f.getLength()) {
								bullet.remove(tembak1);
								enemies.remove(f);
								if(enemies.isEmpty()) {
									Main.frame.remove(GamePanel.this);
									Main.frame.add(new GameWin());
									Main.frame.validate();
									bullet.removeAllElements();
									isInGame = false;
								}
								break;
							}
						}
					}

				} catch (Exception e) {
				
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				repaint();
			}
		}
	});

	Thread generateEnemy = new Thread(new Runnable() {

		@Override
		public void run() {
			for (int i = 0; i < 15; i++) {
				Enemy a1 = new Enemy(new Point(new Random().nextInt(800), 50));
				enemies.add(a1);
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for (int i = 0; i < 15; i++) {
				try {
					Enemy a1 = new Enemy(new Point(new Random().nextInt(800), 150));
					enemies.add(a1);
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for (int i = 0; i < 15; i++) {
				try {
					Enemy a1 = new Enemy(new Point(new Random().nextInt(800), 250));
					enemies.add(a1);
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	});

	Thread enemyThread = new Thread(new Runnable() {

		@Override
		public void run() {
			while (isInGame) {
				int idx = -1;
				int idxEnemy = -1;
				try {
					for (Enemy f : enemies) {
						f.setIndex(f.getIndex() + 1);
						if (f.getPoint().x > 740)
							f.setCount(-15);
						else if (f.getPoint().x < 10)
							f.setCount(+15);

						f.getPoint().x += f.getCount();

						int rand = (int) (Math.random() * 1000);

						if (rand % 30 == 0) {
							f.getBullets().add(new Bullet(new Point(f.getPoint().x + f.getWidth()/2, f.getPoint().y + f.getLength())));
						}

						for (Bullet bullet2 : f.getBullets()) {

							bullet2.setPoint(new Point(bullet2.getPoint().x, bullet2.getPoint().y+20));

							if (bullet2.getPoint().y + bullet2.getHeight() >= player.getPoint().y &&
									bullet2.getPoint().x <= player.getPoint().x + player.getWidth() &&
									bullet2.getPoint().x + bullet2.getWidth() >= player.getPoint().x &&
									bullet2.getPoint().y <= player.getPoint().y + player.getLength()) {
								live--;
								if(live==0) {
									Main.frame.remove(GamePanel.this);
									Main.frame.add(new GameOver());
									Main.frame.validate();
									isInGame = false;
								}
							}
						}


					}
				} catch (Exception e) {
				}

				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				repaint();
			}
		}
	});

	KeyListener aa = (new KeyListener() {
		
		@Override
		public void keyTyped(KeyEvent e) {
	
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_LEFT) {
				if (player.getPoint().x - 20 >= 0) {
					player.setPoint(new Point(player.getPoint().x - 20, player.getPoint().y));
					player.setIndex(player.getIndex()+1);
					player.setIndex(player.getIndex()%3);
				}
			} else if (key == KeyEvent.VK_RIGHT) {
				if (player.getPoint().x + 20 <= 740) {
					player.setPoint(new Point(player.getPoint().x + 20, player.getPoint().y));
					player.setIndex(player.getIndex()+1);
					player.setIndex(player.getIndex()%3);
				}
			}
			else if(key == KeyEvent.VK_SPACE) {
				bullet.add(new Bullet(new Point(player.getPoint().x + player.getWidth()/2 - 2, player.getPoint().y)));
			}
			repaint();
		}
	});
	
	public GamePanel() {
		try {
			BufferedImage bgBuffer = ImageIO.read(new File("assets/background.jpg"));
			background = bgBuffer.getScaledInstance(bgBuffer.getWidth(), bgBuffer.getHeight(), Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		addKeyListener(aa);
	
		playerControl.start();
		generateEnemy.start();
		enemyThread.start();
	}
	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
		g.drawImage(player.getImage(), player.getPoint().x, player.getPoint().y, null);
		try {
			for (Bullet b : bullet) {
				g.drawImage(b.getImage(), b.getPoint().x, b.getPoint().y, Color.GREEN, null);
			}			
		} catch (Exception e) {
		}
		try {
			for (Enemy f : enemies) {
				g.drawImage(f.getImage(), f.getPoint().x, f.getPoint().y, null);
				for (Bullet bullet2 : f.getBullets()) {
					g.drawImage(bullet2.getImage(), bullet2.getPoint().x, bullet2.getPoint().y, Color.RED, null);
				}
			}
		} catch (Exception e) {
		}
	}

}
