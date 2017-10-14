package sudokuBoard2;

import javax.swing.ImageIcon;

import sudokuBoard.SudokuBoard;

public enum SudokuSettings {
	final ImageIcon box_unselected = new ImageIcon(SudokuBoard.class.getResource("./box_unselected.png"));
	final ImageIcon box_selected = new ImageIcon(SudokuBoard.class.getResource("./box_selected.png"));
	final ImageIcon dbox_unselected1 = new ImageIcon(SudokuBoard.class.getResource("./dbox_unselected1.png"));
	final ImageIcon dbox_selected1 = new ImageIcon(SudokuBoard.class.getResource("./dbox_selected1.png"));
	final ImageIcon dbox_unselected2 = new ImageIcon(SudokuBoard.class.getResource("./dbox_unselected2.png"));
	final ImageIcon dbox_selected2 = new ImageIcon(SudokuBoard.class.getResource("./dbox_selected2.png"));
	final ImageIcon dbox_unselected3 = new ImageIcon(SudokuBoard.class.getResource("./dbox_unselected3.png"));
	final ImageIcon dbox_selected3 = new ImageIcon(SudokuBoard.class.getResource("./dbox_selected3.png"));
	final ImageIcon eobox_unselected = new ImageIcon(SudokuBoard.class.getResource("./eobox_unselected.png"));
	final ImageIcon eobox_selected = new ImageIcon(SudokuBoard.class.getResource("./eobox_selected.png"));
	final int iconWidth = box_unselected.getIconWidth();
	final int iconHeight = box_unselected.getIconHeight();

}
