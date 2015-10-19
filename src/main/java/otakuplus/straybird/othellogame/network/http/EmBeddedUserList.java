package otakuplus.straybird.othellogame.network.http;

import com.google.api.client.util.Key;

public class EmBeddedUserList {
    @Key("_embedded")
    private UserList userList;

    public UserList getUserList() {
        return userList;
    }

    public void setUserList(UserList userList) {
        this.userList = userList;
    }
}
