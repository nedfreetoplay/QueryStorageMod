package ru.nedfreetoplay.querystorage;

import net.minecraft.item.Item;
import net.minecraft.util.math.Vec3f;

//Данный класс должен лежать внутри BlockEntity трубы в виде колекции.
//Эти предметы будут летать внутри блока
public class ItemInPipe<T extends Item> {

    //Предмет который будет находится в трубе
    //Возможно придется переделать тип данных на <* extends Item
    //(пока не уверен как это делать)
    protected T item;
    //Его текущая позиция внутри блока
    protected Vec3f posInBlock;
    //Вектор направления движения
    protected Vec3f direction;
    //Скорость (это скорость изменения смещения)
    protected float speed;
    //Ускорение(это скорость изменения скорости)
    protected float acceleration;

    //Пустой метод только для тестирования
    public ItemInPipe(){

    }

    public T getItem() {
        return item;
    }
}
