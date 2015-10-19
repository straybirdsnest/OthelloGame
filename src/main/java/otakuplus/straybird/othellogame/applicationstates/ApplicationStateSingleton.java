package otakuplus.straybird.othellogame.applicationstates;

public class ApplicationStateSingleton {

	private static class InitStateHolder {
		static final ApplicationIntializeState INSTANCE = new ApplicationIntializeState();
	}

	public static ApplicationIntializeState getInitStateInstance() {
		return InitStateHolder.INSTANCE;
	}

	private static class ConnectStateHolder {
		static final ApplicationConnectState INSTANCE = new ApplicationConnectState();
	}

	public static ApplicationConnectState getConnectStateInstance() {
		return ConnectStateHolder.INSTANCE;
	}

	private static class LoginStateHolder {
		static final ApplicationLoginState INSTANCE = new ApplicationLoginState();
	}

	public static ApplicationLoginState getLoginStateInstance() {
		return LoginStateHolder.INSTANCE;
	}

	private static class EnterGameHallStateHolder {
		static final ApplicationEnterGameHallState INSTANCE = new ApplicationEnterGameHallState();
	}

	public static ApplicationEnterGameHallState getEnterGameHallStateInstance() {
		return EnterGameHallStateHolder.INSTANCE;
	}

	private static class LeaveGameHallStateHolder {
		static final ApplicationLeaveGameHallState INSTANCE = new ApplicationLeaveGameHallState();
	}

	public static ApplicationLeaveGameHallState getLeaveGameHallStateInstance() {
		return LeaveGameHallStateHolder.INSTANCE;
	}

	private static class EnterGameTableStateHolder{
        static final ApplicationEnterGameTableState INSTANCE = new ApplicationEnterGameTableState();
    }

    public static ApplicationEnterGameTableState getEnterGameTableStateInstance(){
        return EnterGameTableStateHolder.INSTANCE;
    }

    private static class LeaveGameTableStateHolder{
        static final ApplicationLeaveGameTableState INSTANCE = new ApplicationLeaveGameTableState();
    }

    public static ApplicationLeaveGameTableState getLeaveGameTableStateInstance(){
        return LeaveGameTableStateHolder.INSTANCE;
    }

    private static class LogoutStateHolder{
		static final ApplicationLogoutState INSTANCE = new ApplicationLogoutState();
	}

	public static ApplicationLogoutState getLogoutStateInstance(){
		return LogoutStateHolder.INSTANCE;
	}

	private static class DisconnectStateHolder {
		static final ApplicationDisconnectState INSTANCE = new ApplicationDisconnectState();
	}

	public static ApplicationDisconnectState getDisconnectStateInstance() {
		return DisconnectStateHolder.INSTANCE;
	}

	private static class DestoryStateHolder {
		static final ApplicationDestoryState INSTANCE = new ApplicationDestoryState();
	}

	public static ApplicationDestoryState getDestoryStateInstance() {
		return DestoryStateHolder.INSTANCE;
	}

}
