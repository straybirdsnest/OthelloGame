package otakuplus.straybird.othellogame.network.http;

import com.google.api.client.util.Key;

public class EmBeddedGameTableList {
    @Key("_embedded")
    private GameTableList gameTableList;

    public GameTableList getGameTableList() {
        return gameTableList;
    }

    public void setGameTableList(GameTableList gameTableList) {
        this.gameTableList = gameTableList;
    }
}
