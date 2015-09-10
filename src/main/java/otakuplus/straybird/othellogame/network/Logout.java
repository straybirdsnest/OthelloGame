package otakuplus.straybird.othellogame.network;

public class Logout {
	private int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public static void main(String[] args) {
		Logout logout = new Logout();
		logout.setUserId(10086);
		assert (logout.getUserId() == 10086);
		System.out.println("Test on setter and getter: OK");
	}

}
