package Breakout;

import java.awt.Color;
import java.awt.Graphics2D;

public class Brick extends Sprite {
    public static final int HITS_BLACK = 3;
    public static final int HITS_WHITE= 2;
    public static final int HITS_GREEN = 1;

    private int hitsLeft;

    public Brick(int x, int y, int width, int height, int hits) {
        super(x, y, width, height);
        this.hitsLeft = hits;
    }

    public void hit() {
        if (hitsLeft > 0) {
            hitsLeft--;
        }
    }

    public boolean isDestroyed() {
        return hitsLeft <= 0;
    }

    @Override
    public void update(Keyboard keyboard) {
    }
    

    @Override
    public void draw(Graphics2D graphics) {
        if (isDestroyed()) return;

        graphics.setColor(getColor());
        graphics.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    private Color getColor() {
        switch (hitsLeft) {
        case HITS_BLACK:    return Color.BLACK;
        case HITS_WHITE: return Color.WHITE;
        case HITS_GREEN: return Color.GREEN;
        default:          return Color.GRAY;
        }
    }

    public static int hitsForRow(int row) {
        switch (row) {
        case 0: case 1: return HITS_GREEN;
        case 2: case 3: return HITS_WHITE;
        default: return HITS_BLACK;
        }
    }
    public int getPointsForHit() {
        switch (hitsLeft) {
            case HITS_GREEN: return 2;
            case HITS_WHITE: return 3;
            case HITS_BLACK: return 5;
            default: return 0;
        }
    }
}