package controller;

import collections.Games;
import controller.game.GameController;
import entity.Game;
import entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;
import service.game.GameService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Games.class)
public class GameControllerTest {
    @Mock
    private UserService userService;

    @Mock
    private User user;

    @Mock
    private Integer i;

    @Mock
    private Map<Integer, Game> map;

    @Mock
    private Game game;
    @Mock
    private GameService gameService;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private GameController gameController;

    @Test
    public void gamePageTest() throws IOException {
        mockStatic(Games.class);
        PowerMockito.when(gameService.isInGame()).thenReturn(true);
        PowerMockito.when(userService.getUser()).thenReturn(user);
        PowerMockito.when(user.getGameId()).thenReturn(i);
        PowerMockito.when(Games.getGame()).thenReturn(map);
        PowerMockito.when(map.get(i)).thenReturn(game);

        ModelAndView test = gameController.gamePage(response);

        assertNotNull(test);
        assertEquals(2, test.getModel().size());
        assertEquals("game", test.getViewName());
    }

    @Test
    public void mainPageNoInGameTest() throws IOException {
        PowerMockito.when(gameService.isInGame()).thenReturn(false);

        ModelAndView model = gameController.gamePage(response);

        assertNull(model);
        verify(response).sendRedirect("/poker/main");
    }

    @Test
    public void increasingRate() throws IOException {
        gameController.increasingRate(i, response);

        verify(gameService).increasingRate(i);
        verify(response).sendRedirect("/poker/game");
    }

    @Test
    public void increasingRateWithoutGameTest() throws IOException {
        doThrow(NullPointerException.class).when(gameService).increasingRate(anyInt());

        gameController.increasingRate(anyInt(), response);

        verify(response).sendRedirect("/poker/main");
        verify(gameService).increasingRate(anyInt());
        verifyNoMoreInteractions(response);
    }
}
