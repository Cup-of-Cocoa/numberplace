package sudokuBoard;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import numberPlace.MiniBasicNanpureSolver;
import numberPlace.MiniDiagnoalNanpureSolver;
import numberPlace.NanpureSolver;

public class SudokuMiniBoard extends SudokuBoard implements ActionListener{

	private static final long serialVersionUID = 1L;

	SudokuMiniBoard() {
		super(Sudoku.MINI_SIZE);
	}

	void setBoard(){
		boardPanel = new JPanel(new GridLayout(8,7));
		for(int i = 0; i < 8; i++) {
			for (int j = 0; j < 7; j++) {
				if (i == 2 || i == 5) boardPanel.add(new JLabel(" "));
				else if (j == 3) boardPanel.add(new JLabel(" "));
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
		if (e.getActionCommand().startsWith("N")) {//数字が押されたとき
			for(int i=0; i < boardSize*boardSize; i++) {
				if(numberBoard.get(i).isSelected()) {
					board[i] = e.getActionCommand().substring(PLACE_INDEX_STRING);
					numberBoard.get(i).setText(board[i]);
					break;
				}
			}
		}
		else if (e.getActionCommand().equals("SPACE")) {//空白が押されたとき
			for(int i=0; i < boardSize*boardSize; i++) {
				if(numberBoard.get(i).isSelected()) {
					board[i] = SudokuBoard.EMPTY;
					numberBoard.get(i).setText("");
					break;
				}
			}
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
			validate();
		}
		else if (e.getActionCommand().equals("OK")) {
			NanpureSolver n;
			if (basicModeButton.isSelected()) n = new MiniBasicNanpureSolver(board);
			else if (diagModeButton.isSelected()) n = new MiniDiagnoalNanpureSolver(board);
			else n = new MiniBasicNanpureSolver(board);
			n.solve();
		}
	}

}
