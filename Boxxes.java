package Breakout;

import java.awt.Color;
import java.awt.Graphics2D;

public class Boxxes extends Sprite {

	public Boxxes(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void update(Keyboard keyboard) {
	}

	@Override
	public void draw(Graphics2D graphics) {
		graphics.setColor(Color.decode("#202020"));
		graphics.fillRect(getX(), getY(), getWidth(), getHeight());

	}

}
