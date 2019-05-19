package collections;

import entity.Game;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Games {
    private static final Map<Integer, Game> games = new ConcurrentHashMap<>();

    public static Map<Integer, Game> getGame() {
        return games;
    }
}
