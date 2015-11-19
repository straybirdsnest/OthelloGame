package otakuplus.straybird.othellogame.applicationstates;

public class ApplicationContextSingleton {
    public static ApplicationContext getInstance() {
        return ApplicationContextHolder.INSTANCE;
    }

    private static class ApplicationContextHolder {
        static final ApplicationContext INSTANCE = new ApplicationContext();
    }
}
