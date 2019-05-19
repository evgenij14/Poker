package controller;

import controller.game.MoveController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import service.game.MoveService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MoveControllerTest {
    @Mock
    private MoveService moveService;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private MoveController moveController;

    @Test
    public void successMovingTest() throws IOException {
        when(moveService.isMoveSuccess()).thenReturn(false);
        moveController.moving(response);
        verify(response).sendRedirect("/poker/game?lowRate");
    }

    @Test
    public void notSuccessMoveTest() throws IOException {
        when(moveService.isMoveSuccess()).thenReturn(true);
        moveController.moving(response);
        verify(response).sendRedirect("/poker/game");
    }

    @Test
    public void successMovingWithoutGameTest() throws IOException {
        doThrow(NullPointerException.class).when(moveService).isMoveSuccess();

        moveController.moving(response);

        verify(response).sendRedirect("/poker/main");
        verify(moveService).isMoveSuccess();
        verifyNoMoreInteractions(response);
    }
}
