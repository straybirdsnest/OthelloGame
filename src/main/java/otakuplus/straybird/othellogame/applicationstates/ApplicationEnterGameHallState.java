package otakuplus.straybird.othellogame.applicationstates;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpStatusCodes;
import otakuplus.straybird.othellogame.models.User;
import otakuplus.straybird.othellogame.models.UserInformation;
import otakuplus.straybird.othellogame.network.http.EmBeddedUserOnlineList;
import otakuplus.straybird.othellogame.network.http.HttpRequestUtil;
import otakuplus.straybird.othellogame.models.UserOnline;
import otakuplus.straybird.othellogame.network.http.UserOnlineList;
import otakuplus.straybird.othellogame.ui.GameHallWindow;
import otakuplus.straybird.othellogame.ui.LoginWindow;

import java.io.IOException;
import java.util.ArrayList;

public class ApplicationEnterGameHallState implements ApplicationState{

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

        String url = HttpRequestUtil.HOST_BASE_URL+"/api/gameHall/enter";
        HttpResponse response = null;
        HttpRequest request;
        applicationContext.updateCsrfToken();

        try{
            request = HttpRequestUtil.buildHttpPostRequest(url, applicationContext.currentUser.getUserId());
            response = request.execute();
            if(response!= null && response.getStatusCode() == HttpStatusCodes.STATUS_CODE_OK)
            {
                loginWindow.hide();
                gameHallWindow.show();
                System.out.println("enter game hall");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void leaveGameHall(){
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        applicationContext.changeState(ApplicationStateSingleton.getLeaveGameHallStateInstance());
        applicationContext.leaveGameHall();
    }

    public void enterGameTable(Long gameTableId,Long seatId) {
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        applicationContext.changeState(ApplicationStateSingleton.getEnterGameTableStateInstance());
        applicationContext.enterGameTable(gameTableId, seatId);
    }

    public void leaveGameTable(Long gameTableId,Long seatId){

    }

    public void logout(){

    }

    public void disconnect() {

    }

    public void destory() {

    }
}

