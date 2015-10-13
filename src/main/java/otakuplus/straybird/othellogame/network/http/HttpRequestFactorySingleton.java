package otakuplus.straybird.othellogame.network.http;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.JsonObjectParser;

public class HttpRequestFactorySingleton {
    private static class HttpRequestFactoryHolder{
        static final HttpRequestFactory INSTANCE = HttpTransportSingleton.getHttpTransportInstance()
                .createRequestFactory(new HttpRequestInitializer() {
            public void initialize(HttpRequest request) {
                request.setParser(new JsonObjectParser(JsonFactorySingleton.getJsonFactoryInstance()));
            }
        });
    }

    public static HttpRequestFactory getHttpRequestFactoryInstance(){
        return HttpRequestFactoryHolder.INSTANCE;
    }
}
