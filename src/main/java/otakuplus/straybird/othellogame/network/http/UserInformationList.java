package otakuplus.straybird.othellogame.network.http;

import com.google.api.client.util.Key;
import otakuplus.straybird.othellogame.models.UserInformation;

import java.util.ArrayList;

public class UserInformationList {
    @Key
    private ArrayList<UserInformation> userInformations;

    public ArrayList<UserInformation> getUserInformations() {
        return userInformations;
    }

    public void setUserInformations(ArrayList<UserInformation> userInformations) {
        this.userInformations = userInformations;
    }
}
