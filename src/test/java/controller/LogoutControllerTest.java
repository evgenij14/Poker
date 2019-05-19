package controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SecurityContextHolder.class)
public class LogoutControllerTest {
    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpServletRequest request;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private SecurityContextLogoutHandler securityContextLogoutHandler;

    @InjectMocks
    private LogoutController logoutController;

    @Test
    public void failLogoutPageTest() throws Exception {
        mockStatic(SecurityContextHolder.class);
        when(SecurityContextHolder.getContext()).thenReturn(securityContext);
        when(securityContext.getAuthentication()).thenReturn(null);
        whenNew(SecurityContextLogoutHandler.class).withNoArguments().thenReturn(securityContextLogoutHandler);

        logoutController.logoutPage(request, response);

        Mockito.verify(securityContextLogoutHandler, Mockito.never()).logout(request, response, authentication);
        Mockito.verify(response).sendRedirect("/poker/login?logout");
    }
}
