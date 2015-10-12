package otakuplus.straybird.othellogame.network.http;

import com.google.api.client.util.Key;

public class AuthorizationCode {
    @Key
    private String authorizationCode;

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }
}
