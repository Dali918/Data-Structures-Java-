import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Huffman {
	private PQ theQueue;
	private String inString; // input from user
	private int strlen;
	private String capsString; // converted to all caps
	private Tree[] treeArray;
	private Tree huffTree; // Huffman tree
	private int freqTable[]; // letter frequencies
	private int alphabetSize; // size of frequency table
	private String codeTable[]; // code for each letter
	private String codedMsg; // binary string
	private String decodedMsg; // back to original msg
// -------------------------------------------------------------

	Huffman(String s) // constructor
	{
		inString = s;
		strlen = inString.length();
		alphabetSize = 28; // 26 letters, cr, lf
		theQueue = new PQ(alphabetSize); // make the queue
		capsString = "";
		codeTable = new String[alphabetSize]; // make code table

		makeFreqTable(); // construct frequency table
		queueTrees(); // put one-node trees in queue
		makeHuffTree(); // construct Huffman tree
	} // end constructor
// -------------------------------------------------------------

	public void displayTree() {
		if (huffTree != null)
			huffTree.display();
	}

// -------------------------------------------------------------
	private void makeFreqTable() {
		freqTable = new int[alphabetSize];
		int temp;

		for (int j = 0; j < strlen; j++) // for every char
		{ // of user's input msg
			char ch = inString.charAt(j);
			if (ch > 96 && ch < 123) // lowercase (97-122)
			{
				temp = (int) ch - 32; // convert to upper
				ch = (char) temp; // (65-90)
			}
			if (ch == 32) // space becomes 91
				ch = 91;
			else if (ch == 10) // linefeed becomes 92
				ch = 92;
			else if (ch < 65 || ch > 92) // if not uppercase
				continue; // letter, ignore it
			capsString = capsString + ch; // add to caps string
			int index = (int) ch - 65; // convert to 0 to 27
			freqTable[index]++; // keep count in
		} // end for // frequency table
		System.out.println(capsString); // all-caps string
		for (int j = 0; j < 28; j++) // row of characters
			System.out.print((char) (j + 65) + " ");
		System.out.println("");
		for (int j = 0; j < 28; j++) // row of frequencies
			System.out.print(freqTable[j] + " ");
		System.out.println("");
	} // end makeFreqTable()

// -------------------------------------------------------------
// TODO: IMPLEMENT THIS METHOD!
//--------------------------------------------------------------

	private void queueTrees() // put trees in queue
	{
		for (int j = 0; j < alphabetSize; j++) // for each char in frequency table
		{

			// Description:
			// This method goes through the frequency table, creates a one-Node tree
			// for each char that appears in the original message, and inserts that tree
			// into the PQ.
			// The insert method of the PQ stores in order of increasing frequency (so
			// lowest frequency
			// is at the top of the queue).

			// Implement:
			// Check frequency of char. If 0, don't add it to the PQ (we don't need it).
			// Skip to next char.
			if (freqTable[j] == 0) {
				continue;
			}

			// Make a Node with the char and its frequency (which you get from the frequency
			// table
			// ** NOTE **: In doing this, you need to add 65 to each char to make it the
			// correct character!
			// This is because the index of this char in the frequency table has had 65
			// subtracted from it
			// in the makeFreqTable method in order to give it an index of 0-27.
			Node newNode = new Node((char) (j + 65), freqTable[j]);
			Tree newTree = new Tree(newNode);
			theQueue.insert(newTree);

			// Insert this Node into the PQ (you'll have to take one additional step to do
			// this:
			// study the PQ insert method carefully to see what that step is.)

		}
	} // end queueTrees()

// -------------------------------------------------------------
// TODO: IMPLEMENT THIS METHOD!
//--------------------------------------------------------------

	private void makeHuffTree() // make Huffman tree
	{
		while (theQueue.getSize() > 1) // repeat until one tree remains in PQ
		{

			// Description:
			// This method combines all the trees in the PQ in the correct order of
			// increasing frequency
			// to make a single tree, the huffTree, that can be walked to generate a unique
			// Huffman code
			// for each char.

			// Implement:
			// Get the trees with the smallest frequencies, storing them in a left and right
			// child
			Tree treeOne = theQueue.remove();
			Tree treeTwo = theQueue.remove();
			// Add their frequencies and store it in a new Node whose char is '+'
			// (This marks the node as a junction rather than a leaf)
			int sumFreq = treeOne.root.freq + treeTwo.root.freq;
			Node junction = new Node('+', sumFreq);
			// Make a tree from this Node and attach the trees you got above as its children
			junction.leftChild = treeOne.root;
			junction.rightChild = treeTwo.root;
			Tree newTree = new Tree(junction);
			// Reinsert this tree into the PQ.
			theQueue.insert(newTree);

		}

		huffTree = theQueue.remove(); // final tree
	} // end makeHuffTree()

// -------------------------------------------------------------
// TODO: IMPLEMENT THIS METHOD!
//--------------------------------------------------------------

	private void makeCodeTable(Node nd, String binaryCode) {

		// Description:
		// This method uses recursion to traverse the huffTree and build a binary string
		// for each stored char. It walks until it finds a leaf, adding "0" to the
		// binaryCode string
		// every time it has to turn left, or "1" every time it has to turn right.
		// When it encounters a leaf, it calculates the codeTable index of the char
		// stored there
		// by subtracting 65 from it. Then it stores the binaryCode string in the
		// codeTable at that index.

		// Implement:
		// If the Node's char is '+', it's not a leaf. Call this method recursively to
		// walk
		// this Node's left child, then right child. Add an appropriate 0 or 1 to the
		// String that is passed
		// to each recursive call.
		if (nd.ch != '+') {
			int index = (int) nd.ch - 65;
			codeTable[index] = binaryCode;
			return;
		}

		// If the Node's char is anything else, it's a char stored in a leaf. Calculate
		// its index
		// in the codeTable by subtracting 65, then store the binaryCode String there.
		// This is the base case, so you need to do one more thing to terminate the call
		// and pass
		// control back to higher-level calls.

		else {
			makeCodeTable(nd.leftChild, binaryCode + "0");
			makeCodeTable(nd.rightChild, binaryCode + "1");
		}

	} // end makeCodeTable()

// -------------------------------------------------------------
// TODO: IMPLEMENT THIS METHOD!
//--------------------------------------------------------------

	public void code() // encode the msg
	{

		// Description:
		// This method first creates a codeTable using the makeCodeTable method, which
		// walks
		// the huffTree to generate a unique Huffman Code for each char in the original
		// message.
		// Then it produces a coded message (a string of 0s and 1s) by looking up each
		// char
		// in the original message in the codeTable and adding its Huffman Code to the
		// coded string.

		// Implement:
		// Create the codeTable by calling makeCodeTable with the appropriate
		// parameters.
		String binaryCode = "";
		makeCodeTable(huffTree.root, binaryCode);
		// Initialize codedMsg to an empty string.
		codedMsg = "";
		// Walk through capsString. For each char in capsString, subtract 65 to get its
		// codeTable index,
		// then look up that char's Huffman Code in codeTable. Add this Huffman Code to
		// codedMsg.
		for (int i = 0; i < capsString.length(); ++i) {
			int lookup = ((int) capsString.charAt(i)) - 65;
			String code = codeTable[lookup];
			codedMsg = codedMsg + code;

		}
		System.out.println("Coded msg:\n" + codedMsg);
	} // end code()

// -------------------------------------------------------------
// TODO: IMPLEMENT THIS METHOD!
//--------------------------------------------------------------

	public void decode() {

		// Description:
		// This method uses the huffTree to walk the coded message until it encounters a
		// leaf,
		// then adds the char in that leaf to the decoded message.

		// Implement:
		// Initialize decodedMsg to an empty string.
		decodedMsg = "";
		// Move through the entire codedMsg one 0 or 1 at a time. Use each 0 or 1 to
		// walk down the
		// huffTree from its root until the current Node doesn't contain '+' as its
		// char.
		// (This means you've found a leaf with an actual char.)
		Node current = huffTree.root;
		for (int i = 0; i < codedMsg.length(); ++i) {
			if (codedMsg.charAt(i) == '0')
				current = current.leftChild;
			if (codedMsg.charAt(i) == '1')
				current = current.rightChild;
			if (current.ch != '+') {
				getChar(current);
				current = huffTree.root;
			}

		}

		// Pass this leaf Node to the helper function getChar, which will attach the
		// right
		// char to decodedMsg for you.

		// Repeat until the entire codedMsg has been traversed.

		System.out.println("Decoded msg:\n" + decodedMsg);
	} // end decode()

	// helper function to attach readable chars to decoded message
	public void getChar(Node nd) {

		char add;

		if (nd.ch == 91)
			add = ' ';
		else if (nd.ch == 92)
			add = '\n';
		else
			add = nd.ch;

		decodedMsg = decodedMsg + add;
	} // end getChar

	// this prints the codeTable for inspection
	public void displayCodeTable() {

		for (int j = 0; j < alphabetSize; j++) {
			if (codeTable[j] == null)
				continue;
			char ch = (char) (j + 65);
			System.out.println(ch + " " + codeTable[j]);
		}
	} // end displayCodeTable
// -------------------------------------------------------------
} // end class Huffman