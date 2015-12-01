package otakuplus.straybird.othellogame.applicationstates.game;

public class GameStateSingleton {
    public static GameNoReadyState getGameNoReadyStateInstance() {
        return NoReadyHolder.INSTANCE;
    }

    public static GameBlackReadyState getGameBlackReadyStateInstance() {
        return BlackReadyHolder.INSTANCE;
    }

    public static GameWhiteReadyState getGameWhiteReadyStateInstance() {
        return WhiteReadyHolder.INSTANCE;
    }

    public static GameWhiteSetState getGameWhiteSetStateInstance() {
        return WhiteSetHolder.INSTANCE;
    }

    public static GameBlackSetState getGameBlackSetStateInstance() {
        return BlackSetHolder.INSTANCE;
    }

    private static class NoReadyHolder {
        static final GameNoReadyState INSTANCE = new GameNoReadyState();
    }

    private static class BlackReadyHolder {
        static final GameBlackReadyState INSTANCE = new GameBlackReadyState();
    }

    private static class WhiteReadyHolder {
        static final GameWhiteReadyState INSTANCE = new GameWhiteReadyState();
    }

    private static class WhiteSetHolder {
        static final GameWhiteSetState INSTANCE = new GameWhiteSetState();
    }

    private static class BlackSetHolder {
        static final GameBlackSetState INSTANCE = new GameBlackSetState();
    }

}
