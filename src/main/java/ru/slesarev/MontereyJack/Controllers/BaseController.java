package ru.slesarev.MontereyJack.Controllers;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.slesarev.MontereyJack.Entity.User;
import ru.slesarev.MontereyJack.Entity.internal.AuthUser;
import ru.slesarev.MontereyJack.Service.UserService;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/authUser")
public class BaseController {

    private final UserService service;

    @Autowired
    public BaseController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public User index (@AuthenticationPrincipal AuthUser user) {
        return user != null ? service.getById(user.getId()) : null;
    }
}
