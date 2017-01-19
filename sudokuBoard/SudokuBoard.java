package sudokuBoard;


import java.awt.FlowLayout;
import java.util.ArrayList;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public abstract class SudokuBoard extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	JPanel numbersPanel, boardPanel, modePanel;
	JRadioButton  basicModeButton, diagModeButton, evenOddModeButton, zigzagModeButton;
	JButton ok;
	List<JButton> numberButtons = new ArrayList<JButton>();
	List<JRadioButton> numBoard = new ArrayList<JRadioButton>();
	ButtonGroup boardButtonGroup = new ButtonGroup(), modeButtonGroup;
	ImageIcon box_unselected = new ImageIcon(SudokuBoard.class.getResource("./box_unselected.png"));
	ImageIcon box_selected = new ImageIcon(SudokuBoard.class.getResource("./box_selected.png"));
	int iconHeight = box_unselected.getIconHeight();
	int iconWidth = box_unselected.getIconWidth();
	static final int PLACE_INDEX_STRING = 1;

	public SudokuBoard(int board_size) {
		setTitle("Number Place");
		setLayout(new FlowLayout());
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//×を押したらウィンドウを閉じる
		//数字入力用のボタン
		numbersPanel = new JPanel(new GridLayout(1,7));
		JButton space = new JButton("");
		space.setActionCommand("SPACE");
		space.addActionListener(this);
		numbersPanel.add(space);
		numberButtons.add(space);
		for(int i = 1; i < board_size+1; i++) {
			JButton number = new JButton(Integer.toString(i));
			number.setActionCommand("N" + Integer.toString(i));//数字を表すために"N"をつけておく
			number.addActionListener(this);
			numbersPanel.add(number);
			numberButtons.add(number);
		}
		add(numbersPanel);
		//数字が入る盤面
		setBoard();
		//解く問題の種類
		modePanel = new JPanel(new GridLayout(4,1));
		basicModeButton = new JRadioButton("Basic", true);
		diagModeButton = new JRadioButton("Diagnoal");
		evenOddModeButton = new JRadioButton("Even-Odd");
		evenOddModeButton.setEnabled(false);
		zigzagModeButton = new JRadioButton("Zigzag");
		zigzagModeButton.setEnabled(false);
		modeButtonGroup = new ButtonGroup();
		modeButtonGroup.add(basicModeButton);
		modeButtonGroup.add(diagModeButton);
		modeButtonGroup.add(evenOddModeButton);
		modeButtonGroup.add(zigzagModeButton);
		modePanel.add(basicModeButton);
		modePanel.add(diagModeButton);
		modePanel.add(evenOddModeButton);
		modePanel.add(zigzagModeButton);
		add(modePanel);
		//OKボタン
		ok = new JButton("OK");
		ok.addActionListener(this);
		ok.setActionCommand("OK");
		add(ok);
	}	

	void clearBoard(){
		for(JRadioButton rb: numBoard) {
			rb.setText("");
		}
		numBoard.get(0).setSelected(true);
	}

	abstract void setBoard();

}
