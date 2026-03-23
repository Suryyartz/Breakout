package Breakout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Player extends Sprite
{
	private static final int MOVEMENT_DELTA = 10;
	private static final int MIN_X = 0;
	private static final int MAX_X = 710;

	public Player(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}

	@Override
	public void update(Keyboard keyboard)
	{
		if(keyboard.isKeyDown(Key.Right) || keyboard.isKeyDown(Key.D))
			setX(getX() + MOVEMENT_DELTA);
		if(keyboard.isKeyDown(Key.Left) || keyboard.isKeyDown(Key.A))
			setX(getX() - MOVEMENT_DELTA);
		if(getX() < MIN_X)
			setX(MIN_X);
		if(getX() > MAX_X)
			setX(MAX_X);
		
	}

	@Override
	public void draw(Graphics2D graphics)
	{
		graphics.setColor(Color.white);
		graphics.fillRect(getX(), getY(), getWidth(), getHeight());
	}

}


