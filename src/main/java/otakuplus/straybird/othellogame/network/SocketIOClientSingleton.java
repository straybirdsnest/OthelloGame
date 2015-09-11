package otakuplus.straybird.othellogame.network;

public class SocketIOClientSingleton {
	private static class SocketIOClientHolder{
		static final SocketIOClient INSTANCE = new SocketIOClient();
	}
	
	public static SocketIOClient getInstance(){
		return SocketIOClientHolder.INSTANCE;
	}

}
