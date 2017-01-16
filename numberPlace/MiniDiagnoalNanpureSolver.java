package numberPlace;

public class MiniDiagnoalNanpureSolver extends NanpureSolver{

	public MiniDiagnoalNanpureSolver(String[] board){
		super(board);
	}

	public boolean isPlaceableInDiag1(int n, int p) {
		for (int i = 0; i < size; i++) {
			if(board[i*size+i] == n) return false;
		}
		return true;
	}

	public boolean isPlaceableInDiag2(int n, int p) {
		for (int i = 0; i < size; i++) {
			if(board[(size-i)*size - (size-i)] == n) return false;
		}
		return true;
	}

	public boolean isPlaceableInBlock(int n, int p) {
		int spnum;
		spnum = (((p/6)/2)*2)*6+((p%6)/3)*3;
		for (int i = spnum; i < spnum+3; i++) {
			if(board[i] == n) return false;
			if(board[i+size] == n) return false;
		}
		if (p%7 == 0) return isPlaceableInDiag1(n,p);
		else if (p%5 ==0) return isPlaceableInDiag2(n,p);
		else return true;
	}

	public void outputBoard() {
		for(int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(board[i*size+j] + " ");
				if(j%3 == 2) System.out.print(" ");
			}
			System.out.println();
			if(i%2 == 1) System.out.println(" ");
		}
		System.out.println();
	}
	public static void main(String[] args) {
		MiniDiagnoalNanpureSolver n = new MiniDiagnoalNanpureSolver(args);
		n.solve();
	}
}
