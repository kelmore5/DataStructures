package kelmore5.java.yeh.data_structures.priority_queue.test;
import kelmore5.java.yeh.data_structures.node.BinarizedNode;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * An extension of JFrame to display a YehLetters binary search tree.
 *
 * @author kelmore5
 * @custom.date Fall 2012
 *
 * @see YehLetters
 */
@SuppressWarnings("serial")
class MainFrame extends JFrame implements Runnable {
	private int x;					//JFrame size
	private YehLetters tree;		//Binary search tree to display
	private String input;			//String to encode
	private MainPanel panel;		//Main panel for JFrame

	/**
	 * Instantiates a new Main frame.
	 */
	private MainFrame() {
		super("Yeh Letters");			//Set Title

		x = 700;						//Set size
		tree = null;					//initialize bst
		input = "";						//initialize input
		panel = new MainPanel();		//initialize JPanel

		add(panel);						//Add panel to main frame
		createMenu();					//Create menu

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);		//Set to exit program when frame closes
	}

	/**
	 * Create menu for the frame
	 */
	private void createMenu() {
		JMenuBar menuBar = new JMenuBar();		//Create menubar

		JMenu menu = new JMenu("File");		//Create menu item
		menuBar.add(menu);						//Add to menubar

		//Create new menu item to encode string
		//and add action listener event to it
		JMenuItem string = new JMenuItem("Encode String");
		string.addActionListener(e -> tree = new YehLetters(JOptionPane.showInputDialog(null, "Enter a string:")));
		menu.add(string);

		//Create menu item to open a saved binary search tree
		JMenuItem open = new JMenuItem("Open File");
		open.addActionListener(e -> {
            //Using JFileChooser class to open files
            JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showOpenDialog(null);

            //If got real file, proceed
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    //Using BufferedReader and going line by line to receive file
                    BufferedReader file = new BufferedReader(new FileReader(chooser.getSelectedFile()));

                    //Get input
                    String line = file.readLine();
                    while(line != null) {
                        input += line;
                        line = file.readLine();
                    }

                    tree = new YehLetters(input);		//Create tree from input

                    file.close();
                }
                catch (FileNotFoundException e1) {
					System.out.println(e1.toString());
                }
                catch (IOException ex) {
					System.out.println("IOException: " + ex.toString());
                }
            }
        });
		menu.add(open);

		//Create menu item to save an encoded tree
		JMenuItem save = new JMenuItem("Save Encoding");
		save.addActionListener(e -> {
            if(tree != null) {
                //Using JFileChooser to get file
                JFileChooser chooser = new JFileChooser();
                int returnVal = chooser.showSaveDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        if(!chooser.getSelectedFile().exists()) {
							//noinspection ResultOfMethodCallIgnored
							chooser.getSelectedFile().createNewFile();
                        }

                        //Using BufferedWriter to write file to storage
                        BufferedWriter writer = new BufferedWriter(new FileWriter(chooser.getSelectedFile()));
                        writer.write(tree.getCoding());

                        System.out.println(tree.decodeString());

                        writer.close();
                    }
                    catch (IOException e1) {
                        e1.printStackTrace();
                    }

                }
            }
        });
		menu.add(save);

		//Add menu bar item for tree events
		JMenu treeMenu = new JMenu("Tree");
		menuBar.add(treeMenu);

		//Add menu item to draw the last tree encoded
		JMenuItem drawTree = new JMenuItem("Draw Tree");
		drawTree.addActionListener(e -> {
            if(tree != null) {
                panel.drawTree();
            }
        });
		treeMenu.add(drawTree);

		//Set the menubar
		this.setJMenuBar(menuBar);
	}

	public void run() {
		setSize(x, 500);
		setVisible(true);
	}

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		MainFrame mf = new MainFrame();
		javax.swing.SwingUtilities.invokeLater(mf);
	}

	private class MainPanel extends JPanel {

		/**
		 * Instantiates a new Main panel.
		 */
		MainPanel() {
			setLayout(null);
			setBackground(Color.white);
		}

		/**
		 * Display the binary search tree onto this panel
		 */
		void drawTree() {
			//This method will go to each node, grab the datum, and then place
			//it onto the JPanel for display at a certain x/y

			//Setting variables for position and depth of current node
			int position = 0;
			int oldDepth = 0;

			//Grab the tree's iterator (Breadth-first)
			Iterator<BinarizedNode<String>> iter = tree.iterator();
			while(iter.hasNext()) {
				BinarizedNode<String> node = iter.next();

				//Get the node's text (if it has a datum element)
				String text;
				if(node.getDatum() == null) { text = ""; }
				else { text = "" + node.getDatum(); }

				//Create a JLabel for the datum's text, set background color, and place in JPanel
				JLabel label = new JLabel(text);
				label.setBackground(Color.red);
				if(oldDepth != node.getDepth()) {
					position = 0;
					oldDepth = node.getDepth();
				}
				position += x/(Math.pow(2, node.getDepth())+1);		//Math for position
				label.setBounds(position, node.getDepth()*25, 25, 25);
				this.add(label);									//Add label
			}

			//Finally, repaint the panel
			repaint();
		}
	}
}