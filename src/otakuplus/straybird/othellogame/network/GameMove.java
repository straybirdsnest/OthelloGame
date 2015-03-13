package otakuplus.straybird.othellogame.network;

public class GameMove {

	private int whitePlayerId;
	private int blackPlayerId;
	private int turnNumber;
	private int movePosition;
	private boolean forceNext;

	public int getWhitePlayerId() {
		return whitePlayerId;
	}

	public void setWhitePlayerId(int whitePlayerId) {
		this.whitePlayerId = whitePlayerId;
	}

	public int getBlackPlayerId() {
		return blackPlayerId;
	}

	public void setBlackPlayerId(int blackPlayerId) {
		this.blackPlayerId = blackPlayerId;
	}

	public int getTurnNumber() {
		return turnNumber;
	}

	public void setTurnNumber(int turnNumber) {
		this.turnNumber = turnNumber;
	}

	public int getMovePosition() {
		return movePosition;
	}

	public void setMovePosition(int movePosition) {
		this.movePosition = movePosition;
	}

	public boolean isForceNext() {
		return forceNext;
	}

	public void setForceNext(boolean forceNext) {
		this.forceNext = forceNext;
	}

	public static void main(String[] args) {

	}

}
