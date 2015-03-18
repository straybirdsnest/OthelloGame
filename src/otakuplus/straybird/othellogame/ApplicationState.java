package otakuplus.straybird.othellogame;

public class ApplicationState {
	public static int INITILIZE = 100;
	public static int LOGIN = 101;
	public static int GAME_HALL = 102;
	public static int GAME_TABLE = 103;
	public static int DESTORY = 104;

	private int applicationState = INITILIZE;

	public void turnLogin() {
		applicationState = LOGIN;
	}

	public void turnGameHall() {
		applicationState = GAME_HALL;
	}

	public void turnGameTable() {
		applicationState = GAME_TABLE;
	}

	public void turnDestory() {
		applicationState = DESTORY;
	}

	public int getState() {
		return applicationState;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
