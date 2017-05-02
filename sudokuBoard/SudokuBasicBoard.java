package sudokuBoard;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import numberPlace.BasicNanpureSolver;
import numberPlace.DiagnoalNanpureSolver;
import numberPlace.EvenOddNanpureSolver;
import numberPlace.NanpureSolver;
import numberPlace.ZigzagNanpureSolver;

public class SudokuBasicBoard extends SudokuBoard implements ActionListener{

	private static final long serialVersionUID = 1L;

	SudokuBasicBoard() {
		super(Sudoku.BASIC_SIZE);
	}

	void setBoard(){
		boardPanel = new JPanel(new GridLayout(11,11));
		for(int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				if (i == 3 || i == 7) boardPanel.add(new JLabel(""));
				else if (j == 3 || j == 7) boardPanel.add(new JLabel(""));
				else {
					JRadioButton box = makeBox();
					boardPanel.add(box);
					boardButtonGroup.add(box);
					numberBoard.add(box);
				}
			}
		}
		numberBoard.get(0).setSelected(true);
		add(boardPanel);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().startsWith("N")) {//�����������ꂽ�Ƃ�
			for(int i=0; i < boardSize*boardSize; i++) {
				if(numberBoard.get(i).isSelected()) {
					board[i] = e.getActionCommand().substring(PLACE_INDEX_STRING);
					numberBoard.get(i).setText(board[i]);
					break;
				}
			}
		}
		else if (e.getActionCommand().equals("SPACE")) {//�󔒂������ꂽ�Ƃ�
			for(int i=0; i < boardSize*boardSize; i++) {
				if(numberBoard.get(i).isSelected()) {
					board[i] = SudokuBoard.EMPTY;
					numberBoard.get(i).setText("");
					break;
				}
			}
		}
		else if (e.getActionCommand().equals("E-O")) {
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
		else if (e.getActionCommand().equals("BASIC")) {
			setNormalBoard();
			evenOddButton.setEnabled(false);
			validate();
		}
		else if (e.getActionCommand().equals("DIAG")) {
			setNormalBoard();
			evenOddButton.setEnabled(false);
			for(int i=0; i < boardSize; i++) {
				numberBoard.get(boardSize*i+i).setIcon(dbox_unselected1);
				numberBoard.get(boardSize*i+i).setSelectedIcon(dbox_selected1);
				numberBoard.get(boardSize*(i+1)-(i+1)).setIcon(dbox_unselected2);
				numberBoard.get(boardSize*(i+1)-(i+1)).setSelectedIcon(dbox_selected2);
			}
			numberBoard.get(sudokuCenter).setIcon(dbox_unselected3);
			numberBoard.get(sudokuCenter).setSelectedIcon(dbox_selected3);
			validate();
		}
		else if (e.getActionCommand().equals("EVEN-ODD")) {
			evenOddButton.setEnabled(true);
			validate();
		}
		else if (e.getActionCommand().equals("OK")) {
			NanpureSolver n;
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
			n.solve();
		}
	}

}
