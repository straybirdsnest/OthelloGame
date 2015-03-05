package otakuplus.straybird.othellogame;

public class ApllicationState {
	public static int INITILIZE = 100;
	public static int LOGIN = 101;
	public static int GAMEHALL = 102;
	public static int GAMEPLAY = 103;
	public static int DESTORY = 104;

	private int applicationState = 100;

	public int getState() {
		return applicationState;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
