package sudokuBoard;


import java.awt.FlowLayout;
import java.util.ArrayList;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
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
	ButtonGroup boardButtonGroup, modeButtonGroup;

	public SudokuBoard() {
		setTitle("Number Place");
		setLayout(new FlowLayout());
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//×を押したらウィンドウを閉じる
		//数字入力用のボタン
		setNumbers();
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
	
	public abstract void setNumbers();
	public abstract void setBoard();
	
}
