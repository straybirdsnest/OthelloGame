package otakuplus.straybird.othellogame.applicationstates;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpStatusCodes;
import otakuplus.straybird.othellogame.models.User;
import otakuplus.straybird.othellogame.models.UserInformation;
import otakuplus.straybird.othellogame.network.http.HttpRequestUtil;
import otakuplus.straybird.othellogame.network.http.Login;
import otakuplus.straybird.othellogame.ui.LoginWindow;

import java.io.IOException;

public class ApplicationLoginState implements ApplicationState {

	public void initialize() {
	}

	public void connect() {
	}

	public void login() {
        String url = HttpRequestUtil.HOST_BASE_URL+"/api/authorization";
        HttpResponse response = null;
        HttpRequest request;
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        applicationContext.updateCsrfToken();
        try {
            Login login = new Login();
            LoginWindow loginWindow = applicationContext.getLoginWinodow();
            login.setUsername(loginWindow.getUsername());
            login.setPassword(loginWindow.getPassword());
            System.out.println("socketId " + applicationContext.getSocketIOClient().getSocketId());
            login.setSocketIOId(applicationContext.getSocketIOClient().getSocketId());

            request = HttpRequestUtil.buildHttpPostRequest(url, login);
            response = request.execute();
            if(response != null && response.getStatusCode() == HttpStatusCodes.STATUS_CODE_OK) {
                User user = response.parseAs(User.class);
                if (user != null && user.getUserId() != null) {
                    applicationContext.currentUser = user;
                    System.out.println("userId: " + user.getUserId());
                    request = HttpRequestUtil.buildHttpGetRequest(
                            HttpRequestUtil.HOST_BASE_URL + "/api/userInformations/" + user.getUserId());
                    response = request.execute();
                    if (response != null && response.getStatusCode() == HttpStatusCodes.STATUS_CODE_OK) {
                        applicationContext.currentUserInformation = response.parseAs(UserInformation.class);

                        // change to enter game hall state
                        applicationContext.changeState(ApplicationStateSingleton.getEnterGameHallStateInstance());
                        applicationContext.enterGameHall();
                    }
                }
            }
        } catch (IOException e) {
            if(e.getMessage().equals("400 Bad Request")) {
                LoginWindow loginWindow = applicationContext.getLoginWinodow();
                loginWindow.showLoginFailureMessage();
            }else{
                e.printStackTrace();
            }
        }
	}

	public void enterGameHall() {
	}

    public void leaveGameHall(){

    }

	public void enterGameTable(Integer gameTableId,Integer seatId) {
	}

    public void leaveGameTable(Integer gameTableId,Integer seatId){

    }

    public void logout(){

    }

	public void disconnect() {
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        applicationContext.changeState(ApplicationStateSingleton.getDisconnectStateInstance());
        applicationContext.disconnect();
	}

	public void destory() {
	}

}
