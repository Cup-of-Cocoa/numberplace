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

	JMenuBar sudokuMenuBar;
	JMenu boardMode;
	JMenuItem basic, mini;
	static final int SUDOKU_BOARD_WIDTH = 630, SUDOKU_BOARD_HEIGHT = 400;
	static final int MINI_SUDOKU_BOARD_WIDTH = 500, MINI_SUDOKU_BOARD_HEIGHT = 350;
	static final int MINI_SIZE = 6, BASIC_SIZE = 9;
	private SudokuBoard basicBoard = new SudokuBasicBoard();
	private SudokuBoard miniBoard = new SudokuMiniBoard();

	public Sudoku() {
		setTitle("Number Place");
		setLayout(new FlowLayout());
		setSize(SUDOKU_BOARD_WIDTH, SUDOKU_BOARD_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�~����������E�B���h�E�����

		sudokuMenuBar = new JMenuBar();
		boardMode = new JMenu("Board");
		basic = new JMenuItem("Basic");
		mini = new JMenuItem("Mini");
		basic.setActionCommand("basic");
		mini.setActionCommand("mini");
		basic.addActionListener(this);
		mini.addActionListener(this);
		boardMode.add(basic);
		boardMode.add(mini);
		sudokuMenuBar.add(boardMode);
		setJMenuBar(sudokuMenuBar);

		setContentPane(basicBoard.getContentPane());
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("basic")) {
			basicBoard.clearBoard();
			setSize(SUDOKU_BOARD_WIDTH, SUDOKU_BOARD_HEIGHT);
			setContentPane(basicBoard.getContentPane());
			setVisible(true);
		}
		else if(e.getActionCommand().equals("mini")) {
			miniBoard.clearBoard();
			setSize(MINI_SUDOKU_BOARD_WIDTH, MINI_SUDOKU_BOARD_HEIGHT);
			setContentPane(miniBoard.getContentPane());
			setVisible(true);
		}
	}

	public static void main(String[] args) {
		Sudoku s = new Sudoku();
		s.setVisible(true);
	}

}
