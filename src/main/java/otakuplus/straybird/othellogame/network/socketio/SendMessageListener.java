package otakuplus.straybird.othellogame.network.socketio;

import io.socket.emitter.Emitter.Listener;
import org.eclipse.swt.widgets.Display;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import otakuplus.straybird.othellogame.applicationstates.ApplicationContext;
import otakuplus.straybird.othellogame.applicationstates.ApplicationContextSingleton;
import otakuplus.straybird.othellogame.ui.GameHallWindow;
import otakuplus.straybird.othellogame.ui.OthelloGameWindow;

public class SendMessageListener implements Listener {

    private static final Logger logger = LoggerFactory.getLogger(SendMessageListener.class);

    private SendMessage sendMessage = new SendMessage();

    public void call(Object... object) {
        int count = object.length;
        JSONObject jsonObject = null;
        for (int i = 0; i < count; i++) {
            jsonObject = (JSONObject) object[i];
            try {
                String nickname = (String) jsonObject.get("nickname");
                String message = (String) jsonObject.get("message");
                final String sendTime = (String) jsonObject.get("sendTime");
                String roomName = (String) jsonObject.get("roomName");
                sendMessage.setNickname(nickname);
                sendMessage.setMessage(message);
                sendMessage.setSendTime(sendTime);
                sendMessage.setRoomName(roomName);
                Display display = ApplicationContextSingleton.getInstance().getDisplay();
                display.asyncExec(new Runnable() {
                    public void run() {
                        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
                        if (sendMessage.getRoomName().equals(SocketIOClient.GAME_HALL_ROOM)) {
                            GameHallWindow gameHallWindow = applicationContext.getGameHallWindow();
                            gameHallWindow.receiveMessage(SendMessageListener.this.sendMessage);
                        } else if (sendMessage.getRoomName().equals(SocketIOClient.GAME_TABLE_ROOM + applicationContext.getCurrentTableId())) {
                            OthelloGameWindow othelloGameWindow = applicationContext.getOthelloGameWindow();
                            othelloGameWindow.receiveMessage(sendMessage);
                            othelloGameWindow.syncGameReadyState();
                        }
                    }
                });
            } catch (JSONException e) {
                logger.error("SendMessageListener", e.getStackTrace());
            }
        }
    }

}
