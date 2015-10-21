package otakuplus.straybird.othellogame.applicationstates;

public class GameContextSigleton {
    private static class GameContextHolder{
        static final GameContext INSTANCE = new GameContext();
    }

    public static GameContext getGameContextInstance(){
        return GameContextHolder.INSTANCE;
    }
}
