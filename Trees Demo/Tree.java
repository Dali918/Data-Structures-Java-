import java.util.Stack;

class Tree {
	private Node root; // first node of tree

// -------------------------------------------------------------
	public Tree() // constructor
	{
		root = null;
	} // no nodes in tree yet
// -------------------------------------------------------------

	public Node find(int key) // find node with given key
	{ // (assumes non-empty tree)
		Node current ;
		current=this.root;
		while (current != null) {
			if (current.iData == key)
				return current;
			if (current.iData < key)
				current = current.rightChild;
			else
				current = current.leftChild;
		}
		return current;
	}// end find()
// -------------------------------------------------------------

	public void insert(int id, double dd) {
		Node newNode = new Node();
		newNode.iData = id;
		newNode.dData = dd;

		if (this.root == null) {
			this.root = newNode;
		} else {
			Node current = this.root;
			while (true) {
				if (current.iData <= newNode.iData) {
					if (current.rightChild == null) {
						current.rightChild = newNode;
						return;
					} else {
						current = current.rightChild;
					}

				} else {
					if (current.leftChild == null) {
						current.leftChild = newNode;
						return;
					} else {
						current = current.leftChild;
					}
				}
			}

		}
	} // end insert()
// -------------------------------------------------------------

	public boolean delete(int key) // delete node with given key
	{ // (assumes non-empty list)
		Node current = this.root;
		Node parent = null;
		boolean isLeft = false;

		while (current != null) {
			if (current.iData == key) {
				if (current.leftChild != null && current.rightChild != null) {
					Node successor = this.getSuccessor(current);
					successor.rightChild = current.rightChild;
					successor.leftChild = current.leftChild;
					if (current == this.root) {
						this.root = successor;
						return true;
					} else {
						if (isLeft)
							parent.leftChild = successor;
						else
							parent.rightChild = successor;
						return true;
					}
				} else if (current.leftChild == null && current.rightChild == null) {
					if (current == this.root) {
						this.root = null;
					} else {
						if (isLeft)
							parent.leftChild = null;
						else
							parent.rightChild = null;
						return true;
					}
				}

				if (current.leftChild == null && current.rightChild != null) {
					if (current == this.root) {
						this.root = current.rightChild;
					} else {
						if (isLeft)
							parent.leftChild = current.rightChild;
						else
							parent.rightChild = current.rightChild;
						return true;
					}
				} else {
					if (current == this.root) {
						this.root = current.leftChild;
					} else {
						if (isLeft)
							parent.leftChild = current.leftChild;
						else
							parent.rightChild = current.leftChild;
						return true;
					}
				}
			}
			if (current.iData < key) {
				parent = current;
				current = current.rightChild;
				isLeft = false;
			} else {
				parent = current;
				current = current.leftChild;
				isLeft = true;
			}

		}
		return false;

		// success
	} // end delete()
// -------------------------------------------------------------
	// returns node with next-highest value after delNode
	// goes to right child, then right child's left descendents

	private Node getSuccessor(Node delNode) {
		Node successorParent = delNode;
		Node successor = delNode;
		Node current = delNode.rightChild; // go to right child
		while (current != null) // until no more
		{ // left children,
			successorParent = successor;
			successor = current;
			current = current.leftChild; // go to left child
		}
		// if successor not
		if (successor != delNode.rightChild) // right child,
		{ // make connections
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}
		return successor;
	}

// -------------------------------------------------------------
	public void traverse(int traverseType) {
		switch (traverseType) {
		case 1:
			System.out.print("\nPreorder traversal: ");
			preOrder(root);
			break;
		case 2:
			System.out.print("\nInorder traversal:  ");
			inOrder(root);
			break;
		case 3:
			System.out.print("\nPostorder traversal: ");
			postOrder(root);
			break;
		}
		System.out.println();
	}

// -------------------------------------------------------------
	private void postOrder(Node localRoot) {
		if (localRoot != null) {
			postOrder(localRoot.leftChild);
			postOrder(localRoot.rightChild);
			localRoot.displayNode();
		}

	}

// -------------------------------------------------------------
	private void inOrder(Node localRoot) {
		if (localRoot != null) {
			inOrder(localRoot.leftChild);
			localRoot.displayNode();
			inOrder(localRoot.rightChild);
		}

	}

// -------------------------------------------------------------
	private void preOrder(Node localRoot) {
		if (localRoot != null) {
			localRoot.displayNode();
			preOrder(localRoot.leftChild);
			preOrder(localRoot.rightChild);
		}

	}

// -------------------------------------------------------------
	public void displayTree() {
		Stack globalStack = new Stack();
		globalStack.push(root);
		int nBlanks = 32;
		boolean isRowEmpty = false;
		System.out.println("......................................................");
		while (isRowEmpty == false) {
			Stack localStack = new Stack();
			isRowEmpty = true;

			for (int j = 0; j < nBlanks; j++)
				System.out.print(' ');

			while (globalStack.isEmpty() == false) {
				Node temp = (Node) globalStack.pop();
				if (temp != null) {
					System.out.print(temp.iData);
					localStack.push(temp.leftChild);
					localStack.push(temp.rightChild);

					if (temp.leftChild != null || temp.rightChild != null)
						isRowEmpty = false;
				} else {
					System.out.print("--");
					localStack.push(null);
					localStack.push(null);
				}
				for (int j = 0; j < nBlanks * 2 - 2; j++)
					System.out.print(' ');
			} // end while globalStack not empty
			System.out.println();
			nBlanks /= 2;
			while (localStack.isEmpty() == false)
				globalStack.push(localStack.pop());
		} // end while isRowEmpty is false
		System.out.println("......................................................");
	} // end displayTree()
// -------------------------------------------------------------
} // end class Tree