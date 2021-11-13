package ru.slesarev.MontereyJack.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.slesarev.MontereyJack.repository.CheeseRepository;
import ru.slesarev.MontereyJack.Entity.Cheese;
import ru.slesarev.MontereyJack.Service.CheeseService;

import java.util.List;
@Service
public class CheeseServiceImpl implements CheeseService {

    private final CheeseRepository cheeseRepository;

    @Autowired
    public CheeseServiceImpl(CheeseRepository cheeseRepository){
        this.cheeseRepository = cheeseRepository;
    }

    @Override
    public List<Cheese> getAllCheese() {
        return cheeseRepository.findAll();
    }

    @Override
    public Cheese getById(long id) {
        return cheeseRepository.findById(id).orElse(null);
    }

    @Override
    public Cheese save(Cheese cheese) {
        return cheeseRepository.save(cheese);
    }

    @Override
    public Cheese update(Cheese cheese) {
        Cheese dbCheese = getById(cheese.getId());

        dbCheese.setName(cheese.getName());
        dbCheese.setPrice(cheese.getPrice());
        dbCheese.setAge(cheese.getAge());
        return cheeseRepository.save(dbCheese);
    }



    @Override
    public void remove(Cheese cheese) {
        Long cheeseId = cheese.getId();
        cheese = getById(cheeseId);
        cheeseRepository.delete(cheese);
    }
}
