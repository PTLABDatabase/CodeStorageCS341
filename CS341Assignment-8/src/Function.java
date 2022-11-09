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
	public static File a;
	public static String s;
	public static String n;
	public static String p;
	public static String q;
	public static Map<String, String> map = new HashMap<String, String>();
	
	public String insert(File in, String sku, String name, String price, String quantity) throws IOException {
		a = in;
		s = sku;
		n = name;
		p = price;
		q = quantity;
		if( a != null && s.isEmpty() == false && n.isEmpty() == false && p.isEmpty() == false && q.isEmpty() == false) {
		FileWriter file = new FileWriter(a,true);
		BufferedWriter out = new BufferedWriter(file);
		String description = " " +n +" " + " $" +  p + " Quantity: " + q +" ";
		map.put(s,description);
		out.write("\n");
		out.write(s + " " + description );
		out.close();
		System.out.print (Arrays.asList(map).toString());
		return("all done");
		}
		else {
		String description = " " +n +" " + " $" +  p + " Quantity:" + q +" ";
		map.put(s,description);
		return("File = null, Book inserted into temporary space");
		}
	}
	
	public String search(String sku) {
		s = sku;
		if(map.containsKey(s)==true) {
			return map.get(s).toString();
		}
		else {
			String err = "not found";
			return err;
		}
	}
	
	public void Upload(File in) throws FileNotFoundException {
		a = in;
		if (a.length() != 0) {
		try (Scanner scnr = new Scanner(a)) {
			while (scnr.hasNextLine()) {
				String line1 = scnr.nextLine();
				String [] arr = (line1.split(" "));
				if(line1.length() != 0) {
				s = arr[0].toString();
				String des = " " + arr[1].toString() + " " +  arr[2].toString() + " " +  arr[3].toString() + " " +  arr[4].toString()+ " " + arr[5].toString() + " ";
				//System.out.println(des);
				map.put(s,des);
			}
			}
		}		
	}
		else {
			System.out.print("file empty");
		}
	}
	
	public String list() {
		return Arrays.asList(map).toString();
	}
	
	public String delete(String sku) throws IOException {
		s = sku;
		if(s.isEmpty()==true) {
			return "Specify SKU";
		}
		if(map.containsKey(s)==true) {
			map.remove(s);
			String app = "all done";
			updateFile();
			return app;
		}
		else {
			String err = "not found";
			return err;
		}
	}
	
	public void updateFile() throws IOException {
		String in = a.toString();
		System.out.println("FIlE: " + in);
		PrintWriter w = new PrintWriter(a);
		FileWriter file = new FileWriter(a,true);
		BufferedWriter out = new BufferedWriter(file);
		w.flush();
		for(Map.Entry<String, String>entry : map.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			out.write("\n");
			out.write(key + " " + value);
		}
		out.close();
	}
}
