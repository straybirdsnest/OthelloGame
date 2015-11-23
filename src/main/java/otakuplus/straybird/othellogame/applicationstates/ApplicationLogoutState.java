package otakuplus.straybird.othellogame.applicationstates;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpStatusCodes;
import otakuplus.straybird.othellogame.network.http.HttpRequestUtil;
import otakuplus.straybird.othellogame.network.http.Logout;

import java.io.IOException;

public class ApplicationLogoutState implements ApplicationState {
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

    }

    public void giveUp() {

    }

    public void logout() {
        String url = HttpRequestUtil.HOST_BASE_URL + "/api/logout";
        HttpResponse response = null;
        HttpRequest request;
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        applicationContext.updateCsrfToken();
        try {
            Logout logout = new Logout();
            logout.setUserId(applicationContext.currentUser.getUserId());

            request = HttpRequestUtil.buildHttpPostRequest(url, logout);
            response = request.execute();
            if (response != null && response.getStatusCode() == HttpStatusCodes.STATUS_CODE_OK) {
                System.out.println("logout ok!");
                applicationContext.changeState(ApplicationStateSingleton.getLoginStateInstance());
                applicationContext.currentUser = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {

    }

    public void destory() {

    }
}
