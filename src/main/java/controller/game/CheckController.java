package controller.game;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.game.CheckService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CheckController {
    private final CheckService checkService;

    public CheckController(CheckService checkService) {
        this.checkService = checkService;
    }

    @RequestMapping(value = "/game/check", method = RequestMethod.POST)
    public void check(HttpServletResponse response) throws IOException {
        try {
            checkService.check();
        } catch (NullPointerException ex) {
            response.sendRedirect("/poker/main");
            return;
        }
        response.sendRedirect("/poker/game");
    }
}
