package numberPlace;

import java.util.ArrayList;

public class EvenOddNanpureSolver extends NanpureSolver{
	ArrayList<Integer> even_oddList = new ArrayList<Integer>();
	boolean isEvenBoard = true;

	public EvenOddNanpureSolver(String[] board){
		size = (int)Math.sqrt(board.length);
		this.board = new int[size*size];
		for (int i = 0; i < size*size; i++) {
			try {
				this.board[i] = Integer.parseInt(board[i])%10;
				if(Integer.parseInt(board[i])/10 == 1) even_oddList.add(i);
			}
			catch(NumberFormatException e) {
				System.out.println("invalid input: not integer");
			}
			if (this.board[i] == 0) emptyBoxList.add(i);
		}
		if(this.board[even_oddList.get(0)]%2 ==1) isEvenBoard = false;
		for (int i = 0; i < even_oddList.size(); i++) {
			if(isEvenBoard ^ (even_oddList.get(i)%2 == 0)) ;
		}
	}

	public boolean isPlaceableInSpPoint(int n, int p) {
		if(!even_oddList.contains(p)) return true;
		else{
			if(isEvenBoard){
				return (n%2 == 0);
			}
			else return (n%2 == 1);

		}
	}

	public boolean isPlaceableInBlock(int n, int p) {
		int spnum;
		spnum = (((p/9)/3)*3)*9+((p%9)/3)*3;
		for (int i = spnum; i < spnum+3; i++) {
			if(board[i] == n) return false;
			if(board[i+size] == n) return false;
			if(board[i+size*2] == n) return false;
		}
		return isPlaceableInSpPoint(n, p);
	}
}
