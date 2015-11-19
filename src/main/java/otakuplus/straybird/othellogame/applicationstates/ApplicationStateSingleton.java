package otakuplus.straybird.othellogame.applicationstates;

public class ApplicationStateSingleton {

    public static ApplicationIntializeState getInitStateInstance() {
        return InitStateHolder.INSTANCE;
    }

    public static ApplicationConnectState getConnectStateInstance() {
        return ConnectStateHolder.INSTANCE;
    }

    public static ApplicationLoginState getLoginStateInstance() {
        return LoginStateHolder.INSTANCE;
    }

    public static ApplicationEnterGameHallState getEnterGameHallStateInstance() {
        return EnterGameHallStateHolder.INSTANCE;
    }

    public static ApplicationLeaveGameHallState getLeaveGameHallStateInstance() {
        return LeaveGameHallStateHolder.INSTANCE;
    }

    public static ApplicationEnterGameTableState getEnterGameTableStateInstance() {
        return EnterGameTableStateHolder.INSTANCE;
    }

    public static ApplicationLeaveGameTableState getLeaveGameTableStateInstance() {
        return LeaveGameTableStateHolder.INSTANCE;
    }

    public static ApplicationLogoutState getLogoutStateInstance() {
        return LogoutStateHolder.INSTANCE;
    }

    public static ApplicationDisconnectState getDisconnectStateInstance() {
        return DisconnectStateHolder.INSTANCE;
    }

    public static ApplicationDestoryState getDestoryStateInstance() {
        return DestoryStateHolder.INSTANCE;
    }

    private static class InitStateHolder {
        static final ApplicationIntializeState INSTANCE = new ApplicationIntializeState();
    }

    private static class ConnectStateHolder {
        static final ApplicationConnectState INSTANCE = new ApplicationConnectState();
    }

    private static class LoginStateHolder {
        static final ApplicationLoginState INSTANCE = new ApplicationLoginState();
    }

    private static class EnterGameHallStateHolder {
        static final ApplicationEnterGameHallState INSTANCE = new ApplicationEnterGameHallState();
    }

    private static class LeaveGameHallStateHolder {
        static final ApplicationLeaveGameHallState INSTANCE = new ApplicationLeaveGameHallState();
    }

    private static class EnterGameTableStateHolder {
        static final ApplicationEnterGameTableState INSTANCE = new ApplicationEnterGameTableState();
    }

    private static class LeaveGameTableStateHolder {
        static final ApplicationLeaveGameTableState INSTANCE = new ApplicationLeaveGameTableState();
    }

    private static class LogoutStateHolder {
        static final ApplicationLogoutState INSTANCE = new ApplicationLogoutState();
    }

    private static class DisconnectStateHolder {
        static final ApplicationDisconnectState INSTANCE = new ApplicationDisconnectState();
    }

    private static class DestoryStateHolder {
        static final ApplicationDestoryState INSTANCE = new ApplicationDestoryState();
    }

}
