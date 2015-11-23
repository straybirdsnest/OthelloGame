package otakuplus.straybird.othellogame.applicationstates;

import otakuplus.straybird.othellogame.ui.GameHallWindow;
import otakuplus.straybird.othellogame.ui.LoginWindow;
import otakuplus.straybird.othellogame.ui.OthelloGameWindow;

public class ApplicationIntializeState implements ApplicationState {

    public void initialize() {
        // Initialize UI
        ApplicationContext applicationContext = ApplicationContextSingleton
                .getInstance();

        LoginWindow loginWindow = applicationContext.getLoginWinodow();
        loginWindow.open();
        GameHallWindow gameHallWindow = applicationContext.getGameHallWindow();
        gameHallWindow.open();
        OthelloGameWindow othelloGameWindow = applicationContext
                .getOthelloGameWindow();
        othelloGameWindow.open();
        gameHallWindow.hide();
        othelloGameWindow.hide();
        applicationContext.changeState(ApplicationStateSingleton
                .getConnectStateInstance());
    }

    public void connect() {
    }

    public void login() {
    }

    public void enterGameHall() {
    }

    public void leaveGameHall() {

    }

    public void enterGameTable(Integer gameTableId, Integer seatId) {
    }

    public void leaveGameTable(Integer gameTableId, Integer seatId) {

    }

    public void giveUp() {

    }

    public void logout() {

    }

    public void disconnect() {
    }

    public void destory() {
    }

}
