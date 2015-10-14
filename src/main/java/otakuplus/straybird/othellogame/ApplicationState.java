package otakuplus.straybird.othellogame;

public interface ApplicationState {

	public void initialize();
	
	public void connect();
	
	public void login();

	public void enterGameHall();

	public void leaveGameHall();

	public void enterGameTable();

	public void disconnect();
	
	public void destory();

}
