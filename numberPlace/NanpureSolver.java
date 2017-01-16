package numberPlace;

import java.util.ArrayList;

public abstract class NanpureSolver {
	int[] board; //盤面
	int size; //一辺のマス数
	ArrayList<Integer> emptyBoxList = new ArrayList<Integer>();//空いてるマスのリスト

	public NanpureSolver(){}

	public NanpureSolver(String[] board){
		size = (int)Math.sqrt(board.length);
		this.board = new int[size*size];
		for (int i = 0; i < size*size; i++) {
			try {
				this.board[i] = Integer.parseInt(board[i]);
			}
			catch(NumberFormatException e) {
				System.out.println("invalid input: not integer");
			}
			if (this.board[i] == 0) emptyBoxList.add(i);
		}
	}
	public boolean boardIsFull() {//盤面が埋まっているかどうか
		return emptyBoxList.isEmpty();	
	}

	public abstract boolean isPlaceableInBlock(int n, int p);

	public boolean isPlaceable(int n, int p) {
		if(board[p] != 0) return false;
		for (int i = 0; i < size; i++) {
			if(board[(p/size)*size+i] == n) return false; 
			if(board[i*size+p%size] == n) return false;
		}
		return isPlaceableInBlock(n,p);		
	}

	public void solve(){
		if(!boardIsFull()) {
			for(int n = 1; n <= size; n++){
				int i = emptyBoxList.get(0);
				if(isPlaceable(n, i)) {
					board[emptyBoxList.get(0)] = n;
					emptyBoxList.remove(0);
					solve();     
					board[i] = 0; 
					emptyBoxList.add(0,i);
				}
			}

		}
		else {
			outputBoard();
		}

	}

	public void outputBoard() {
		for(int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(board[i*size+j] + " ");
				if(j%3 == 2) System.out.print(" ");
			}
			System.out.println();
			if(i%3 == 2) System.out.println();
		}
		System.out.println();
	}
}
