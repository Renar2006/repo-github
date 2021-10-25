package ru.geekbrains.Homework7;


public class Cat {
    public String name;
    public int appetit;
    public boolean fullness;

    public Cat(String name, int appetit, boolean fullness) {
        this.name = name;
        this.appetit = appetit;
        this.fullness = fullness;
    }

    public void eat(Plate p) {
        p.decreaseFood(appetit);
    }
}
