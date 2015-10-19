package otakuplus.straybird.othellogame.network.http;

import com.google.api.client.util.Key;

public class UserInformationLinks {
    @Key
    private HalHrefLink self;
    @Key
    private HalHrefLink user;

    public HalHrefLink getSelf() {
        return self;
    }

    public void setSelf(HalHrefLink self) {
        this.self = self;
    }

    public HalHrefLink getUser() {
        return user;
    }

    public void setUser(HalHrefLink user) {
        this.user = user;
    }
}
