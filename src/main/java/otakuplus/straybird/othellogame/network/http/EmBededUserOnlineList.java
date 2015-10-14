package otakuplus.straybird.othellogame.network.http;

import com.google.api.client.util.Key;

public class EmBededUserOnlineList {

    @Key("_embedded")
    private UserOnlineList userOnlineList;

    public UserOnlineList getUserOnlineList() {
        return userOnlineList;
    }

    public void setUserOnlineList(UserOnlineList userOnlineList) {
        this.userOnlineList = userOnlineList;
    }
}
