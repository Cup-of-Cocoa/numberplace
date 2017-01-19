package sudokuBoard;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import numberPlace.BasicNanpureSolver;
import numberPlace.EvenOddNanpureSolver;
import numberPlace.DiagnoalNanpureSolver;
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
				if (i == 3 || i == 7) boardPanel.add(new JLabel("@"));
				else if (j == 3 || j == 7) boardPanel.add(new JLabel("@"));
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
		if (e.getActionCommand().startsWith("N")) {//”Žš‚ª‰Ÿ‚³‚ê‚½‚Æ‚«
			for(int i=0; i < boardSize*boardSize; i++) {
				if(numberBoard.get(i).isSelected()) {
					String number = e.getActionCommand().substring(PLACE_INDEX_STRING);
					numberBoard.get(i).setText(number);
					board[i] = number;
					break;
				}
			}
		}
		else if (e.getActionCommand().equals("SPACE")) {
			for(int i=0; i < boardSize*boardSize; i++) {
				if(numberBoard.get(i).isSelected()) {
					numberBoard.get(i).setText("");
					board[i] = SudokuBoard.EMPTY;
					break;
				}
			}
		}	
		else if (e.getActionCommand().equals("E-O")) {
			for(int i=0; i < boardSize*boardSize; i++) {
				if(numberBoard.get(i).isSelected()) {
					if(numberBoard.get(i).getIcon().equals(box_unselected)){
						numberBoard.get(i).setIcon(eobox_unselected);
						numberBoard.get(i).setSelectedIcon(eobox_selected);
					}
					else {
						numberBoard.get(i).setIcon(box_unselected);
						numberBoard.get(i).setSelectedIcon(box_selected);
					}
				}
			}
			validate();
		}
		else if (e.getActionCommand().equals("BASIC")) {
			evenOddModeButton.setEnabled(false);
			for(int i=0; i < boardSize; i++) {
				numberBoard.get(boardSize*i+i).setIcon(box_unselected);
				numberBoard.get(boardSize*i+i).setSelectedIcon(box_selected);
				numberBoard.get(boardSize*(i+1)-(i+1)).setIcon(box_unselected);
				numberBoard.get(boardSize*(i+1)-(i+1)).setSelectedIcon(box_selected);
			}
			numberBoard.get(sudokuCenter).setIcon(box_unselected);
			numberBoard.get(sudokuCenter).setSelectedIcon(box_selected);
			validate();
		}
		else if (e.getActionCommand().equals("DIAG")) {
			evenOddModeButton.setEnabled(false);
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
			evenOddModeButton.setEnabled(true);
			validate();
		}
		else if (e.getActionCommand().equals("OK")) {
			NanpureSolver n;
			if (basicModeButton.isSelected()) n = new BasicNanpureSolver(board);
			else if (diagModeButton.isSelected()) n = new DiagnoalNanpureSolver(board);
			else if (evenOddModeButton.isSelected()) n = new EvenOddNanpureSolver(board);
			else if (zigzagModeButton.isSelected()) n = new ZigzagNanpureSolver(board);
			else n = new BasicNanpureSolver(board);
			n.solve();
		}
	}

}
