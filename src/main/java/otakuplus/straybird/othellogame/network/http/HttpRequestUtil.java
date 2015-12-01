package otakuplus.straybird.othellogame.network.http;

import com.google.api.client.http.*;
import com.google.api.client.http.json.JsonHttpContent;
import otakuplus.straybird.othellogame.applicationstates.ApplicationContext;
import otakuplus.straybird.othellogame.applicationstates.ApplicationContextSingleton;
import otakuplus.straybird.othellogame.models.GameTable;
import otakuplus.straybird.othellogame.models.User;
import otakuplus.straybird.othellogame.models.UserInformation;
import otakuplus.straybird.othellogame.models.UserOnline;

import java.io.IOException;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;

public class HttpRequestUtil {
    public static String HOST_BASE_URL = "http://localhost:8080";

    public static void setUpHostBaseUrl(){
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        HOST_BASE_URL = "http://"+applicationContext.getServerName()+":"+applicationContext.getServerPort();
    }

    public static HttpRequest buildHttpGetRequest(String url) {
        HttpRequestFactory requestFactory = HttpRequestFactorySingleton.getHttpRequestFactoryInstance();
        GenericUrl genericUrl = new GenericUrl(url);
        HttpRequest request = null;
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        try {
            request = requestFactory.buildGetRequest(genericUrl);
            if (applicationContext.getCurrentCookie() != null) {
                System.out.println("current Cookie: " + applicationContext.getCurrentCookie());
                request.getHeaders().set("cookie", applicationContext.getCurrentCookie());
                List<HttpCookie> cookie = HttpCookie.parse(applicationContext.getCurrentCookie().get(0));
                request.getHeaders().set("X-XSRF-TOKEN", cookie.get(0).getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return request;
    }

    public static HttpRequest buildHttpPostRequest(String url, Object object) {

        updateCsrfToken();

        HttpRequestFactory requestFactory = HttpRequestFactorySingleton.getHttpRequestFactoryInstance();
        GenericUrl genericUrl = new GenericUrl(url);
        HttpRequest request = null;
        JsonHttpContent jsonHttpContent = new JsonHttpContent(JsonFactorySingleton.getJsonFactoryInstance(), object);
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        try {
            request = requestFactory.buildPostRequest(genericUrl, jsonHttpContent);
            if (applicationContext.getCurrentCookie() != null) {
                System.out.println("current Cookie: " + applicationContext.getCurrentCookie());
                request.getHeaders().set("cookie", applicationContext.getCurrentCookie());
                List<HttpCookie> cookie = HttpCookie.parse(applicationContext.getCurrentCookie().get(0));
                request.getHeaders().set("X-XSRF-TOKEN", cookie.get(0).getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return request;
    }

    public static User getUserByHref(String href) {
        User user = null;
        if (href != null) {
            HttpRequest request = HttpRequestUtil.buildHttpGetRequest(href);
            HttpResponse response;
            try {
                response = request.execute();
                user = response.parseAs(User.class);
                if (user != null) {
                    System.out.println("UserInformation: " + user.getLinks().getUserInformation().getHref());
                }
            } catch (IOException e) {
                if (e.getMessage().equals("404 Not Found")) {
                    return null;
                } else {
                    e.printStackTrace();
                }
            }
        }
        return user;
    }

    public static UserInformation getUserInformationByHref(String href) {
        UserInformation userInformation = null;
        if (href != null) {
            HttpRequest request = HttpRequestUtil.buildHttpGetRequest(href);
            HttpResponse response;
            try {
                response = request.execute();
                userInformation = response.parseAs(UserInformation.class);
                if (userInformation != null) {
                    System.out.println("UserNickName " + userInformation.getNickname());
                }
            } catch (IOException e) {
                if (e.getMessage().equals("404 Not Found")) {
                    return null;
                } else {
                    e.printStackTrace();
                }
            }
        }
        return userInformation;
    }

    public static void updateUserOnlineList() {
        String url = HttpRequestUtil.HOST_BASE_URL
                + "/api/userOnlines/search/findByOnlineState?onlineState="
                + UserOnline.ONLINE;
        HttpResponse response = null;
        HttpRequest request;
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        try {
            request = HttpRequestUtil.buildHttpGetRequest(url);
            response = request.execute();

            if (response != null && response.getStatusCode() == HttpStatusCodes.STATUS_CODE_OK) {
                EmBeddedUserOnlineList embeddedUserOnlineList = response.parseAs(EmBeddedUserOnlineList.class);
                if (embeddedUserOnlineList != null) {
                    UserOnlineList userOnlineList = embeddedUserOnlineList.getUserOnlineList();
                    if (userOnlineList != null && userOnlineList.getUserOnlines() != null) {
                        applicationContext.getUserInformationList().clear();
                        ArrayList<UserOnline> userOnlines = userOnlineList.getUserOnlines();
                        for (int i = 0; i < userOnlines.size(); i++) {
                            User user = HttpRequestUtil.getUserByHref(userOnlines.get(i).getLinks().getUser().getHref());
                            UserInformation userInformation = HttpRequestUtil.getUserInformationByHref(user.getLinks().getUserInformation().getHref());
                            applicationContext.getUserInformationList().add(userInformation);
                        }
                    }
                }
            }
        } catch (IOException e) {
            if (e.getMessage().equals("404 Not Found")) {
                return;
            } else {
                e.printStackTrace();
            }
        }
    }

    public static void updateGameTableList() {
        String url = HttpRequestUtil.HOST_BASE_URL + "/api/gameTables";
        HttpResponse response = null;
        HttpRequest request;
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        try {
            request = HttpRequestUtil.buildHttpGetRequest(url);
            response = request.execute();

            if (response != null && response.getStatusCode() == HttpStatusCodes.STATUS_CODE_OK) {
                EmBeddedGameTableList embeddedGameTableList = response.parseAs(EmBeddedGameTableList.class);
                if (embeddedGameTableList != null) {
                    GameTableList gameTableList = embeddedGameTableList.getGameTableList();
                    if (gameTableList != null && gameTableList.getGameTables() != null) {
                        applicationContext.getGameTableList().clear();
                        ArrayList<GameTable> gameTables = gameTableList.getGameTables();
                        for (int i = 0; i < gameTables.size(); i++) {
                            gameTables.get(i).setGameTableId(new Integer(i + 1));
                            User user = HttpRequestUtil.getUserByHref(gameTables.get(i)
                                    .getLinks().getPlayerA().getHref());
                            gameTables.get(i).setPlayerA(user);
                            user = HttpRequestUtil.getUserByHref(gameTables.get(i)
                                    .getLinks().getPlayerB().getHref());
                            gameTables.get(i).setPlayerB(user);
                            applicationContext.getGameTableList().add(gameTables.get(i));
                        }
                    }
                }
            }
        } catch (IOException e) {
            if (e.getMessage().equals("404 Not Found")) {
                return;
            } else {
                e.printStackTrace();
            }
        }
    }

    public static void updateCsrfToken() {
        ApplicationContext applicationContext = ApplicationContextSingleton.getInstance();
        String url = HttpRequestUtil.HOST_BASE_URL + "/api/csrftoken";
        HttpResponse response = null;
        HttpRequest request;
        try {
            request = HttpRequestUtil.buildHttpGetRequest(url);
            response = request.execute();
            if (response != null && response.getStatusCode() == HttpStatusCodes.STATUS_CODE_OK) {
                if (response.getHeaders().getHeaderStringValues("set-cookie").isEmpty() == false) {
                    applicationContext.setCurrentCookie(response.getHeaders().getHeaderStringValues("set-cookie"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
