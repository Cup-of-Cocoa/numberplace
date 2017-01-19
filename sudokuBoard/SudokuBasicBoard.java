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
					numBoard.add(box);
				}
			}
		}
		numBoard.get(0).setSelected(true);
		add(boardPanel);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().startsWith("N")) {//”Žš‚ª‰Ÿ‚³‚ê‚½‚Æ‚«
			for(int i=0; i < boardSize*boardSize; i++) {
				if(numBoard.get(i).isSelected()) {
					String number = e.getActionCommand().substring(PLACE_INDEX_STRING);
					numBoard.get(i).setText(number);
					board[i] = number;
					break;
				}
			}
		}
		else if (e.getActionCommand().equals("SPACE")) {
			for(int i=0; i < boardSize*boardSize; i++) {
				if(numBoard.get(i).isSelected()) {
					numBoard.get(i).setText("");
					board[i] = SudokuBoard.EMPTY;
					break;
				}
			}
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
