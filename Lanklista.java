package Breakout;
import java.io.*;

public class Lanklista {
	private Nod head;
	public Lanklista() {
		head = null;
	}
	
	public void add(String name, int score) {
		Nod node = new Nod(name, score);
		node.setNext(head);
		head = node;
	}
	
	public Nod getHead() {
		return head;
	}
	
	public void savetoFile(String fileName) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) { 
			Nod tmp = head; 
			while (tmp != null) { 
				writer.println(tmp.getName() + " -> " + tmp.getData());
				tmp = tmp.getNext();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Program.main(args);
	}
}
