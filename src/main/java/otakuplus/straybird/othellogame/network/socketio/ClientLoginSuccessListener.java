package otakuplus.straybird.othellogame.network.socketio;

import org.json.JSONObject;

import io.socket.emitter.Emitter.Listener;

public class ClientLoginSuccessListener implements Listener {

	public void call(Object... object) {
		System.out.print("Login Success event.");
		int count = object.length;
		System.out.print("object number :"+count);
		JSONObject jsonObject = null;
		for(int i=0; i< count; i++){
			 jsonObject = (JSONObject)object[i];
			 System.out.print(jsonObject.toString());
		}
	}

}
