package otakuplus.straybird.othellogame.network.socketio;

import io.socket.emitter.Emitter;
import org.eclipse.swt.widgets.Display;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import otakuplus.straybird.othellogame.applicationstates.ApplicationContext;
import otakuplus.straybird.othellogame.applicationstates.ApplicationContextSingleton;
import otakuplus.straybird.othellogame.applicationstates.game.GameContext;
import otakuplus.straybird.othellogame.applicationstates.game.GameContextSigleton;
import otakuplus.straybird.othellogame.models.ChessBoard;
import otakuplus.straybird.othellogame.ui.OthelloGameWindow;

public class GameOperationListener implements Emitter.Listener {

    private static final Logger logger = LoggerFactory.getLogger(GameOperationListener.class);

    private GameOperation gameOperation = new GameOperation();

    public void call(Object... objects) {
        logger.info("receive game operation event");
        int count = objects.length;
        logger.info("event number " + count);
        //ObjectMapper objectMapper = ObjectMapperSingleton.getObjectMapperInstance();
        JSONObject jsonObject = null;
        Integer setX = null;
        Integer setY = null;
        for (int i = 0; i < count; i++) {
            jsonObject = (JSONObject) objects[i];
            logger.info("json object" + jsonObject.toString());
            try {
                String roomName = (String) jsonObject.get("roomName");
                Integer seatId = (Integer) jsonObject.get("seatId");
                // Json.org 's lib can not read null
                if (!jsonObject.isNull("setX")) {
                    setX = (Integer) jsonObject.get("setX");
                }
                if (!jsonObject.isNull("setY")) {
                    setY = (Integer) jsonObject.get("setY");

                }
                String operation = (String) jsonObject.get("operation");
                gameOperation.setRoomName(roomName);
                gameOperation.setSeatId(seatId);
                gameOperation.setSetX(setX);
                gameOperation.setSetY(setY);
                gameOperation.setOperation(operation);
                Display display = ApplicationContextSingleton.getInstance().getDisplay();
                display.asyncExec(new Runnable() {
                    public void run() {
                        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
                        if (gameOperation.getRoomName().equals(SocketIOClient.GAME_TABLE_ROOM + applicationContext.getCurrentTableId())) {
                            OthelloGameWindow othelloGameWindow = applicationContext.getOthelloGameWindow();
                            GameContext gameContext = GameContextSigleton.getGameContextInstance();
                            ChessBoard chessBoard = gameContext.getChessBoard();
                            if (gameOperation.getOperation().equals(GameOperation.STAND_BY)) {
                                if (gameOperation.getSeatId() == 0) {
                                    gameContext.whiteStandBy();
                                    othelloGameWindow.redrawChessBoard();
                                }
                                if (gameOperation.getSeatId() == 1) {
                                    gameContext.blackStandBy();
                                    othelloGameWindow.redrawChessBoard();
                                }
                            }
                            if (gameOperation.getOperation().equals(GameOperation.STAND_BY_CANCLE)) {
                                if (gameOperation.getSeatId() == 0) {
                                    gameContext.whiteStandByCancel();
                                    othelloGameWindow.redrawChessBoard();
                                }
                                if (gameOperation.getSeatId() == 1) {
                                    gameContext.blackStandByCancel();
                                    othelloGameWindow.redrawChessBoard();
                                }
                            }
                            if (gameOperation.getOperation().equals(GameOperation.BLACK_SET)) {
                                if (gameOperation.getSetX() != null && gameOperation.getSetY() != null) {
                                    gameContext.blackSet(gameOperation.getSetX(), gameOperation.getSetY());
                                    othelloGameWindow.redrawChessBoard();
                                }
                            }
                            if (gameOperation.getOperation().equals(GameOperation.WHITE_SET)) {
                                if (gameOperation.getSetX() != null && gameOperation.getSetY() != null) {
                                    gameContext.whiteSet(gameOperation.getSetX(), gameOperation.getSetY());
                                    othelloGameWindow.redrawChessBoard();
                                }
                            }
                            if (gameOperation.getOperation().equals(GameOperation.GIVE_UP)) {
                                if (gameOperation.getSeatId() != null) {
                                    gameContext.reboot();
                                    othelloGameWindow.redrawChessBoard();
                                    if (!gameOperation.getSeatId().equals(applicationContext.getCurrentSeatId())) {
                                        othelloGameWindow.showGiveUpMessage();
                                    }
                                }
                            }
                            if (gameOperation.getOperation().equals(GameOperation.DRAW)) {
                                if (gameOperation.getSeatId() != null) {
                                    othelloGameWindow.redrawChessBoard();
                                    if (!gameOperation.getSeatId().equals(applicationContext.getCurrentSeatId())) {
                                        othelloGameWindow.showDrawMessage();
                                    }
                                }
                            }
                            if (gameOperation.getOperation().equals(GameOperation.DRAW_AGREE)) {
                                if (gameOperation.getSeatId() != null) {
                                    othelloGameWindow.redrawChessBoard();
                                    gameContext.reboot();
                                    othelloGameWindow.redrawChessBoard();
                                }
                            }
                            if (gameOperation.getOperation().equals(GameOperation.TAKE_BACK)) {
                                if (gameOperation.getSeatId() != null) {
                                    othelloGameWindow.redrawChessBoard();
                                    if (!gameOperation.getSeatId().equals(applicationContext.getCurrentSeatId())) {
                                        othelloGameWindow.showTakeBackMessage();
                                    }
                                }
                            }
                            if (gameOperation.getOperation().equals(GameOperation.TAKE_BACK_AGREE)) {
                                if (gameOperation.getSeatId() != null) {
                                    othelloGameWindow.redrawChessBoard();
                                    gameContext.takeBack();
                                    othelloGameWindow.redrawChessBoard();
                                }
                            }

                            if(chessBoard.checkGameOver()){
                                if(chessBoard.getBlackNumber()> chessBoard.getWhiteNumber()){

                                }
                            }

                        }
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
