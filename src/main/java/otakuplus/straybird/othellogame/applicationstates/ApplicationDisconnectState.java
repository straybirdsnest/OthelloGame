package otakuplus.straybird.othellogame.applicationstates;

import otakuplus.straybird.othellogame.network.socketio.SocketIOClient;

public class ApplicationDisconnectState implements ApplicationState {

	public void initialize() {
	}

	public void connect() {
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
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
		SocketIOClient socketIOClient = applicationContext.socketIOClient;
		socketIOClient.disconnect();
		applicationContext.changeState(ApplicationStateSingleton
				.getDestoryStateInstance());
        applicationContext.destory();
	}

	public void destory() {
	}

}