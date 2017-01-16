package sudokuBoard;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Sudoku extends JFrame implements ActionListener{
	
	JButton basic, mini;
	
	public Sudoku() {
		setTitle("Number Place");
		setLayout(new FlowLayout());
		setSize(500, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//×を押したらウィンドウを閉じる
		
		basic = new JButton("Basic Sudoku");
		mini = new JButton("Mini Sudoku");
		basic.setActionCommand("basic");
		mini.setActionCommand("mini");
		basic.addActionListener(this);
		mini.addActionListener(this);
		add(basic);
		add(mini);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("basic")) {
			JFrame basicSudoku = new SudokuBoard();
			setContentPane(basicSudoku.getContentPane());
			setVisible(true);
		}
		else if(e.getActionCommand().equals("mini")) {
			
		}
	}
	
	public static void main(String[] args) {
		Sudoku s = new Sudoku();
		s.setVisible(true);
	}

}
