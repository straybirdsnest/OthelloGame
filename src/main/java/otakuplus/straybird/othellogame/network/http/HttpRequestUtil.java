package otakuplus.straybird.othellogame.network.http;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.json.JsonHttpContent;
import otakuplus.straybird.othellogame.ApplicationContext;
import otakuplus.straybird.othellogame.ApplicationContextSingleton;

import java.io.IOException;
import java.net.HttpCookie;
import java.util.List;

public class HttpRequestUtil {
    public static final String HOST_BASE_URL = "http://localhost:8080";

    public static HttpRequest buildHttpGetRequest(String url){
        HttpRequestFactory requestFactory = HttpRequestFactorySingleton.getHttpRequestFactoryInstance();
        GenericUrl genericUrl = new GenericUrl(url);
        HttpRequest request = null;
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        try {
            request = requestFactory.buildGetRequest(genericUrl);
            if(applicationContext.getCurrentCookie() != null) {
                System.out.println("current Cookie: "+applicationContext.getCurrentCookie());
                request.getHeaders().set("cookie", applicationContext.getCurrentCookie());
                List<HttpCookie> cookie = HttpCookie.parse(applicationContext.getCurrentCookie().get(0));
                request.getHeaders().set("X-XSRF-TOKEN", cookie.get(0).getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return request;
    }

    public static HttpRequest buildHttpPostRequest(String url, Object object){
        HttpRequestFactory requestFactory = HttpRequestFactorySingleton.getHttpRequestFactoryInstance();
        GenericUrl genericUrl = new GenericUrl(url);
        HttpRequest request = null;
        JsonHttpContent jsonHttpContent = new JsonHttpContent(JsonFactorySingleton.getJsonFactoryInstance() ,object);
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        try {
            request = requestFactory.buildPostRequest(genericUrl, jsonHttpContent);
            if(applicationContext.getCurrentCookie() != null) {
                System.out.println("current Cookie: "+applicationContext.getCurrentCookie());
                request.getHeaders().set("cookie", applicationContext.getCurrentCookie());
                List<HttpCookie> cookie = HttpCookie.parse(applicationContext.getCurrentCookie().get(0));
                request.getHeaders().set("X-XSRF-TOKEN", cookie.get(0).getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return request;
    }
}
