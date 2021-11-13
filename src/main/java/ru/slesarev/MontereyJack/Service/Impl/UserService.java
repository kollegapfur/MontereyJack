package ru.slesarev.MontereyJack.Service.Impl;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.slesarev.MontereyJack.Entity.User;
import ru.slesarev.MontereyJack.inner.Role;
import ru.slesarev.MontereyJack.repository.RoleRepository;
import ru.slesarev.MontereyJack.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @PersistenceContext
    private EntityManager entityManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username);

        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User findUserById(Long userId) {
        Optional<User> userDB = userRepository.findById(userId);
        return  userDB.orElse(new User());

    }

    public List<User> allUser() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        User userDB = userRepository.findByLogin(user.getLogin());

        if (userDB != null) {
            return userDB;
        }

        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public List<User> userList(Long idMin) {
        return entityManager.createQuery("SELECT u from User u where u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }




}
