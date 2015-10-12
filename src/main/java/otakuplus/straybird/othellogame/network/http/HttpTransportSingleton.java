package otakuplus.straybird.othellogame.network.http;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

/**
 * Created by otakuplus on 2015/9/22.
 */
public class HttpTransportSingleton {
    private static class HttpTransportHolder{
        static final HttpTransport INSTANCE = new NetHttpTransport();
    }

    public static HttpTransport getHttpTransportInstance(){
        return HttpTransportHolder.INSTANCE;
    }
}
