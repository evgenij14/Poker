package controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {
    @InjectMocks
    private LoginController loginController;

    @Test
    public void loginPage() {
        ModelAndView modelAndView = loginController.loginPage();
        Assert.assertEquals("login", modelAndView.getViewName());
        Assert.assertEquals(0, modelAndView.getModel().size());
    }
}
