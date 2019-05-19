package service.game;

import collections.Games;
import entity.Game;
import entity.User;
import org.springframework.stereotype.Component;
import service.UserService;

import java.util.Optional;

@Component
public class CheckService {
    private final EndGameService endGameService;
    private final UserService userService;

    public CheckService(UserService userService, EndGameService endGameService) {
        this.userService = userService;
        this.endGameService = endGameService;
    }

    public void check() throws NullPointerException {
        Optional<Game> optionalGame = Optional.of(Games.getGame().get(userService.getUser().getGameId()));
        Game g = optionalGame.orElseThrow(NullPointerException::new);
        User me = userService.getUser();

        User player1 = g.getPlayer1();
        User player2 = g.getPlayer2();
        if (me.equals(player1)) {
            g.setFirstMove(true);
            player1.setMoneyInGame(player1.getMoneyInGame() + g.getRate1());
            g.setRate1(0);
            if (g.isSecondMove() && g.getRate2() == 0) {
                g.incrementNumOfMove();
                g.setSuccessMove(true);
                g.setFirstMove(false);
                g.setSecondMove(false);
            } else if (g.isSecondMove() && g.getRate2() > 0) {
                g.setFirstMove(false);
            }
        } else if (me.equals(player2)) {
            g.setSecondMove(true);
            player2.setMoneyInGame(player2.getMoneyInGame() + g.getRate2());
            g.setRate2(0);
            if (g.isFirstMove() && g.getRate1() == 0) {
                g.incrementNumOfMove();
                g.setSuccessMove(true);
                g.setFirstMove(false);
                g.setSecondMove(false);
            } else if (g.isFirstMove() && g.getRate1() > 0) {
                g.setSecondMove(false);
            }
        }
        if (g.getNumOfMove() >= 4) {
            endGameService.endGame();
            g.setFirstMove(true);
            g.setSecondMove(true);
        }
    }
}
