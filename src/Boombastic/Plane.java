package Boombastic;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Plane extends Characters{

	public Plane(Point point) {
		super(point, 0);
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

	@Override
	public int getWidth() {
		return 50;
	}

	@Override
	public int getLength() {
		return 50;
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
