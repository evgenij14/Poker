package controller.game;

import collections.Games;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;
import service.game.GameService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class GameController {
    private final GameService gameService;
    private final UserService userService;

    public GameController(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @RequestMapping(name = "/game", method = RequestMethod.GET)
    public ModelAndView gamePage(HttpServletResponse response) throws IOException {
        if (!gameService.isInGame()) {
            response.sendRedirect("/poker/main");
            return null;
        }
        ModelAndView modelAndView = new ModelAndView("game");
        modelAndView.addObject("game", Games.getGame().get(userService.getUser().getGameId()));
        modelAndView.addObject("loginUser", userService.getUser());
        return modelAndView;
    }

    @RequestMapping(value = "/game/rate{id}", method = RequestMethod.POST)
    public void increasingRate(@PathVariable("id") int id, HttpServletResponse response) throws IOException {
        try {
            gameService.increasingRate(id);
        } catch (NullPointerException ex) {
            response.sendRedirect("/poker/main");
            return;
        }
        response.sendRedirect("/poker/game");
    }
}
