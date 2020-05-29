/* STUDENT NAME HERE
 * 
 * 
 * INSTRUCTIONS
 * ------------
 * 
 * This DFS program makes use of a graph represented by an adjacency matrix.
 * Modify this file so that the graph is represented by an ADJACENCY LIST instead.
 * 
 * You'll need to use the linkList class included in this Exercise, and you should modify
 * its find() method to return a reference to an unvisited Link rather than searching
 * for a key value. To observe proper encapsulation, I've modified the other Link methods
 * so that each Link object contains a Vertex object as its data.
 * 
 */

// to run this program: C>java DFSApp
////////////////////////////////////////////////////////////////
class StackX {
	private final int SIZE = 20;
	private int[] st;
	private int top;

// ------------------------------------------------------------
	public StackX() // constructor
	{
		st = new int[SIZE]; // make array
		top = -1;
	}

// ------------------------------------------------------------
	public void push(int j) // put item on stack
	{
		st[++top] = j;
	}

// ------------------------------------------------------------
	public int pop() // take item off stack
	{
		return st[top--];
	}

// ------------------------------------------------------------
	public int peek() // peek at top of stack
	{
		return st[top];
	}

// ------------------------------------------------------------
	public boolean isEmpty() // true if nothing on stack
	{
		return (top == -1);
	}
// ------------------------------------------------------------
} // end class StackX
////////////////////////////////////////////////////////////////

class Vertex {
	public char label; // label (e.g. 'A')
	public boolean wasVisited;

// ------------------------------------------------------------
	public Vertex(char lab) // constructor
	{
		label = lab;
		wasVisited = false;
	}
// ------------------------------------------------------------
} // end class Vertex
////////////////////////////////////////////////////////////////

class Graph {
	private final int MAX_VERTS = 20;
	private LinkList vertexList[]; // list of vertices
//   private int adjList[];      
	// adjacency matrix
	private int nVerts; // current number of vertices
	private StackX theStack;

// ------------------------------------------------------------
	public Graph() // constructor
	{
		// Adjacency list
		vertexList = new LinkList[MAX_VERTS];
		theStack = new StackX();
	} // end constructor
// ------------------------------------------------------------

	public void addVertex(char lab) {
		// add vertex to a link list with the vertex as first, add the link list to the
		// "vertexList array"
		Vertex vert = new Vertex(lab);
		LinkList vertLL = new LinkList();
		vertLL.insertFirst(vert);
		vertexList[nVerts++] = vertLL;
	}

// ------------------------------------------------------------
	public void addEdge(int start, int end) {
		vertexList[start].insert(vertexList[end]);

	}

// ------------------------------------------------------------
	public void displayVertex(int i) {
		System.out.print(vertexList[i].first.v.label);
	}

// ------------------------------------------------------------
	public void dfs() // depth-first search
	{ // begin at vertex 0
		vertexList[0].first.v.wasVisited = true; // mark it
		displayVertex(0); // display it
		theStack.push(0); // push it

		while (!theStack.isEmpty()) // until stack empty,
		{
			// get an unvisited vertex adjacent to stack top
			Link current = getAdjUnvisitedVertex(theStack.peek());
			if (current == null) // if no such vertex,
				theStack.pop();
			else // if it exists,
			{
				current.v.wasVisited = true; // mark it
				displayVertex(current); // display it
				theStack.push(current); // push it
			}
		} // end while

		// stack is empty, so we're done
		for (int j = 0; j < nVerts; j++) // reset flags
			vertexList[j].v.wasVisited = false;
	} // end dfs
// ------------------------------------------------------------
	// returns an unvisited vertex adj to v

	public Link getAdjUnvisitedVertex(int v) {
		Link current=vertexList[v].first;
		while (current != null) {
			if (current.v.wasVisited == false)
				return current;
			current = current.next;
		}
		return null;
	} // end getAdjUnvisitedVertex()
// ------------------------------------------------------------
} // end class Graph
////////////////////////////////////////////////////////////////

class DFSApp {
	public static void main(String[] args) {
		Graph theGraph = new Graph();
		theGraph.addVertex('A'); // 0 (start for dfs)
		theGraph.addVertex('B'); // 1
		theGraph.addVertex('C'); // 2
		theGraph.addVertex('D'); // 3
		theGraph.addVertex('E'); // 4

		theGraph.addEdge(0, 1); // AB
		theGraph.addEdge(1, 2); // BC
		theGraph.addEdge(0, 3); // AD
		theGraph.addEdge(3, 4); // DE

		System.out.print("Visits: ");
		theGraph.dfs(); // depth-first search
		System.out.println();
	} // end main()
}

// end class DFSApp
////////////////////////////////////////////////////////////////
