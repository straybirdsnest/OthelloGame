package otakuplus.straybird.othello.model;

import java.time.Instant;

public class Game {

	/**
	 * Game represent one game between two players.
	 * A game should record the game time, two players, and the result.
	 */
	Player playerBlack;
	Player playerWhite;
	int black;
	int white;
	Instant gameBeginTime;
	Instant gameEndTime;
	public static void main(String[] args){
		Instant instant = Instant.now();
	}
}
