package otakuplus.straybird.othellogame.network.socketio;

import io.socket.emitter.Emitter;
import org.eclipse.swt.widgets.Display;
import org.json.JSONException;
import org.json.JSONObject;
import otakuplus.straybird.othellogame.applicationstates.ApplicationContext;
import otakuplus.straybird.othellogame.applicationstates.ApplicationContextSingleton;
import otakuplus.straybird.othellogame.ui.OthelloGameWindow;

public class GameOperationListener implements Emitter.Listener{
    private GameOperation gameOperation = new GameOperation();
    public void call(Object... objects) {
        System.out.print("game operation event.");
        int count = objects.length;
        System.out.print("object number :"+count);
        JSONObject jsonObject = null;
        for(int i=0; i< count; i++){
            jsonObject = (JSONObject) objects[i];
            try {
                String roomName = (String) jsonObject.get("roomName");
                Long seatId = (Long) jsonObject.get("seatId");
                Long setX = (Long) jsonObject.get("setX");
                Long setY = (Long) jsonObject.get("setY");
                gameOperation.setRoomName(roomName);
                gameOperation.setSeatId(seatId);
                gameOperation.setSetX(setX);
                gameOperation.setSetY(setY);
                Display display = ApplicationContextSingleton.getInstance().getDisplay();
                display.asyncExec(new Runnable() {
                    public void run() {
                        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
                        if(gameOperation.getRoomName().equals(SocketIOClient.GAME_TABLE_ROOM+applicationContext.getCurrentTableId())){
                            OthelloGameWindow othelloGameWindow = applicationContext.getOthelloGameWindow();
                        }
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
