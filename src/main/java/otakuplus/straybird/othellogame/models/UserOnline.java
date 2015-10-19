package otakuplus.straybird.othellogame.models;

import com.google.api.client.util.Key;
import otakuplus.straybird.othellogame.network.http.HalPages;
import otakuplus.straybird.othellogame.network.http.UserOnlineLinks;

public class UserOnline {
    public static final int ONLINE = 100;
    public static final int STAND_BY = 101;
    public static final int GAMING = 102;
    public static final int OFFLINE= 199;

    @Key
    private Long userId;
    @Key
    private int onlineState;
    @Key("_links")
    private UserOnlineLinks links;
    @Key("page")
    private HalPages pages;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getOnlineState() {
        return onlineState;
    }

    public void setOnlineState(int onlineState) {
        this.onlineState = onlineState;
    }

    public UserOnlineLinks getLinks() {
        return links;
    }

    public void setLinks(UserOnlineLinks links) {
        this.links = links;
    }

    public HalPages getPages() {
        return pages;
    }

    public void setPages(HalPages pages) {
        this.pages = pages;
    }
}
