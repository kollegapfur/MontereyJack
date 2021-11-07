package ru.slesarev.MontereyJack.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.slesarev.MontereyJack.CheeseRepository.CheeseRepository;
import ru.slesarev.MontereyJack.Entity.Cheese;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/")
public class CheeseController {

    @Autowired
    CheeseRepository cheeseRepository;

    @GetMapping("/cheeses")
    public ResponseEntity<List<Cheese>> getAllCheese(String name) {

        try {
            List<Cheese> cheeses = new ArrayList<Cheese>();

            if (name == null)
                cheeseRepository.findAll().forEach(cheeses::add);
            else
                cheeseRepository.findByNameContaining(name).forEach(cheeses::add);

            if (cheeses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cheeses, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/cheeses/{id}")
    public ResponseEntity<Cheese> getCheeseById (@PathVariable("id") long id){

        //Optional for null-check
        Optional<Cheese> cheeseData = cheeseRepository.findById(id);

        if (cheeseData.isPresent()){

            return new ResponseEntity<>(cheeseData.get(), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/cheeses")
    public ResponseEntity<Cheese> createCheese(@RequestBody Cheese cheese){

        try {
            Cheese cheese1 = cheeseRepository
                    .save(new Cheese(cheese.getName(), cheese.getAge(),cheese.getPrice()));
            return new ResponseEntity<>(cheese1, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cheeses/{id}")
    public ResponseEntity<Cheese> updateCheese(@PathVariable("id") long id, @RequestBody Cheese cheese){

        Optional<Cheese> cheeseData = cheeseRepository.findById(id);

        if (cheeseData.isPresent()){
            Cheese cheese1 = cheeseData.get();
            cheese1.setName(cheese.getName());
            cheese1.setAge(cheese.getAge());
            cheese1.setPrice(cheese.getPrice());

            return new ResponseEntity<>(cheeseRepository.save(cheese1), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/cheeses/{id}")
    public ResponseEntity<HttpStatus> deleteCheese(@PathVariable("id") long id){

        try {
            cheeseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/cheeses")
    public ResponseEntity<HttpStatus> deleteAllCheese(){

        try {
            cheeseRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
