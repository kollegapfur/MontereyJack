package ru.slesarev.MontereyJack.Service;


import ru.slesarev.MontereyJack.Dto.form.ClientForm;
import ru.slesarev.MontereyJack.Entity.User;

import javax.naming.AuthenticationException;

public interface UserService {

    User getByLogin(String login);

    User getById(Long id);

    User registrationNewClient(ClientForm clientForm, boolean isInnerRegistration) throws AuthenticationException;

    User save (User user);

    User activateUser (String code);

}
