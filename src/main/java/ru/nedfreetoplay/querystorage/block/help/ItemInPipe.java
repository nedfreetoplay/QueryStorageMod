package ru.nedfreetoplay.querystorage.block.help;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.Vec3f;

//Данный класс должен лежать внутри BlockEntity трубы в виде коллекции.
//Эти предметы будут летать внутри блока
public class ItemInPipe {

    //Предмет, который будет находиться в трубе
    public ItemStack itemStack;
    //Его текущая позиция внутри блока
    public Vec3f posInBlock;
    //Вектор направления движения
    public Vec3f direction;
    //Скорость (это скорость изменения смещения)
    public float speed;
    //Ускорение(это скорость изменения скорости)
    public float acceleration;

    //Тестовый экземпляр класса
    public ItemInPipe(){
        this.itemStack = new ItemStack(Items.GOLD_BLOCK);
        this.posInBlock = new Vec3f(0.5f, 0.5f, 0.5f);
        this.direction = new Vec3f(0.75f, 0.75f, 0.75f); //В будущем нормализовать
        this.speed = 1f;
        this.acceleration = 0.99f;
    }

    public ItemInPipe(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.posInBlock = new Vec3f(0.5f, 0.5f, 0.5f);
        this.direction = new Vec3f(0.75f, 0.75f, 0.75f); //В будущем нормализовать
        this.speed = 1f;
        this.acceleration = 0.99f;
    }

    public ItemInPipe(ItemStack itemStack, Vec3f posInBlock, Vec3f direction, float speed, float acceleration){
        this.itemStack = itemStack;
        this.posInBlock = posInBlock;
        this.direction = direction;
        this.speed = speed;
        this.acceleration = acceleration;
    }
}
