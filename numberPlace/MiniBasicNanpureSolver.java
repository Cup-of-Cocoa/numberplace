package numberPlace;

public class MiniBasicNanpureSolver extends NanpureSolver{

	public MiniBasicNanpureSolver(String[] board){
		super(board);
	}

	public boolean isPlaceableInBlock(int n, int p) {
		int spnum;
		spnum = (((p/6)/2)*2)*6+((p%6)/3)*3;
		for (int i = spnum; i < spnum+3; i++) {
			if(board[i] == n) return false;
			if(board[i+size] == n) return false;
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
			if(i%2 == 1) System.out.println(" ");
		}
		System.out.println();
	}
}
