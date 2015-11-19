package otakuplus.straybird.othellogame.applicationstates;

public class ApplicationConnectState implements ApplicationState {

    public void initialize() {

    }

    public void connect() {
        ApplicationContext applicationContext = ApplicationContextSingleton
                .getInstance();
        applicationContext.socketIOClient.connect();
        applicationContext.changeState(ApplicationStateSingleton
                .getLoginStateInstance());
    }

    public void login() {

    }

    public void enterGameHall() {

    }

    public void leaveGameHall() {

    }

    public void enterGameTable(Integer gameTableId, Integer seatId) {

    }

    public void leaveGameTable(Integer gameTableId, Integer seatId) {

    }

    public void logout() {

    }

    public void disconnect() {

    }

    public void destory() {

    }

}
