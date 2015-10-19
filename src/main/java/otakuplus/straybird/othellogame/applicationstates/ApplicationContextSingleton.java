package otakuplus.straybird.othellogame.applicationstates;

public class ApplicationContextSingleton {
	private static class ApplicationContextHolder{
		static final ApplicationContext INSTANCE = new ApplicationContext();
	}
	
	public static ApplicationContext getInstance(){
		return ApplicationContextHolder.INSTANCE;
	}
}
