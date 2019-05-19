package controller;

import controller.game.CheckController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import service.game.CheckService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CheckControllerTest {
    @Mock
    private HttpServletResponse response;

    @Mock
    private CheckService checkService;

    @InjectMocks
    private CheckController checkController;

    @Test
    public void checkTest() throws IOException {
        checkController.check(response);
        verify(checkService).check();
        verify(response).sendRedirect("/poker/game");
    }

    @Test
    public void checkWithoutGameTest() throws IOException {
        doThrow(NullPointerException.class).when(checkService).check();

        checkController.check(response);

        verify(response).sendRedirect("/poker/main");
        verify(checkService).check();
        verifyNoMoreInteractions(response);
    }
}
