package otakuplus.straybird.othellogame.network;

import java.sql.Timestamp;
import java.util.Date;

import otakuplus.straybird.othellogame.network.SendMessage;
import otakuplus.straybird.othellogame.network.Login;
import otakuplus.straybird.othellogame.network.Logout;
import otakuplus.straybird.othellogame.model.UserInformation;
import otakuplus.straybird.othellogame.model.User;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

public class KryonetUtil {

	public static final int SERVER_PORT = 8123;

	public static void register(EndPoint endPoint) {
		Kryo kryo = endPoint.getKryo();
		kryo.register(Timestamp.class);
		kryo.register(Date.class);

		kryo.register(Login.class);
		kryo.register(Logout.class);
		kryo.register(ProcessResponse.class);
		kryo.register(User.class);
		kryo.register(GetUserInformation.class);
		kryo.register(UserInformation.class);
		kryo.register(SendMessage.class);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}