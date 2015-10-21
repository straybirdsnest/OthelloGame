package otakuplus.straybird.othellogame.network.http;

import com.google.api.client.util.Key;
import otakuplus.straybird.othellogame.models.GameTable;

import java.util.ArrayList;

public class GameTableList {
    @Key
    private ArrayList<GameTable> gameTables;

    public ArrayList<GameTable> getGameTables() {
        return gameTables;
    }

    public void setGameTables(ArrayList<GameTable> gameTables) {
        this.gameTables = gameTables;
    }
}
