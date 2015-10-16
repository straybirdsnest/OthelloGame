package otakuplus.straybird.othellogame.network.http;

import com.google.api.client.util.Key;

public class Logout {
    @Key
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
