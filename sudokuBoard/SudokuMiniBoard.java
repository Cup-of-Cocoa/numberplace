package sudokuBoard;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import numberPlace.NanpureSolver;
import numberPlace.MiniBasicNanpureSolver;
import numberPlace.MiniDiagnoalNanpureSolver;

public class SudokuMiniBoard extends SudokuBoard implements ActionListener{

	private static final long serialVersionUID = 1L;

	SudokuMiniBoard() {
		super(Sudoku.MINI_SIZE);
	}

	void setBoard(){
		boardPanel = new JPanel(new GridLayout(8,7));
		for(int i = 0; i < 8; i++) {
			for (int j = 0; j < 7; j++) {
				if (i == 2 || i == 5) boardPanel.add(new JLabel("@"));
				else if (j == 3) boardPanel.add(new JLabel("@"));
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
			if (basicModeButton.isSelected()) n = new MiniBasicNanpureSolver(board);
			else if (diagModeButton.isSelected()) n = new MiniDiagnoalNanpureSolver(board);
			else n = new MiniBasicNanpureSolver(board);
			n.solve();
		}	
	}

}
