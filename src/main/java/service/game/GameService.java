package service.game;

import collections.Games;
import entity.Game;
import org.springframework.stereotype.Component;
import service.UserService;

import java.util.Optional;

@Component
public class GameService {
    private final UserService userService;

    public GameService(UserService userService) {
        this.userService = userService;
    }

    public void increasingRate(int id) throws NullPointerException {
        Optional<Game> optionalGame = Optional.of(Games.getGame().get(userService.getUser().getGameId()));
        Game g = optionalGame.orElseThrow(NullPointerException::new);

        if (g.getPlayer1().equals(userService.getUser())) {
            g.setRate1(g.getRate1() + id);
            g.getPlayer1().setMoneyInGame(g.getPlayer1().getMoneyInGame() - id);
        } else {
            g.setRate2(g.getRate2() + id);
            g.getPlayer2().setMoneyInGame(g.getPlayer2().getMoneyInGame() - id);
        }
    }

    public boolean isInGame() {
        return Games.getGame().containsKey(userService.getUser().getGameId());
    }
}