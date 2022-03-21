package ru.nedfreetoplay.querystorage.block.help;

import java.util.Map;

/*
* Этот класс хранит состояния сторон блока.
* Я так и не нашел класс делающий что-то подобное.
* И нет Propreties.FACING хранит только одну сторону почти как enum.
* */
public class DirectionConnectList {

    private Map<direction, Boolean> facing;

    public DirectionConnectList() {
        //Хотел сделать здесь foreach, но пока не понял как это сделать...
        facing.put(direction.UP,   false);
        facing.put(direction.DOWN, false);
        facing.put(direction.EAST, false);
        facing.put(direction.WEST, false);
        facing.put(direction.SOUTH,false);
    }

    public void set(direction key, boolean value)
    {
        facing.put(key, value);
    }

    public boolean get(direction key){
        return facing.get(key);
    }

}

enum direction {
    UP,
    DOWN,
    NORTH,
    WEST,
    EAST,
    SOUTH
}
