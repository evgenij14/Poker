package controller.game;

import collections.Games;
import collections.PlayersGame;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.UserService;
import service.game.CalculatingService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CalculatingController {
    private final UserService userService;
    private final CalculatingService calculatingService;

    public CalculatingController(UserService userService, CalculatingService calculatingService) {
        this.userService = userService;
        this.calculatingService = calculatingService;
    }


    @RequestMapping(value = "/game/calculating", method = RequestMethod.POST)
    public void calculating(HttpServletResponse response) throws IOException {
        if (!Games.getGame().get(userService.getUser().getGameId()).isFirstOut()) {
            try {
                calculatingService.calculatePoints();
            } catch (NullPointerException ex) {
                response.sendRedirect("/poker/main");
                return;
            }
            Games.getGame().get(userService.getUser().getGameId()).setFirstOut(true);
            PlayersGame.getPlayersInGame().remove(userService.getUser());

        } else {
            PlayersGame.getPlayersInGame().remove(userService.getUser());
            Games.getGame().remove(userService.getUser().getGameId());
        }
        response.sendRedirect("/poker/main");
    }
}
