package otakuplus.straybird.othellogame.network.http;

import com.google.api.client.util.Key;

public class EmBeddedUserInformationList {
    @Key("_embedded")
    private UserInformationList userInformationList;

    public UserInformationList getUserInformationList() {
        return userInformationList;
    }

    public void setUserInformationList(UserInformationList userInformationList) {
        this.userInformationList = userInformationList;
    }
}
