package otakuplus.straybird.othellogame;

public class ApplicationStateSingleton {

	private static class InitStateHolder{
		static final ApplicationIntializeState INSTANCE = new ApplicationIntializeState();
	}
	
	public static ApplicationIntializeState getInitStateInstance(){
		return InitStateHolder.INSTANCE;
	}
	
	private static class LoginStateHolder{
		static final ApplicationIntializeState INSTANCE = new ApplicationIntializeState();
	}
	
	public static ApplicationIntializeState getLoginStateInstance(){
		return LoginStateHolder.INSTANCE;
	}
	
	private static class LogOutStateHolder{
		static final ApplicationIntializeState INSTANCE = new ApplicationIntializeState();
	}
	
	public static ApplicationIntializeState getLogOutStateInstance(){
		return LogOutStateHolder.INSTANCE;
	}
}
