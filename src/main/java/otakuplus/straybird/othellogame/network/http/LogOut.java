package otakuplus.straybird.othellogame.network.http;

import com.google.api.client.util.Key;

public class Logout {
    @Key
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
