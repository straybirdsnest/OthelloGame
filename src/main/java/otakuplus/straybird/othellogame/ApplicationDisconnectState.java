package otakuplus.straybird.othellogame;

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

	public void enterGameTable() {
	}

	public void disconnect() {
		ApplicationContext applicationContext = ApplicationContextSingleton
				.getInstance();
		SocketIOClient socketIOClient = applicationContext.socketIOClient;
		socketIOClient.disconnect();
		applicationContext.changeState(ApplicationStateSingleton
				.getDestoryStateInstance());
	}

	public void destory() {
	}

}
