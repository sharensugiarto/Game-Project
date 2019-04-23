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
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenuPanel extends JPanel {

	private Clip clip;
	private JLabel play;
	private JLabel exit;
	private JLabel help;
	private JLabel back;
	private Image bgr;

	public MainMenuPanel() {
		setLayout(null);

		try {
			BufferedImage bgBuffer = ImageIO.read(new File("assets/menuMain.png"));
			bgr = bgBuffer.getScaledInstance(bgBuffer.getWidth(), bgBuffer.getHeight(), Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}

		play = new JLabel("Play", JLabel.CENTER);
		play.setBounds(366, 255, 100, 50);
		play.setFont(new Font("Nexa Rust Slab Black Shadow 01", Font.PLAIN, 22));
		play.setForeground(Color.BLACK);
		play.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				play.setForeground(Color.black);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				play.setForeground(Color.white);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				Main.frame.remove(MainMenuPanel.this);
				JPanel panel = new GamePanel();
				Main.frame.add(panel);
				panel.requestFocus();
				Main.frame.validate();

			}
		});
		add(play);
		help = new JLabel("Help", JLabel.CENTER);
		help.setBounds(390, 325, 50, 50);
		help.setFont(new Font("Nexa Rust Slab Black Shadow 01", Font.PLAIN, 15));
		help.setForeground(Color.BLACK);
		help.addMouseListener(new MouseListener() {

			private Image img;

			@Override
			public void mouseReleased(MouseEvent e) {
		
			}

			@Override
			public void mousePressed(MouseEvent e) {
		
			}

			@Override
			public void mouseExited(MouseEvent e) {
				help.setForeground(Color.black);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				help.setForeground(Color.white);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				Main.frame.remove(MainMenuPanel.this);
				JPanel panel = new HelpPanel();
				Main.frame.add(panel);
				panel.requestFocus();
				Main.frame.validate();

			}
		});
		add(help);

		exit = new JLabel("Exit", JLabel.CENTER);
		exit.setBounds(390, 385, 50, 50);
		exit.setFont(new Font("Nexa Rust Slab Black Shadow 01", Font.PLAIN, 15));
		exit.setForeground(Color.BLACK);
		exit.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exit.setForeground(Color.black);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				exit.setForeground(Color.white);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		add(exit);

		try {
			clip = AudioSystem.getClip();
			AudioInputStream stream = AudioSystem.getAudioInputStream(new File("assets/NoTimeForCaution.wav"));
			clip.open(stream);
			clip.start();
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e1) {
			e1.printStackTrace();
		}

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bgr, 0, 0, null);

	}
}
