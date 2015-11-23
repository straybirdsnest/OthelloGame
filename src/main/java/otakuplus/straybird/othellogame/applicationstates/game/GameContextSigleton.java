package otakuplus.straybird.othellogame.applicationstates.game;

public class GameContextSigleton {
    public static GameContext getGameContextInstance() {
        return GameContextHolder.INSTANCE;
    }

    private static class GameContextHolder {
        static final GameContext INSTANCE = new GameContext();
    }
}
