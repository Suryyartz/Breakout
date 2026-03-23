package Breakout;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Game {
	private static final String GAME_OVER_VIDEO_PATH = "/Users/abbe/Desktop/videoplayback.mp4";
	private static final int INITIAL_LIVES = 3;

	private static final int PLAYER_START_X = 350;
	private static final int PLAYER_START_Y = 560;
	private static final int PLAYER_WIDTH = 90;
	private static final int PLAYER_HEIGHT = 15;

	private static final int BALL_START_X = 392;
	private static final int BALL_START_Y = 540;
	private static final int BALL_SIZE = 15;

	private static final int BOARD_START_X = 0;
	private static final int BOARD_START_Y = 0;

	private static final int BOTTOM_DEATH_OFFSET = 1;

	private static final int HUD_SCORE_X = 12;
	private static final int HUD_SCORE_Y = 24;
	private static final int HUD_LIVES_X = 700;
	private static final int HUD_LIVES_Y = 24;

	private static final int OVERLAY_ALPHA = 170;

	private static final String HUD_FONT_FAMILY = "SansSerif";
	private static final int HUD_FONT_SIZE = 18;
	private static final int GAME_OVER_FONT_SIZE = 48;
	private static final int GAME_OVER_TEXT_X = 250;
	private static final int GAME_OVER_TEXT_Y = 280;

	private static final String WIN_FONT_FAMILY = "SanSrif";
	private static final int WIN_TITLE_FONT_SIZE = 48;
	private static final int WIN_TITLE_X = 200;
	private static final int WIN_TITLE_Y = 280;
	private static final int WIN_SCORE_FONT_SIZE = 36;
	private static final int WIN_SCORE_X = 280;
	private static final int WIN_SCORE_Y = 340;

	private Player p;
	private Ball b;
	private Boxxes bbx;
	private List<Brick> bricks;
	private int score;
	private int lives = INITIAL_LIVES;
	private boolean gameOver;
	private boolean gameWon;
	private boolean gameOverVideoPlayed;
	
	private Highscore hi;
	private int tick;
	public Game(Highscore h,GameBoard board) {
		hi=h;
		tick=0;
		p = new Player(PLAYER_START_X, PLAYER_START_Y, PLAYER_WIDTH, PLAYER_HEIGHT);
		b = new Ball(BALL_START_X, BALL_START_Y, BALL_SIZE, BALL_SIZE);
		bbx = new Boxxes(BOARD_START_X, BOARD_START_Y, 800, 600);
		bricks = new ArrayList<>(new BrickGen().generate());
	}

	public void update(Keyboard keyboard) {
		tick++;
		if(tick%40==0)
			hi.andraTextPaKnapp(""+ tick);
		if(gameOver || gameWon) return;
		p.update(keyboard);
		b.update(keyboard);
		
		if(b.getY() > 600 - BOTTOM_DEATH_OFFSET) {
			lives--;
			int centerX = p.getX() + p.getWidth() / 2 - b.getWidth() / 2;
			b.reset(centerX, p.getY() - b.getHeight());
			if(lives<=0) {
				gameOver = true;
				playGameOverVideo();
			}
		}


		Rectangle ballRect = new Rectangle(b.getX(), b.getY(), b.getWidth(), b.getHeight());
		Rectangle playerRect = new Rectangle(p.getX(), p.getY(), p.getWidth(), p.getHeight());

		if (ballRect.intersects(playerRect)) {
			b.setY(p.getY() - b.getHeight());
			
			b.bounceFromPaddle(p.getX(), p.getWidth());
		}
		
		for (int i=0; i<bricks.size();i++) {
			Brick brick= bricks.get(i);
			if(brick.isDestroyed())continue;
			Rectangle brickRect= new Rectangle (brick.getX(),brick.getY(), brick.getWidth(), brick.getHeight());
			if(ballRect.intersects(brickRect)) {
				score +=brick.getPointsForHit();
				brick.hit();
				
				if (b.getX() + b.getWidth() <= brickRect.x || b.getX() >= brickRect.x + brickRect.width) {
					b.bounceHorizontal();
				} else {
					b.bounceVertical();
				}
				if(brick.isDestroyed())bricks.remove(i);
				break;
			}
		}
		if (bricks.isEmpty()) {
			gameWon = true;
			playGameOverVideo();
		}
	}

	public void draw(Graphics2D graphics) {
		bbx.draw(graphics);
		for (Brick brick : bricks) {
			brick.draw(graphics);
		}
		p.draw(graphics);
		b.draw(graphics);
		
		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font(HUD_FONT_FAMILY, Font.BOLD, HUD_FONT_SIZE));
		graphics.drawString("Score:"+ score, HUD_SCORE_X, HUD_SCORE_Y);
		graphics.drawString("Lives: " + lives, HUD_LIVES_X, HUD_LIVES_Y);
		
		if (gameOver) {
			graphics.setColor(new Color(0, 0, 0, OVERLAY_ALPHA));
			graphics.fillRect(BOARD_START_X, BOARD_START_Y, 800, 600);
			
			graphics.setColor(Color.WHITE);
			graphics.setFont(new Font(HUD_FONT_FAMILY, Font.BOLD, GAME_OVER_FONT_SIZE));
			graphics.drawString("GAME OVER", GAME_OVER_TEXT_X, GAME_OVER_TEXT_Y);


		}
		
		if (gameWon) {
			graphics.setColor(new Color(0, 0, 0, OVERLAY_ALPHA));
			graphics.fillRect(BOARD_START_X, BOARD_START_Y, 800, 600);
			graphics.setColor(Color.WHITE);
			graphics.setFont(new Font(WIN_FONT_FAMILY, Font.BOLD, WIN_TITLE_FONT_SIZE));
			graphics.drawString("Level Complete!", WIN_TITLE_X, WIN_TITLE_Y);
			graphics.setFont(new Font(WIN_FONT_FAMILY, Font.BOLD, WIN_SCORE_FONT_SIZE));
			graphics.drawString("Total Score: " + score, WIN_SCORE_X, WIN_SCORE_Y);
		}
	}

	public boolean isGameOver() {
		return gameOver;
	}

	private void playGameOverVideo() {
		if (gameOverVideoPlayed) return;
		gameOverVideoPlayed = true;
		new Thread(() -> {
			try {
				File video = new File(GAME_OVER_VIDEO_PATH);
				if (video.isFile() && Desktop.isDesktopSupported()) {
					Desktop.getDesktop().open(video);
				}
			} catch (Exception e) { }
		}).start();
	}
}
