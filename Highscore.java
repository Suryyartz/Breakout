package Breakout;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Highscore extends JPanel {
	private static final int BEST_PLAYER = 3;
	
	private JTextArea textArea;
	private Lanklista list;
	private JLabel text;
	private JButton knapp;

	public Highscore() {
		list = new Lanklista();
		
		text = new JLabel("Highscore");
		knapp = new JButton("OK");
		textArea = new JTextArea(10, 12);
		textArea.setEditable(false);
		
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(150, 400));
		
		add(text, BorderLayout.NORTH);
		add(new JScrollPane(textArea), BorderLayout.CENTER);
		add(knapp, BorderLayout.SOUTH);
	}
	
	public void andraTextPaKnapp(String nytext) {
		knapp.setText(nytext);
	}
	
	public void updateDisplay() {
		textArea.setText("");
		Nod tmp = list.getHead();
		List<Nod> basta = new ArrayList<>();
		while(tmp != null) {
			basta.add(tmp);
			tmp = tmp.getNext();
		}

		Collections.sort(basta, (a,b) -> b.getData() - a.getData());
		for(int i = 0; i < Math.min(BEST_PLAYER, basta.size()); i++) {
			textArea.append(basta.get(i).getName() + " - " + basta.get(i).getData() + "\n");
		}
	}
	
	public void addScore(String name, int score) {
		list.add(name, score);
		updateDisplay();
	}
}



