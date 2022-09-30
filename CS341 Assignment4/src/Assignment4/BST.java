package Assignment4;
import java.util.*;
public class BST {

	Node root;
	private Node temp;

	public void insert(String string) {
		Node node = new Node(string);
		if (root == null) {
			root = node;
			assert(root == node):"root is not populated";
			return;
		}

		Node prev = null;
		temp = root;

		while (temp != null) {
			if (temp.val.compareTo(string) > 0) {
				prev = temp;
				temp = temp.left;
			}

			else if (temp.val.compareTo(string)<0) {
				prev = temp;
				temp = temp.right;
			}
		}

		if (prev.val.compareTo(string) > 0)
			prev.left = node;
		else
			prev.right = node;
	}
	
	public boolean searchData(String data) {
        return search(this.root, data);
    }
    private boolean search(Node root, String data) {
        if (root == null) {
            return false;
        } else if (root.val == data) {
        	assert(root.val == data):"root value to search is not populated";
            return true;
        } else if (root.val.compareTo(data) >0) {
            return search(root.left, data);
        }
        return search(root.right, data);
    }
    
    public boolean deleteData(String data) {
        return search(this.root, data);
    }
    
	public Node delete(Node root, String value)
	{
		if(root == null)
			return root;		
		if(value.compareTo(root.val)<0)
		    root.left = delete(root.left , value);
		else if(value.compareTo(root.val)>0)
			root.right = delete(root.right , value);
		else if(value == root.val)
		{
			if(root.left != null &&root.right != null)
			{
				String min = root.right.val;
				Node temp = root.right;
				while(temp.left != null)
				{
					min = temp.left.val;
					temp = temp.left;
				}
				root.val = min;
				root.right= delete(root.right, min);
			}			
			else if(root.left == null)
				return root.right;
			else if(root.right== null)
				return root.left;			
			else
				return null;
		}
		return root;
	}

	public void inorder() {
		Node temp = root;
		assert(temp == root):"temp is not populated with the root";
		Stack<Node> stack = new Stack<>();
		while (temp != null || !stack.isEmpty()) {
			if (temp != null) {
				stack.add(temp);
				temp = temp.left;
			} else {
				temp = stack.pop();
				System.out.print(temp.val + " ");
				temp = temp.right;
			}
		}
	}
}
