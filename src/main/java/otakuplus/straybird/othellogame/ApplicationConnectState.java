package otakuplus.straybird.othellogame;

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

	public void enterGameTable() {

	}

	public void disconnect() {

	}

	public void destory() {

	}

}