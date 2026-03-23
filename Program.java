package Breakout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.*;

public class Program extends JFrame {
	GameBoard board;
	Highscore hi;
	public Program() {
		hi= new Highscore();
		
		board = new GameBoard(hi);
		setLayout(new BorderLayout());
		add(board, BorderLayout.CENTER);
		add(hi, BorderLayout.WEST);
		add(new JLabel("Latest runs"), BorderLayout.EAST);
		setResizable(false);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		board.grabFocus();
		board.start();
	}

	@Override
	protected void processKeyEvent(KeyEvent e) {
		super.processKeyEvent(e);
		board.processKeyEvent(e);
	}

	public static void main(String[] args) {
		Program program = new Program();
	}

}
