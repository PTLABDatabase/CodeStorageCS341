import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;

public class Main {
	private static JButton inputBtn;
	private static JButton computeBtn;
	private static JFrame jframeWindow;
	private static JPanel panel;
	private static File fileToRead;
	private static File fileToSave;
	public static String a;
	private static JTextArea res;

	public static void main(String[] args) {

		constructAppWindow();
		addListenerEvents();
	}

	private static void constructAppWindow() {
		jframeWindow = new JFrame();
		jframeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel(new GridLayout(3, 0));
		panel.setPreferredSize(new Dimension(500, 150));
		panel.setBackground(Color.DARK_GRAY);

		inputBtn = new JButton("Specify Input Text File");
		panel.add(inputBtn);
		computeBtn = new JButton("Perform Work");
		panel.add(computeBtn);

		res = new JTextArea();
		res.setBackground(new Color(173, 216, 230));
		res.setForeground(new Color(0, 0, 0));
		res.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel.add(res);
		jframeWindow.getContentPane().add(panel);
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
					e1.printStackTrace();
				}
			}
		});

	}

	public static void requestInputFile() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			fileToRead = jfc.getSelectedFile();
			a = fileToRead.getAbsolutePath();
			System.out.println(fileToRead.getAbsolutePath());
		}
	}

	//Computing the user file input to return sum, mean and standard deviation
	
	public static void computeSomething() throws FileNotFoundException {
		
		
		
		//Step 1: Getting file input to be used and instantiating the required variables for computation
		
		
		
		//Letting user now the inputed file is now being computed
		System.out.println("now computing : " + a);
		res.setText("Computing: " + a + " | ");
		//int count for the number of input values
		int count = 0;
		LL l = new LL();//new linkedlist instantiation
		File text = new File(a);//declaring file variable text to hold the inputed file location
		
		
		
		//Step 2: Checking to see if input is appropriate
		
		
		
		if (text.length() == 0) { //checking to see file is empty
			exit();//call to exit sequence
			return;//stop the code
		}
		Scanner scnr = new Scanner(text);//new scanner instantiation for reading the inputs
		String line = null;//string value to hold the inputed values
		while (scnr.hasNextLine()) {//traverse through the inputed file
			line = scnr.nextLine();//string line holds the inputed value from the file
			char[] charArray = line.toCharArray();//char array to traverse through the input string
			//System.out.print(line);
			if (line == "") {//if input is empty
				exit();
				return;
			} else if (line.contains("a")) {//test case: to see if input is anything other a number
				exit();
				return;
			} else {
				for (int i = 0; i < charArray.length; i++) {//traverse through the string input stored in a char array
			         char ch = charArray[i];
			         if (!(ch >= '0' && ch <= '9')) {//check if anything other than a number has been inputed
			        	exit();
			            return;
			         }
			      }
				
				
				
		//Step 3: If input is good, proceed with updating list elements and processing sum, mean and standard deviation 
				
				
					count++;//if the string input is adequate, the count is updated and the number of elements is updated
					int a = Integer.parseInt(line);//changing string input to int
					l.push(a);//input enqueued to the list
			}
		}
		
		
		
		//Step 4: Get the computed values from classes and present output to user
		
		
		
			String sum_and_mean = l.sumOfNodesUtil(count);//string placeholder for sum and mean of elements in list
			String sd = l.sdUtil();//string placeholder for standard deviation of sum of elements in list
			res.append(sum_and_mean + " | " + "Standard deviation: " + sd);//outputing the sum, mean and standard deviation to the text area in the GUI
			System.out.println(sum_and_mean);//printing the sum and mean for console record
			System.out.println(sd);//printing standard deviation for console record
	}

//Exit sequence of providing error message to user in case input is not appropriate
	
public static void exit() {//exit sequence in case anything goes wrong
	res.setText("Error has occured: Make sure file has each number on a new line, no spaces in between lines, no letters in file");//output to indicate error
}
}
