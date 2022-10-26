import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		Map<Integer, String> map = new HashMap<Integer, String>();
		boolean res;
		int tester = 7;

		File text2 = new File("C:/Users/ab121/OneDrive/Desktop/javakeywords.txt");
		if (text2.length() == 0) {
			System.out.print("file is empty");
			return;
		}
		Scanner scnr1 = new Scanner(text2);
		String line1 = null;
		while (scnr1.hasNextLine()) {
			line1 = scnr1.nextLine();
			int hash = 7;
			hash = 7 * (line1.length() * 2 + ((line1.charAt(line1.length() - 1) + line1.charAt(0)))) / line1.length();
			map.put(hash, line1);
		}
		System.out.println("Initial Mappings are: " + map);
		File text1 = new File ("C:/Users/ab121/eclipse-workspace/TrialRead/src/MyClass.java");
		//File text1 = new File("C:/Users/ab121/eclipse-workspace/TrialRead/src/trial.java");
		KeywordList k = new KeywordList();
		if (text1.length() == 0) {
			System.out.print("file1 is empty");
			return;
		}

		Scanner scnr = new Scanner(text1);
		String line = null;
		while (scnr.hasNextLine()) {
			line1 = scnr.nextLine();
			String[] arrOfStr = line.trim().split(" ");
			for (int i = 0; i < arrOfStr.length; i++) {
				String fin = arrOfStr[i];
				boolean result = fin.matches("[a-zA-Z]+");
				if(result == true ){
					//System.out.println(fin);
						tester = 7* (fin.length()*2 + ((fin.charAt(fin.length() - 1) + fin.charAt(0))))/fin.length();
						if(map.containsKey(tester)) {
							k.push(fin);
					}
			}
		}
		}
		System.out.println(k.getCount());
		System.out.println(k.toString());
}
}

