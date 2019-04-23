package Boombastic;

import javax.swing.JFrame;

public class Main {

	public static JFrame frame;

	public Main() {
		frame = new JFrame("main");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 488);
		frame.setLocationRelativeTo(null);
		frame.add(new MainMenuPanel());
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new Main();
	}
}
