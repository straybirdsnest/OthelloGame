package otakuplus.straybird.othellogame.models;

import com.google.api.client.util.Key;
import otakuplus.straybird.othellogame.network.http.UserLinks;

public class User {
	
	@Key
	private Long userId;
	
	@Key
	private String username;
	
	@Key
	private String emailAddress;
	
	private String password;
	
	@Key
	private String createTime;
	
	@Key
	private boolean isActive = false;
	
	private UserInformation userInformation;

	@Key("_links")
	private UserLinks links;

	public User() {

	}

	public User(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
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

	public UserInformation getUserInformation() {
		return userInformation;
	}

	public void setUserInformation(UserInformation userInformation) {
		this.userInformation = userInformation;
	}

    public UserLinks getLinks() {
        return links;
    }

    public void setLinks(UserLinks links) {
        this.links = links;
    }
}
