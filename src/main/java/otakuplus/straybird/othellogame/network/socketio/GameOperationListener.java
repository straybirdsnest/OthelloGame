package otakuplus.straybird.othellogame.network.socketio;

import io.socket.emitter.Emitter;
import org.eclipse.swt.widgets.Display;
import org.json.JSONException;
import org.json.JSONObject;
import otakuplus.straybird.othellogame.applicationstates.ApplicationContext;
import otakuplus.straybird.othellogame.applicationstates.ApplicationContextSingleton;
import otakuplus.straybird.othellogame.applicationstates.GameContext;
import otakuplus.straybird.othellogame.applicationstates.GameContextSigleton;
import otakuplus.straybird.othellogame.ui.OthelloGameWindow;

public class GameOperationListener implements Emitter.Listener{
    private GameOperation gameOperation = new GameOperation();

    public void call(Object... objects) {
        System.out.println("game operation event.");
        int count = objects.length;
        System.out.println("object number :" + count);
        JSONObject jsonObject = null;
        for(int i=0; i< count; i++){
            jsonObject = (JSONObject) objects[i];
            try {
                String roomName = (String) jsonObject.get("roomName");
                Long seatId = (Long) jsonObject.get("seatId");
                Long setX = (Long) jsonObject.get("setX");
                Long setY = (Long) jsonObject.get("setY");
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
                        if(gameOperation.getRoomName().equals(SocketIOClient.GAME_TABLE_ROOM+applicationContext.getCurrentTableId())){
                            OthelloGameWindow othelloGameWindow = applicationContext.getOthelloGameWindow();
                            if(gameOperation.getOperation().equals(GameOperation.STAND_BY)){
                                GameContext gameContext = GameContextSigleton.getGameContextInstance();
                                if(gameOperation.getSeatId() == 0L){
                                    gameContext.whiteStandBy();
                                }
                                if(gameOperation.getSeatId() == 1L){
                                    gameContext.blackStandBy();
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
