package otakuplus.straybird.othellogame.network.socketio;

public class SocketIOClientSingleton {
    public static SocketIOClient getInstance() {
        return SocketIOClientHolder.INSTANCE;
    }

    private static class SocketIOClientHolder {
        static final SocketIOClient INSTANCE = new SocketIOClient();
    }

}
