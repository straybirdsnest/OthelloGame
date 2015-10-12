package otakuplus.straybird.othellogame.models;

public class Chessman {
	/**
	 * A Chessman stands for one chessman on the chessboard. It should be a
	 * black or white chessman. Otherwise, there should be no chessman on the
	 * position of a chessboard. When a chessman flaps, it turn to the other
	 * color. That is to say, when a black chessman flaps, it turns to a white
	 * chessman.
	 */
	public static final int CHESSMAN_NONE = 0;
	public static final int CHESSMAN_BLACK = 1;
	public static final int CHESSMAN_WHITE = 2;
	private int chessman = CHESSMAN_NONE;

	public void flip() {
		if (chessman != CHESSMAN_NONE) {
			if (chessman == CHESSMAN_BLACK) {
				chessman = CHESSMAN_WHITE;
			} else {
				chessman = CHESSMAN_BLACK;
			}
		}
	}

	public static void main(String[] args) {
		Chessman chessman = new Chessman();
		System.out.println("A new chessman should be CHESSMAN_NONE.");
		System.out.println("Chessman stat:" + chessman.toString() + ".");
		assert (chessman.getChessman() == CHESSMAN_NONE);
		chessman.setChessman(CHESSMAN_BLACK);
		System.out.println("Now, put a black chessman, it becomes "
				+ chessman.toString() + ".");
		assert (chessman.getChessman() == CHESSMAN_BLACK);
		chessman.flip();
		System.out.println("flip this chessman, we get " + chessman.toString()
				+ ".");
		assert (chessman.getChessman() == CHESSMAN_WHITE);
		System.out.println("Now, do the same thing to a white chessman.");
		chessman.flip();
		assert (chessman.getChessman() == CHESSMAN_BLACK);
		System.out
				.println("Take this chessman out, there should be no chessman on this position.");
		chessman.setChessman(CHESSMAN_NONE);
		assert (chessman.getChessman() == CHESSMAN_NONE);
		System.out.println("Well, this class has passed all tests.");
	}

	public int getChessman() {
		return chessman;
	}

	public void setChessman(int chessman) {
		this.chessman = chessman;
	}

	@Override
	public String toString() {
		if (chessman == CHESSMAN_NONE) {
			return new String("CHESSMAN_NONE");
		} else if (chessman == CHESSMAN_BLACK) {
			return new String("CHESSMAN_BLACK");
		} else {
			return new String("CHESSMANE_WHITE");
		}
	}

}
