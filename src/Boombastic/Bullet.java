package Boombastic;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

public class Bullet {

	private Point point;
	private int width = 5;
	private int height = 5;
	private BufferedImage[] bullet = new BufferedImage[1];

	public Bullet(Point point) {
		super();
		this.point = point;
		try {
			bullet[0] = ImageIO.read(new File("assets/shot.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Image getImage() {
		return bullet[0].getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

}
