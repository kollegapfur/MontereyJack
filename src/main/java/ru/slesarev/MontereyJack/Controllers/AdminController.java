package ru.slesarev.MontereyJack.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.slesarev.MontereyJack.Service.Impl.UserService;

@Controller
public class AdminController {

    @Autowired
    private final UserService userService;


    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("AllUsers", userService.allUser());
        return "admin";
    }

    @PostMapping("/admin")
    public String deleteUser (@RequestParam(required = true, defaultValue = "") Long userId,
                              @RequestParam(required = true, defaultValue = "") String action,
                              Model model) {
        if (action.equals("delete")) {
            userService.deleteUser(userId);
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/gt/{userId}")
    public String gtUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("allUsers", userService.userList(userId));
        return "admin";
    }

}
