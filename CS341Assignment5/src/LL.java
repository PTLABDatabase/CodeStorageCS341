public class LL {
	static Node head = null;
	static double sum = 0;
	static double sd = 0;
	static double sq = 0;
	static double res = 0;
	static double mean = 0;
	static double ct = 0;

	void push(int data)
	{
	    Node newNode = new Node(data);
	    newNode.data = data;
	    newNode.next = (head);
	    (head) = newNode;
	}

	static void sumOfNodes(Node head) {
		if (head == null)
			return;
		sumOfNodes(head.next);
		sum = sum + head.data;
	}

	String sumOfNodesUtil(int count) {
		ct  = count;
		sumOfNodes(head);
		mean = sum/count;
		return "Sum of All Elements: " + sum + " | " + " Mean of All Elements: " + mean ;
	}
	
	static void sdOfNodes(Node head) {
		if(head ==null)
			return;
		sdOfNodes(head.next);
		sd += Math.pow((head.data - mean), 2);
	}
	
	String sdUtil() {
		sdOfNodes(head);
		sq = sd / ct;
        res = Math.sqrt(sq);
		return "Standard Deviation: " + res;
	}
}
