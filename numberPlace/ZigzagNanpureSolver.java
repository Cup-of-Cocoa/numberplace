package numberPlace;

public class ZigzagNanpureSolver extends NanpureSolver{
	int[][] blocks;


	public ZigzagNanpureSolver(String[] board){
		size = (int)Math.sqrt(board.length);
		this.board = new int[size*size];
		blocks = new int[size][size];
		for (int i = 0; i < size*size; i++) {
			try {
				int n = Integer.parseInt(board[i]);
				this.board[i] = n/100;
				blocks[(n/10)%10][n%10] = i;
			}
			catch(NumberFormatException e) {
				System.out.println("invalid input: not integer");
			}
			if (this.board[i] == 0) emptyBoxList.add(i);
		}
	}

	public boolean isPlaceableInBlock(int n, int p) {
		int blocknum = 0;
		for(int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if(blocks[i][j] == p) blocknum = i;
			}
		}
		for (int i = 0; i< size; i++) {
			if(board[blocks[blocknum][i]] == n) return false;
		}
		return true;
	}
}
