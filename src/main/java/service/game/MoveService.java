package service.game;

import collections.Games;
import entity.Game;
import entity.User;
import org.springframework.stereotype.Component;
import service.UserService;

import java.util.Optional;

@Component
public class MoveService {
    private final EndGameService endGameService;
    private final UserService userService;

    public MoveService(UserService userService, EndGameService endGameService) {
        this.userService = userService;
        this.endGameService = endGameService;
    }

    public boolean isMoveSuccess() throws NullPointerException {
        User me = userService.getUser();
        Optional<Game> optionalGame = Optional.of(Games.getGame().get(me.getGameId()));
        Game g = optionalGame.orElseThrow(NullPointerException::new);

        if (me.equals(g.getPlayer1())) {
            if (g.getNumOfMove() > 0) {
                if (g.getRate1() >= g.getMinRate()) {
                    g.setFirstMove(true);
                } else {
                    return false;
                }
            } else {
                if (g.getRate1() >= g.getFirstRate()) {
                    g.setFirstMove(true);
                } else {
                    return false;
                }
            }
        } else if (me.equals(g.getPlayer2())) {
            if (g.getNumOfMove() > 0) {
                if (g.getRate2() >= g.getMinRate()) {
                    g.setSecondMove(true);
                } else {
                    return false;
                }
            } else {
                if (g.getRate2() >= g.getFirstRate()) {
                    g.setSecondMove(true);
                } else {
                    return false;
                }
            }
        }
        if (g.getRate1() == g.getRate2() && g.isFirstMove() && g.isSecondMove()) {
            g.setSuccessMove(true);
            g.incrementNumOfMove();
            g.setBank(g.getBank() + g.getRate1() + g.getRate2());
            g.setRate1(0);
            g.setRate2(0);
            if (g.getNumOfMove() >= 4) {
                endGameService.endGame();
                return true;
            } else {
                g.setSecondMove(false);
                g.setFirstMove(false);
            }
        } else if (g.getRate2() > g.getRate1() && g.isFirstMove() && g.isSecondMove()) {
            g.setFirstMove(false);
        } else if (g.isFirstMove() && g.isSecondMove()) {
            g.setSecondMove(false);
        }
        return true;
    }
}
