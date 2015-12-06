package otakuplus.straybird.othellogame.applicationstates;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import otakuplus.straybird.othellogame.models.GameRecord;
import otakuplus.straybird.othellogame.models.GameTable;
import otakuplus.straybird.othellogame.models.User;
import otakuplus.straybird.othellogame.models.UserInformation;
import otakuplus.straybird.othellogame.network.http.HttpRequestUtil;
import otakuplus.straybird.othellogame.network.socketio.SocketIOClient;
import otakuplus.straybird.othellogame.ui.GameHallWindow;
import otakuplus.straybird.othellogame.ui.LoginWindow;
import otakuplus.straybird.othellogame.ui.OthelloGameWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ApplicationContext {

    public static final String SERVER_NAME = "servername";
    public static final String SERVER_PORT = "serverport";
    public static final String SOCKET_PORT = "socketport";

    public static final int REFRESH_TIME = 10000;

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
    protected GameRecord gameRecord;
    // Network Scope
    protected String serverName;
    protected String serverPort;
    protected String socketPort;
    protected SocketIOClient socketIOClient;
    protected List<String> currentCookie;
    protected Runnable timer = new Runnable() {
        @Override
        public void run() {
            HttpRequestUtil.updateGameTableList();
            HttpRequestUtil.updateUserOnlineList();
            display.asyncExec(new Runnable() {
                @Override
                public void run() {
                    gameHallWindow.notifyGameTableListUpdate();
                    gameHallWindow.notifyUserListUpdate();
                }
            });
        }
    };
    private ApplicationState applicationState;

    public ApplicationContext() {
        applicationState = ApplicationStateSingleton.getInitStateInstance();
        // UI
        display = Display.getDefault();
        loginWindow = new LoginWindow();
        gameHallWindow = new GameHallWindow();
        othelloGameWindow = new OthelloGameWindow();
        // Model
        currentCookie = null;
        currentUser = null;
        currentUserInformation = null;
        userInformationList = new ArrayList<UserInformation>();
        gameTableList = new ArrayList<GameTable>();
        for (int i = 1; i <= 100; i++) {
            GameTable gameTable = new GameTable();
            gameTable.setGameTableId(i);
            gameTableList.add(gameTable);
        }
        gameRecord = new GameRecord();

    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        applicationContext.loadPropertiesFromEnv();
        applicationContext.loadPropertiesFromCommandLineArguments(args);
        applicationContext.initialize();
        applicationContext.connect();
        applicationContext.startUp();
    }

    public void loadPropertiesFromEnv() {
        String serverNameEnv = System.getenv(SERVER_NAME);
        String serverPortEnv = System.getenv(SERVER_PORT);
        String socketPortEnv = System.getenv(SOCKET_PORT);
        if (serverName == null) {
            serverName = serverNameEnv;
        }
        if (serverPort == null) {
            serverPort = serverPortEnv;
        }
        if (socketPort == null) {
            socketPort = socketPortEnv;
        }
    }

    public void loadPropertiesFromCommandLineArguments(String[] args) {
        if (args == null) return;
        Optional<String> _serverName = Arrays.stream(args)
                .filter(e -> e.contains(SERVER_NAME)).findFirst();
        if (_serverName.isPresent()) {
            serverName = _serverName.get().split("=")[1];
        }
        Optional<String> _serverPort = Arrays.stream(args)
                .filter(e -> e.contains(SERVER_PORT)).findFirst();
        if (_serverPort.isPresent()) {
            serverPort = _serverPort.get().split("=")[1];
        }
        Optional<String> _socketPort = Arrays.stream(args)
                .filter(e -> e.contains(SOCKET_PORT)).findFirst();
        if (_socketPort.isPresent()) {
            socketPort = _socketPort.get().split("=")[1];
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

    public void leaveGameHall() {
        applicationState.leaveGameHall();
    }

    public void enterGameTable(Integer gameTableId, Integer seatId) {
        applicationState.enterGameTable(gameTableId, seatId);
    }

    public void leaveGameTable(Integer gameTableId, Integer seatId) {
        applicationState.leaveGameTable(gameTableId, seatId);
    }

    public void giveUp() {
        applicationState.giveUp();
    }

    public void draw() {
        applicationState.draw();
    }

    public void win() {
        applicationState.win();
    }

    public void logout() {
        applicationState.logout();
    }

    public void disconnect() {
        applicationState.disconnect();
    }

    public void destory() {
        applicationState.destroy();
    }

    public void changeState(ApplicationState applicationState) {
        System.out.println("from state " + this.applicationState.getClass());
        this.applicationState = applicationState;
        System.out.println("change to " + this.applicationState.getClass());
    }

    public void setLoginWindowShell(Shell shell) {
        loginWindowShell = shell;
    }

    public Display getDisplay() {
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

    public SocketIOClient getSocketIOClient() {
        return socketIOClient;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public UserInformation getCurrentUserInformation() {
        return currentUserInformation;
    }

    public Integer getCurrentTableId() {
        return currentTableId;
    }

    public Integer getCurrentSeatId() {
        return currentSeatId;
    }

    public ArrayList<GameTable> getGameTableList() {
        return gameTableList;
    }

    public ArrayList<UserInformation> getUserInformationList() {
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

    public String getSocketPort() {
        return socketPort;
    }

    public String getServerPort() {
        return serverPort;
    }

    public String getServerName() {
        return serverName;
    }

    public GameRecord getGameRecord() {
        return gameRecord;
    }

    public List<String> getCurrentCookie() {
        return currentCookie;
    }

    public void setCurrentCookie(List<String> currentCookie) {
        this.currentCookie = currentCookie;
    }

    public void startRefresh() {
        display.timerExec(REFRESH_TIME, timer);
    }

    public void stopRefresh() {
        display.timerExec(-1, timer);
    }

}
