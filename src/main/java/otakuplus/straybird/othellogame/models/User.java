package otakuplus.straybird.othellogame.models;

import com.google.api.client.util.Key;
import otakuplus.straybird.othellogame.network.http.HalPages;
import otakuplus.straybird.othellogame.network.http.UserLinks;
import otakuplus.straybird.othellogame.network.http.UserOnlineLinks;

public class User {
	
	@Key
	private Integer userId;
	
	@Key
	private String username;
	
	@Key
	private String emailAddress;
	
	private String password;
	
	@Key
    private String createTime;
	
	@Key
	private boolean isActive = false;

	@Key("_links")
	private UserLinks links;

	@Key("page")
	private HalPages pages;

	public User() {

	}

	public User(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

    public UserLinks getLinks() {
        return links;
    }

    public void setLinks(UserLinks links) {
        this.links = links;
    }

    public HalPages getPages() {
        return pages;
    }

    public void setPages(HalPages pages) {
        this.pages = pages;
    }
}
