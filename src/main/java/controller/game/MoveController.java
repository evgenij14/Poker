package controller.game;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.game.MoveService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class MoveController {
    private final MoveService moveService;

    public MoveController(MoveService moveService) {
        this.moveService = moveService;
    }

    @RequestMapping(value = "/game/move", method = RequestMethod.POST)
    public void moving(HttpServletResponse response) throws IOException {
        boolean b;
        try {
            b = moveService.isMoveSuccess();
        } catch (NullPointerException ex) {
            response.sendRedirect("/poker/main");
            return;
        }
        if (!b) {
            response.sendRedirect("/poker/game?lowRate");
        } else {
            response.sendRedirect("/poker/game");
        }

    }
}
