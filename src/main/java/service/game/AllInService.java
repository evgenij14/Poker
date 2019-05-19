package service.game;

import collections.Games;
import entity.Game;
import entity.User;
import org.springframework.stereotype.Component;
import service.UserService;

import java.util.Optional;

@Component
public class AllInService {
    private final EndGameService endGameService;
    private final UserService userService;

    public AllInService(UserService userService, EndGameService endGameService) {
        this.userService = userService;
        this.endGameService = endGameService;
    }

    public void allIn() throws NullPointerException {
        User me = userService.getUser();
        Optional<Game> optionalGame = Optional.of(Games.getGame().get(me.getGameId()));
        Game g = optionalGame.orElseThrow(NullPointerException::new);

        User player1 = g.getPlayer1();
        User player2 = g.getPlayer2();
        if (me.equals(player1)) {
            g.setRate1(player1.getMoneyInGame() + g.getRate1());
            player1.setMoneyInGame(0);
            g.setFirstMove(true);
            if (g.getRate2() < g.getRate1() && g.isSecondMove() && player2.getMoneyInGame() > 0) {
                g.setSecondMove(false);
            } else if (g.getRate2() < g.getRate1() && g.isSecondMove() && player2.getMoneyInGame() == 0) {
                player1.setMoneyInGame(g.getRate1() - g.getRate2());
                g.setRate1(g.getRate2());
            } else if (g.getRate2() > g.getRate1() && g.isSecondMove()) {
                player2.setMoneyInGame(g.getRate2() - g.getRate1());
                g.setRate2(g.getRate1());
            }
        } else if (me.equals(player2)) {
            g.setRate2(player2.getMoneyInGame() + g.getRate2());
            player2.setMoneyInGame(0);
            g.setSecondMove(true);
            if (g.getRate1() < g.getRate2() && g.isFirstMove() && player1.getMoneyInGame() > 0) {
                g.setFirstMove(false);
            } else if (g.getRate1() < g.getRate2() && g.isFirstMove() && player1.getMoneyInGame() == 0) {
                player2.setMoneyInGame(g.getRate2() - g.getRate1());
                g.setRate2(g.getRate1());
            } else if (g.getRate1() > g.getRate2() && g.isFirstMove()) {
                player1.setMoneyInGame(g.getRate1() - g.getRate2());
                g.setRate1(g.getRate2());
            }
        }
        if (g.isSecondMove() && g.isFirstMove()) {
            g.setBank(g.getBank() + g.getRate2() + g.getRate1());
            g.setNumOfMove(4);
            g.setRate2(0);
            g.setRate1(0);
            endGameService.endGame();
        }
    }
}
