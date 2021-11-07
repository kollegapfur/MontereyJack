package ru.slesarev.MontereyJack.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.slesarev.MontereyJack.Entity.User;
import ru.slesarev.MontereyJack.Service.Impl.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final UserServiceImpl service;

    public UserController(UserServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {

        try {
            User userDB = service.save(new User(user.getLogin(), user.getPassword(), user.getEmail()));
            return new ResponseEntity<>(userDB, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
