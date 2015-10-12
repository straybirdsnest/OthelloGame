package otakuplus.straybird.othellogame;

import otakuplus.straybird.othellogame.models.User;
import otakuplus.straybird.othellogame.models.UserInformation;
import otakuplus.straybird.othellogame.ui.GameHallWindow;
import otakuplus.straybird.othellogame.ui.LoginWindow;
import otakuplus.straybird.othellogame.ui.OthelloGameWindow;

public class Application {
	// UI Scope
	protected LoginWindow loginWindow;
	protected GameHallWindow gameHallWindow;
	protected OthelloGameWindow othelloGameWindow;
	// Model Scope
	protected User currentUser;
	protected UserInformation currentUserInformation;
	
	private ApplicationState applicationState;
	
	public Application() {
		applicationState = ApplicationStateSingleton.getInitStateInstance();
		loginWindow = new LoginWindow();
		gameHallWindow = new GameHallWindow();
		othelloGameWindow = new OthelloGameWindow();
	}

	public void initialize() {
		applicationState.initialize();
	}

	public void connect() {
		applicationState.connect();
	}

	public void login() {
		applicationState.login();
	}

	public void enterGameHall() {
		applicationState.enterGameHall();
	}

	public void enterGameTable() {
		applicationState.enterGameTable();
	}
	
	public void disconnect(){
		applicationState.disconnect();
	}

	public void destory() {
		applicationState.destory();
	}

	public void changeState(ApplicationState applicationState) {
		this.applicationState = applicationState;
	}
	
	public LoginWindow getLoginWinodow(){
		return loginWindow;
	}
	
	public GameHallWindow getGameHallWindow(){
		return gameHallWindow;
	}
	
	public OthelloGameWindow getOthelloGameWindow(){
		return othelloGameWindow;
	}
	
	public static void main(String[] args){
		Application application = new Application();
		application.initialize();
		application.connect();
		application.login();
        application.destory();
	}
}
