package Breakout;

public class Nod {
	private String name;
	private int data;
	private Nod next;
	
	public Nod(String name, int startData) {
		this.name = name;
		this.data = startData;
		this.next = null;
	}
	
	public Nod getNext() {
		return next;
	}
	public void setNext(Nod next) {
		this.next = next;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public int getData() 
	{
		return data; 
	}
	public void setData(int data) {
		this.data = data; 
	}
	

}
