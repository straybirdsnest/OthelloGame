package otakuplus.straybird.othellogame;

public class ApplicationContextSingleton {
	private static class ApplicationContextHolder{
		static final Application INSTANCE = new Application();
	}
	
	public static Application getInstance(){
		return ApplicationContextHolder.INSTANCE;
	}
}
