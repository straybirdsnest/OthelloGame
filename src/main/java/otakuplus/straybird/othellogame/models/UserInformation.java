package otakuplus.straybird.othellogame.models;

import com.google.api.client.util.Key;

public class UserInformation {
    public static final String GENDER_MALE = "male";
    public static final String GENDER_FEMALE = "female";
    @Key
	private Long userId;
	@Key
    private String nickname = null;
	@Key
    private String gender = null;
	@Key
    private String birthday = null;
	@Key
    private int gameWins = 0;
	@Key
    private int gameDraws = 0;
	@Key
    private int gameLosts = 0;
	@Key
    private int rankPoints = 0;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

    public void setBirthday(String birthday){
        this.birthday = birthday;
    }

	public String getBirthday() {
		return birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getGameWins() {
		return gameWins;
	}

	public void setGameWins(int gameWins) {
		this.gameWins = gameWins;
	}

	public int getGameDraws() {
		return gameDraws;
	}

	public void setGameDraws(int gameDraws) {
		this.gameDraws = gameDraws;
	}

	public int getGameLosts() {
		return gameLosts;
	}

	public void setGameLosts(int gameLosts) {
		this.gameLosts = gameLosts;
	}

	public int getRankPoints() {
		return rankPoints;
	}

	public void setRankPoints(int rankPoints) {
		this.rankPoints = rankPoints;
	}

}
