package sudokuBoard;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import numberPlace.BasicNanpureSolver;
import numberPlace.EvenOddNanpureSolver;
import numberPlace.DiagnoalNanpureSolver;
import numberPlace.NanpureSolver;
import numberPlace.ZigzagNanpureSolver;

public class SudokuBasicBoard extends SudokuBoard implements ActionListener{
	
	final int BASIC_SIZE = 81;

	private static final long serialVersionUID = 1L;

	public SudokuBasicBoard() {
		super();
	}

	public void setNumbers() { 
		numbersPanel = new JPanel(new GridLayout(1,10));
		JButton space = new JButton("");
		space.setActionCommand("SPACE");
		space.addActionListener(this);
		numbersPanel.add(space);
		numberButtons.add(space);
		for(int i = 1; i < 10; i++) {
			JButton number = new JButton(Integer.toString(i));
			number.setActionCommand("N" + Integer.toString(i));//”Žš‚ð•\‚·‚½‚ß‚É"N"‚ð‚Â‚¯‚Ä‚¨‚­
			number.addActionListener(this);
			numbersPanel.add(number);
			numberButtons.add(number);
		}
		add(numbersPanel);
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
					box.setPreferredSize(new Dimension(20,25));
					box.setHorizontalTextPosition(JRadioButton.CENTER);
					box.setBorderPainted(true);
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
			for(int i= 0; i < BASIC_SIZE; i++) {
				if(numBoard.get(i).isSelected()) {
					numBoard.get(i).setText(e.getActionCommand().substring(1));
					break;
				}
			}
		}
		else if (e.getActionCommand().equals("SPACE")) {
			for(int i= 0; i < 81; i++) {
				if(numBoard.get(i).isSelected()) {
					numBoard.get(i).setText("");
					break;
				}
			}
		}
		else if (e.getActionCommand().equals("OK")) {
			int tmpBox = 0;
			String[] numInBoard = new String[81];
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
