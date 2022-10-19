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
        //create GUI application window
        constructAppWindow();
        addListenerEvents();
    }
    
    private static void constructAppWindow() {
        jframeWindow = new JFrame();
        jframeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //construct a panel container to store buttons, etc.
        panel = new JPanel(new GridLayout(3, 0)); // 3 ROWS NO COLUMNS
        panel.setPreferredSize(new Dimension(500, 150));
        panel.setBackground(Color.DARK_GRAY);

        //build buttons, etc. and add them to the panel
        inputBtn = new JButton("Specify Input Text File");
        panel.add(inputBtn);
        computeBtn = new JButton("Perform Work");
        panel.add(computeBtn);
        
        res = new JTextArea();
        panel.add(res);

        //add panel to the application window
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
	    //letting user know what file is being computed
        System.out.println("now computing : " + a);
	    //count for the number of empty spaces in the code
        int spaceCount =0;
	    // variable for method list
        MethodList m = new MethodList();
	     // variable for Lines of Code list
        LinkedList ll = new LinkedList();
	     // variable for  for loop list
        ForList fl = new ForList();
	     // variable for while loop list
        WhileLoop w = new WhileLoop();
	     // variable for Comment list
        CommentList llist = new CommentList();
	    //File variable placeholder
		File text = new File(a);
	    //checking if file is empty
		if (text.length() == 0) { 
			System.out.print("file is empty"); 
			return;
		}
	    //Scanner varaibale to read each line in file
		Scanner scnr = new Scanner(text);
	    //String varaible placeholder for each input line
		String line = null;
	    //while scanner has a next line
		while (scnr.hasNextLine()) {
			//line contains the input line from file
			line = scnr.nextLine();
			//if line is empty, space count incremented
			if(line == "") {
				spaceCount++;
			}
			//checking for methods, push input to comment and lines of code list
			else if (line.contains(") {") && !(line.contains("\""))&& !(line.contains("for"))&& !(line.contains("while"))&& !(line.contains("//") || line.contains("/*") || line.contains("*/")
					|| line.contains("*") || (line.startsWith("String") || (line.contains("if") || (line.contains("else if ")|| (line.contains("else"))))))) {
				System.out.println(m.toString());
				m.push(line);
				ll.push(line);
			}
			//checking for comments, push to comment list
			else if (line.contains("//") || line.contains("/*") || line.contains("*/")
					|| line.contains("*") && !(line.startsWith("String"))) {
				llist.push(line);
			//check for for loop, push to for list and lines of code list
			} else if (line.contains("for (") && !(line.contains("\""))) {
				fl.push(line);
				ll.push(line);
			//check for while loop, push to while list and lines of code list
			} else if (line.contains("while (") && !(line.contains("\""))) {
				w.push(line);
				ll.push(line);
			//else push to lines of code list
			} else {
				ll.push(line);
			}
		}
	    //print result in console and into GUI text area
		System.out.println("number of for loops in code: " + fl.getCount());
		System.out.println("number of while loops in code: " + w.getCount());
		System.out.println("number of comments in code: " + llist.getCount());
		System.out.println("number of methods in code: "  + m.getCount());
		System.out.println("lines of code without comments: "  + ll.getCount());
		System.out.println("number of spaces in code: " + spaceCount);
		res.setText(" number of for loops in code: " + fl.getCount());
		res.append(" number of while loops in code: " + w.getCount());
		res.append(" number of comments in code: " + llist.getCount());
		res.append(" number of methods in code: "  + m.getCount());
		res.append(" lines of code without comments: "  + ll.getCount());
		res.append(" number of spaces in code: " + spaceCount);
		res.append(m.toString());
		
	}
}
