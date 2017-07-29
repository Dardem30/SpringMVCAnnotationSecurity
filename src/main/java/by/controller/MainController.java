package by.controller;

import by.model.Users;
import by.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Роман on 28.07.2017.
 */
@Controller
public class MainController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String main(){
        return "index";
    }
    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/registration")
    public String registration(Model model){
        model.addAttribute("userForm",new Users());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm")Users users){
        userService.saveUser(users);
        return "redirect:/login";
    }
}
