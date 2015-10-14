package otakuplus.straybird.othellogame;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.json.JsonHttpContent;
import otakuplus.straybird.othellogame.network.http.AuthorizationCode;
import otakuplus.straybird.othellogame.network.http.HttpRequestFactorySingleton;
import otakuplus.straybird.othellogame.network.http.JsonFactorySingleton;
import otakuplus.straybird.othellogame.network.http.Login;
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
        HttpResponse response = null;
        HttpRequest request;
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        applicationContext.updateCsrfToken();
        try {
            Login login = new Login();
            LoginWindow loginWindow = applicationContext.getLoginWinodow();
            login.setUsername(loginWindow.getUsername());
            login.setPassword(loginWindow.getPassword());
            JsonHttpContent jsonHttpContent = new JsonHttpContent(JsonFactorySingleton.getJsonFactoryInstance() ,login);

            request = requestFactory.buildPostRequest(genericUrl,jsonHttpContent );
            if(applicationContext.currentCookie != null) {
                System.out.println("current Cookie: " + applicationContext.currentCookie);
                request.getHeaders().set("cookie", applicationContext.currentCookie);
                List<HttpCookie> cookie = HttpCookie.parse(applicationContext.currentCookie.get(0));
                request.getHeaders().set("X-XSRF-TOKEN", cookie.get(0).getValue());
            }
            response = request.execute();

            AuthorizationCode authorizationCode = response.parseAs(AuthorizationCode.class);
            if(authorizationCode != null){
                System.out.println("login with auth code "+authorizationCode.getAuthorizationCode());
            }

            // change to enter game hall state
            applicationContext.changeState(ApplicationStateSingleton.getEnterGameHallStateInstance());
            applicationContext.enterGameHall();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public void enterGameHall() {
	}

    public void leaveGameHall(){

    }

	public void enterGameTable() {
	}

	public void disconnect() {
	}

	public void destory() {
	}

}
