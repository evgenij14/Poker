package service;

import collections.Cards;
import collections.Games;
import collections.PlayersGame;
import collections.Tables;
import entity.Card;
import entity.Game;
import entity.User;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.*;


@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "collections.*")
public class MainServiceTest {
    @Mock
    private Game game;

    @Mock
    private User user2;

    @Mock
    private Collection<List<User>> col;

    @Mock
    private User user;

    @Mock
    private LinkedList<Card> cards1;

    @Mock
    private LinkedList<Card> cards2;

    @Mock
    private LinkedList<Card> onTable;

    @Mock
    private List<Card> deck;

    @Mock
    private List<User> users;

    @Mock
    private List<User> users2;

    @Mock
    private List<User> users3;

    @Mock
    private Integer idGame;

    @InjectMocks
    private MainService mainService;

    @Test
    public void addUserOnTable1Test() {
        mockStatic(Tables.class);
        PowerMockito.when(Tables.getTable1()).thenReturn(users);
        mainService.addUserOnTable(1, user);

        verifyStatic(times(1));
        users.add(user);
    }

    @Test
    public void addUserOnTable2Test() {
        mockStatic(Tables.class);
        PowerMockito.when(Tables.getTable2()).thenReturn(users);
        mainService.addUserOnTable(2, user);

        verifyStatic(times(1));
        users.add(user);
    }

    @Test
    public void addUserOnTable3Test() {
        mockStatic(Tables.class);
        PowerMockito.when(Tables.getTable3()).thenReturn(users);
        mainService.addUserOnTable(3, user);

        verifyStatic(times(1));
        users.add(user);
    }


    @Test
    public void number1OfTableTest() {
        mockStatic(Tables.class);
        PowerMockito.when(Tables.getTable1()).thenReturn(users);
        PowerMockito.when(Tables.getTable1().contains(user)).thenReturn(true);

        int i = mainService.numberOfTable(user);
        assertEquals(i, 1);
    }

    @Test
    public void number2OfTableTableTest() {
        mockStatic(Tables.class);
        PowerMockito.when(Tables.getTable2()).thenReturn(users);
        PowerMockito.when(Tables.getTable2().contains(user)).thenReturn(true);

        int i = mainService.numberOfTable(user);
        assertEquals(i, 2);
    }

    @Test
    public void numberOfTableTableTest() {
        mockStatic(Tables.class);
        PowerMockito.when(Tables.getTable3()).thenReturn(users);
        PowerMockito.when(Tables.getTable3().contains(user)).thenReturn(true);

        int i = mainService.numberOfTable(user);
        assertEquals(i, 3);
    }

    @Test
    public void failNumberOfTableTableTest() {
        mockStatic(Tables.class);
        PowerMockito.when(Tables.getTable1()).thenReturn(users);
        PowerMockito.when(Tables.getTable2()).thenReturn(users2);
        PowerMockito.when(Tables.getTable3()).thenReturn(users3);
        PowerMockito.when(Tables.getTable1().contains(user)).thenReturn(false);
        PowerMockito.when(Tables.getTable2().contains(user)).thenReturn(false);
        PowerMockito.when(Tables.getTable3().contains(user)).thenReturn(false);

        int i = mainService.numberOfTable(user);
        assertEquals(i, 0);
    }

    @Test
    public void table1OfUserTest() {
        mockStatic(Tables.class);
        PowerMockito.when(Tables.getTable1()).thenReturn(users);

        Collection<User> out = mainService.tableOfUser(1);
        assertEquals(out, users);
    }

    @Test
    public void table2OfUserTest() {
        mockStatic(Tables.class);
        PowerMockito.when(Tables.getTable2()).thenReturn(users2);

        Collection<User> out = mainService.tableOfUser(2);
        assertEquals(out, users2);
    }

    @Test
    public void table3OfUserTest() {
        mockStatic(Tables.class);
        PowerMockito.when(Tables.getTable3()).thenReturn(users3);

        Collection<User> out = mainService.tableOfUser(3);
        assertEquals(out, users3);
    }

    @Test
    public void setNotZeroMoneyInGameTest() {
        Mockito.when(user.getMoneyInGame()).thenReturn(1000);

        mainService.setMoneyOnGame(200, user);

        verify(user, times(2)).setMoney(anyInt());
        verify(user).setMoneyInGame(anyInt());
        verify(user, times(3)).getMoneyInGame();
        verify(user, times(2)).getMoney();
    }

    @Test
    public void setZeroMoneyInGameTest() {
        Mockito.when(user.getMoneyInGame()).thenReturn(0);

        mainService.setMoneyOnGame(200, user);

        verify(user).setMoney(anyInt());
        verify(user).setMoneyInGame(anyInt());
        verify(user, times(2)).getMoneyInGame();
        verify(user).getMoney();
    }

    @Ignore
    @Test
    public void creatingGameOnFirstTableTest() throws Exception {
        mockStatic(Tables.class);
        mockStatic(Cards.class);
        mockStatic(PlayersGame.class);
        mockStatic(Games.class);
        PowerMockito.when(Tables.size1()).thenReturn(2);
        whenNew(Game.class).withNoArguments().thenReturn(game);
        PowerMockito.when(Cards.getDeck()).thenReturn(deck);
        PowerMockito.when(Tables.getTable1()).thenReturn(users);
        PowerMockito.when(Tables.getTable1().remove(1)).thenReturn(user);
        PowerMockito.when(Tables.getTable1().remove(0)).thenReturn(user2);
        whenNew(LinkedList.class).withNoArguments().thenReturn(cards1).thenReturn(cards2).thenReturn(onTable);


        mainService.creatingGame(1);

        verifyNew(Game.class).withNoArguments();
        verify(game).setPlayer1(user);
        verify(game).setPlayer2(user2);
        verify(game).setMinRate(25);
        verify(game).setFirstRate(50);

        verifyStatic(times(1));
        Cards.getDeck();

        verifyNew(LinkedList.class, times(3));
        assertThat(cards1).hasSize(2);
        assertThat(cards2).hasSize(2);
        assertThat(onTable).hasSize(5);
        verify(game).setCards1(cards1);
        verify(game).setCards2(cards2);
        verify(game).setOnTable(onTable);
        verify(game).setId(anyInt());

        verifyStatic(times(1));
        PlayersGame.getPlayersInGame().add(user);
        PlayersGame.getPlayersInGame().add(user2);

        verify(user).setGameId(anyInt());
        verify(user2).setGameId(anyInt());

        verifyStatic(times(1));
        Games.getGame().put(eq(anyInt()), game);

    }
}
