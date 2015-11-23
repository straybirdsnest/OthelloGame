package otakuplus.straybird.othellogame.applicationstates;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpStatusCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import otakuplus.straybird.othellogame.applicationstates.game.GameContext;
import otakuplus.straybird.othellogame.applicationstates.game.GameContextSigleton;
import otakuplus.straybird.othellogame.applicationstates.game.GameStateSingleton;
import otakuplus.straybird.othellogame.network.http.HttpRequestUtil;
import otakuplus.straybird.othellogame.ui.OthelloGameWindow;

import java.io.IOException;

public class ApplicationEnterGameTableState implements ApplicationState {

    private final static Logger logger = LoggerFactory.getLogger(ApplicationEnterGameTableState.class);

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
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        OthelloGameWindow othelloGameWindow = applicationContext.getOthelloGameWindow();

        String url = HttpRequestUtil.HOST_BASE_URL
                + "/api/gameTables/" + gameTableId + "/seats/" + seatId + "/enter";
        HttpResponse response = null;
        HttpRequest request;
        applicationContext.updateCsrfToken();

        try {
            request = HttpRequestUtil.buildHttpPostRequest(url, applicationContext.currentUser.getUserId());
            response = request.execute();
            if (response != null && response.getStatusCode() == HttpStatusCodes.STATUS_CODE_OK) {
                othelloGameWindow.show();
                applicationContext.currentTableId = gameTableId;
                applicationContext.currentSeatId = seatId;
                GameContext gameContext = GameContextSigleton.getGameContextInstance();
                gameContext.changeState(GameStateSingleton.getGameNoReadyStateInstance());
                logger.debug("enter game table");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void leaveGameTable(Integer gameTableId, Integer seatId) {
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        applicationContext.changeState(ApplicationStateSingleton.getLeaveGameTableStateInstance());
        applicationContext.leaveGameTable(gameTableId, seatId);
    }

    public void giveUp(){
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        OthelloGameWindow othelloGameWindow = applicationContext.getOthelloGameWindow();

        String url = HttpRequestUtil.HOST_BASE_URL
                + "/api/gameTables/" + applicationContext.getCurrentTableId() + "/seats/" + applicationContext.getCurrentSeatId() + "/giveUp";
        HttpResponse response = null;
        HttpRequest request;
        applicationContext.updateCsrfToken();

        try {
            request = HttpRequestUtil.buildHttpPostRequest(url, applicationContext.currentUser.getUserId());
            response = request.execute();
            if (response != null && response.getStatusCode() == HttpStatusCodes.STATUS_CODE_OK) {
                logger.info("giveUp");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(){
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        OthelloGameWindow othelloGameWindow = applicationContext.getOthelloGameWindow();

        String url = HttpRequestUtil.HOST_BASE_URL
                + "/api/gameTables/" + applicationContext.getCurrentTableId() + "/seats/" + applicationContext.getCurrentSeatId() + "/draw";
        HttpResponse response = null;
        HttpRequest request;
        applicationContext.updateCsrfToken();

        try {
            request = HttpRequestUtil.buildHttpPostRequest(url, applicationContext.currentUser.getUserId());
            response = request.execute();
            if (response != null && response.getStatusCode() == HttpStatusCodes.STATUS_CODE_OK) {
                logger.info("draw");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void win() {
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        OthelloGameWindow othelloGameWindow = applicationContext.getOthelloGameWindow();

        String url = HttpRequestUtil.HOST_BASE_URL
                + "/api/gameTables/" + applicationContext.getCurrentTableId() + "/seats/" + applicationContext.getCurrentSeatId() + "/win";
        HttpResponse response = null;
        HttpRequest request;
        applicationContext.updateCsrfToken();

        try {
            request = HttpRequestUtil.buildHttpPostRequest(url, applicationContext.currentUser.getUserId());
            response = request.execute();
            if (response != null && response.getStatusCode() == HttpStatusCodes.STATUS_CODE_OK) {
                logger.info("win");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logout() {

    }

    public void disconnect() {

    }

    public void destory() {

    }
}
