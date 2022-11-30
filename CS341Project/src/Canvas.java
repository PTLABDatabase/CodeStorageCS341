import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Executable;
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Canvas extends JComponent implements ActionListener, KeyListener {
	// DEFAULT SERIAL NUMBER
	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private Timer gameLoopTimer;
	private static List<GameObject> gameObjectList;
	private int highlighted = 0;
	ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
	


	public Canvas() {
		// TASK 1: CREATE A LIST OF CHARACTERS THAT WILL APPEAR ON THE CANVAS
		gameObjectList = new LinkedList<GameObject>();

		// TASK 2: CREATE A WINDOW FOR THE APPLICATION
		frame = new JFrame("Animation Canvas");
		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);

		// TASK 3: CONSTRUCT A TIMER FOR GAME LOOP
		gameLoopTimer = new Timer(25, this);
		gameLoopTimer.start();
		
		setFocusTraversalKeysEnabled(false);
	    addKeyListener(this);

		// TASK 4: MAKE THE WINDOW VISIBLE
		frame.setVisible(true);

	}
	
	/**
	 * Adds GameObjects to the List, which are latter added to the Canvas
	 */
	public synchronized void addGameObject(GameObject sprite) {
		gameObjectList.add(sprite);
	}

	/**
	 * Draws the GameObject graphic onto the Canvas
	 */
	public synchronized void paint(Graphics g) {
		for (GameObject s : gameObjectList) {
			s.move(this);
			s.draw(this, g);
		}	
	}
	
	
	// ****************************************************
	// Canvas must implement the inherited abstract method
	// ActionListener.actionPerformed(ActionEvent)
	public synchronized void actionPerformed(ActionEvent e) {
		for (GameObject gameObject : gameObjectList) {
			gameObject.move(this);
			gameObject.setImage();
		}
		repaint();
	}


	
	
	// ****************************************************
	// Canvas must implement the inherited abstract methods
	// for KeyListener
	
	  public void keyTyped(KeyEvent e) {
	  }

	  public void keyPressed (KeyEvent e) {
	  }

	  public void keyReleased(KeyEvent e) {
		 //calling method to shut off the executor
		clear();
		//declaring a graphics variable to get the graphics
		Graphics g = getGraphics();
	    if (e.getKeyCode() == KeyEvent.VK_TAB) {
	      highlighted = highlighted + 1;
	      if (highlighted == gameObjectList.size()) {
	        highlighted = 0;
	      }
	    }
	    GameObject s = gameObjectList.get(highlighted); 
	    //Creating a runnable which will highlight the objected being selected
	    Runnable helloRunnable = new Runnable() {
			    public void run() {
			    	//drawing a rectangle around the selected object's dimensions
			    	g.drawRect(s.getX(), s.getY(), s.getCurrentImage().getIconHeight()+10, s.getCurrentImage().getIconWidth()+10);
					g.setColor(Color.blue);
		    }
			};
		//executor to run the runnable every 10 milliseconds
		executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(helloRunnable, 0, 10, TimeUnit.MILLISECONDS);
		
		//set the velocity of the selected object as 10
	    s.setVelocity(10);
	    s.setVelocity(s.getVelocity()+1);
	    
	    //for all objects not selected the velocity and direction is set to zero
	    for(int i = 0; i<gameObjectList.size(); i++) {
	    	if(i != highlighted) {
	    		GameObject a = gameObjectList.get(i);
	    		a.setVelocity(0);
	    		a.setDirection(0);
	    	}
	    }
	  }
	  
	  //method shutting off the executor 
	  public void clear() {
		  executor.shutdown();
	  }
}
