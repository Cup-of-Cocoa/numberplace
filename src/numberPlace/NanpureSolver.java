package numberPlace;

import java.util.ArrayList;

public abstract class NanpureSolver {
	static final int EMPTY = 0;

	int[] board;
	int size;
	ArrayList<Integer> emptyBoxIndexList = new ArrayList<Integer>();
	boolean isValidBoard = true;

	public NanpureSolver() {
	}

	public NanpureSolver(String[] board) {
		size = (int) Math.sqrt(board.length);
		this.board = new int[size * size];
		for (int i = 0; i < size * size; i++) {
			try {
				this.board[i] = Integer.parseInt(board[i]);
			} catch (NumberFormatException e) {
				System.out.println("invalid input: not integer");
			}
			if (this.board[i] == NanpureSolver.EMPTY) {
				emptyBoxIndexList.add(i);
			}
		}
	}

	public boolean boardIsFull() {
		return emptyBoxIndexList.isEmpty();
	}

	public abstract boolean isPlaceableInBlock(int n, int p);

	public boolean isPlaceable(int n, int p) {
		if (board[p] != 0) {
			return false;
		}
		for (int i = 0; i < size; i++) {
			if (board[(p / size) * size + i] == n) {
				return false;
			}
			if (board[i * size + p % size] == n) {
				return false;
			}
		}
		return isPlaceableInBlock(n, p);
	}

	public void solveAll() {
		if (isValidBoard) {
			if (boardIsFull()) {
				outputBoard();
			} else {
				for (int n = 1; n <= size; n++) {
					int i = emptyBoxIndexList.get(0);
					if (isPlaceable(n, i)) {
						board[emptyBoxIndexList.get(0)] = n;
						emptyBoxIndexList.remove(0);
						solveAll();
						board[i] = 0;
						emptyBoxIndexList.add(0, i);
					}
				}
			}
		}
	}

	public int solve() {
		if (isValidBoard) {
			if (!boardIsFull()) {
				for (int n = 1; n <= size; n++) {
					int i = emptyBoxIndexList.get(0);
					if (isPlaceable(n, i)) {
						board[emptyBoxIndexList.get(0)] = n;
						emptyBoxIndexList.remove(0);
						if (solve() == 1)
							return 1;
						else {
							board[i] = 0;
							emptyBoxIndexList.add(0, i);
						}
					}

				}
				return 0;
			} else {
				outputBoard();
				return 1;
			}
		} else
			return 2;
	}

	public void outputBoard() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(board[i * size + j] + " ");
				if (j % 3 == 2) {
					System.out.print(" ");
				}
			}
			System.out.println();
			if (i % 3 == 2) {
				System.out.println();
			}
		}
		System.out.println();
	}
}
