package numberPlace;

public class DiagnoalNanpureSolver extends NanpureSolver{

	public DiagnoalNanpureSolver(String[] board){
		super(board);
	}

	public boolean isPlaceableInBlock(int n, int p) {
		int spnum;
		spnum = (((p/9)/3)*3)*9+((p%9)/3)*3;
		for (int i = spnum; i < spnum+3; i++) {
			if(board[i] == n || board[i+size] == n || board[i+size*2] == n) return false;
		}
		if(p%10 == 0)	return isPlaceableInDiag1(n, p);
		else if (p%8 == 0) return isPlaceableInDiag2(n, p);
		else return true;
	}

	public boolean isPlaceableInDiag1(int n, int p) {
		for (int i = 0; i < size; i++) {
			if(board[i*size+i] == n) return false;
		}
		if(p == 40) return isPlaceableInDiag2(n,p);
		else return true;
	}

	public boolean isPlaceableInDiag2(int n, int p) {
		for (int i = 0; i < size; i++) {
			if(board[(size-i)*size - (size-i)] == n) return false;
		}
		return true;
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
