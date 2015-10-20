package otakuplus.straybird.othellogame.network.socketio;

import io.socket.client.IO;
import io.socket.client.Socket;
import org.json.JSONException;
import org.json.JSONObject;
import otakuplus.straybird.othellogame.applicationstates.ApplicationContext;
import otakuplus.straybird.othellogame.applicationstates.ApplicationContextSingleton;
import otakuplus.straybird.othellogame.models.UserInformation;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class SocketIOClient {

	public static final String SERVER_URI = "http://localhost:8081";
    public static final String SEND_MESSAGE_EVENT = "sendMessage";
	public static final String GAME_OPERATION_EVENT = "gameOperation";
    public static final String GAME_HALL_ROOM = "gamehall";
    public static final String GAME_TABLE_ROOM = "gametable";

	private Socket socket;
	
	public SocketIOClient() {
		createSocketIOClient();
	}

	public void createSocketIOClient() {
		try {
			socket = IO.socket(SERVER_URI);
		} catch (java.net.URISyntaxException urisyntaxexception) {

		}
	}

	public void setupSocketIOClient() {
		if(socket != null)
		{
			socket.on(Socket.EVENT_CONNECT, new ClientConnectListener());
			socket.on(Socket.EVENT_CONNECT_TIMEOUT, new ClientConnectTimeoutListener());
            socket.on(SocketIOClient.SEND_MESSAGE_EVENT, new SendMessageListener());
			socket.on(SocketIOClient.GAME_OPERATION_EVENT, new GameOperationListener());
		}
	}

	public void connect() {
		if (socket != null) {
			socket.connect();
		}
	}

	public void disconnect() {
		if (socket != null) {
			socket.disconnect();
		}
	}

	public String getSocketId(){
		String id = null;
		if(socket != null){
			id = socket.id();
		}
		return id;
	}

	public void sendeMessage(String roomName, String message){
        if(socket != null){
            JSONObject jsonObject = new JSONObject();
            ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
            UserInformation userInformation = applicationContext.getCurrentUserInformation();
            if(userInformation != null){
                try {
                    ZonedDateTime nowTime = ZonedDateTime.now(ZoneId.of("GMT+8"));
                    jsonObject.put("nickname", userInformation.getNickname());
                    jsonObject.put("message", message);
                    jsonObject.put("sendTime", nowTime.toString());
                    jsonObject.put("roomName", roomName);
                    socket.emit(SEND_MESSAGE_EVENT, jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

	public void doGameOperation(GameOperation gameOperation){
		if(socket!= null){
			JSONObject jsonObject = new JSONObject();
			ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
			try {
				if(applicationContext.getCurrentTableId() != null && applicationContext.getCurrentSeatId() != null) {
					jsonObject.put("roomName", GAME_TABLE_ROOM+applicationContext.getCurrentTableId());
					jsonObject.put("seatId", applicationContext.getCurrentSeatId());
					jsonObject.put("setX",gameOperation.getSetX() );
					jsonObject.put("setY", gameOperation.getSetY());
					jsonObject.put("standBy", gameOperation.getStandBy());
					socket.emit(GAME_OPERATION_EVENT,jsonObject);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

}
