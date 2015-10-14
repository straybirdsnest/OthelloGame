package otakuplus.straybird.othellogame;

import com.google.api.client.http.*;
import com.google.api.client.http.json.JsonHttpContent;
import otakuplus.straybird.othellogame.network.http.*;
import otakuplus.straybird.othellogame.ui.GameHallWindow;
import otakuplus.straybird.othellogame.ui.LoginWindow;

import java.io.IOException;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;

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

        HttpRequestFactory requestFactory = HttpRequestFactorySingleton.getHttpRequestFactoryInstance();
        GenericUrl genericUrl = new GenericUrl("http://localhost:8080/api/gamehall/enter");
        HttpResponse response = null;
        HttpRequest request;
        applicationContext.updateCsrfToken();

        try{
            JsonHttpContent jsonHttpContent = new JsonHttpContent(JsonFactorySingleton.getJsonFactoryInstance() , 1L);
            request = requestFactory.buildPostRequest(genericUrl, jsonHttpContent);
            if(applicationContext.currentCookie != null) {
                System.out.println("current Cookie: "+applicationContext.currentCookie);
                request.getHeaders().set("cookie", applicationContext.currentCookie);
                List<HttpCookie> cookie = HttpCookie.parse(applicationContext.currentCookie.get(0));
                request.getHeaders().set("X-XSRF-TOKEN", cookie.get(0).getValue());
            }

            response = request.execute();
            if(response!= null && response.getStatusCode() == HttpStatusCodes.STATUS_CODE_OK)
            {
                System.out.println("enter game hall");
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        getUserOnline();
    }

    public void leaveGameHall(){

    }

    public void enterGameTable() {

    }

    public void disconnect() {

    }

    public void destory() {

    }

    private void getUserOnline(){
        HttpRequestFactory requestFactory = HttpRequestFactorySingleton.getHttpRequestFactoryInstance();
        GenericUrl genericUrl = new GenericUrl("http://localhost:8080/api/userOnlines/search/findByOnlineState?onlineState="+UserOnline.ONLINE);
        HttpResponse response = null;
        HttpRequest request;
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        try{
            request = requestFactory.buildGetRequest(genericUrl);
            request.getHeaders().set("cookie", ApplicationContextSingleton.getInstance().currentCookie);

            if(applicationContext.currentCookie != null) {
                System.out.println("current Cookie: "+applicationContext.currentCookie);
                request.getHeaders().set("cookie", applicationContext.currentCookie);
                List<HttpCookie> cookie = HttpCookie.parse(applicationContext.currentCookie.get(0));
                request.getHeaders().set("X-XSRF-TOKEN", cookie.get(0).getValue());
            }

            response = request.execute();

            if(response!= null && response.getStatusCode() == HttpStatusCodes.STATUS_CODE_OK){
                EmBededUserOnlineList embededUserOnlineList= response.parseAs(EmBededUserOnlineList.class);
                if(embededUserOnlineList != null){
                    UserOnlineList userOnlineList = embededUserOnlineList.getUserOnlineList();
                    if(userOnlineList != null && userOnlineList.getUserOnlines() != null){
                        ArrayList<UserOnline> userOnlines = userOnlineList.getUserOnlines();
                        for (int i=0; i<userOnlines.size();i++){
                            System.out.println("users: "+userOnlines.get(i).getLinks().getUser().getHref());
                        }
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

