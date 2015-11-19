package otakuplus.straybird.othellogame.network.socketio;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperSingleton {

    public static ObjectMapper getObjectMapperInstance() {
        return InstanceHolder.INSTANCE;
    }

    private static class InstanceHolder {
        static final ObjectMapper INSTANCE = new ObjectMapper();
    }
}
