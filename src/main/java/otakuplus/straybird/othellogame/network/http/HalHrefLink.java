package otakuplus.straybird.othellogame.network.http;

import com.google.api.client.util.Key;

public class HalHrefLink {
    @Key("href")
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
