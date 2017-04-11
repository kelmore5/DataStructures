package kelmore5.java.yeh.data_structures.tokenizer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import kelmore5.java.yeh.data_structures.node.Token;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements Runnable {
	private int x;
	private Tokenizer tokenizer;
	private MainPanel panel;

	public MainFrame() {
		super("Tokenizer");
		x = 900;
		tokenizer = new Tokenizer("((1+(1+24)+((1+1)**3))**3)+((6*2)**2)");
		panel = new MainPanel();
		
		add(panel);
		createMenu();
	}

	public void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem change = new JMenuItem("Change Expression");
		change.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String string = JOptionPane.showInputDialog("Enter a new expression");
				if(string != "" && string != null) {
					tokenizer = new Tokenizer(string);
					remove(panel);
					repaint();
					panel = new MainPanel();
					add(panel);
					panel.repaint();
					revalidate();
					repaint();
				}
			}
		});
		
		JMenuItem evaluate = new JMenuItem("Evaluate");
		evaluate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.evaluate();
			}
		});

		menu.add(change);
		menu.add(evaluate);
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
	}

	public void run() {
		setSize(x, 500);
		setVisible(true);
	}

	public static void main(String[] args) {
		MainFrame mf = new MainFrame();
		javax.swing.SwingUtilities.invokeLater(mf);
	}

	private class MainPanel extends JPanel {
		private JPanel tree;
		private JLabel eval;
		
		public MainPanel() {
			setLayout(new BorderLayout());
			setBackground(Color.white);
			tree = new JPanel();
			tree.setLayout(null);
			add(tree, BorderLayout.CENTER);
			drawTree();
			JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());
			panel.add(new JLabel("Equation: " + tokenizer.getToken()), BorderLayout.WEST);
			eval = new JLabel("Evaluation: ");
			panel.add(eval, BorderLayout.EAST);
			add(panel, BorderLayout.SOUTH);
		}
		
		public void evaluate() {
			eval.setText("Evaluation: " + tokenizer.evaulator());
		}

		public void drawTree() {
			int oldDepth = 0;
			int position = 0;
			Iterator<Token<String>> iter = tokenizer.iterator();
			while(iter.hasNext()) {
				Token<String> node = iter.next();
				String string = (node.getOperator() == null && node.getDatum() == null)? "" : node.toString();
				JLabel label = new JLabel(string);
				label.setBackground(Color.red);
				if(oldDepth != node.getDepth()) {
					position = 0;
					oldDepth = node.getDepth();
				}
				position += x/(Math.pow(2, node.getDepth())+1);

				label.setBounds(position, node.getDepth()*25, 25, 25);
				tree.add(label);
			}
		}
	}
}