package otakuplus.straybird.othellogame.applicationstates;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpStatusCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import otakuplus.straybird.othellogame.applicationstates.game.GameContext;
import otakuplus.straybird.othellogame.applicationstates.game.GameContextSigleton;
import otakuplus.straybird.othellogame.applicationstates.game.GameStateSingleton;
import otakuplus.straybird.othellogame.models.ChessBoard;
import otakuplus.straybird.othellogame.models.GameRecord;
import otakuplus.straybird.othellogame.models.GameTable;
import otakuplus.straybird.othellogame.network.http.HttpRequestUtil;
import otakuplus.straybird.othellogame.ui.OthelloGameWindow;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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

        try {
            request = HttpRequestUtil.buildHttpPostRequest(url, applicationContext.currentUser.getUserId());
            response = request.execute();
            if (response != null && response.getStatusCode() == HttpStatusCodes.STATUS_CODE_OK) {
                othelloGameWindow.show();
                applicationContext.currentTableId = gameTableId;
                applicationContext.currentSeatId = seatId;
                GameContext gameContext = GameContextSigleton.getGameContextInstance();
                gameContext.changeState(GameStateSingleton.getGameNoReadyStateInstance());
                gameContext.reboot();
                logger.debug("enter game table");
            }
        } catch (IOException e) {
            if (e.getMessage().equals("400 Bad Request")) {
                logger.info("error while enter table");
            }
            e.printStackTrace();
        }
    }

    public void leaveGameTable(Integer gameTableId, Integer seatId) {
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        applicationContext.changeState(ApplicationStateSingleton.getLeaveGameTableStateInstance());
        applicationContext.leaveGameTable(gameTableId, seatId);
    }

    public void giveUp() {
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        OthelloGameWindow othelloGameWindow = applicationContext.getOthelloGameWindow();

        GameRecord gameRecord = applicationContext.getGameRecord();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZonedDateTime nowTime = ZonedDateTime.now(ZoneId.of("GMT+8"));
        gameRecord.setGameEndTime(nowTime.format(formatter));

        if (applicationContext.getCurrentTableId() != null) {
            GameTable gameTable = applicationContext.getGameTableList().get(applicationContext.getCurrentTableId() - 1);
            gameRecord.setPlayerA(gameTable.getPlayerA().getUsername());
            gameRecord.setPlayerB(gameTable.getPlayerB().getUsername());
        }

        GameContext gameContext = GameContextSigleton.getGameContextInstance();
        ChessBoard chessBoard = gameContext.getChessBoard();
        gameRecord.setBlackNumber(chessBoard.getBlackNumber());
        gameRecord.setWhiteNumber(chessBoard.getWhiteNumber());
        gameRecord.setRecord(chessBoard.getRecord());

        String url = HttpRequestUtil.HOST_BASE_URL
                + "/api/gameTables/" + applicationContext.getCurrentTableId() + "/seats/" + applicationContext.getCurrentSeatId() + "/giveUp";
        HttpResponse response = null;
        HttpRequest request;

        try {
            request = HttpRequestUtil.buildHttpPostRequest(url, applicationContext.currentUser.getUserId());
            response = request.execute();
            if (response != null && response.getStatusCode() == HttpStatusCodes.STATUS_CODE_OK) {
                logger.info("giveUp");
                HttpRequestUtil.uploadGameRecord(gameRecord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw() {
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        OthelloGameWindow othelloGameWindow = applicationContext.getOthelloGameWindow();

        GameRecord gameRecord = applicationContext.getGameRecord();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZonedDateTime nowTime = ZonedDateTime.now(ZoneId.of("GMT+8"));
        gameRecord.setGameEndTime(nowTime.format(formatter));

        if (applicationContext.getCurrentTableId() != null) {
            GameTable gameTable = applicationContext.getGameTableList().get(applicationContext.getCurrentTableId() - 1);
            gameRecord.setPlayerA(gameTable.getPlayerA().getUsername());
            gameRecord.setPlayerB(gameTable.getPlayerB().getUsername());
        }

        GameContext gameContext = GameContextSigleton.getGameContextInstance();
        ChessBoard chessBoard = gameContext.getChessBoard();
        gameRecord.setBlackNumber(chessBoard.getBlackNumber());
        gameRecord.setWhiteNumber(chessBoard.getWhiteNumber());
        gameRecord.setRecord(chessBoard.getRecord());

        String url = HttpRequestUtil.HOST_BASE_URL
                + "/api/gameTables/" + applicationContext.getCurrentTableId() + "/seats/" + applicationContext.getCurrentSeatId() + "/draw";
        HttpResponse response = null;
        HttpRequest request;

        try {
            request = HttpRequestUtil.buildHttpPostRequest(url, applicationContext.currentUser.getUserId());
            response = request.execute();
            if (response != null && response.getStatusCode() == HttpStatusCodes.STATUS_CODE_OK) {
                logger.info("draw");
                HttpRequestUtil.uploadGameRecord(gameRecord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void win() {
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        OthelloGameWindow othelloGameWindow = applicationContext.getOthelloGameWindow();

        GameRecord gameRecord = applicationContext.getGameRecord();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZonedDateTime nowTime = ZonedDateTime.now(ZoneId.of("GMT+8"));
        gameRecord.setGameEndTime(nowTime.format(formatter));

        if (applicationContext.getCurrentTableId() != null) {
            GameTable gameTable = applicationContext.getGameTableList().get(applicationContext.getCurrentTableId() - 1);
            gameRecord.setPlayerA(gameTable.getPlayerA().getUsername());
            gameRecord.setPlayerB(gameTable.getPlayerB().getUsername());
        }

        GameContext gameContext = GameContextSigleton.getGameContextInstance();
        ChessBoard chessBoard = gameContext.getChessBoard();
        gameRecord.setBlackNumber(chessBoard.getBlackNumber());
        gameRecord.setWhiteNumber(chessBoard.getWhiteNumber());
        gameRecord.setRecord(chessBoard.getRecord());

        String url = HttpRequestUtil.HOST_BASE_URL
                + "/api/gameTables/" + applicationContext.getCurrentTableId() + "/seats/" + applicationContext.getCurrentSeatId() + "/win";
        HttpResponse response = null;
        HttpRequest request;

        try {
            request = HttpRequestUtil.buildHttpPostRequest(url, applicationContext.currentUser.getUserId());
            response = request.execute();
            if (response != null && response.getStatusCode() == HttpStatusCodes.STATUS_CODE_OK) {
                logger.info("win");
                HttpRequestUtil.uploadGameRecord(gameRecord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logout() {

    }

    public void disconnect() {

    }

    public void destroy() {

    }
}
