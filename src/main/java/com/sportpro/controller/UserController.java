package com.sportpro.controller;

import com.sportpro.dao.DAOError;
import com.sportpro.entity.User;
import com.sportpro.service.UserService;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ilmir
 */
@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> users() {
        return userService.getUsers();
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm(Model model) {
        model.addAttribute(new User());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistration(
            @Valid User user,
            BindingResult errors) {
        if (errors.hasErrors()) {
            return "register";
        }
        userService.registerNewUser(user);
        return "redirect:/users/" + user.getUsername();
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String username(
            @PathVariable String username, Model model, HttpSession session) {
        User user = userService.getUserByUsername(username);
        model.addAttribute(user);
        if (session.getAttribute("user") != null && session.getAttribute("user").equals(user)) {
            session.setAttribute("user", userService.getUserByUsername(username));
            return "profile";
        }
        return "error";
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.POST)
    public String update(@PathVariable String username, User user, Model model, HttpSession session) {
        user = userService.updateUser(user);
        model.addAttribute("user", user);
        return "profile";
    }
    
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView doLogin(@RequestParam("name") String name, @RequestParam("password") String password, HttpSession session) {
        ModelAndView mv = new ModelAndView("home");

        boolean authorized = userService.checkUserPassword(name, password);
        if (authorized) {
            session.setAttribute("user", userService.getUserByUsername(name));
        } else {
            mv.setViewName("error");
            mv.addObject("msg", "Ошибка входа в систему");
            mv.addObject("details", "Пользователя с таким именем и паролем не найдено");
        }
        return mv;
    }

    @RequestMapping(value = "logout")
    public ModelAndView doLogout(HttpSession session, HttpServletResponse response) throws IOException {
        ModelAndView mv = new ModelAndView("home");
        session.invalidate();
        return mv;
    }

    @ExceptionHandler(DAOError.class)
    public ModelAndView exceprionHandler(DAOError ex, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("msg", ex.getTitle());
        mv.addObject("details", ex.getMessage());
        return mv;

    }
}
