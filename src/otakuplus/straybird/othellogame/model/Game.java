package otakuplus.straybird.othellogame.model;

import java.time.Instant;

public class Game {

	/**
	 * Game represent one game between two players. A game should record the
	 * game time, two players, and the result.
	 */
	User playerBlack;
	User playerWhite;
	int black;
	int white;
	Instant gameBeginTime;
	Instant gameEndTime;

	private ChessBoard chessBoard = null;

	public Game() {
		if (chessBoard == null) {
			chessBoard = new ChessBoard();
		}
	}

	public void newGame() {
		chessBoard.initChessboard();
	}

	public void update() {
		chessBoard.searchSuggestedChessmanPosition();

	}

	public static void main(String[] args) {
		Instant instant = Instant.now();
	}
}
