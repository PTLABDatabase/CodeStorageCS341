import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Function {
	public static File a; //FILE LOCATION VARIABLE
	public static String s; //SKU VARIABLE 
	public static String n; // NAME VARIABLE
	public static String p; //PRICE VARIABLE
	public static String q; //QUANTITY VARIABLE
	public static Map<String, String> map = new HashMap<String, String>(); //MAP ACTS AS THE INVENTORY

	//Upload function CALLED ON TO IMPORT ALL THE DATA FROM THE FILE INTO THE MAP
	
	public void Upload(File in) throws FileNotFoundException {
		a = in;
		//IF THE FILE IS NOT EMPTY
		if(a != null) {
		if (a.length() != 0) {
			try (Scanner scnr = new Scanner(a)) {
				while (scnr.hasNextLine()) {
					String line1 = scnr.nextLine();
					String[] arr = (line1.split(" "));
					if (line1.length() != 0) {
						s = arr[0].toString();
						String des = " " + arr[1].toString() + " " + arr[2].toString() + " " + arr[3].toString() + " "
								+ arr[4].toString() + " " + arr[5].toString() + " ";
						
						System.out.println(des);
						
						//MAP => KEY = SKU, VALUE = DESCRIPTION(NAME, PRICE, QUANTITY)
						map.put(s, des);
					}
				}
			}
		} }else {
			System.out.print("file empty OR not specified");//ELSE FILE IS EMPTY
		}
	}
	
	//insert() FUNCTION CALLED ON WHEN INSERTING INTO THE INVENTORY, IF THE FILE IS SPECIFIED THE INPUT IS WRITTEN INTO THE FILE

	public String insert(File in, String sku, String name, String price, String quantity) throws IOException {
		a = in;
		s = sku;
		n = name;
		p = price;
		q = quantity;
		
		//IF THE FILE, NAME QUNATITY AND PRICE IS INPUTED
		
		if (a != null && s.isEmpty() == false && n.isEmpty() == false && p.isEmpty() == false && q.isEmpty() == false) {
			FileWriter file = new FileWriter(a, true);
			BufferedWriter out = new BufferedWriter(file);
			String description = " " + n + " " + " $" + p + " Quantity: " + q + " ";
			map.put(s, description);
			out.write("\n");
			out.write(s + " " + description);
			out.close();
			System.out.print(Arrays.asList(map).toString());
			return ("all done");
		} else {
			//IF FILE NAME IS NOT SPECIFIED
			if(s.isEmpty() == false && n.isEmpty() == false && p.isEmpty() == false && q.isEmpty() == false) {
			String description = " " + n + " " + " $" + p + " Quantity:" + q + " ";
			map.put(s, description);
			return ("File = null, Book inserted into temporary space");
		}
			//IF NOTHING IS SPECIFIED OR ONLY SOMETHINGS ARE SPECIFIED
			else {
				return ("specify sku, name, quantity, price");
			}
		}
	}
	
	//SREACH THROUGH THE MAP/INVENTORY

	public String search(String sku) {
		s = sku;
		if (map.containsKey(s) == true) {
			return map.get(s).toString();
		} else {
			String err = "not found";
			return err;
		}
	}
	
	//LIST ALL ELEMENTS OF THE MAP/INVENTORY

	public String list() {
		return Arrays.asList(map).toString();
	}
	
	//DELETE ELEMENTS FROM THE MAP/INVENTORY AND FROM FILE IF SPECIFIED

	public String delete(String sku) throws IOException {
		s = sku;
		
		//IF SKU NOT SPECIFIED
		
		if (s.isEmpty() == true) {
			return "Specify SKU";
		}
		
		//IF MAP/INVENTORY CONTAINS THE SKU
		
		if (map.containsKey(s) == true) {
			
			//REMOVE FROM MAP
			
			map.remove(s);
			String app = "all done";
			
			//IF FILE LOCATION HAS BEEN SPECIFIED
			
			if (a != null) {
				updateFile();
			}
			
			//RETURN APPROVAL OF DELETION
			
			return app;
			
			//ELSE NOT FOUND
			
		} else {
			String err = "not found";
			return err;
		}
	}

	//UPDATE THE FILE AFTER DELETION WITH NEW UPDATED MAP -> THIS IS CALLED ONLY IF THE FILE LOCATION HAS BEEN SPECIFIED
	
	public void updateFile() throws IOException {
		String in = a.toString();
		PrintWriter w = new PrintWriter(a);
		FileWriter file = new FileWriter(a, true);
		BufferedWriter out = new BufferedWriter(file);
		
		//THE FILE IS CLEARED OF DATA AND MADE EMPTY
		
		w.flush();
		
		//EACH ELEMENT IN THE UPDATED MAP/INVENTORY IS RECORDED INTO THE FILE
		for (Map.Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			
			//EACH ELEMENT WRITTEN ON A NEW LINE
			
			out.write("\n");
			out.write(key + " " + value);
		}
		out.close();
	}
}
