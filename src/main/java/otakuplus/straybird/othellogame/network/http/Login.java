package otakuplus.straybird.othellogame.network.http;

import com.google.api.client.util.Key;

public class Login {
    @Key
    private String username;
    @Key
    private String password;
    @Key
    private String socketIOId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSocketIOId() {
        return socketIOId;
    }

    public void setSocketIOId(String socketIOId) {
        this.socketIOId = socketIOId;
    }
}
