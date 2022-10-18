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
        System.out.println("now computing : " + a);
        int spaceCount =0;
        LinkedList ll = new LinkedList();
        ForList fl = new ForList();
        WhileLoop w = new WhileLoop();
        CommentList llist = new CommentList();
		File text = new File(a);
		if (text.length() == 0) { 
			System.out.print("file is empty");
			return;
		}
		Scanner scnr = new Scanner(text);
		String line = null;
		while (scnr.hasNextLine()) {
			line = scnr.nextLine();
			if(line == "") {
				spaceCount++;
			}
			else if (line.contains("//") || line.contains("/*") || line.contains("*/")
					|| line.contains("*") && !(line.startsWith("String"))) {
				llist.push(line);
			} else if (line.contains("for (") && !(line.contains("\""))) {
				fl.push(line);
				ll.push(line);
			} else if (line.contains("while (") && !(line.contains("\""))) {
				w.push(line);
				ll.push(line);
			} else {
				ll.push(line);
			}
		}
		System.out.println("number of for loops in code: " + fl.getCount());
		System.out.println("number of while loops in code: " + w.getCount());
		System.out.println("number of comments in code: " + llist.getCount());
		System.out.println("lines of code without comments: "  + ll.getCount());
		System.out.println("number of spaces in code: " + spaceCount);
		res.setText(" number of for loops in code: " + fl.getCount());
		res.append(" number of while loops in code: " + w.getCount());
		res.append(" number of comments in code: " + llist.getCount());
		res.append(" lines of code without comments: "  + ll.getCount());
		res.append(" lines of code without comments: "  + ll.getCount());
		res.append(" number of spaces in code: " + spaceCount);
	}
}
