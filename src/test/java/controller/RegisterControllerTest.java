package controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RegisterControllerTest {
    @Mock
    private UserService userService;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private RegisterController registerController;

    @Test
    public void successRegistrationTest() throws IOException {
        when(userService.isAddUser(anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(true);
        registerController.registration(anyString(), anyString(), anyString(), anyString(), anyString(), response);
        verify(response).sendRedirect("/poker/login?registered");
    }

    @Test
    public void unSuccessRegistrationTest() throws IOException {
        when(userService.isAddUser(anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(false);
        registerController.registration(anyString(), anyString(), anyString(), anyString(), anyString(), response);
        verify(response).sendRedirect("/poker/register?invalid_email");
    }
}
