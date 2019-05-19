package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView registerPage() {
        return new ModelAndView("register");
    }

    @PostMapping
    public void registration(@RequestParam String login,
                             @RequestParam String password,
                             @RequestParam String name,
                             @RequestParam String lastname,
                             @RequestParam String email,
                             HttpServletResponse response) throws IOException {
        if (userService.isAddUser(login, password, name, lastname, email)) {
            response.sendRedirect("/poker/login?registered");
        } else {
            response.sendRedirect("/poker/register?invalid_email");
        }
    }

}
