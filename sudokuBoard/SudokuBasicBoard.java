package sudokuBoard;

import java.awt.Dimension;
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

	public SudokuBasicBoard() {
		super(Sudoku.BASIC_SIZE);
	}

	public void setBoard(){
		boardPanel = new JPanel(new GridLayout(11,11));
		for(int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				if (i == 3 || i == 7) boardPanel.add(new JLabel("@"));
				else if (j == 3 || j == 7) boardPanel.add(new JLabel("@"));
				else {
					JRadioButton box = new JRadioButton("", box_unselected);
					box.setSelectedIcon(box_selected);
					int height = box_unselected.getIconHeight();
					int width = box_unselected.getIconWidth();
					box.setPreferredSize(new Dimension(width+5, height));
					box.setHorizontalTextPosition(JRadioButton.CENTER);
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
			for(int i= 0; i < Sudoku.BASIC_SIZE*Sudoku.BASIC_SIZE; i++) {
				if(numBoard.get(i).isSelected()) {
					numBoard.get(i).setText(e.getActionCommand().substring(1));
					break;
				}
			}
		}
		else if (e.getActionCommand().equals("SPACE")) {
			for(int i= 0; i < Sudoku.BASIC_SIZE*Sudoku.BASIC_SIZE; i++) {
				if(numBoard.get(i).isSelected()) {
					numBoard.get(i).setText("");
					break;
				}
			}
		}
		else if (e.getActionCommand().equals("OK")) {
			int tmpBox = 0;
			String[] numInBoard = new String[Sudoku.BASIC_SIZE*Sudoku.BASIC_SIZE];
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
			if (basicModeButton.isSelected()) n = new BasicNanpureSolver(numInBoard);
			else if (diagModeButton.isSelected()) n = new DiagnoalNanpureSolver(numInBoard);
			else if (evenOddModeButton.isSelected()) n = new EvenOddNanpureSolver(numInBoard);
			else if (zigzagModeButton.isSelected()) n = new ZigzagNanpureSolver(numInBoard);
			else n = new BasicNanpureSolver(numInBoard);
			n.solve();
		}	
	}

}
