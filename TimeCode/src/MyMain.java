import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JTextArea;

public class MyMain {

	// SPECIFY THE APPLICATION ELEMENTS: UI AND OBJECTS
	private static JButton inputBtn;
	private static JButton computeBtn;
	private static JFrame jframeWindow;
	private static JPanel panel;
	private static File fileToRead;
	private static File fileToSave;
	public static String a;
	public static int count = 0;
	private static JTextArea res;

	public static void main(String[] args) {
		// create GUI application window
		constructAppWindow();
		addListenerEvents();
	}

	private static void constructAppWindow() {
		jframeWindow = new JFrame();
		jframeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// construct a panel container to store buttons, etc.
		panel = new JPanel(new GridLayout(3, 0)); // 3 ROWS NO COLUMNS
		panel.setPreferredSize(new Dimension(500, 150));
		panel.setBackground(Color.DARK_GRAY);

		// build buttons, etc. and add them to the panel
		inputBtn = new JButton("Specify Input Text File");
		panel.add(inputBtn);
		computeBtn = new JButton("Perform Work");
		panel.add(computeBtn);

		res = new JTextArea();
		panel.add(res);

		// add panel to the application window
		jframeWindow.getContentPane().add(panel);

		// TASK 5: MAKE THE APPLICATION WINDOW VISIBLE TO THE USER
		jframeWindow.pack();
		jframeWindow.setVisible(true);
	}

	private static void addListenerEvents() {
		inputBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requestInputFile();
			}
		});
		computeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					computeSomething();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}

	public static void requestInputFile() {
		// parent component of the dialog
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			fileToRead = jfc.getSelectedFile();
			a = fileToRead.getAbsolutePath();
			System.out.println(fileToRead.getAbsolutePath());
		}
	}

	public static void computeSomething() throws FileNotFoundException {
		Timer t = new Timer();// Variable for the timer
		t.start();// Start the timer
		System.out.println("now computing : " + a);// letting the user know what file is being computed
		int spaceCount = 0;// counter for the number of spaces in the code
		CodeList ll = new CodeList();// list for the code lines
		CommentList llist = new CommentList();// list for the comment lines in the code
		KeywordList k = new KeywordList();// List for keywords in the code

		Map<Integer, String> map = new HashMap<Integer, String>();// Map for the keywords
		int tester = 7;// integer for finding the key of the input words to match with the map

		File text2 = new File("C:/Users/ab121/OneDrive/Desktop/javakeywords.txt");// keywords file
		if (text2.length() == 0) {
			System.out.print("file is empty");// checking if file is empty
			return;
		}
		Scanner scnr1 = new Scanner(text2);
		String line1 = null;
		while (scnr1.hasNextLine()) {
			line1 = scnr1.nextLine();
			int hash = 7;
			hash = 7 * (line1.length() * 2 + ((line1.charAt(line1.length() - 1) + line1.charAt(0)))) / line1.length();// key
																														// hash
																														// function
			map.put(hash, line1);
		}
		System.out.println("Initial Mappings are: " + map);// printing out the map

		File text = new File(a);// input file
		if (text.length() == 0) {
			System.out.print("file is empty");// check if file is empty
			return;
		}
		Scanner scnr = new Scanner(text);
		String line = null;
		while (scnr.hasNextLine()) {
			line = scnr.nextLine();
			if (line == "") {
				spaceCount++; // if line is empty, spaceCounter is updated
			} else if (line.contains("//") || line.contains("/*") || line.contains("*/")
					|| line.contains("*") && !(line.startsWith("String"))) {
				llist.push(line);// if line has comment expressions, comment list updated
			} else {
				ll.push(line);// else remaining code line is pushed to the code line list
				String[] arrOfStr = line.trim().split(" "); // created an array of the input word by each space from the
															// code and trimmed
				for (int i = 0; i < arrOfStr.length; i++) {// for loop to go through the input word array
					String fin = arrOfStr[i];// String placeholder to hold the input word from the code
					boolean result = fin.matches("[a-zA-Z]+");// making sure that the string placeholder is a word/has
																// letters
					if (result == true) {// if yes
						tester = 7 * (fin.length() * 2 + ((fin.charAt(fin.length() - 1) + fin.charAt(0))))
								/ fin.length();// finding the hash function of the input word
						if (map.containsKey(tester)) {// if the map contains the inputed word hash function
							k.push(fin);// push the input word to the keyword list
						}
					}
				}
			}
		}
		t.end();// ending the timer
		System.out.println(t.time());
		System.out.println("number of comments in code: " + llist.getCount());
		System.out.println("lines of code without comments: " + ll.getCount());
		System.out.println("number of spaces in code: " + spaceCount);
		System.out.println("number of keywords in the code: " + k.getCount());
		System.out.println("List of keywords in the code: " + k.toString());
		res.append(" number of comments in code: " + llist.getCount());
		res.append(" lines of code without comments: " + ll.getCount());
		res.append(" number of spaces in code: " + spaceCount);
		res.append(" number of keywords in the code: " + k.getCount());
		res.append(" List of keywords in the code: " + k.toString());
		res.append(t.time());
	}
}
