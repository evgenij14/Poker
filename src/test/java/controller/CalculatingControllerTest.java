package controller;

import collections.Games;
import collections.PlayersGame;
import controller.game.CalculatingController;
import entity.Game;
import entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import service.UserService;
import service.game.CalculatingService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Games.class, PlayersGame.class})
public class CalculatingControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private CalculatingService calculatingService;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Game game;

    @Mock
    private User user;

    @Mock
    private Integer i;

    @Mock
    private Map<Integer, Game> map;

    @Mock
    private List<User> list;

    @InjectMocks
    private CalculatingController controller;

    @Before
    public void before() {
        mockStatic(Games.class);
        mockStatic(PlayersGame.class);
        PowerMockito.when(PlayersGame.getPlayersInGame()).thenReturn(list);
        PowerMockito.when(userService.getUser()).thenReturn(user);
        PowerMockito.when(user.getGameId()).thenReturn(i);
        PowerMockito.when(Games.getGame()).thenReturn(map);
        PowerMockito.when(map.get(i)).thenReturn(game);
    }

    @Test
    public void calculatingFirstOutTest() throws IOException {
        PowerMockito.when(game.isFirstOut()).thenReturn(false);

        controller.calculating(response);

        verify(calculatingService).calculatePoints();
        verify(game).setFirstOut(true);
        verify(response).sendRedirect("/poker/main");

        verifyStatic(times(1));
        list.remove(user);
    }

    @Test
    public void calculatingSecondOutTest() throws IOException {
        PowerMockito.when(game.isFirstOut()).thenReturn(true);

        controller.calculating(response);

        verify(response).sendRedirect("/poker/main");
        verify(calculatingService, never()).calculatePoints();
        verifyStatic();
        list.remove(user);
        map.remove(i);
    }

    @Test
    public void calculatingWithNullGameTest() throws IOException {
        PowerMockito.when(game.isFirstOut()).thenReturn(false);
        doThrow(NullPointerException.class).when(calculatingService).calculatePoints();

        controller.calculating(response);

        verify(response).sendRedirect("/poker/main");
        verify(calculatingService).calculatePoints();
        PowerMockito.verifyNoMoreInteractions(response);
    }

}
