package Boombastic;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Characters {
	
	protected int width = 40;
	protected int length = 40;
	protected Point point;
	protected int index;
	protected int direction;
	protected int col = 3;
	protected int row = 8;
	protected BufferedImage[][] sprites = new BufferedImage[row][col];
	
	public Characters(Point point, int direction) {
		super();
		this.point = point;
		this.direction = direction;
		try {
			BufferedImage image = ImageIO.read(new File("assets/pesawat.png"));
			int width = image.getWidth(); 
			int height = image.getHeight();
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					sprites[i][j] = image.getSubimage(
							j*width/col, i*height/row,
							width/col, height/row);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Image getImage() {
		return sprites[direction][index%3].getScaledInstance(getWidth(), getLength(), Image.SCALE_SMOOTH);
	}
	
	public abstract int getWidth();
	
	public abstract int getLength();
	
	public abstract int getCol();
	
	public abstract int getRow();
	
	public abstract Point getPoint();
	
	public abstract int getIndex();

	public abstract void setPoint(Point point);
	
	public abstract void setIndex(int index);
	
}
