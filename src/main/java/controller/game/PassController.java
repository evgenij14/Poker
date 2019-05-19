package controller.game;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.game.PassService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class PassController {
    private final PassService passService;

    public PassController(PassService passService) {
        this.passService = passService;
    }

    @RequestMapping(value = "/game/pass", method = RequestMethod.POST)
    public void pass(HttpServletResponse response) throws IOException {
        try {
            passService.pass();
        } catch (NullPointerException ex) {
            response.sendRedirect("/poker/main");
            return;
        }
        response.sendRedirect("/poker/game");
    }
}
