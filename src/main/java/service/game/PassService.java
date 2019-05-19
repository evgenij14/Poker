package service.game;

import collections.Games;
import entity.Game;
import entity.User;
import org.springframework.stereotype.Component;
import service.UserService;

import java.util.Optional;

@Component
public class PassService {
    private final UserService userService;

    public PassService(UserService userService) {
        this.userService = userService;
    }

    public void pass() throws NullPointerException {
        User me = userService.getUser();
        Optional<Game> optionalGame = Optional.of(Games.getGame().get(userService.getUser().getGameId()));
        Game g = optionalGame.orElseThrow(NullPointerException::new);

        if (me.equals(g.getPlayer1())) {
            g.setFirstMove(true);
            g.setSecondMove(true);
            g.setSecondWin(true);
            g.setBank(g.getBank() + g.getRate1() + g.getRate2());
            g.setRate1(0);
            g.setRate2(0);
            g.getPlayer2().setMoneyInGame(g.getPlayer2().getMoneyInGame() + g.getBank());
            g.setBank(0);
        } else if (me.equals(g.getPlayer2())) {
            g.setSecondMove(true);
            g.setFirstMove(true);
            g.setFirstWin(true);
            g.setBank(g.getBank() + g.getRate2() + g.getRate1());
            g.setRate2(0);
            g.setRate1(0);
            g.getPlayer1().setMoneyInGame(g.getPlayer1().getMoneyInGame() + g.getBank());
            g.setBank(0);
        }
    }
}
