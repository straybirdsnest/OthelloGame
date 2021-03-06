package otakuplus.straybird.othellogame.network.socketio;

import io.socket.client.IO;
import io.socket.client.Socket;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import otakuplus.straybird.othellogame.applicationstates.ApplicationContext;
import otakuplus.straybird.othellogame.applicationstates.ApplicationContextSingleton;
import otakuplus.straybird.othellogame.models.UserInformation;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class SocketIOClient {

    public static final String SEND_MESSAGE_EVENT = "sendMessage";
    public static final String GAME_OPERATION_EVENT = "gameOperation";
    public static final String GAME_HALL_ROOM = "gamehall";
    public static final String GAME_TABLE_ROOM = "gametable";
    public static final String NOTIFY_USER_INFORMATIONS_UPDATE = "notifyUserInformationsUpdate";
    public static final String NOTIFY_GAME_TABLES_UPDATE = "notifyGameTablesUpdate";
    private static final Logger logger = LoggerFactory.getLogger(SocketIOClient.class);
    private Socket socket;

    public SocketIOClient() {
        createSocketIOClient();
        setupSocketIOClient();
    }

    public void createSocketIOClient() {
        try {
            ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
            String serverUri = "http://" + applicationContext.getServerName() + ":" + applicationContext.getSocketPort();
            socket = IO.socket(serverUri);
        } catch (java.net.URISyntaxException urisyntaxexception) {

        }
    }

    public void setupSocketIOClient() {
        if (socket != null) {
            socket.on(Socket.EVENT_CONNECT, new ClientConnectListener());
            socket.on(Socket.EVENT_CONNECT_TIMEOUT, new ClientConnectTimeoutListener());
            socket.on(SocketIOClient.SEND_MESSAGE_EVENT, new SendMessageListener());
            socket.on(SocketIOClient.GAME_OPERATION_EVENT, new GameOperationListener());
            socket.on(NOTIFY_USER_INFORMATIONS_UPDATE, new NotifyUpdateUserInformationsListener());
            socket.on(NOTIFY_GAME_TABLES_UPDATE, new NotifyUpdateGameTablesListener());
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

    public String getSocketId() {
        String id = null;
        if (socket != null) {
            id = socket.id();
        }
        return id;
    }

    public void sendeMessage(String roomName, String message) {
        if (socket != null) {
            JSONObject jsonObject = new JSONObject();
            ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
            UserInformation userInformation = applicationContext.getCurrentUserInformation();
            if (userInformation != null) {
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

    public void doGameOperation(GameOperation gameOperation) {
        if (socket != null) {
            JSONObject jsonObject = new JSONObject();
            ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
            try {
                if (applicationContext.getCurrentTableId() != null && applicationContext.getCurrentSeatId() != null) {
                    jsonObject.put("roomName", GAME_TABLE_ROOM + applicationContext.getCurrentTableId());
                    jsonObject.put("seatId", applicationContext.getCurrentSeatId());
                    if (gameOperation.getSetX() == null) {
                        jsonObject.put("setX", JSONObject.NULL);
                    } else {
                        jsonObject.put("setX", gameOperation.getSetX());
                    }
                    if (gameOperation.getSetY() == null) {
                        jsonObject.put("setY", JSONObject.NULL);
                    } else {
                        jsonObject.put("setY", gameOperation.getSetY());
                    }
                    jsonObject.put("operation", gameOperation.getOperation());
                    socket.emit(GAME_OPERATION_EVENT, jsonObject);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
