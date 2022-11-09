import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class myVisual {

	private static JFrame frame;
	private static File fileToSave;
	private static JButton outputBtn;
	private static JTextArea res1;
	private static JTextArea res3;
	private static JTextArea res4;
	private static JTextArea price;
	private static JTextArea quantity;
	private static JTextArea name;
	private static JTextArea sku;
	private static JTextArea srch;
	private static JTextArea num;
	private static JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myVisual window = new myVisual();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public myVisual() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private static void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 478, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JButton bt4 = new JButton("LIST ALL");
		bt4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listall();
			}
		});
		bt4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bt4.setBounds(307, 132, 127, 76);
		frame.getContentPane().add(bt4);
		
		JButton bt2 = new JButton("INSERT");
		bt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFileConfirm();
			}
		});
		bt2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bt2.setBounds(307, 45, 127, 76);
		frame.getContentPane().add(bt2);
		
		JButton bt1 = new JButton("SEARCH");
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		bt1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bt1.setBounds(24, 47, 127, 76);
		frame.getContentPane().add(bt1);
		
		JButton bt3 = new JButton("DELETE");
		bt3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bt3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		bt3.setBounds(24, 132, 127, 76);
		frame.getContentPane().add(bt3);
		
		JLabel lblNewLabel = new JLabel("BOOK STORE INVENTORY");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel.setBounds(82, 11, 323, 38);
		frame.getContentPane().add(lblNewLabel);		
		
	}
	
    public static void requestSaveFile() {
        // parent component of the dialog
        JFrame parentFrame = new JFrame();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");
        int userSelection = fileChooser.showSaveDialog(parentFrame);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            fileToSave = fileChooser.getSelectedFile();
            System.out.println("Save as file: " + fileToSave.getAbsolutePath());
            textFileConfirm();
        }
    }
	
	public static void returnback() {
		frame.getContentPane().removeAll();
		frame.repaint();
		
		JButton bt4 = new JButton("LIST ALL");
		bt4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listall();
			}
		});
		bt4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bt4.setBounds(260, 132, 127, 76);
		frame.getContentPane().add(bt4);
		
		JButton bt2 = new JButton("INSERT");
		bt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFileConfirm();
			}
		});
		bt2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bt2.setBounds(260, 47, 127, 76);
		frame.getContentPane().add(bt2);
		
		JButton bt1 = new JButton("SEARCH");
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		bt1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bt1.setBounds(24, 47, 127, 76);
		frame.getContentPane().add(bt1);
		
		JButton bt3 = new JButton("DELETE");
		bt3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bt3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		bt3.setBounds(24, 132, 127, 76);
		frame.getContentPane().add(bt3);
		
		JLabel lblNewLabel = new JLabel("BOOK STORE INVENTORY");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel.setBounds(101, 11, 323, 38);
		frame.getContentPane().add(lblNewLabel);		
		
	}
	
	//method for switching into search screen
	public static void search() {
		// remove all contents from the frame
		frame.getContentPane().removeAll(); 
		frame.repaint();
		//insert new components into the frame
		JLabel lblNewLabel = new JLabel("SEARCH BOOK STORE INVENTORY");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel.setBounds(100, 11, 323, 38);
		frame.getContentPane().add(lblNewLabel);
		JButton rtn = new JButton("RETURN");
		rtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnback();
			}
		});
		rtn.setBounds(1, 1, 89, 23);
		frame.getContentPane().add(rtn);
		
		JLabel lblNewLabel_1 = new JLabel("ENTER SKU AND CLICK SEARCH");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(35, 61, 199, 21);
		frame.getContentPane().add(lblNewLabel_1);
		
		srch = new JTextArea();
		srch.setBounds(24, 89, 212, 38);
		frame.getContentPane().add(srch);
		
		JButton srchnow = new JButton("SEARCH");
		srchnow.setBounds(254, 90, 97, 47);
		frame.getContentPane().add(srchnow);
		srchnow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchmap();
			}
		});
		
		res1 = new JTextArea();
		res1.setBounds(72, 158, 284, 92);
		frame.getContentPane().add(res1);
		
	}
	
	public static void textFileConfirm() {
		// remove all contents from the frame
		frame.getContentPane().removeAll();
		frame.repaint();
		outputBtn = new JButton("Specify Output Text File");
		outputBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		outputBtn.setBounds(90, 100 , 257, 50);
		frame.getContentPane().add(outputBtn);
		outputBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                requestSaveFile();
                JTextArea LocRes = new JTextArea();
        		LocRes.setBounds(64, 199, 309, 51);
        		frame.getContentPane().add(LocRes);
        		String text = fileToSave.toString();
        		LocRes.setText(text);
        		JButton con = new JButton("Confirm");
        		con.setBounds(345, 227, 89, 23);
        		frame.getContentPane().add(con);
        		con.addActionListener(new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				Function f = new Function();
        				try {
							f.Upload(fileToSave);
							insert();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
        			}
        		});
            }
        });

		JButton skip = new JButton("SKIP");
		skip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insert();
			}
		});
		skip.setBounds(335, 26, 89, 23);
		frame.getContentPane().add(skip);
		JButton rtn = new JButton("RETURN");
		rtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnback();
			}
		});
		rtn.setBounds(1, 1, 89, 23);
		frame.getContentPane().add(rtn);
	}
	
	
	//method for switching into insert screen
		public static void insert() {
			// remove all contents from the frame
			frame.getContentPane().removeAll(); 
			frame.repaint();
			//insert new components into the frame
			JLabel lblNewLabel = new JLabel("INSERT TO BOOK STORE INVENTORY");
			lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
			lblNewLabel.setBounds(100, 11, 323, 38);
			frame.getContentPane().add(lblNewLabel);
			JButton rtn = new JButton("RETURN");
			rtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					returnback();
				}
			});
			rtn.setBounds(1, 1, 89, 23);
			frame.getContentPane().add(rtn);
			
			JLabel lblNewLabel_1 = new JLabel("ENTER SKU, NAME, QUANTITY and PRICE AND CLICK INSERT");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_1.setBounds(35,35, 399, 21);
			frame.getContentPane().add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("SKU");
			lblNewLabel_2.setBounds(143, 60, 76, 22);
			frame.getContentPane().add(lblNewLabel_2);
			
			JLabel lblNewLabel_31 = new JLabel("Name");
			lblNewLabel_31.setBounds(143, 95, 76, 22);
			frame.getContentPane().add(lblNewLabel_31);
			
			JLabel lblNewLabel_41 = new JLabel("Quantity");
			lblNewLabel_41.setBounds(143, 130, 76, 22);
			frame.getContentPane().add(lblNewLabel_41);
			
			JLabel lblNewLabel_411 = new JLabel("Price");
			lblNewLabel_411.setBounds(143, 170, 76, 22);
			frame.getContentPane().add(lblNewLabel_411);
			
			sku = new JTextArea();
			sku.setBounds(24, 55, 120, 29);
			frame.getContentPane().add(sku);
			
			name = new JTextArea();
			name.setBounds(24, 95, 120, 29);
			frame.getContentPane().add(name);
			
			quantity = new JTextArea();
			quantity.setBounds(24, 130, 120, 29);
			frame.getContentPane().add(quantity);
			
			price = new JTextArea();
			price.setBounds(24, 170, 120, 29);
			frame.getContentPane().add(price);
			
			
			JButton innow = new JButton("INSERT");
			innow.setBounds(254, 90, 97, 47);
			frame.getContentPane().add(innow);
			innow.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						insertFunction();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			
			textArea = new JTextArea("File: " + fileToSave);
			textArea.setBounds(0, 220,1000, 30);
			frame.getContentPane().add(textArea);
			
		}
		
		//method for switching into delete screen
		public static void delete() {
			// remove all contents from the frame
			frame.getContentPane().removeAll(); 
			frame.repaint();
			//insert new components into the frame
			JLabel lblNewLabel = new JLabel("DELETE FROM BOOK STORE INVENTORY");
			lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
			lblNewLabel.setBounds(100, 11, 423, 38);
			frame.getContentPane().add(lblNewLabel);
			JButton rtn = new JButton("RETURN");
			rtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					returnback();
				}
			});
			rtn.setBounds(1, 1, 89, 23);
			frame.getContentPane().add(rtn);
			
			JLabel lblNewLabel_1 = new JLabel("ENTER SKU AND CLICK DELETE");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_1.setBounds(35, 61, 199, 21);
			frame.getContentPane().add(lblNewLabel_1);
			
			num = new JTextArea();
			num.setBounds(24, 89, 212, 38);
			frame.getContentPane().add(num);
			
			JButton dltnow = new JButton("DELETE");
			dltnow.setBounds(254, 90, 110, 47);
			frame.getContentPane().add(dltnow);
			dltnow.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						deletemap();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			
			res3 = new JTextArea();
			res3.setBounds(72, 158, 284, 92);
			frame.getContentPane().add(res3);
			
		}
		
		//method for switching into listall screen
		public static void listall() {
			// remove all contents from the frame
			frame.getContentPane().removeAll(); 
			frame.repaint();
			//insert new components into the frame
			JLabel lblNewLabel = new JLabel("LIST ALL FROM BOOK STORE INVENTORY");
			lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
			lblNewLabel.setBounds(100, 11, 423, 38);
			frame.getContentPane().add(lblNewLabel);
			JButton rtn = new JButton("RETURN");
			rtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					returnback();
				}
			});
			rtn.setBounds(1, 1, 89, 23);
			frame.getContentPane().add(rtn);
			
			JLabel lblNewLabel_1 = new JLabel("CLICK LIST ALL TO GET FULL LIST OF BOOKS");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblNewLabel_1.setBounds(55, 61, 299, 23);
			frame.getContentPane().add(lblNewLabel_1);
			
			JButton lstnow = new JButton("LIST ALL");
			lstnow.setBounds(254, 90, 97, 47);
			frame.getContentPane().add(lstnow);
			lstnow.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					list();
				}
			});
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(90, 197, 249, 64);
			frame.getContentPane().add(scrollPane);

			res4 = new JTextArea();
			res4.setBounds(72, 158, 284, 92);
			frame.getContentPane().add(res4);
			scrollPane.setViewportView(res4);
			
		}
		
		public static void insertFunction() throws IOException {
			Function a = new Function();
			textArea.setText(a.insert(fileToSave, sku.getText(), name.getText(), price.getText(), quantity.getText()));
		}
		
		public static void searchmap() {
			Function a = new Function();
			res1.setText(a.search(srch.getText()));
		}
		
		public static void deletemap() throws IOException {
			Function a = new Function();
			res3.setText(a.delete(num.getText()));
		}
		
		
		public static void list() {
			Function a = new Function();
			res4.setText(a.list());
		}
}
