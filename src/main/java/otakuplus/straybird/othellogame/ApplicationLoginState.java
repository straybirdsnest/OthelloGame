package otakuplus.straybird.othellogame;

import com.google.api.client.http.*;
import com.google.api.client.http.json.JsonHttpContent;
import otakuplus.straybird.othellogame.models.User;
import otakuplus.straybird.othellogame.network.http.*;
import otakuplus.straybird.othellogame.ui.LoginWindow;

import java.io.IOException;
import java.net.HttpCookie;
import java.util.List;

public class ApplicationLoginState implements ApplicationState {

	public void initialize() {
	}

	public void connect() {
	}

	public void login() {
        HttpRequestFactory requestFactory = HttpRequestFactorySingleton.getHttpRequestFactoryInstance();
        GenericUrl genericUrl = new GenericUrl("http://localhost:8080/api/authorization");
        //GenericUrl genericUrl = new GenericUrl("http://localhost:8080/api/users/1");
        HttpResponse response = null;
        HttpRequest request;
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();

        try {

            request = requestFactory.buildGetRequest(genericUrl);
            response = request.execute();
            if(response!= null && response.getStatusCode() == HttpStatusCodes.STATUS_CODE_OK){
                System.out.println(response.getStatusCode());
                System.out.println(response.getHeaders());
                System.out.println(response.getHeaders().getHeaderStringValues("set-cookie"));

                Login login = new Login();

                LoginWindow loginWindow = ApplicationContextSingleton.getInstance().getLoginWinodow();
                login.setUsername(loginWindow.getUsername());
                login.setPassword(loginWindow.getPassword());
                JsonHttpContent jsonHttpContent = new JsonHttpContent(JsonFactorySingleton.getJsonFactoryInstance() ,login);

                request = requestFactory.buildPostRequest(genericUrl,jsonHttpContent );
                request.getHeaders().set("cookie", response.getHeaders().getHeaderStringValues("set-cookie"));

                applicationContext.currentCookie = response.getHeaders().getHeaderStringValues("set-cookie");

                List<HttpCookie> cookie = HttpCookie.parse(response.getHeaders().getHeaderStringValues("set-cookie").get(0));
                request.getHeaders().set("X-XSRF-TOKEN",cookie.get(0).getValue());

                response = request.execute();
                parseResponse(response);

                getUserData(1);

                // change to game hall state
                applicationContext.changeState(ApplicationStateSingleton.getGameHallStateInstance());
                applicationContext.enterGameHall();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public void enterGameHall() {
	}

	public void enterGameTable() {
	}

	public void disconnect() {
	}

	public void destory() {
	}

    private void parseResponse(HttpResponse response) throws IOException {
        System.out.println("context encode: "+response.getContentEncoding());
        System.out.println("context type: "+response.getContentType());
        System.out.println("state code: "+response.getStatusCode());
        System.out.println("context: "+response.getContent());

        AuthorizationCode authorizationCode = response.parseAs(AuthorizationCode.class);
        if(authorizationCode != null){
            System.out.println("login with auth code "+authorizationCode.getAuthorizationCode());
        }

    }

    private void getUserData(long userId){
        HttpRequestFactory requestFactory = HttpRequestFactorySingleton.getHttpRequestFactoryInstance();
        GenericUrl genericUrl = new GenericUrl("http://localhost:8080/api/users/"+userId);
        HttpResponse response = null;
        HttpRequest request;
        try{
            request = requestFactory.buildGetRequest(genericUrl);
            request.getHeaders().set("X-AUTH-TOKEN", userId);
            request.getHeaders().set("cookie", ApplicationContextSingleton.getInstance().currentCookie);

            System.out.println(ApplicationContextSingleton.getInstance().currentCookie);

            response = request.execute();
            if(response!= null && response.getStatusCode() == HttpStatusCodes.STATUS_CODE_OK){
                User user = response.parseAs(User.class);
                if(user != null){
                    ApplicationContextSingleton.getInstance().currentUser = user;
			    System.out.println("userId: "+ user.getUserId());
			    System.out.println("username: " + user.getUsername());
			    System.out.println("emailAddress: " + user.getEmailAddress());
			    System.out.println("createTime: " + user.getCreateTime());
			    System.out.println("isActive: " + user.getIsActive());
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
