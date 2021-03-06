package otakuplus.straybird.othellogame.network.socketio;

import io.socket.emitter.Emitter.Listener;
import org.json.JSONException;
import org.json.JSONObject;

public class ClientConnectListener implements Listener {

    public void call(Object... object) {
        System.out.println("connect event");
        int count = object.length;
        System.out.println("object number :" + count);
        JSONObject jsonObject = null;
        for (int i = 0; i < count; i++) {
            jsonObject = (JSONObject) object[i];
            System.out.println(jsonObject.toString());
            try {
                System.out.println(jsonObject.getString("username"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
