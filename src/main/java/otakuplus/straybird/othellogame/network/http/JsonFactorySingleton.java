package otakuplus.straybird.othellogame.network.http;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

public class JsonFactorySingleton {
    public static JsonFactory getJsonFactoryInstance() {
        return JsonFactoryHolder.INSTANCE;
    }

    private static class JsonFactoryHolder {
        static final JsonFactory INSTANCE = new JacksonFactory();
    }
}
