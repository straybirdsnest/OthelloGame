package otakuplus.straybird.othellogame.applicationstates;

public interface ApplicationState {

	public void initialize();
	
	public void connect();
	
	public void login();

	public void enterGameHall();

	public void leaveGameHall();

	public void enterGameTable(Long gameTableId,Long seatId);

	public void leaveGameTable(Long gameTableId,Long seatId);

	public void logout();

	public void disconnect();
	
	public void destory();

}
