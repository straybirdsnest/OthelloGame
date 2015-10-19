package otakuplus.straybird.othellogame.network.http;

import com.google.api.client.util.Key;
import otakuplus.straybird.othellogame.models.User;

import java.util.ArrayList;

public class UserList {
    @Key
    private ArrayList<User> users;

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
