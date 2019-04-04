package cobaLatihan;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Coba1 extends JFrame {

	Rectangle pesawat = new Rectangle();
	Rectangle musuh = new Rectangle();
	ImageIcon img = new ImageIcon("pesawat.png");
	int w, h;
	int x = 0;
	boolean valid = true;
	boolean kiri = true;
	Vector<Rectangle> bullet = new Vector<Rectangle>();

	KeyListener controller = new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_W:
				pesawat.y -= 20;
				break;
			case KeyEvent.VK_A:
				pesawat.x -= 20;
				break;
			case KeyEvent.VK_S:
				pesawat.y += 20;
				break;
			case KeyEvent.VK_D:
				pesawat.x += 20;
				break;
			case KeyEvent.VK_SPACE:
				bullet.add(new Rectangle((int) pesawat.getCenterX(), pesawat.y - 5, 2, 5));
				break;
			default:
				break;
			}
			repaint();
		}
	};

	@Override
	public void paint(Graphics g) {
//		super.paint(g);
		g.clearRect(0, 0, 1000, 1000);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1000, 1000);
		if(valid) {
			g.drawImage(img.getImage(), musuh.x, musuh.y, musuh.x + w, musuh.y + h, x * w, 7 * h, (x * w) + w, (7 * h) + h, null);
		}
		g.drawImage(img.getImage(), pesawat.x, pesawat.y, pesawat.x + w, pesawat.y + h, x * w, 0, (x * w) + w, h, null);
		for (Rectangle r : bullet) {
			if (r.y >= 0) {
				g.setColor(Color.MAGENTA);
				g.fillOval(r.x, r.y, r.width, r.height);
			}
			if(musuh.intersects(r)) {
				valid = false;
			}
		}
		if(valid) {
			if(kiri) {
				musuh.x -= 20;
				if(musuh.x - 20 < 0) {
					kiri = false;
				}
			}
			else if(!kiri) {
				musuh.x += 20;
				if(musuh.x + 20 > 400 - w) {
					kiri = true;
				}
			}			
		}
	}

	Thread th = new Thread(() -> {
		while (true) {
			x++;
			x %= 3;
			try {
				Iterator<Rectangle> it = bullet.iterator();
				while (it.hasNext()) {
					Rectangle peluru = (Rectangle) it.next();
					peluru.y -= 40;
					if (peluru.y <= 0) {
						bullet.remove(peluru);
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	});

	public Coba1() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 500);
		setVisible(true);
		w = img.getIconWidth() / 3;
		h = img.getIconHeight() / 8;
		pesawat.x = musuh.x = 200 - w / 2;
		pesawat.y = 400;
		musuh.y = 100;
		pesawat.height = musuh.height = h;
		pesawat.width = musuh.width = w;
		addKeyListener(controller);
		th.start();
	}

	public static void main(String[] args) {
		new Coba1();

	}

}
