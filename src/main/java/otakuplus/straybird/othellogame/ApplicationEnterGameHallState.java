package otakuplus.straybird.othellogame;

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
        loginWindow.hide();
        GameHallWindow gameHallWindow = applicationContext.getGameHallWindow();
        gameHallWindow.show();

        String url = HttpRequestUtil.HOST_BASE_URL+"/api/gamehall/enter";
        HttpResponse response = null;
        HttpRequest request;
        applicationContext.updateCsrfToken();

        try{
            request = HttpRequestUtil.buildHttpPostRequest(url, applicationContext.currentUser.getUserId());
            response = request.execute();
            if(response!= null && response.getStatusCode() == HttpStatusCodes.STATUS_CODE_OK)
            {
                System.out.println("enter game hall");
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        getUserOnline();
        gameHallWindow.notifyUserListUpdate();
    }

    public void leaveGameHall(){
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        applicationContext.changeState(ApplicationStateSingleton.getLeaveGameHallStateInstance());
        applicationContext.leaveGameHall();
    }

    public void enterGameTable() {

    }

    public void logout(){

    }

    public void disconnect() {

    }

    public void destory() {

    }

    private void getUserOnline(){
        String url = HttpRequestUtil.HOST_BASE_URL
                +"/api/userOnlines/search/findByOnlineState?onlineState="
                +UserOnline.ONLINE;
        HttpResponse response = null;
        HttpRequest request;
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        try{
            request = HttpRequestUtil.buildHttpGetRequest(url);
            response = request.execute();

            if(response!= null && response.getStatusCode() == HttpStatusCodes.STATUS_CODE_OK){
                EmBeddedUserOnlineList embeddedUserOnlineList = response.parseAs(EmBeddedUserOnlineList.class);
                if(embeddedUserOnlineList != null){
                    UserOnlineList userOnlineList = embeddedUserOnlineList.getUserOnlineList();
                    if(userOnlineList != null && userOnlineList.getUserOnlines() != null){
                        applicationContext.userInformationList.clear();
                        ArrayList<UserOnline> userOnlines = userOnlineList.getUserOnlines();
                        for (int i=0; i<userOnlines.size();i++){
                            User user = HttpRequestUtil.getUserByHref(userOnlines.get(i).getLinks().getUser().getHref());
                            UserInformation userInformation = HttpRequestUtil.getUserInformationByHref(user.getLinks().getUserInformation().getHref());
                            applicationContext.userInformationList.add(userInformation);
                        }
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}

