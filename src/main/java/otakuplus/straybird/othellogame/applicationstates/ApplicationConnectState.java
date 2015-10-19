package otakuplus.straybird.othellogame.applicationstates;

public class ApplicationConnectState implements ApplicationState {

	public void initialize() {

	}

	public void connect() {
		ApplicationContext applicationContext = ApplicationContextSingleton
				.getInstance();
		applicationContext.socketIOClient.connect();
        applicationContext.changeState(ApplicationStateSingleton
                .getLoginStateInstance());
	}

	public void login() {

	}

	public void enterGameHall() {

	}

	public void leaveGameHall(){

	}

	public void enterGameTable(Long gameTableId,Long seatId) {

	}

    public void leaveGameTable(Long gameTableId,Long seatId){

    }

	public void logout(){

	}

	public void disconnect() {

	}

	public void destory() {

	}

}
