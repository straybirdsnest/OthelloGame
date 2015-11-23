package otakuplus.straybird.othellogame.applicationstates;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpStatusCodes;
import otakuplus.straybird.othellogame.network.http.HttpRequestUtil;
import otakuplus.straybird.othellogame.ui.GameHallWindow;
import otakuplus.straybird.othellogame.ui.LoginWindow;

import java.io.IOException;

public class ApplicationLeaveGameHallState implements ApplicationState {
    public void initialize() {

    }

    public void connect() {

    }

    public void login() {

    }

    public void enterGameHall() {

    }

    public void leaveGameHall() {
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        LoginWindow loginWindow = applicationContext.getLoginWinodow();
        GameHallWindow gameHallWindow = applicationContext.getGameHallWindow();

        String url = HttpRequestUtil.HOST_BASE_URL + "/api/gameHall/leave";
        HttpResponse response = null;
        HttpRequest request;
        applicationContext.updateCsrfToken();

        try {
            request = HttpRequestUtil.buildHttpPostRequest(url, applicationContext.currentUser.getUserId());
            response = request.execute();
            if (response != null && response.getStatusCode() == HttpStatusCodes.STATUS_CODE_OK) {
                gameHallWindow.hide();
                loginWindow.show();
                System.out.println("leave game hall");
                applicationContext.changeState(ApplicationStateSingleton.getLogoutStateInstance());
                applicationContext.logout();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enterGameTable(Integer gameTableId, Integer seatId) {

    }

    public void leaveGameTable(Integer gameTableId, Integer seatId) {

    }

    public void giveUp() {

    }

    public void draw(){

    }

    public void win() {

    }

    public void logout() {

    }

    public void disconnect() {

    }

    public void destory() {

    }
}
