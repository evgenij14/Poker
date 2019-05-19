package controller.game;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.game.AllInService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class AllInController {
    private final AllInService allInService;

    public AllInController(AllInService allInService) {
        this.allInService = allInService;
    }

    @RequestMapping(value = "/game/allin", method = RequestMethod.POST)
    public void allIn(HttpServletResponse response) throws IOException {
        try {
            allInService.allIn();
        } catch (NullPointerException ex) {
            response.sendRedirect("/poker/main");
            return;
        }
        response.sendRedirect("/poker/game");
    }
}
