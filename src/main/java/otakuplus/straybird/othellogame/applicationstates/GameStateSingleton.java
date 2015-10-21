package otakuplus.straybird.othellogame.applicationstates;

public class GameStateSingleton {
    private static class NoReadyHolder{
        static final GameNoReadyState INSTANCE = new GameNoReadyState();
    }

    public static GameNoReadyState getGameNoReadyStateInstance(){
        return NoReadyHolder.INSTANCE;
    }

    private static class BlackReadyHolder{
        static final GameBlackReadyState INSTANCE = new GameBlackReadyState();
    }

    public static GameBlackReadyState getGameBlackReadyStateInstance(){
        return BlackReadyHolder.INSTANCE;
    }

    private static class WhiteReadyHolder{
        static final GameWhiteReadyState INSTANCE = new GameWhiteReadyState();
    }

    public static GameWhiteReadyState getGameWhiteReadyStateInstance(){
        return WhiteReadyHolder.INSTANCE;
    }

    private static class WhiteSetHolder{
        static final GameWhiteSetState INSTANCE = new GameWhiteSetState();
    }

    public static GameWhiteSetState getGameWhiteSetStateInstance(){
        return WhiteSetHolder.INSTANCE;
    }

    private static class BlackSetHolder{
        static final GameBlackSetState INSTANCE = new GameBlackSetState();
    }

    public static GameBlackSetState getGameBlackSetStateInstance(){
        return BlackSetHolder.INSTANCE;
    }
}
