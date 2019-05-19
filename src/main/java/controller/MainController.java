package controller;

import collections.PlayersGame;
import collections.Tables;
import entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.MainService;
import service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class MainController {
    private final UserService userService;
    private final MainService mainService;

    public MainController(MainService mainService, UserService userService) {
        this.mainService = mainService;
        this.userService = userService;
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView mainPage(HttpServletResponse response) throws IOException {
        User user = userService.getUser();
        if (PlayersGame.getPlayersInGame().contains(user)) {
            response.sendRedirect("/poker/game");
            return null;
        }
        ModelAndView model = new ModelAndView("main");
        model.addObject("isInTable", mainService.isInTable(user));
        model.addObject("table", mainService.numberOfTable(user));
        model.addObject("user", user);
        model.addObject("isInGame", mainService.isInGame(user));
        model.addObject("t1", Tables.size1());
        model.addObject("t2", Tables.size2());
        model.addObject("t3", Tables.size3());
        return model;
    }

    @RequestMapping(value = "/main/table{id}", method = RequestMethod.POST)
    public void addTables(@PathVariable("id") int id, HttpServletResponse response) throws IOException {

        mainService.addUserOnTable(id, userService.getUser());
        if (mainService.tableOfUser(id).size() >= 2) {
            mainService.creatingGame(id);
        }
        response.sendRedirect("/poker/main");
    }

    @RequestMapping(value = "/main/delete", method = RequestMethod.POST)
    public void deleteFromTable(HttpServletResponse response) throws IOException {
        mainService.deleteFromTable(userService.getUser());
        response.sendRedirect("/poker/main");
    }

    @RequestMapping(value = "/main/money", method = RequestMethod.POST)
    public void addingMoney(@RequestParam int money, HttpServletResponse response) throws IOException {
        mainService.setMoneyOnGame(money, userService.getUser());
        response.sendRedirect("/poker/main");
    }

}
