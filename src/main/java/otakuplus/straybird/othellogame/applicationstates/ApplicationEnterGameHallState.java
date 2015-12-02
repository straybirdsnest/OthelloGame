package otakuplus.straybird.othellogame.applicationstates;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpStatusCodes;
import otakuplus.straybird.othellogame.network.http.HttpRequestUtil;
import otakuplus.straybird.othellogame.ui.GameHallWindow;
import otakuplus.straybird.othellogame.ui.LoginWindow;

import java.io.IOException;

public class ApplicationEnterGameHallState implements ApplicationState {

    public void initialize() {

    }

    public void connect() {

    }

    public void login() {

    }

    public void enterGameHall() {
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        LoginWindow loginWindow = applicationContext.getLoginWinodow();
        GameHallWindow gameHallWindow = applicationContext.getGameHallWindow();

        String url = HttpRequestUtil.HOST_BASE_URL + "/api/gameHall/enter";
        HttpResponse response = null;
        HttpRequest request;

        try {
            request = HttpRequestUtil.buildHttpPostRequest(url, applicationContext.currentUser.getUserId());
            response = request.execute();
            if (response != null && response.getStatusCode() == HttpStatusCodes.STATUS_CODE_OK) {
                HttpRequestUtil.updateUserOnlineList();
                HttpRequestUtil.updateGameTableList();
                gameHallWindow.notifyUserListUpdate();
                gameHallWindow.notifyGameTableListUpdate();
                loginWindow.hide();
                gameHallWindow.show();
                System.out.println("enter game hall");
                applicationContext.startRefresh();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void leaveGameHall() {
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        applicationContext.changeState(ApplicationStateSingleton.getLeaveGameHallStateInstance());
        applicationContext.stopRefresh();
        applicationContext.leaveGameHall();
    }

    public void enterGameTable(Integer gameTableId, Integer seatId) {
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        applicationContext.changeState(ApplicationStateSingleton.getEnterGameTableStateInstance());
        applicationContext.enterGameTable(gameTableId, seatId);
    }

    public void leaveGameTable(Integer gameTableId, Integer seatId) {

    }

    public void giveUp() {

    }

    public void draw() {

    }

    public void win() {

    }

    public void logout() {

    }

    public void disconnect() {

    }

    public void destroy() {

    }
}

