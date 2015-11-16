package otakuplus.straybird.othellogame.applicationstates;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpStatusCodes;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import otakuplus.straybird.othellogame.models.GameTable;
import otakuplus.straybird.othellogame.models.User;
import otakuplus.straybird.othellogame.models.UserInformation;
import otakuplus.straybird.othellogame.network.http.HttpRequestUtil;
import otakuplus.straybird.othellogame.network.socketio.SocketIOClient;
import otakuplus.straybird.othellogame.network.socketio.SocketIOClientSingleton;
import otakuplus.straybird.othellogame.ui.GameHallWindow;
import otakuplus.straybird.othellogame.ui.LoginWindow;
import otakuplus.straybird.othellogame.ui.OthelloGameWindow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationContext {
	// UI Scope
	protected Display display;
	protected Shell loginWindowShell;
	protected LoginWindow loginWindow;
	protected GameHallWindow gameHallWindow;
	protected OthelloGameWindow othelloGameWindow;
	// Model Scope
	protected User currentUser;
	protected UserInformation currentUserInformation;
    protected Integer currentTableId;
    protected Integer currentSeatId;
	protected ArrayList<GameTable> gameTableList;
    protected ArrayList<UserInformation> userInformationList;
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
        // Model
        currentCookie = null;
        currentUser = null;
        currentUserInformation = null;
        userInformationList = new ArrayList<UserInformation>();
        gameTableList = new ArrayList<GameTable>();
        for(int i=1;i<=100;i++){
            GameTable gameTable = new GameTable();
            gameTable.setGameTableId(i);
            gameTableList.add(gameTable);
        }
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

	public void enterGameTable(Integer gameTableId,Integer seatId) {
		applicationState.enterGameTable(gameTableId, seatId);
	}

    public void leaveGameTable(Integer gameTableId,Integer seatId){
        applicationState.leaveGameTable(gameTableId, seatId);
    }

    public void logout(){
        applicationState.logout();
    }

	public void disconnect() {
        applicationState.disconnect();
	}

	public void destory() {
		applicationState.destory();
	}

	public void changeState(ApplicationState applicationState) {
        System.out.println("from state " + this.applicationState.getClass());
		this.applicationState = applicationState;
        System.out.println("change to "+this.applicationState.getClass());
    }

    public void setLoginWindowShell(Shell shell){
        loginWindowShell = shell;
    }

    public Display getDisplay(){
        return display;
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

	public SocketIOClient getSocketIOClient(){
		return socketIOClient;
	}

	public List<String> getCurrentCookie(){
		return currentCookie;
	}

    public User getCurrentUser(){
        return currentUser;
    }

    public UserInformation getCurrentUserInformation(){
        return currentUserInformation;
    }

    public Integer getCurrentTableId(){
        return currentTableId;
    }

    public Integer getCurrentSeatId(){
        return currentSeatId;
    }

    public ArrayList<GameTable> getGameTableList(){
        return gameTableList;
    }

    public ArrayList<UserInformation> getUserInformationList(){
        return userInformationList;
    }

	public void startUp() {
		// This is the main UI thread
		while (!loginWindowShell.isDisposed()) {
			if (!display.readAndDispatch()) {
                display.sleep();
			}
		}
	}

    public void updateCsrfToken(){
        String url =HttpRequestUtil.HOST_BASE_URL+"/api/csrftoken";
        HttpResponse response = null;
        HttpRequest request;
        try {
            request = HttpRequestUtil.buildHttpGetRequest(url);
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
	}
}
