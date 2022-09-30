package Assignment4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GFG {
	public static void main (String[] args) throws FileNotFoundException {
       BST tree=new BST();
       File text = new File("C:/Users/ab121/OneDrive/Desktop/try.txt");
       Scanner scnr = new Scanner(text);
       String line =null;
       while (scnr.hasNextLine()) {
            line = scnr.nextLine();
            tree.insert(line);
       }
//       tree.insert("30");
//       tree.insert("50");
//       tree.insert("15");
//       tree.insert("20");
//       tree.insert("10");
//       tree.insert("40");
//       tree.insert("60");
       tree.inorder();
       System.out.print(tree.searchData(line));
   }
}

