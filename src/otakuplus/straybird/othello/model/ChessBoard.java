package otakuplus.straybird.othello.model;

public class ChessBoard {

	/**
	 * ChessBoard stands for one game's chessboard. each player should set one
	 * chessman on the chessboard in his/her turn. This class should check that
	 * if a chessman is legal to be set to one position.
	 */
	public static final int BOARDWIDTH = 8;

	private Chessman chessmen[][] = null;
	private int suggestPostion[] = null;
	private Chessman currentChessman = null;

	public ChessBoard() {
		if (currentChessman == null) {
			currentChessman = new Chessman();
		}
		if (suggestPostion == null) {
			suggestPostion = new int[64];
		}
		if (chessmen == null) {
			chessmen = new Chessman[BOARDWIDTH][BOARDWIDTH];
			int i = 0, j = 0;
			for (i = 0; i < BOARDWIDTH; i++) {
				for (j = 0; j < BOARDWIDTH; j++) {
					chessmen[i][j] = new Chessman();
				}
			}
		}
		initChessboard();
	}

	/**
	 * initChessboard funtion resets the chessboard.
	 */
	public void initChessboard() {
		int i = 0, j = 0;
		if (chessmen == null) {
			System.err.println("Chessboard initialization failed!");
			return;
		}
		// Clean the chessboard
		for (i = 0; i < BOARDWIDTH; i++) {
			for (j = 0; j < BOARDWIDTH; j++) {
				chessmen[i][j].setChessman(Chessman.CHESSMAN_NONE);
			}
		}
		// Set four chessmen
		chessmen[3][3].setChessman(Chessman.CHESSMAN_WHITE);
		chessmen[3][4].setChessman(Chessman.CHESSMAN_BLACK);
		chessmen[4][3].setChessman(Chessman.CHESSMAN_BLACK);
		chessmen[4][4].setChessman(Chessman.CHESSMAN_WHITE);

		// Set Player's current chessman to black
		currentChessman.setChessman(Chessman.CHESSMAN_BLACK);
	}

	/**
	 * checkChessmanPosition function is used to check if a chessman is able to
	 * be set to the position. This function should be called after
	 * searchSuggestedChessmanPosition.
	 * 
	 * @param x
	 *            the row position of the chessman, from 0 to BOARDWIDTH - 1
	 * @param y
	 *            the column position of the chessman, from 0 to BOARDWIDTH - 1
	 * @return boolean value stands for if it is ok to set a chessman
	 */
	private boolean checkChessmanPosition(int x, int y) {
		if (0 <= x && x < 8 && 0 <= y && y < 8) {
			int position = x * 8 + y;
			if (suggestPostion[position] == 1) {
				return true;
			}
		}
		return false;
	}

	// private void test

	public void searchSuggestedChessmanPosition() {
		int i = 0, j = 0, position = 0;
		for (i = 0; i < BOARDWIDTH; i++) {
			for (j = 0; j < BOARDWIDTH; j++) {
				position = i * 8 + j;
				if (chessmen[i][j].getChessman() != Chessman.CHESSMAN_NONE) {
					suggestPostion[position] = 0;
				} else {

				}
			}
		}
	}

	/**
	 * set current player's chessman to the position (x,y) of chessboard. This
	 * fucntion should be called after searchSuggestedChessmanPosition.
	 * 
	 * @param x
	 *            the row position of the chessman, from 0 to BOARDWIDTH - 1
	 * @param y
	 *            the column position of the chessman, from 0 to BOARDWIDTH - 1
	 */
	public void setChessman(int x, int y) {
		if (chessmen != null) {
			if (0 <= x && x < BOARDWIDTH && 0 <= y && y < BOARDWIDTH
					&& chessmen[x][y].getChessman() == Chessman.CHESSMAN_NONE) {
				if (checkChessmanPosition(x, y) == true) {
					chessmen[x][y].setChessman(currentChessman.getChessman());
				}
			}
		} else {
			System.err.println("Chessboard initialization failed!.");
		}
	}

	/**
	 * get the chessman of position (x,y).
	 * 
	 * @param x
	 *            the row position of the chessman, from 0 to BOARDWIDTH - 1
	 * @param y
	 *            the column position of the chessman, from 0 to BOARDWIDTH - 1
	 * @return the chessman of position (x,y);
	 */
	public Chessman getChessman(int x, int y) {
		if (0 <= x && x < 8 && 0 <= y && y < 8) {
			return chessmen[x][y];
		} else {
			System.err.println("out of index.");
			return null;
		}
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ChessBoard chessBoard = new ChessBoard();
		int i = 0, j = 0;
		System.out.println("The chessboard:");
		for (i = 0; i < ChessBoard.BOARDWIDTH; i++) {
			for (j = 0; j < ChessBoard.BOARDWIDTH; j++) {
				System.out.print(chessBoard.getChessman(i, j) + " ");
			}
			System.out.println("");
		}
	}

}
