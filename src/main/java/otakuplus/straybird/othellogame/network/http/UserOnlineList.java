package otakuplus.straybird.othellogame.network.http;

import com.google.api.client.util.Key;
import otakuplus.straybird.othellogame.models.UserOnline;

import java.util.ArrayList;

public class UserOnlineList {
    @Key
    private ArrayList<UserOnline> userOnlines;

    public void setUserOnlines(ArrayList<UserOnline> userOnlines){
        this.userOnlines = userOnlines;
    }

    public ArrayList<UserOnline> getUserOnlines(){
        return userOnlines;
    }
}
