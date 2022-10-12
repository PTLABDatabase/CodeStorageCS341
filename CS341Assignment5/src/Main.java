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

	public static void computeSomething() throws FileNotFoundException {
		System.out.println("now computing : " + a);
		res.setText("Computing: " + a + " | ");
		int count = 0;
		LL l = new LL();
		File text = new File(a);
		if (text.length() == 0) { 
			exit();
			return;
		}
		Scanner scnr = new Scanner(text);
		String line = null;
		while (scnr.hasNextLine()) {
			line = scnr.nextLine();
			char[] charArray = line.toCharArray();
			//System.out.print(line);
			if (line == "") {
				exit();
				return;
			} else if (line.contains("a")) {
				exit();
				return;
			} else {
				for (int i = 0; i < charArray.length; i++) {
			         char ch = charArray[i];
			         if (!(ch >= '0' && ch <= '9')) {
			        	exit();
			            return;
			         }
			      }
					count++;
					int a = Integer.parseInt(line);
					l.push(a);
			}
		}
			String sum_and_mean = l.sumOfNodesUtil(count);
			String sd = l.sdUtil();
			res.append(sum_and_mean + " | " + "Standard deviation: " + sd);
			System.out.println(sum_and_mean);
			System.out.println(sd);
	}

public static void exit() {
	res.setText("Error has occured: Make sure file has each number on a new line, no spaces in between lines, no letters in file");
}
}