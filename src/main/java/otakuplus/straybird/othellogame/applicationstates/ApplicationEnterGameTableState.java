package otakuplus.straybird.othellogame.applicationstates;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpStatusCodes;
import otakuplus.straybird.othellogame.network.http.HttpRequestUtil;
import otakuplus.straybird.othellogame.ui.GameHallWindow;
import otakuplus.straybird.othellogame.ui.LoginWindow;
import otakuplus.straybird.othellogame.ui.OthelloGameWindow;

import java.io.IOException;

public class ApplicationEnterGameTableState implements ApplicationState{

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

    public void enterGameTable(Long gameTableId, Long seatId) {
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        OthelloGameWindow othelloGameWindow = applicationContext.getOthelloGameWindow();

        String url = HttpRequestUtil.HOST_BASE_URL
                +"/api/gameTables/"+gameTableId+"/seats/"+seatId+"/enter";
        HttpResponse response = null;
        HttpRequest request;
        applicationContext.updateCsrfToken();

        try{
            request = HttpRequestUtil.buildHttpPostRequest(url, applicationContext.currentUser.getUserId());
            response = request.execute();
            if(response!= null && response.getStatusCode() == HttpStatusCodes.STATUS_CODE_OK)
            {
                othelloGameWindow.show();
                applicationContext.currentTableId = gameTableId;
                applicationContext.currentSeatId = seatId;
                System.out.println("enter game table");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void leaveGameTable(Long gameTableId,Long seatId) {
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        applicationContext.changeState(ApplicationStateSingleton.getLeaveGameTableStateInstance());
        applicationContext.leaveGameTable(gameTableId, seatId);
    }

    public void logout() {

    }

    public void disconnect() {

    }

    public void destory() {

    }
}
