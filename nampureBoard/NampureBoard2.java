package nampureBoard;


import java.awt.FlowLayout;
import java.util.ArrayList;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import numberPlace.BasicNanpureSolver;
import numberPlace.EvenOddNanpureSolver;
import numberPlace.DiagnoalNanpureSolver;
import numberPlace.NanpureSolver;


public class NampureBoard2 extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	JPanel board, buttons;
	JRadioButton diag, evenOdd, basic;
	List<JTextField> numBoard = new ArrayList<JTextField>();
	ButtonGroup mode;

	public NampureBoard2() {
		setTitle("Number Place");
		setLayout(new FlowLayout());
		setSize(400, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//×を押したらウィンドウを閉じる
		//数字が入る盤面
		board = new JPanel();
		board.setLayout(new GridLayout(11,11));
		for(int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				if (i == 3 || i == 7) board.add(new JLabel(" "));
				else if (j == 3 || j == 7) board.add(new JLabel(" "));
				else {
					JTextField tf = new JTextField(1);
					board.add(tf);
					numBoard.add(tf);
				}
			}

		}
		add(board);
		buttons = new JPanel(new GridLayout(3,1));
		diag = new JRadioButton("Diagnoal");
		evenOdd = new JRadioButton("Even-Odd");
		basic = new JRadioButton("Basic");
		mode = new ButtonGroup();
		mode.add(basic);
		mode.add(diag);
		mode.add(evenOdd);
		buttons.add(basic);
		buttons.add(diag);
		buttons.add(evenOdd);
		add(buttons);
		basic.setSelected(true);
		JButton ok = new JButton("OK");
		ok.addActionListener(this);
		ok.setActionCommand("OK");
		add(ok);
	}


	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("OK")) {
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

			if (basic.isSelected()) n = new BasicNanpureSolver(numInBoard);
			else if (evenOdd.isSelected()) n = new EvenOddNanpureSolver(numInBoard);
			else if (diag.isSelected()) n = new DiagnoalNanpureSolver(numInBoard);
			else n = new BasicNanpureSolver(numInBoard);
			n.solve();
		}
	}

	public static void main(String[] args) {
		NampureBoard2 nb = new NampureBoard2();
		nb.setVisible(true);
	}

}
