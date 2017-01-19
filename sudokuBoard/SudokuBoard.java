package sudokuBoard;


import java.awt.Dimension;
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
	JButton spaceButton, evenOddButton, okButton;
	List<JButton> numberButtons = new ArrayList<JButton>();
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
	final int iconHeight = box_unselected.getIconHeight();
	final int iconWidth = box_unselected.getIconWidth();
	final int sudokuCenter = 40;
	
	static final int PLACE_INDEX_STRING = 1;
	static final String EMPTY = "0";
	static final String EVENODD_MAGIC_NUMBER = "1";

	public SudokuBoard(int board_size) {
		boardSize = board_size;
		board = new String[boardSize*boardSize];
		for(int i=0; i < boardSize*boardSize; i++) {
			board[i] = EMPTY;
		}
		setTitle("Number Place");
		setLayout(new FlowLayout());
		setSize(Sudoku.SUDOKU_BOARD_HEIGHT, Sudoku.SUDOKU_BOARD_WIDTH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�~����������E�B���h�E�����
		//�������͗p�̃{�^��
		numbersPanel = new JPanel(new GridLayout(1,board_size+2));
		spaceButton = new JButton("");
		spaceButton.setActionCommand("SPACE");
		spaceButton.addActionListener(this);
		numbersPanel.add(spaceButton);
		numberButtons.add(spaceButton);
		for(int i = 1; i < board_size+1; i++) {
			JButton number = new JButton(Integer.toString(i));
			number.setActionCommand("N" + Integer.toString(i));//������\�����߂�"N"�����Ă���
			number.addActionListener(this);
			numbersPanel.add(number);
			numberButtons.add(number);
		}
		evenOddButton = new JButton("E-O");
		evenOddButton.setActionCommand("E-O");
		evenOddButton.addActionListener(this);
		evenOddButton.setEnabled(false);
		numbersPanel.add(evenOddButton);
		numberButtons.add(evenOddButton);
		add(numbersPanel);
		//����������Ֆ�
		setBoard();
		//�������̎��
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
		//OK�{�^��
		okButton = new JButton("OK");
		okButton.addActionListener(this);
		okButton.setActionCommand("OK");
		add(okButton);
	}	

	void clearBoard(){
		for(JRadioButton rb: numberBoard) {
			rb.setText("");
		}
		for(int i=0; i < boardSize*boardSize; i++) {
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

	abstract void setBoard();

}
