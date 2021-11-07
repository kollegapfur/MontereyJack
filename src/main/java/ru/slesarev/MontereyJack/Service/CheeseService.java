package ru.slesarev.MontereyJack.Service;

import ru.slesarev.MontereyJack.Entity.Cheese;

import java.util.List;

public interface CheeseService {

    List<Cheese> getAllCheese();

    Cheese getById (long id);

    Cheese save (Cheese cheese);

    Cheese update (Cheese cheese);

//    Cheese getCheese (String cheeseId);

    void remove(Cheese cheese);

}
