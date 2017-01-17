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

	static final int MINI_SIZE = 6;

	public SudokuMiniBoard() {
		super(MINI_SIZE);
	}

	public void setBoard(){
		boardPanel = new JPanel(new GridLayout(8,8));
		for(int i = 0; i < 8; i++) {
			for (int j = 0; j < 7; j++) {
				if (i == 2 || i == 5) boardPanel.add(new JLabel("@"));
				else if (j == 3) boardPanel.add(new JLabel("@"));
				else {
					JRadioButton box = new JRadioButton("", box_unselected);
					box.setSelectedIcon(box_selected);
					int height = box_unselected.getIconHeight();
					int width = box_unselected.getIconWidth();
					box.setPreferredSize(new Dimension(width, height));
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
			for(int i= 0; i < 36; i++) {
				if(numBoard.get(i).isSelected()) {
					numBoard.get(i).setText(e.getActionCommand().substring(1));
					break;
				}
			}
		}
		else if (e.getActionCommand().equals("SPACE")) {
			for(int i= 0; i < 36; i++) {
				if(numBoard.get(i).isSelected()) {
					numBoard.get(i).setText("");
					break;
				}
			}
		}
		else if (e.getActionCommand().equals("OK")) {
			int tmpBox = 0;
			String[] numInBoard = new String[MINI_SIZE*MINI_SIZE];
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
