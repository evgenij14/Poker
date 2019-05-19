package controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

@RunWith(MockitoJUnitRunner.class)
public class WelcomeControllerTest {
    @InjectMocks
    private WelcomeController welcomeController;

    @Test
    public void welcomePage() {
        ModelAndView modelAndView = welcomeController.welcomePage();
        Assert.assertEquals("welcome", modelAndView.getViewName());
        Assert.assertNotNull(modelAndView);
    }
}
