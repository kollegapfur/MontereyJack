package ru.slesarev.MontereyJack.Entity;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table (name = "cheese")
public class Cheese {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long Id;

    @Column(name = "name")
    public String name;

    @Column(name = "age")
    public int age;

    @Column(name = "price")
    public double price;

    public Cheese() {
    }

    public Cheese(String name, int age, double price) {
        this.name = name;
        this.age = age;
        this.price = price;
    }

    public Long getId() {return  Id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Cheese{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", price=" + price +
                '}';
    }
}
