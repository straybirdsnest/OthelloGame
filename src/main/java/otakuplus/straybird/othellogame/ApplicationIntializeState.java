package otakuplus.straybird.othellogame;

import otakuplus.straybird.othellogame.network.SocketIOClient;
import otakuplus.straybird.othellogame.network.SocketIOClientSingleton;
import otakuplus.straybird.othellogame.ui.GameHallWindow;
import otakuplus.straybird.othellogame.ui.LoginWindow;
import otakuplus.straybird.othellogame.ui.OthelloGameWindow;

public class ApplicationIntializeState implements ApplicationState {

	public void initialize() {
		// Initialize UI
		Application application = ApplicationContextSingleton
				.getInstance();
		LoginWindow loginWindow = application.getLoginWinodow();
		loginWindow.open();
		GameHallWindow gameHallWindow = application.getGameHallWindow();
		gameHallWindow.open();
		OthelloGameWindow othelloGameWindow = application
				.getOthelloGameWindow();
		othelloGameWindow.open();
		gameHallWindow.hide();
		othelloGameWindow.hide();
		// Initialize Socket.io client
		SocketIOClient socketIOClient = SocketIOClientSingleton.getInstance();
		socketIOClient.setupSocketIOClient();
	}

	public void connect() {
	}

	public void login() {
	}

	public void enterGameHall() {
	}

	public void enterGameTable() {
	}

	public void destory() {
	}

	public void disconnect() {
	}

}
