package service.game;

import collections.Games;
import dao.HibernateUserDao;
import entity.Game;
import entity.User;
import org.springframework.stereotype.Component;
import service.UserService;

import java.util.Optional;

@Component
public class CalculatingService {
    private final UserService userService;
    private final HibernateUserDao hibernateUserDao;

    public CalculatingService(UserService userService, HibernateUserDao hibernateUserDao) {
        this.userService = userService;
        this.hibernateUserDao = hibernateUserDao;
    }

    public void calculatePoints() throws NullPointerException {
        Optional<Game> optionalGame = Optional.of(Games.getGame().get(userService.getUser().getGameId()));
        Game g = optionalGame.orElseThrow(NullPointerException::new);

        User player1 = g.getPlayer1();
        User player2 = g.getPlayer2();
        if (g.isFirstWin() && g.isSecondWin()) {
            player1.setWins(player1.getWins() + 1);
            player2.setWins(player2.getWins() + 1);
        } else if (g.isFirstWin()) {
            player1.setWins(player1.getWins() + 1);
            player2.setLoses(player2.getLoses() + 1);
        } else if (g.isSecondWin()) {
            player2.setWins(player2.getWins() + 1);
            player1.setLoses(player1.getLoses() + 1);
        }
        player1.setGames(player1.getGames() + 1);
        player2.setGames(player2.getGames() + 1);
        player1.setWinRate((int) Math.round((double) player1.getWins() / (double) player1.getGames() * 100));
        player2.setWinRate((int) Math.round((double) player2.getWins() / (double) player2.getGames() * 100));
        player1.setMoney(player1.getMoney() + player1.getMoneyInGame());
        player2.setMoney(player2.getMoney() + player2.getMoneyInGame());
        player1.setMoneyInGame(0);
        player2.setMoneyInGame(0);
        hibernateUserDao.update(player1);
        hibernateUserDao.update(player2);
    }
}
