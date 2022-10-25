 class CodeList {
	//head of the list, a node declaration
    Node head; 
    public void push(String new_data)
    {
    	//new node to hold the new data value being inserted 
    	//into the list
        Node new_node = new Node(new_data);
        //pointing to the next in list
        new_node.next = head;
        //setting the head to be the new data node
        head = new_node;
    }
    //get the number of elements in the list
    public int getCount()
    {
    	//a variable to hold the head value
        Node temp = head;
        //int variable to hold the count of elements in the list
        int count = 0;
        //while loop to loop through the entire list while the head is not null
        while (temp != null) {
        	//increment the count
            count++;
            //temp to the next element in the list
            temp = temp.next;
        }
        //return the count value
        return count;
    }
}
