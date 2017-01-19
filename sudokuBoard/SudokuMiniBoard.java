package sudokuBoard;

import java.awt.Dimension;
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
				if (i == 2 || i == 5) boardPanel.add(new JLabel("　"));
				else if (j == 3) boardPanel.add(new JLabel("　"));
				else {
					JRadioButton box = new JRadioButton("", box_unselected);
					box.setSelectedIcon(box_selected);
					box.setPreferredSize(new Dimension(iconWidth, iconHeight));
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
		if (e.getActionCommand().startsWith("N")) {//数字が押されたとき
			for(JRadioButton rb: numBoard) {
				if(rb.isSelected()) {
					rb.setText(e.getActionCommand().substring(PLACE_INDEX_STRING));
					break;
				}
			}
		}
		else if (e.getActionCommand().equals("SPACE")) {
			for(JRadioButton rb: numBoard) {
				if(rb.isSelected()) {
					rb.setText("");
					break;
				}
			}
		}
		else if (e.getActionCommand().equals("OK")) {
			int tmpBox = 0;
			String[] numInBoard = new String[Sudoku.MINI_SIZE*Sudoku.MINI_SIZE];
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 7; j++) {
					if (i == 2 || i == 5) continue;
					else if (j == 3) continue;
					else {
						String tmp = numBoard.get(tmpBox).getText();
						if(tmp.equals(""))numInBoard[tmpBox] = "0";
						else numInBoard[tmpBox] = numBoard.get(tmpBox).getText();
						tmpBox++;
					}
				}
			}
			NanpureSolver n;
			if (basicModeButton.isSelected()) n = new MiniBasicNanpureSolver(numInBoard);
			else if (diagModeButton.isSelected()) n = new MiniDiagnoalNanpureSolver(numInBoard);
			else n = new MiniBasicNanpureSolver(numInBoard);
			n.solve();
		}	
	}

}
