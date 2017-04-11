package kelmore5.java.yeh.data_structures.binary_search_trees.test;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

import kelmore5.java.yeh.data_structures.binary_search_trees.SplayTree;
import kelmore5.java.yeh.data_structures.node.ParentedNode;


/**
 * <pre class="doc_header">
 * MainFrame is an extension of the JFrame class to test an implementation of splay tree ({@link SplayTree}).
 * It displays a tree with random numbers inserted into it
 * </pre>
 *
 * @author kelmore5
 * @custom.date Fall 2012
 */
@SuppressWarnings("serial")
class MainFrame extends JFrame implements Runnable {
	private int x;								//Width of JFrame
	private SplayTree<Integer> tree;			//Tree to display

	/**
	 * Instantiates a new Main frame
	 */
	private MainFrame() {
		super("Splay Tree Demonstration");	//Set title
		
		x = 500;								//Width
		tree = new SplayTree<>();				//Instantiate tree
		
		addToTree();							//Add numbers
		add(new MainPanel());					//Add a custom panel to the JFrame
		createMenu();							//Make the menu
	}

	/**
	 * <pre>
	 * Creates the menu of the JFrame
	 * *Note: Not implemented yet
	 * </pre>
	 */
	private void createMenu() {
		// TODO Auto-generated method stub
		/*JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu();
		JMenuItem add = new JMenuItem();
		add.addActionListener(new ActionListener() {
			public void actionPerformed() {
				JOptionPane pane = new JOptionPane();
			}
		});
		
		this.setMenuBar(menuBar);*/
	}

	/**
	 * Add random numbers to the splay tree
	 */
	private void addToTree() {
		for(int i = 0; i < 8; i++) {
			tree.add((int) (Math.random()*30));
		}
	}

	public void run() {
		//Set the size and set visibility to true for this frame
		//upon initialization
		setSize(x, 500);
		setVisible(true);
	}

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		//Create the JFrame to display and then run it
		MainFrame mf = new MainFrame();
		javax.swing.SwingUtilities.invokeLater(mf);
	}

	/**
	 * <pre>
	 * MainPanel is an extension of JPanel
	 * Upon instantiation, it will draw a map of the splay tree
	 * created when this class is initialized
	 * </pre>
	 */
	private class MainPanel extends JPanel {
		/**
		 * Instantiates a new Main panel.
		 */
		MainPanel() {
			setLayout(null);					//No layout - very simple display
			setBackground(Color.white);			//Set background to white
			drawTree();							//Draw the splay tree onto the MainPanel (JPanel)
		}

		/**
		 * Draw the splay tree onto the MainPanel (JPanel)
		 */
		void drawTree() {
			int position = 0;
			int oldDepth = 0;
			Iterator<ParentedNode<Integer>> iter = tree.iterator();
			while(iter.hasNext()) {
				ParentedNode<Integer> node = iter.next();
				JLabel label = new JLabel("" + node.getDatum());
				label.setBackground(Color.red);
				if(oldDepth != node.getDepth()) {
					position = 0;
					oldDepth = node.getDepth();
				}
				position += x/(Math.pow(2, node.getDepth())+1);
				label.setBounds(position, node.getDepth()*25, 25, 25);
				this.add(label);
			}
		}
	}
}