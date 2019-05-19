package collections;

import entity.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PlayersGame {
    private static final List<User> playersInGame = new CopyOnWriteArrayList<>();

    public static List<User> getPlayersInGame() {
        return playersInGame;
    }
}
