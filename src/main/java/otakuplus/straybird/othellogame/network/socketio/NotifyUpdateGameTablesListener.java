package otakuplus.straybird.othellogame.network.socketio;

import io.socket.emitter.Emitter;
import org.eclipse.swt.widgets.Display;
import org.json.JSONException;
import org.json.JSONObject;
import otakuplus.straybird.othellogame.applicationstates.ApplicationContext;
import otakuplus.straybird.othellogame.applicationstates.ApplicationContextSingleton;
import otakuplus.straybird.othellogame.network.http.HttpRequestUtil;
import otakuplus.straybird.othellogame.ui.GameHallWindow;

public class NotifyUpdateGameTablesListener implements Emitter.Listener {
    private NotifyUpdateGameTables notifyUpdateGameTables = new NotifyUpdateGameTables();

    public void call(Object... objects) {
        System.out.print("notify gameTables message event.");
        int count = objects.length;
        System.out.print("object number :"+count);
        JSONObject jsonObject = null;
        for(int i=0; i< count; i++) {
            jsonObject = (JSONObject) objects[i];
            try {
                String roomName = (String) jsonObject.get("roomName");
                notifyUpdateGameTables.setRoomName(roomName);
                Display display = ApplicationContextSingleton.getInstance().getDisplay();
                display.asyncExec(new Runnable() {
                    public void run() {
                        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
                        if(notifyUpdateGameTables.getRoomName().equals(SocketIOClient.GAME_HALL_ROOM)){
                            GameHallWindow gameHallWindow = applicationContext.getGameHallWindow();
                            HttpRequestUtil.updateGameTableList();
                            gameHallWindow.notifyGameTableListUpdate();
                        }
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
