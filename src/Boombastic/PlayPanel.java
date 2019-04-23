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

public class PlayPanel extends JPanel {

	private Image backs;
	private JLabel back;

	public PlayPanel() {
		setLayout(null);

		try {
			BufferedImage bgBuffer = ImageIO.read(new File("assets/backgrounds.png"));
			backs = bgBuffer.getScaledInstance(bgBuffer.getWidth(), bgBuffer.getHeight(), Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		back = new JLabel("Back", JLabel.CENTER);
		back.setBounds(406, 380, 500, 50);
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
				Main.frame.remove(PlayPanel.this);
				JPanel panel = new MainMenuPanel();
				Main.frame.add(panel);
				panel.requestFocus();
				Main.frame.validate();

			}
		});
		add(back);
	}

//	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backs, 0, 0, null);

	}
}
