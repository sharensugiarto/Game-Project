package Boombastic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOver extends JPanel {
	
	private Image background;
	private JLabel back;

	public GameOver() {
		setLayout(null);
		
		try {
			BufferedImage bgBuffer = ImageIO.read(new File("assets/loses.png"));
			background = bgBuffer.getScaledInstance(bgBuffer.getWidth(), bgBuffer.getHeight(), Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 back = new JLabel("Back", JLabel.CENTER);
		 back.setBounds(650, 380, 50, 50);
		 back.setFont(new Font("Nexa Rust Slab Black Shadow 01", Font.PLAIN, 15));
		 back.setForeground(Color.WHITE);
		 back.addMouseListener(new MouseListener() {
				
				
				@Override
				public void mouseReleased(MouseEvent e) {
				
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
				
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					 back.setForeground(Color.white);
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					 back.setForeground(Color.black);
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					Main.frame.remove(GameOver.this);
					JPanel panel = new MainMenuPanel();
					Main.frame.add(panel);
					panel.requestFocus();
					Main.frame.validate();
				
				}
			});
			add(back);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);

	}

}
