package otakuplus.straybird.othellogame.network.http;

import com.google.api.client.util.Key;

public class GameTableLinks {
    @Key
    private HalHrefLink self;
    @Key
    private HalHrefLink playerA;
    @Key
    private HalHrefLink playerB;

    public HalHrefLink getSelf() {
        return self;
    }

    public void setSelf(HalHrefLink self) {
        this.self = self;
    }

    public HalHrefLink getPlayerA() {
        return playerA;
    }

    public void setPlayerA(HalHrefLink playerA) {
        this.playerA = playerA;
    }

    public HalHrefLink getPlayerB() {
        return playerB;
    }

    public void setPlayerB(HalHrefLink playerB) {
        this.playerB = playerB;
    }
}
