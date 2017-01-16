package sudokuBoard;


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


import numberPlace.BasicNanpureSolver;
import numberPlace.EvenOddNanpureSolver;
import numberPlace.DiagnoalNanpureSolver;
import numberPlace.NanpureSolver;
import numberPlace.ZigzagNanpureSolver;
import numberPlace.MiniBasicNanpureSolver;
import numberPlace.MiniDiagnoalNanpureSolver;
import javax.swing.ImageIcon;

public class SudokuBoard extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	JPanel numbersPanel, boardPanel, modePanel;
	JRadioButton  basicModeButton, diagModeButton, evenOddModeButton, zigzagModeButton;
	JButton ok;
	List<JButton> numberButtons = new ArrayList<JButton>();
	List<JRadioButton> numBoard = new ArrayList<JRadioButton>();
	ButtonGroup boardButtonGroup, modeButtonGroup;
	private final int BASICSIZE = 1, MINI = 2; 
	int solverMode = BASICSIZE;

	public SudokuBoard() {
		setTitle("Number Place");
		setLayout(new FlowLayout());
		setSize(500, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//×を押したらウィンドウを閉じる
		//数字入力用のボタン
		numbersPanel = new JPanel(new GridLayout(1,10));
		JButton space = new JButton("");
		space.setActionCommand("SPACE");
		space.addActionListener(this);
		numbersPanel.add(space);
		numberButtons.add(space);
		for(int i = 1; i < 10; i++) {
			JButton number = new JButton(Integer.toString(i));
			number.setActionCommand("N" + Integer.toString(i));//数字を表すために"N"をつけておく
			number.addActionListener(this);
			numbersPanel.add(number);
			numberButtons.add(number);
		}
		add(numbersPanel);
		//数字が入る盤面
		boardPanel = new JPanel(new GridLayout(11,11));
		boardButtonGroup = new ButtonGroup();
		ImageIcon box_unselected = new ImageIcon("/Game/src/sudokuBoard/box_unselected.png");
		ImageIcon box_selected = new ImageIcon("/Game/src/sudokuBoard/box_selected.png");
		for(int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				if (i == 3 || i == 7) boardPanel.add(new JLabel("　"));
				else if (j == 3 || j == 7) boardPanel.add(new JLabel("　"));
				else {
					JRadioButton box = new JRadioButton("", box_unselected);
					box.setSelectedIcon(box_selected);
					box.setPreferredSize(new Dimension(20,25));
					box.setHorizontalTextPosition(JRadioButton.CENTER);
					box.setBorderPainted(true);
					boardPanel.add(box);
					boardButtonGroup.add(box);
					numBoard.add(box);
				}
			}
		}
		numBoard.get(0).setSelected(true);
		add(boardPanel);
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

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().startsWith("N")) {//数字が押されたとき
			for(int i= 0; i < 81; i++) {
				if(numBoard.get(i).isSelected()) {
					numBoard.get(i).setText(e.getActionCommand().substring(1));
					break;
				}
			}
		}
		else if (e.getActionCommand().equals("SPACE")) {
			for(int i= 0; i < 81; i++) {
				if(numBoard.get(i).isSelected()) {
					numBoard.get(i).setText("");
					break;
				}
			}
		}
		else if (e.getActionCommand().equals("OK")) {
			int tmpBox = 0;
			String[] numInBoard = new String[81];
			for (int i = 0; i < 11; i++) {
				for (int j = 0; j < 11; j++) {
					if (i == 3 || i == 7) continue;
					else if (j == 3 || j == 7) continue;
					else {
						String tmp = numBoard.get(tmpBox).getText();
						if(tmp.equals(""))numInBoard[tmpBox] = "0";
						else numInBoard[tmpBox] = numBoard.get(tmpBox).getText();
						tmpBox++;
					}
				}
			}
			NanpureSolver n;

			if (solverMode == BASICSIZE) {
				if (basicModeButton.isSelected()) n = new BasicNanpureSolver(numInBoard);
				else if (diagModeButton.isSelected()) n = new DiagnoalNanpureSolver(numInBoard);
				else if (evenOddModeButton.isSelected()) n = new EvenOddNanpureSolver(numInBoard);
				else if (zigzagModeButton.isSelected()) n = new ZigzagNanpureSolver(numInBoard);
				else n = new BasicNanpureSolver(numInBoard);
			}
			else {
				if (basicModeButton.isSelected()) n = new MiniBasicNanpureSolver(numInBoard);
				else if (diagModeButton.isSelected()) n = new MiniDiagnoalNanpureSolver(numInBoard);
				else n = new BasicNanpureSolver(numInBoard);
			}
			n.solve();
		}	
	}

	public static void main(String[] args) {
		SudokuBoard nb = new SudokuBoard();
		nb.setVisible(true);
	}

}
