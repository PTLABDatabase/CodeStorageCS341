public class LL {
	
	//list of needed variables for computing sum, mean and standard deviation
	
	static Node head = null; // head of list 
	static double sum = 0; // sum of all elements in list
	static double sd = 0; // standard deviation of all elements in list
	static double sq = 0;// square root variable
	static double res = 0;// resultant of square root
	static double mean = 0;// mean of elements in list
	static double ct = 0; // count of all elements in list

	// Enqueueing data into the list
	
	void push(int data)
	{
	    Node newNode = new Node(data); //creating a newNode
	    newNode.data = data; // getting node data
	    newNode.next = (head); // setting head to point to next node
	    (head) = newNode; // head holds newNode
	}
	
	//Calculate the sum of elements in list

	static void sumOfNodes(Node head) {
		if (head == null) //exit if head ==0
			return;
		sumOfNodes(head.next); // calling sumOfNodes function with the next in list
		sum = sum + head.data; // double variable sum added with new values
	}

	String sumOfNodesUtil(int count) { 
		ct  = count; // get count from main class
		sumOfNodes(head);// call on sum function
		mean = sum/count;//calculate the mean -> sum/ count
		return "Sum of All Elements: " + sum + " | " + " Mean of All Elements: " + mean ; // return sum and mean to main class
	}
	
	//Calculate the standard deviation of elements in list
	
	static void sdOfNodes(Node head) {
		if(head ==null) //exit if head ==0
			return;
		sdOfNodes(head.next); // calling sdOfNodes function with the next in list
		sd += Math.pow((head.data - mean), 2); //double standard deviation variable added with computed variables
	}
	
	String sdUtil() {
		sdOfNodes(head);// call on sd function
		sq = sd / ct;// calculating square root of the sd/count
        	res = Math.sqrt(sq);//finally finding the square root of the sd variable
		return "Standard Deviation: " + res; // return standard deviation to main class
	}
}
