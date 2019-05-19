package controller;

import controller.game.AllInController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import service.game.AllInService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AllInControllerTest {

    @Mock
    private AllInService allInService;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private AllInController allInController;

    @Test
    public void allInTest() throws IOException {
        allInController.allIn(response);
        verify(allInService).allIn();
        verify(response).sendRedirect("/poker/game");
    }

    @Test
    public void allInExceptionTest() throws IOException {
        doThrow(NullPointerException.class).when(allInService).allIn();

        allInController.allIn(response);

        verify(response).sendRedirect("/poker/main");
        verify(allInService).allIn();
        verify(response, never()).sendRedirect("/poker/game");
    }
}
