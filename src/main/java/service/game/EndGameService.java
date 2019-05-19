package service.game;

import collections.Games;
import entity.Game;
import entity.User;
import org.springframework.stereotype.Component;
import service.UserService;

import java.util.Optional;

@Component
public class EndGameService {
    private final UserService userService;
    private final AnalysisOfCombinationsService combinationsService;

    public EndGameService(AnalysisOfCombinationsService combinationsService, UserService userService) {
        this.combinationsService = combinationsService;
        this.userService = userService;
    }

    void endGame() throws NullPointerException {
        Optional<Game> optionalGame = Optional.of(Games.getGame().get(userService.getUser().getGameId()));
        Game g = optionalGame.orElseThrow(NullPointerException::new);

        User player1 = g.getPlayer1();
        User player2 = g.getPlayer2();
        combinationsService.analysis();
        if (g.isFirstWin() && g.isSecondWin()) {
            player1.setMoneyInGame(player1.getMoneyInGame() + g.getBank() / 2);
            player2.setMoneyInGame(player2.getMoneyInGame() + g.getBank() / 2);

        } else if (g.isFirstWin()) {
            player1.setMoneyInGame(player1.getMoneyInGame() + g.getBank());
        } else if (g.isSecondWin()) {
            player2.setMoneyInGame(player2.getMoneyInGame() + g.getBank());
        }
        g.setBank(0);
    }
}
