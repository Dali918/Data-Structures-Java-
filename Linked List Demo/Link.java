

public class Link {
	
	public int data;
	public String name;
	
	public Link next;
	
	public Link(int data, String name) {
		this.data = data;
		this.name = name;
		next = null;
	}
	
	public void display() {
		System.out.printf("Name and data: %s, %d%n", name, data);
	}	
}
