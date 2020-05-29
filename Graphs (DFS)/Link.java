/* 
 * Dalitso Banda
 */

// demonstrates linked list
// to run this program: C>java LinkList2App
////////////////////////////////////////////////////////////////
class Link {
	public Vertex v;
	public Link next; // next link in list
// -------------------------------------------------------------

	public Link(Vertex vert) // constructor
	{
		v = vert;
	}

// -------------------------------------------------------------
	public void displayLink() // display ourself
	{
		System.out.print("{" + v.label + ", Visited: " + v.wasVisited + "} ");
	}
} // end class Link
////////////////////////////////////////////////////////////////

class LinkList {
	public Link first; // ref to first link on list

// -------------------------------------------------------------
	public LinkList() // constructor
	{
		first = null; // no links on list yet
	}

// -------------------------------------------------------------
	public void insertFirst(Vertex vert) { // make new link

		Link newLink = new Link(vert);
		newLink.next = first; // it points to old first link
		first = newLink;
		// now first points to this
	}

	public void insert(LinkList vert) {
		Link current = first;
		while (true) {
			if (current.next == null) {
				current.next = new Link(vert.first.v);
				break;
			} else
				current = current.next;
		}

	}

// -------------------------------------------------------------
	public Link find() // find link with given key
	{ // (assumes non-empty list)
		Link current = first; // start at 'first'
		while (current != null) // while not at the end of the list,
		{
			if (current.v.wasVisited == false) // if current vertex is unvisited,
				return current; // return the Link
			else // already visited,
				current = current.next; // check the next link in the list
		}
		return current;
	} // return null if all links in the list are visited

// -------------------------------------------------------------
	public Link delete(char c) // delete link with given key
	{ // (assumes non-empty list)
		Link current = first; // search for link
		Link previous = first;
		while (current.v.label != c) {
			if (current.next == null)
				return null; // didn't find it
			else {
				previous = current; // go to next link
				current = current.next;
			}
		} // found it
		if (current == first) // if first link,
			first = first.next; // change first
		else // otherwise,
			previous.next = current.next; // bypass it
		return current;
	}

// -------------------------------------------------------------
	public void displayList() // display the list
	{
		System.out.print("List (first-->last): ");
		Link current = first; // start at beginning of list
		while (current != null) // until end of list,
		{
			current.displayLink(); // print data
			current = current.next; // move to next link
		}
		System.out.println("");
	}
// -------------------------------------------------------------
} // end class LinkList
////////////////////////////////////////////////////////////////

class LinkListApp {
	public static void main(String[] args) {
		LinkList theList = new LinkList(); // make list

		// ADD CODE HERE TO INSTERT VERTICES IF DESIRED
		// AND TEST INSERT / DELETE METHODS

		theList.displayList(); // display list

	} // end main()
} // end class LinkList2App
////////////////////////////////////////////////////////////////
