package otakuplus.straybird.othellogame;

import org.eclipse.swt.widgets.Display;

import otakuplus.straybird.othellogame.models.User;
import otakuplus.straybird.othellogame.models.UserInformation;
import otakuplus.straybird.othellogame.network.socketio.SocketIOClient;
import otakuplus.straybird.othellogame.network.socketio.SocketIOClientSingleton;
import otakuplus.straybird.othellogame.ui.GameHallWindow;
import otakuplus.straybird.othellogame.ui.LoginWindow;
import otakuplus.straybird.othellogame.ui.OthelloGameWindow;

public class ApplicationContext {
	// UI Scope
	protected Display display;
	protected LoginWindow loginWindow;
	protected GameHallWindow gameHallWindow;
	protected OthelloGameWindow othelloGameWindow;
	// Model Scope
	protected User currentUser;
	protected UserInformation currentUserInformation;
	// Network Scope
	protected SocketIOClient socketIOClient;

	private ApplicationState applicationState;

	public ApplicationContext() {
		this.applicationState = ApplicationStateSingleton.getInitStateInstance();
		// UI
		display = Display.getDefault();
		loginWindow = new LoginWindow();
		gameHallWindow = new GameHallWindow();
		othelloGameWindow = new OthelloGameWindow();
		// Initialize Socket.io client
		socketIOClient = SocketIOClientSingleton.getInstance();
		socketIOClient.setupSocketIOClient();
	}

	public void initialize() {
		applicationState.initialize();
	}

	public void connect() {
		applicationState.connect();
        System.out.println("state "+applicationState.getClass());
        System.out.println("call connect");
	}

	public void login() {
        System.out.println(applicationState.getClass());
        System.out.println("call login!");
        applicationState.login();
	}

	public void enterGameHall() {
		applicationState.enterGameHall();
	}

	public void enterGameTable() {
		applicationState.enterGameTable();
	}

	public void disconnect() {
		applicationState.disconnect();
	}

	public void destory() {
		applicationState.destory();
	}

	public void changeState(ApplicationState applicationState) {
		System.out.println("state:" + this.applicationState.getClass());
        this.applicationState = applicationState;
        System.out.println("change to:" + this.applicationState.getClass());
	}

	public LoginWindow getLoginWinodow() {
		return loginWindow;
	}

	public GameHallWindow getGameHallWindow() {
		return gameHallWindow;
	}

	public OthelloGameWindow getOthelloGameWindow() {
		return othelloGameWindow;
	}

	public void startUp() {
		// This is the main UI thread
		while (!display.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public static void main(String[] args) {
		ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
		applicationContext.initialize();
        applicationContext.connect();
		applicationContext.startUp();
        applicationContext.disconnect();
        applicationContext.destory();
	}
}
