package service;

import collections.Cards;
import collections.Games;
import collections.PlayersGame;
import collections.Tables;
import entity.Card;
import entity.Game;
import entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MainService {

    public void addUserOnTable(int id, User user) {
        switch (id) {
            case 1:
                Tables.getTable1().add(user);
                break;
            case 2:
                Tables.getTable2().add(user);
                break;
            case 3:
                Tables.getTable3().add(user);
        }
    }

    public boolean isInTable(User user) {
        for (List<User> list : Tables.getTables()) {
            if (list.contains(user)) {
                return true;
            }
        }
        return false;
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public int numberOfTable(User user) {
        if (Tables.getTable1().contains(user)) {
            return 1;
        }
        if (Tables.getTable2().contains(user)) {
            return 2;
        }
        if (Tables.getTable3().contains(user)) {
            return 3;
        }
        return 0;
    }

    public Collection<User> tableOfUser(int id) {
        if (id == 1) {
            return Tables.getTable1();
        } else if (id == 2) {
            return Tables.getTable2();
        } else return Tables.getTable3();
    }

    public void deleteFromTable(User user) {
        for (List<User> list : Tables.getTables()) {
            list.remove(user);
        }
    }

    public void creatingGame(int id) {
        Game game = null;
        User p1 = null;
        User p2 = null;
        switch (id) {
            case 1:
                if (Tables.size1() > 1) {
                    game = new Game();
                    p2 = Tables.getTable1().remove(1);
                    p1 = Tables.getTable1().remove(0);
                    game.setPlayer1(p1);
                    game.setPlayer2(p2);
                    game.setMinRate(25);
                    game.setFirstRate(50);
                } else {
                    return;
                }
                break;
            case 2:
                if (Tables.size2() > 1) {
                    game = new Game();
                    p2 = Tables.getTable2().remove(1);
                    p1 = Tables.getTable2().remove(0);
                    game.setPlayer1(p1);
                    game.setPlayer2(p2);
                    game.setMinRate(50);
                    game.setFirstRate(100);
                } else {
                    return;
                }
                break;
            case 3:
                if (Tables.size3() > 1) {
                    game = new Game();
                    p2 = Tables.getTable3().remove(1);
                    p1 = Tables.getTable3().remove(0);
                    game.setPlayer1(p1);
                    game.setPlayer2(p2);
                    game.setMinRate(100);
                    game.setFirstRate(200);
                } else {
                    return;
                }
        }
        List<Card> deck = Cards.getDeck();
        List<Card> cards1 = new LinkedList<>();
        List<Card> cards2 = new LinkedList<>();
        List<Card> onTable = new LinkedList<>();
        Random random = new Random();
        Collections.addAll(cards1, deck.remove(random.nextInt(deck.size())), deck.remove(random.nextInt(deck.size())));
        Collections.addAll(cards2, deck.remove(random.nextInt(deck.size())), deck.remove(random.nextInt(deck.size())));
        for (int i = 0; i < 5; i++) {
            onTable.add(deck.remove(random.nextInt(deck.size())));
        }
        game.setCards1(cards1);
        game.setCards2(cards2);
        game.setOnTable(onTable);
        int idGame = new Random().nextInt();
        game.setId(idGame);
        PlayersGame.getPlayersInGame().add(p1);
        PlayersGame.getPlayersInGame().add(p2);
        p1.setGameId(idGame);
        p2.setGameId(idGame);
        Games.getGame().put(idGame, game);
    }

    public boolean isInGame(User user) {
        return PlayersGame.getPlayersInGame().contains(user);
    }

    public void setMoneyOnGame(int money, User user) {
        if (user.getMoneyInGame() > 0) {
            user.setMoney(user.getMoney() + user.getMoneyInGame());
        }
        user.setMoneyInGame(money);
        user.setMoney(user.getMoney() - user.getMoneyInGame());
    }
}


