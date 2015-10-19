package otakuplus.straybird.othellogame.network.socketio;

import io.socket.emitter.Emitter.Listener;
import org.eclipse.swt.widgets.Display;
import org.json.JSONException;
import org.json.JSONObject;
import otakuplus.straybird.othellogame.ApplicationContext;
import otakuplus.straybird.othellogame.ApplicationContextSingleton;
import otakuplus.straybird.othellogame.ui.GameHallWindow;

public class SendMessageListener implements Listener{

    private SendMessage sendMessage = new SendMessage();

	public void call(Object... object) {
		System.out.print("send message event.");
		int count = object.length;
		System.out.print("object number :"+count);
		JSONObject jsonObject = null;
		for(int i=0; i< count; i++){
            jsonObject = (JSONObject)object[i];
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
                        if(sendMessage.getRoomName().equals(SocketIOClient.GAME_HALL_ROOM)) {
                            GameHallWindow gameHallWindow = applicationContext.getGameHallWindow();
                            gameHallWindow.receiveMessage(SendMessageListener.this.sendMessage);
                        }
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
		}
	}

}
