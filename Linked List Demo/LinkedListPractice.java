public class LinkedListPractice {

	public static void main(String[] args) {
		
		LinkedList list = new LinkedList();
		
		list.insertFirst(7, "list?!?");
		list.insertFirst(6, "this");
		list.insertFirst(5, "in");
		list.insertFirst(4, "doing");
		list.insertFirst(3, "I");
		list.insertFirst(2, "am");
		list.insertFirst(1, "What");
		
		list.displayList();
		list.delete(7);
		
		list.displayList();
		
		/*
		 * ADD COMMANDS HERE FOR FIND AND DELETE
		 */
	}

}

