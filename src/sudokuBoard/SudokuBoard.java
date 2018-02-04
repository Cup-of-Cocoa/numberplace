package sudokuBoard;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import numberPlace.BasicNanpureSolver;
import numberPlace.DiagnoalNanpureSolver;
import numberPlace.EvenOddNanpureSolver;
import numberPlace.MiniBasicNanpureSolver;
import numberPlace.MiniDiagnoalNanpureSolver;
import numberPlace.NanpureSolver;
import numberPlace.ZigzagNanpureSolver;

public class SudokuBoard extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	JPanel numbersPanel, boardPanel, modePanel, checkPanel;
	JRadioButton  basicModeButton, diagModeButton, evenOddModeButton, zigzagModeButton;
	JCheckBox solveAllCheckBox;
	JButton spaceButton, evenOddButton, allClearButton, okButton;
	List<JRadioButton> numberBoard = new ArrayList<JRadioButton>();
	ButtonGroup boardButtonGroup = new ButtonGroup(), modeButtonGroup;
	int boardSize;
	String[] board;

	final ImageIcon box_unselected = new ImageIcon(this.getClass().getResource("box_unselected.png"));
	final ImageIcon box_selected = new ImageIcon(this.getClass().getResource("box_selected.png"));
	final ImageIcon dbox_unselected1 = new ImageIcon(this.getClass().getResource("dbox_unselected1.png"));
	final ImageIcon dbox_selected1 = new ImageIcon(this.getClass().getResource("dbox_selected1.png"));
	final ImageIcon dbox_unselected2 = new ImageIcon(this.getClass().getResource("dbox_unselected2.png"));
	final ImageIcon dbox_selected2 = new ImageIcon(this.getClass().getResource("dbox_selected2.png"));
	final ImageIcon dbox_unselected3 = new ImageIcon(this.getClass().getResource("dbox_unselected3.png"));
	final ImageIcon dbox_selected3 = new ImageIcon(this.getClass().getResource("dbox_selected3.png"));
	final ImageIcon eobox_unselected = new ImageIcon(this.getClass().getResource("eobox_unselected.png"));
	final ImageIcon eobox_selected = new ImageIcon(this.getClass().getResource("eobox_selected.png"));
	final int iconWidth = box_unselected.getIconWidth();
	final int iconHeight = box_unselected.getIconHeight();
	final int num_of_Buttons = boardSize+3;//EMPTY, E-O, ALLCLEARのボタン

	static final int BASIC_BOARD_CENTER = 40;//真ん中のマスの位置
	static final int PLACE_INDEX_STRING = 1;//どの数字が押されたかを表すのはアクションコマンドの２文字目
	static final String EMPTY = "0";//numberPlaceパッケージ内のプログラムの都合上空白マスは0で表している
	static final String EVENODD_MAGIC_NUMBER = "1";//上と同じ理由で偶数奇数マス内の数字は頭に1をつけて表す

	static final String SPACE_AC = "Space", NUMBER_AC_INITIAL = "N", EO_AC = "E-O";
	static final String BASIC_AC = "BASIC", DIAG_AC = "DIAG", OK_AC = "OK", EVENODD_AC = "EVEN-ODD";
	static final String AC_AC = "ALLCLEAR";

	public SudokuBoard(int board_size) {
		boardSize = board_size;
		board = new String[boardSize*boardSize];
		for(int i=0; i < boardSize*boardSize; i++) {
			board[i] = EMPTY;
		}
		//ウィンドウのタイトルやレイアウトを決める
		//setTitle(Sudoku.TITLE);
		setLayout(new FlowLayout());
		setSize(Sudoku.SUDOKU_BOARD_WIDTH, Sudoku.SUDOKU_BOARD_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//×を押したらウィンドウを閉じる
		//数字入力用のボタンをつくる
		numbersPanel = new JPanel(new GridLayout(1,num_of_Buttons));
		spaceButton = new JButton(Sudoku.EMPTY_BOX);//入力を消す用のボタン
		spaceButton.setActionCommand(SPACE_AC);
		spaceButton.addActionListener(this);
		numbersPanel.add(spaceButton);
		for(int i = 1; i <= board_size; i++) {
			JButton number = new JButton(Integer.toString(i));
			number.setActionCommand(NUMBER_AC_INITIAL + Integer.toString(i));//数字を表すために"N"をつけておく
			number.addActionListener(this);
			numbersPanel.add(number);
		}
		evenOddButton = new JButton("E-O");
		evenOddButton.setActionCommand(EO_AC);
		evenOddButton.addActionListener(this);
		evenOddButton.setEnabled(false);//even-oddモードの時だけ使える
		numbersPanel.add(evenOddButton);
		allClearButton = new JButton("AC");
		allClearButton.setActionCommand(AC_AC);
		allClearButton.addActionListener(this);
		numbersPanel.add(allClearButton);
		add(numbersPanel);
		//数字が入る盤面
		if(boardSize == Sudoku.BASIC_SIZE) {
			boardPanel = new JPanel(new GridLayout(Sudoku.BASIC_SIZE+2, Sudoku.BASIC_SIZE+2));
			for(int i = 0; i < Sudoku.BASIC_SIZE+2; i++) {
				for (int j = 0; j < Sudoku.BASIC_SIZE+2; j++) {
					if (i%4 == 3) boardPanel.add(new JLabel(Sudoku.EMPTY_BOX));
					else if (j%4 == 3) boardPanel.add(new JLabel(Sudoku.EMPTY_BOX));
					else {
						JRadioButton box = makeBox();
						boardPanel.add(box);
						boardButtonGroup.add(box);
						numberBoard.add(box);
					}
				}
			}
			numberBoard.get(Sudoku.INITIAL_SELECTED_BOX).setSelected(true);
		}
		else {
			boardPanel = new JPanel(new GridLayout(Sudoku.MINI_SIZE+2 ,Sudoku.MINI_SIZE+1));
			for(int i = 0; i < Sudoku.MINI_SIZE+2; i++) {
				for (int j = 0; j < Sudoku.MINI_SIZE+1; j++) {
					if (i%3 == 2) boardPanel.add(new JLabel(Sudoku.EMPTY_BOX));
					else if (j%4 == 3) boardPanel.add(new JLabel(Sudoku.EMPTY_BOX));
					else {
						JRadioButton box = makeBox();
						boardPanel.add(box);
						boardButtonGroup.add(box);
						numberBoard.add(box);
					}
				}
			}
			numberBoard.get(Sudoku.INITIAL_SELECTED_BOX).setSelected(true);
		}
		add(boardPanel);
		//解く問題の種類を選ぶボタンをつくる
		modePanel = new JPanel(new GridLayout(Sudoku.NUM_OF_SUDOKU_VARIATIONS,1));
		basicModeButton = new JRadioButton("Basic", true);
		basicModeButton.setActionCommand(BASIC_AC);
		basicModeButton.addActionListener(this);
		diagModeButton = new JRadioButton("Diagnoal");
		diagModeButton.setActionCommand(DIAG_AC);
		diagModeButton.addActionListener(this);
		evenOddModeButton = new JRadioButton("Even-Odd");
		evenOddModeButton.setActionCommand(EVENODD_AC);
		evenOddModeButton.addActionListener(this);
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
		checkPanel = new JPanel(new GridLayout(Sudoku.NUM_OF_OPTIONS,1));
		solveAllCheckBox = new JCheckBox("答えをすべて表示する");
		checkPanel.add(solveAllCheckBox);
		add(checkPanel);
		//OKボタンをつくる
		okButton = new JButton("OK");
		okButton.addActionListener(this);
		okButton.setActionCommand(OK_AC);
		add(okButton);
	}

	void setBasicBoard(){
		boardPanel = new JPanel(new GridLayout(Sudoku.BASIC_SIZE+2, Sudoku.BASIC_SIZE+2));
		for(int i = 0; i < Sudoku.BASIC_SIZE+2; i++) {
			for (int j = 0; j < Sudoku.BASIC_SIZE+2; j++) {
				if (i%4 == 3) boardPanel.add(new JLabel(Sudoku.EMPTY_BOX));
				else if (j%4 == 3) boardPanel.add(new JLabel(Sudoku.EMPTY_BOX));
				else {
					JRadioButton box = makeBox();
					boardPanel.add(box);
					boardButtonGroup.add(box);
					numberBoard.add(box);
				}
			}
		}
		numberBoard.get(Sudoku.INITIAL_SELECTED_BOX).setSelected(true);
		validate();
	}

	void setMiniBoard(){
		boardPanel = new JPanel(new GridLayout(Sudoku.MINI_SIZE+2 ,Sudoku.MINI_SIZE+1));
		for(int i = 0; i < Sudoku.MINI_SIZE+2; i++) {
			for (int j = 0; j < Sudoku.MINI_SIZE+1; j++) {
				if (i%3 == 2) boardPanel.add(new JLabel(Sudoku.EMPTY_BOX));
				else if (j%4 == 3) boardPanel.add(new JLabel(Sudoku.EMPTY_BOX));
				else {
					JRadioButton box = makeBox();
					boardPanel.add(box);
					boardButtonGroup.add(box);
					numberBoard.add(box);
				}
			}
		}
		numberBoard.get(Sudoku.INITIAL_SELECTED_BOX).setSelected(true);
		validate();
	}

	void clearBoard(){
		for(int i=0; i < boardSize*boardSize; i++) {
			numberBoard.get(i).setText(Sudoku.EMPTY_BOX);
			board[i] = EMPTY;
		}
	}

	JRadioButton makeBox() {
		JRadioButton box = new JRadioButton("", box_unselected);
		box.setSelectedIcon(box_selected);
		box.setPreferredSize(new Dimension(iconWidth+5, iconHeight));
		box.setHorizontalTextPosition(JRadioButton.CENTER);
		return box;
	}

	void setNormalBoard(){
		for(JRadioButton rb: numberBoard) {
			rb.setIcon(box_unselected);
			rb.setSelectedIcon(box_selected);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().startsWith(NUMBER_AC_INITIAL)) {
			for(int i=0; i < boardSize*boardSize; i++) {
				if(numberBoard.get(i).isSelected()) {
					board[i] = e.getActionCommand().substring(PLACE_INDEX_STRING);
					numberBoard.get(i).setText(board[i]);
					break;
				}
			}
		}
		else if (e.getActionCommand().equals(SPACE_AC)) {
			for(int i=0; i < boardSize*boardSize; i++) {
				if(numberBoard.get(i).isSelected()) {
					board[i] = SudokuBoard.EMPTY;
					numberBoard.get(i).setText("");
					break;
				}
			}
		}
		else if (e.getActionCommand().equals(EO_AC)) {
			for(JRadioButton rb: numberBoard) {
				if(rb.isSelected()) {
					if(rb.getIcon().equals(box_unselected)){
						rb.setIcon(eobox_unselected);
						rb.setSelectedIcon(eobox_selected);
					}
					else {
						rb.setIcon(box_unselected);
						rb.setSelectedIcon(box_selected);
					}
				}
			}
			validate();
		}
		else if (e.getActionCommand().equals(AC_AC)) {
			clearBoard();
			validate();
		}
		else if (e.getActionCommand().equals(BASIC_AC)) {
			setNormalBoard();
			evenOddButton.setEnabled(false);
			validate();
		}
		else if (e.getActionCommand().equals(DIAG_AC)) {
			setNormalBoard();
			evenOddButton.setEnabled(false);
			for(int i=0; i < boardSize; i++) {
				numberBoard.get(boardSize*i+i).setIcon(dbox_unselected1);
				numberBoard.get(boardSize*i+i).setSelectedIcon(dbox_selected1);
				numberBoard.get(boardSize*(i+1)-(i+1)).setIcon(dbox_unselected2);
				numberBoard.get(boardSize*(i+1)-(i+1)).setSelectedIcon(dbox_selected2);
			}
			if (Sudoku.sudokuMode == Sudoku.BASIC_MODE) {
				numberBoard.get(BASIC_BOARD_CENTER).setIcon(dbox_unselected3);
				numberBoard.get(BASIC_BOARD_CENTER).setSelectedIcon(dbox_selected3);
			}
			validate();
		}
		else if (e.getActionCommand().equals(EVENODD_AC)) {
			for(JRadioButton rb: numberBoard) {
				if(rb.getIcon().equals(eobox_selected) || rb.getIcon().equals(eobox_unselected)) continue;
				else {
					rb.setIcon(box_unselected);
					rb.setSelectedIcon(box_selected);
				}
			}
			evenOddButton.setEnabled(true);
			validate();
		}
		else if (e.getActionCommand().equals(OK_AC)) {
			NanpureSolver n;
			if (Sudoku.sudokuMode == Sudoku.BASIC_MODE) {
				if (basicModeButton.isSelected()) n = new BasicNanpureSolver(board);
				else if (diagModeButton.isSelected()) n = new DiagnoalNanpureSolver(board);
				else if (evenOddModeButton.isSelected()) {
					String[] newBoard = new String[boardSize*boardSize];
					for (int i=0; i < boardSize*boardSize; i++) {
						if(numberBoard.get(i).getIcon().equals(eobox_unselected)) {
							newBoard[i] = SudokuBoard.EVENODD_MAGIC_NUMBER + board[i];
						}
						else {
							newBoard[i] = board[i];
						}
					}
					n = new EvenOddNanpureSolver(newBoard);
				}
				else if (zigzagModeButton.isSelected()) n = new ZigzagNanpureSolver(board);
				else n = new BasicNanpureSolver(board);
				if(solveAllCheckBox.isSelected()) n.solveAll();
				else {
					n.solve();
				}
			}
			else {
				if (basicModeButton.isSelected()) n = new MiniBasicNanpureSolver(board);
				else if (diagModeButton.isSelected()) n = new MiniDiagnoalNanpureSolver(board);
				/*
				else if (evenOddModeButton.isSelected()) {
					String[] newBoard = new String[boardSize*boardSize];
					for (int i=0; i < boardSize*boardSize; i++) {
						if(numberBoard.get(i).getIcon().equals(eobox_unselected)) {
							newBoard[i] = SudokuBoard.EVENODD_MAGIC_NUMBER + board[i];
						}
						else {
							newBoard[i] = board[i];
						}
					}
					n = new EvenOddNanpureSolver(newBoard);
					}

				else if (zigzagModeButton.isSelected()) n = new ZigzagNanpureSolver(board);
				 */
				else n = new MiniBasicNanpureSolver(board);
				if(solveAllCheckBox.isSelected()) n.solveAll();
				else {
					n.solve();
				}
			}
		}
	}

}
