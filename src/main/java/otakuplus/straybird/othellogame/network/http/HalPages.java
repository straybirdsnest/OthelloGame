package otakuplus.straybird.othellogame.network.http;

import com.google.api.client.util.Key;

public class HalPages {
    @Key
    private long size;
    @Key
    private long totalElements;
    @Key
    private long totalPages;
    @Key
    private long number;

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }
}
