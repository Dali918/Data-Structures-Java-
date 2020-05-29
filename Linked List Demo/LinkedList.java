public class LinkedList {

	private Link head;

	public void LinkedList() {

		this.head = null;
	}

	// TODO: IMPLEMENT THIS METHOD
	public Link delete(int key) {

		/*
		 * You can choose to implement this with two references or with just one. I've
		 * provided one already gratis - you're welcome!
		 */

		Link cur = head;
		Link prev = head;
		while (cur != null) {
			if (cur.data == key) {
				if (prev == head) {
					head = head.next;
				} else {
					prev.next = cur.next;
					return cur;
				}
			} else {
				prev = cur;
				cur = cur.next;
			}
		}

		return cur;

	} // end delete

	// Find is implemented as in the lecture slides
	public Link find(int key) {

		Link cur = head;

		while (cur != null) {
			if (cur.data == key)
				return cur;
			cur = cur.next;
		}

		return null;
	} // end find

	public void insertFirst(int data, String name) {

		Link newLink = new Link(data, name);
		newLink.next = this.head;
		this.head = newLink;
	} // end insertFirst

	// Use this method to look at the list
	public void displayList() {
		Link cur = head;

		System.out.println("\nList consists of:\n");
		while (cur != null) {
			cur.display();
			cur = cur.next;
		}
	} // end displayList
}
