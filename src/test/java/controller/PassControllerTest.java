package controller;

import controller.game.PassController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import service.game.PassService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PassControllerTest {

    @Mock
    private PassService passService;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private PassController passController;

    @Test
    public void passTest() throws IOException {
        passController.pass(response);
        verify(passService).pass();
        verify(response).sendRedirect("/poker/game");
    }

    @Test
    public void passWithoutGameTest() throws IOException {
        doThrow(NullPointerException.class).when(passService).pass();

        passController.pass(response);

        verify(response).sendRedirect("/poker/main");
        verify(passService).pass();
        verifyNoMoreInteractions(response);
    }
}
