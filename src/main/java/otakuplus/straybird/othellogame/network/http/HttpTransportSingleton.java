package otakuplus.straybird.othellogame.network.http;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

public class HttpTransportSingleton {
    public static HttpTransport getHttpTransportInstance() {
        return HttpTransportHolder.INSTANCE;
    }

    private static class HttpTransportHolder {
        static final HttpTransport INSTANCE = new NetHttpTransport();
    }
}
