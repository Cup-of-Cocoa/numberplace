package sudokuBoard;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
	JButton spaceButton, evenOddButton, okButton;
	List<JRadioButton> numberBoard = new ArrayList<JRadioButton>();
	ButtonGroup boardButtonGroup = new ButtonGroup(), modeButtonGroup;
	int boardSize;
	String[] board;

	final ImageIcon box_unselected = new ImageIcon(SudokuBoard.class.getResource("./box_unselected.png"));
	final ImageIcon box_selected = new ImageIcon(SudokuBoard.class.getResource("./box_selected.png"));
	final ImageIcon dbox_unselected1 = new ImageIcon(SudokuBoard.class.getResource("./dbox_unselected1.png"));
	final ImageIcon dbox_selected1 = new ImageIcon(SudokuBoard.class.getResource("./dbox_selected1.png"));
	final ImageIcon dbox_unselected2 = new ImageIcon(SudokuBoard.class.getResource("./dbox_unselected2.png"));
	final ImageIcon dbox_selected2 = new ImageIcon(SudokuBoard.class.getResource("./dbox_selected2.png"));
	final ImageIcon dbox_unselected3 = new ImageIcon(SudokuBoard.class.getResource("./dbox_unselected3.png"));
	final ImageIcon dbox_selected3 = new ImageIcon(SudokuBoard.class.getResource("./dbox_selected3.png"));
	final ImageIcon eobox_unselected = new ImageIcon(SudokuBoard.class.getResource("./eobox_unselected.png"));
	final ImageIcon eobox_selected = new ImageIcon(SudokuBoard.class.getResource("./eobox_selected.png"));
	final int iconWidth = box_unselected.getIconWidth();
	final int iconHeight = box_unselected.getIconHeight();

	static final int SUDOKU_CENTER = 40;//真ん中のマスの位置
	static final int PLACE_INDEX_STRING = 1;//どの数字が押されたかを表すのはアクションコマンドの２文字目
	static final String EMPTY = "0";//numberPlaceパッケージ内のプログラムの都合上空白マスは0で表している
	static final String EVENODD_MAGIC_NUMBER = "1";//上と同じ理由で偶数奇数マス内の数字は頭に1をつけて表す

	public SudokuBoard(int board_size) {
		boardSize = board_size;
		board = new String[boardSize*boardSize];
		for(int i=0; i < boardSize*boardSize; i++) {
			board[i] = EMPTY;
		}
		//ウィンドウのタイトルやレイアウトを決める
		setTitle("Number Place");
		setLayout(new FlowLayout());
		setSize(Sudoku.SUDOKU_BOARD_WIDTH, Sudoku.SUDOKU_BOARD_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//×を押したらウィンドウを閉じる
		//数字入力用のボタンをつくる
		numbersPanel = new JPanel(new GridLayout(1,board_size+2));
		spaceButton = new JButton("");//入力を消す用のボタン
		spaceButton.setActionCommand("SPACE");
		spaceButton.addActionListener(this);
		numbersPanel.add(spaceButton);
		for(int i = 1; i <= board_size; i++) {
			JButton number = new JButton(Integer.toString(i));
			number.setActionCommand("N" + Integer.toString(i));//数字を表すために"N"をつけておく
			number.addActionListener(this);
			numbersPanel.add(number);
		}
		evenOddButton = new JButton("E-O");
		evenOddButton.setActionCommand("E-O");
		evenOddButton.addActionListener(this);
		evenOddButton.setEnabled(false);//even-oddモードの時だけ使える
		numbersPanel.add(evenOddButton);
		add(numbersPanel);
		//数字が入る盤面
		setBoard();
		//解く問題の種類を選ぶボタンをつくる
		modePanel = new JPanel(new GridLayout(4,1));
		basicModeButton = new JRadioButton("Basic", true);
		basicModeButton.setActionCommand("BASIC");
		basicModeButton.addActionListener(this);
		diagModeButton = new JRadioButton("Diagnoal");
		diagModeButton.setActionCommand("DIAG");
		diagModeButton.addActionListener(this);
		evenOddModeButton = new JRadioButton("Even-Odd");
		evenOddModeButton.setActionCommand("EVEN-ODD");
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
		//OKボタンをつくる
		okButton = new JButton("OK");
		okButton.addActionListener(this);
		okButton.setActionCommand("OK");
		add(okButton);
	}

	void clearBoard(){
		for(int i=0; i < boardSize*boardSize; i++) {
			numberBoard.get(i).setText("");
			board[i] = EMPTY;
		}
		numberBoard.get(0).setSelected(true);
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

	abstract void setBoard();

}
