package numberPlace;

public class BasicNanpureSolver extends NanpureSolver{

	public BasicNanpureSolver(String[] board){
		super(board);
	}

	public boolean isPlaceableInBlock(int n, int p) {
		int spnum;
		spnum = (((p/9)/3)*3)*9+((p%9)/3)*3;
		for (int i = spnum; i < spnum+3; i++) {
			if(board[i] == n) return false;
			if(board[i+size] == n) return false;
			if(board[i+size*2] == n) return false;			
		}
		return true;
	}

	public static void main(String[] args) {
		BasicNanpureSolver n = new BasicNanpureSolver(args);
		n.solve();

	}
}
