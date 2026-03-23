package Breakout;

import java.util.ArrayList;
import java.util.List;

public class BrickGen {
	
	private static final int COLS=10;
	private static final int ROWS=6;
	private static final int GAP=6;
	private static final int RAW_GAP=4;
    private final int BOARD_WIDTH =800;
    private final int START_Y=50;
    
    
    public List<Brick>generate() {
    	List<Brick> bricks = new ArrayList<>();
    	int totalGapwidth=(COLS+1)*GAP;
    	int brickWidth =(BOARD_WIDTH- totalGapwidth) / COLS;
    	int brickHeight=22;
    	
    	for (int i =0 ; i<ROWS;i++) {
    		for(int j=0;j<COLS;j++) {
    			int x=GAP + j*(brickWidth+GAP);
    			int y= START_Y + i *(brickHeight + RAW_GAP);
    			int hits=Brick.hitsForRow(i);
    			bricks.add(new Brick(x,y,brickWidth,brickHeight,hits));
    			
    		}
    	}
    	return bricks;
    }
    
}
