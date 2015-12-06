package otakuplus.straybird.othellogame.models;

import com.google.api.client.util.Key;

public class GameRecord {

    private int gameRecordId = 0;
    @Key
    private String playerA;
    @Key
    private String playerB;
    @Key
    private String gameBeginTime;
    @Key
    private String gameEndTime;
    @Key
    private int whiteNumber = 0;
    @Key
    private int blackNumber = 0;
    @Key
    private byte[] record;

    public int getGameRecordId() {
        return gameRecordId;
    }

    public void setGameRecordId(int gameRecordId) {
        this.gameRecordId = gameRecordId;
    }


    public String getPlayerA() {
        return playerA;
    }

    public void setPlayerA(String playerA) {
        this.playerA = playerA;
    }

    public String getPlayerB() {
        return playerB;
    }

    public void setPlayerB(String playerB) {
        this.playerB = playerB;
    }

    public String getGameBeginTime() {
        return gameBeginTime;
    }

    public void setGameBeginTime(String gameBeginTime) {
        this.gameBeginTime = gameBeginTime;
    }

    public String getGameEndTime() {
        return gameEndTime;
    }

    public void setGameEndTime(String gameEndTime) {
        this.gameEndTime = gameEndTime;
    }

    public int getWhiteNumber() {
        return whiteNumber;
    }

    public void setWhiteNumber(int whiteNumber) {
        this.whiteNumber = whiteNumber;
    }

    public int getBlackNumber() {
        return blackNumber;
    }

    public void setBlackNumber(int blackNumber) {
        this.blackNumber = blackNumber;
    }

    public byte[] getRecord() {
        return record;
    }

    public void setRecord(byte[] record) {
        this.record = record;
    }
}
