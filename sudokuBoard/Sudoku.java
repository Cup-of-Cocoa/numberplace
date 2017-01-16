package sudokuBoard;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Sudoku extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;

	JMenuBar jmb;
	JMenu boardMode;
	JMenuItem basic, mini;
	static final int SUDOKU_HEIGHT = 500, SUDOKU_WIDTH = 400;
	
	public Sudoku() {
		setTitle("Number Place");
		setLayout(new FlowLayout());
		setSize(SUDOKU_HEIGHT, SUDOKU_WIDTH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//×を押したらウィンドウを閉じる
		
		jmb = new JMenuBar();
		boardMode = new JMenu("Board");
		basic = new JMenuItem("Basic");
		mini = new JMenuItem("Mini");
		basic.setActionCommand("basic");
		mini.setActionCommand("mini");
		basic.addActionListener(this);
		mini.addActionListener(this);
		boardMode.add(basic);
		boardMode.add(mini);
		jmb.add(boardMode);
		setJMenuBar(jmb);
		
		setContentPane(new SudokuBasicBoard().getContentPane());
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("basic")) {
			setContentPane(new SudokuBasicBoard().getContentPane());
			setVisible(true);
		}
		else if(e.getActionCommand().equals("mini")) {
			setSize(400,350);
			setContentPane(new SudokuMiniBoard().getContentPane());
			setVisible(true);
		}
	}
	
	public static void main(String[] args) {
		Sudoku s = new Sudoku();
		s.setVisible(true);
	}

}
