package otakuplus.straybird.othellogame.applicationstates;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpStatusCodes;
import otakuplus.straybird.othellogame.network.http.HttpRequestUtil;
import otakuplus.straybird.othellogame.ui.OthelloGameWindow;

import java.io.IOException;

public class ApplicationLeaveGameTableState implements ApplicationState {
    public void initialize() {

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
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        OthelloGameWindow othelloGameWindow = applicationContext.getOthelloGameWindow();

        String url = HttpRequestUtil.HOST_BASE_URL
                + "/api/gameTables/" + gameTableId + "/seats/" + seatId + "/leave";
        HttpResponse response = null;
        HttpRequest request;

        try {
            request = HttpRequestUtil.buildHttpPostRequest(url, applicationContext.currentUser.getUserId());
            response = request.execute();
            if (response != null && response.getStatusCode() == HttpStatusCodes.STATUS_CODE_OK) {
                othelloGameWindow.hide();
                applicationContext.currentTableId = null;
                applicationContext.currentSeatId = null;
                System.out.println("leave game table");
                applicationContext.changeState(ApplicationStateSingleton.getEnterGameHallStateInstance());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
