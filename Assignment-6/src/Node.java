class Node {
	//string data from the input of the java file
	String data;
	//point to next in list
    Node next;
    //constructor for the data 
    Node(String d)
    {
     //data instantiation
    	data = d;
    	//pointing to the next element in the list
        next = null;
    }
    public String toString (){
    	//get the data from the list
        return data;
    }
}