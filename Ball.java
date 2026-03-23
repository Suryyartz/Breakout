package Breakout;

import java.awt.Color;
import java.awt.Graphics2D;

public class Ball extends Sprite {

	private static final int INITIAL_DX = 3;
	private static final int INITIAL_DY = -4;
	private static final int PADDLE_THIRD_COUNT = 3;
	private static final int LEFT_BOUNCE_SPEED = -4;
	private static final int RIGHT_BOUNCE_SPEED = 4;
	private static final int CENTER_BOUNCE_SPEED = 2;
	private static final int BORDER_OFFSET = 1;

	private int dx = INITIAL_DX;
	private int dy = INITIAL_DY;

	public Ball(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public void bounceFromPaddle(int paddleX, int paddleWidth) {
		int ballCenter = getX() + getWidth() / 2;
		int third = paddleWidth / PADDLE_THIRD_COUNT;
		int leftThird = paddleX + third;
		int rightThird = paddleX + 2 * third;

		if (ballCenter < leftThird) {
			dx = LEFT_BOUNCE_SPEED; 
		} else if (ballCenter > rightThird) {
			dx = RIGHT_BOUNCE_SPEED;  
		} else {
			dx = (dx > 0) ? CENTER_BOUNCE_SPEED : -CENTER_BOUNCE_SPEED; 
		}
		dy = -Math.abs(dy);
	}

	public void bounceVertical() {
		dy = -dy;
	}

	public void bounceHorizontal() {
		dx = -dx;
	}

	public void reset(int x, int y) {
		setX(x);
		setY(y);
		dx = INITIAL_DX;
		dy = INITIAL_DY;
	}

	@Override
	public void update(Keyboard keyboard) {
		setX(getX() + dx);
		setY(getY() + dy);
		if (getX() <= BORDER_OFFSET || getX() + getWidth() >= 800 - BORDER_OFFSET) {
			dx = -dx;
		}
		if (getY() <= BORDER_OFFSET) {
			dy = -dy;
		}
	}

	@Override
	public void draw(Graphics2D graphics) {
		graphics.setColor(Color.white);
		graphics.fillOval(getX(), getY(), getWidth(), getHeight());
	}
}
