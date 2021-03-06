package otakuplus.straybird.othellogame.models;

import com.google.api.client.util.Key;
import otakuplus.straybird.othellogame.network.http.GameTableLinks;
import otakuplus.straybird.othellogame.network.http.HalPages;

public class GameTable {

    @Key
    private Integer gameTableId;
    @Key
    private User playerA;
    @Key
    private User playerB;
    @Key("_links")
    private GameTableLinks links;

    @Key("page")
    private HalPages pages;

    public Integer getGameTableId() {
        return gameTableId;
    }

    public void setGameTableId(Integer gameTableId) {
        this.gameTableId = gameTableId;
    }

    public User getPlayerA() {
        return playerA;
    }

    public void setPlayerA(User playerA) {
        this.playerA = playerA;
    }

    public User getPlayerB() {
        return playerB;
    }

    public void setPlayerB(User playerB) {
        this.playerB = playerB;
    }

    public GameTableLinks getLinks() {
        return links;
    }

    public void setLinks(GameTableLinks links) {
        this.links = links;
    }

    public HalPages getPages() {
        return pages;
    }

    public void setPages(HalPages pages) {
        this.pages = pages;
    }
}
