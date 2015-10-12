package otakuplus.straybird.othellogame.network.http;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

/**
 * Created by otakuplus on 2015/9/22.
 */
public class JsonFactorySingleton {
    private static class JsonFactoryHolder
    {
        static final JsonFactory INSTANCE = new JacksonFactory();
    }
    public static JsonFactory getJsonFactoryInstance(){
        return JsonFactoryHolder.INSTANCE;
    }
}
