package ru.slesarev.MontereyJack.CheeseRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.slesarev.MontereyJack.Entity.Cheese;

import java.util.List;

public interface CheeseRepository extends JpaRepository<Cheese, Long> {

    List<Cheese> findByNameContaining (String name);



}
