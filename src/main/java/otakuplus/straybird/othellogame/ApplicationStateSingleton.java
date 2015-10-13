package otakuplus.straybird.othellogame;

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

	private static class GameHallStateHolder {
		static final ApplicationGameHallState INSTANCE = new ApplicationGameHallState();
	}

	public static ApplicationGameHallState getGameHallStateInstance() {
		return GameHallStateHolder.INSTANCE;
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
