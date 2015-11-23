package otakuplus.straybird.othellogame.applicationstates;

public interface ApplicationState {

    public void initialize();

    public void connect();

    public void login();

    public void enterGameHall();

    public void leaveGameHall();

    public void enterGameTable(Integer gameTableId, Integer seatId);

    public void leaveGameTable(Integer gameTableId, Integer seatId);

    public void giveUp();

    public void logout();

    public void disconnect();

    public void destory();

}
