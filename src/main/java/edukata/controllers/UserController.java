package edukata.controllers;

import edukata.models.User;
import edukata.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView allUsers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
//        List<User> userList = userService.getUsers();
//        System.out.println(userList);
//        modelAndView.addObject("users", userList);
        return modelAndView;
    }

//    @GetMapping(value = "/")
//    public String printWelcome(ModelMap model) {
//        List<User> userList = userService.getUsers();
//        model.addAttribute("users", userList);
//        System.out.println("userList " + userList);
//        return "index";
//    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id) {
        User user = userService.getUser((long) id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        userService.updateUser(user);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        userService.addUser(user);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ModelAndView deleteUser(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        userService.deleteUser((long) id);
        return modelAndView;
    }
}
