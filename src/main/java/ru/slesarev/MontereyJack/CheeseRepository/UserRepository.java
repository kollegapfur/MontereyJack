package ru.slesarev.MontereyJack.CheeseRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.slesarev.MontereyJack.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);
}
