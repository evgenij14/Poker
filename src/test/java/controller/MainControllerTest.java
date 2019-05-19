package controller;

import collections.PlayersGame;
import entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.web.servlet.ModelAndView;
import service.MainService;
import service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PlayersGame.class)
public class MainControllerTest {
    @Mock
    private UserService userService;

    @Mock
    private Collection<User> col;

    @Mock
    private MainService mainService;

    @Mock
    private List<User> list;

    @Mock
    private User user;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private MainController mainController;

    @Before
    public void before() {
        mockStatic(PlayersGame.class);
        when(PlayersGame.getPlayersInGame()).thenReturn(list);
        when(userService.getUser()).thenReturn(user);
    }

    @Test
    public void mainPageTest() throws IOException {
        when(list.contains(user)).thenReturn(false);

        ModelAndView model = mainController.mainPage(response);

        assertNotNull(model);
        assertEquals("main", model.getViewName());
        assertEquals(7, model.getModel().size());
    }

    @Test
    public void mainPageAlreadyInGameTest() throws IOException {
        when(list.contains(user)).thenReturn(true);

        ModelAndView model = mainController.mainPage(response);

        assertNull(model);
        Mockito.verify(response).sendRedirect("/poker/game");
    }

    @Test
    public void addTablesWithoutCreatingGame() throws IOException {
        when(mainService.tableOfUser(3)).thenReturn(col);
        when(col.size()).thenReturn(1);

        mainController.addTables(3, response);

        Mockito.verify(mainService).addUserOnTable(3, user);
        Mockito.verify(response).sendRedirect("/poker/main");
    }

    @Test
    public void addTablesWithCreatingGame() throws IOException {
        when(mainService.tableOfUser(3)).thenReturn(col);
        when(col.size()).thenReturn(2);

        mainController.addTables(3, response);

        Mockito.verify(mainService).addUserOnTable(3, user);
        Mockito.verify(response).sendRedirect("/poker/main");
        Mockito.verify(mainService).creatingGame(3);
    }

    @Test
    public void deleteFromTable() throws IOException {
        mainController.deleteFromTable(response);

        Mockito.verify(mainService).deleteFromTable(user);
        Mockito.verify(response).sendRedirect("/poker/main");
    }

    @Test
    public void addingMoneyTest() throws IOException {
        mainController.addingMoney(1000, response);

        Mockito.verify(mainService).setMoneyOnGame(1000, user);
        Mockito.verify(response).sendRedirect("/poker/main");
    }

}
