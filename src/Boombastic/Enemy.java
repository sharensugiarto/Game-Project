package Boombastic;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Enemy extends Characters{

	private Vector<Bullet> bullets = new Vector<Bullet>();
	private int count = +15;
	private int nembak= 0;

	public Enemy(Point point) {
		super(point, 7);
		index = 0;
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

	public int getNembak() {
		return nembak;
	}
	
	public void setNembak(int nembak) {
		this.nembak = nembak;
	}
	
	public Vector<Bullet> getBullets() {
		return bullets;
	}
	
	public void setBullets(Vector<Bullet> bullets) {
		this.bullets = bullets;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int getWidth() {

		return super.width;
	}

	@Override
	public int getLength() {

		return super.length;
	}

	@Override
	public int getCol() {
	
		return super.col;
	}

	@Override
	public int getRow() {
		
		return super.row;
	}

	@Override
	public Point getPoint() {
		
		return super.point;
	}

	@Override
	public void setPoint(Point point) {
	
		this.point = point;
	}

	@Override
	public void setIndex(int index) {

		this.index = index;
	}

	@Override
	public int getIndex() {
		
		return super.index;
	}

}
