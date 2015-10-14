package otakuplus.straybird.othellogame;

import com.google.api.client.http.*;
import org.eclipse.swt.widgets.Display;

import otakuplus.straybird.othellogame.models.User;
import otakuplus.straybird.othellogame.models.UserInformation;
import otakuplus.straybird.othellogame.network.http.HttpRequestFactorySingleton;
import otakuplus.straybird.othellogame.network.socketio.SocketIOClient;
import otakuplus.straybird.othellogame.network.socketio.SocketIOClientSingleton;
import otakuplus.straybird.othellogame.ui.GameHallWindow;
import otakuplus.straybird.othellogame.ui.LoginWindow;
import otakuplus.straybird.othellogame.ui.OthelloGameWindow;

import java.io.IOException;
import java.net.HttpCookie;
import java.util.List;

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

	protected List<String> currentCookie;

	private ApplicationState applicationState;

	public ApplicationContext() {
		applicationState = ApplicationStateSingleton.getInitStateInstance();
		// UI
		display = Display.getDefault();
		loginWindow = new LoginWindow();
		gameHallWindow = new GameHallWindow();
		othelloGameWindow = new OthelloGameWindow();
		// Initialize Socket.io client
		socketIOClient = SocketIOClientSingleton.getInstance();
		socketIOClient.setupSocketIOClient();

        currentCookie = null;
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

	public void leaveGameHall(){
		applicationState.leaveGameHall();
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
		this.applicationState = applicationState;
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

    public void updateCsrfToken(){
        HttpRequestFactory requestFactory = HttpRequestFactorySingleton.getHttpRequestFactoryInstance();
        GenericUrl genericUrl = new GenericUrl("http://localhost:8080/api/csrftoken");
        HttpResponse response = null;
        HttpRequest request;
        try {
            request = requestFactory.buildGetRequest(genericUrl);
            if(currentCookie != null){
                request.getHeaders().set("cookie", currentCookie);
                List<HttpCookie> cookie = HttpCookie.parse(currentCookie.get(0));
                request.getHeaders().set("X-XSRF-TOKEN", cookie.get(0).getValue());
            }
            response = request.execute();
            if(response != null && response.getStatusCode() == HttpStatusCodes.STATUS_CODE_OK){
                if(response.getHeaders().getHeaderStringValues("set-cookie").isEmpty() == false) {
                    currentCookie = response.getHeaders().getHeaderStringValues("set-cookie");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
