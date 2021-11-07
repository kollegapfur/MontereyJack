package ru.slesarev.MontereyJack.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.slesarev.MontereyJack.CheeseRepository.UserRepository;
import ru.slesarev.MontereyJack.Dto.form.ClientForm;
import ru.slesarev.MontereyJack.Entity.User;
import ru.slesarev.MontereyJack.Service.UserService;

import javax.naming.AuthenticationException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User getByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public User registrationNewClient(ClientForm clientForm, boolean isInnerRegistration) throws AuthenticationException {
        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User activateUser(String code) {
        return null;
    }
}
