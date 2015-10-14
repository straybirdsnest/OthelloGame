package otakuplus.straybird.othellogame.network.http;

import com.google.api.client.util.Key;

public class UserLinks {
    @Key
    private HalHrefLink self;

    @Key
    private HalHrefLink userInformation;

    @Key
    private HalHrefLink userOnline;

    public HalHrefLink getSelf() {
        return self;
    }

    public void setSelf(HalHrefLink self) {
        this.self = self;
    }

    public HalHrefLink getUserInformation() {
        return userInformation;
    }

    public void setUserInformation(HalHrefLink userInformation) {
        this.userInformation = userInformation;
    }

    public HalHrefLink getUserOnline() {
        return userOnline;
    }

    public void setUserOnline(HalHrefLink userOnline) {
        this.userOnline = userOnline;
    }
}
