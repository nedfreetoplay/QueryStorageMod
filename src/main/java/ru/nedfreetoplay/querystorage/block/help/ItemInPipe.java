package ru.nedfreetoplay.querystorage.block.help;

import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3f;
import org.jetbrains.annotations.NotNull;

//Данный класс должен лежать внутри BlockEntity трубы в виде коллекции.
//Эти предметы будут летать внутри блока
public class ItemInPipe {

    //Предмет, который будет находиться в трубе
    public ItemStack itemStack;
    //Его текущая позиция внутри блока
    /*
    * Возможно придется изменить на float и direciton,
    * так можно будет высчитать позицию предмета без записи доп. данных.
    * */
    public Vec3f posInBlock;
    //Вектор направления движения
    public Direction direction;
    //Скорость (это скорость изменения смещения)
    public float speed;
    //Ускорение(это скорость изменения скорости)
    public float acceleration;

    //Пустой объект
    public ItemInPipe(){
        itemStack = ItemStack.EMPTY;
    }

    public ItemInPipe(ItemStack itemStack) {
        this(itemStack,
                new Vec3f(0.5f, 0.5f, 0.5f),
                Direction.UP,
                1f,
                0.99f);
    }

    public ItemInPipe(ItemStack itemStack, Vec3f posInBlock, Direction direction, float speed, float acceleration){
        this.itemStack = itemStack;
        this.posInBlock = posInBlock;
        this.direction = direction;
        this.speed = speed;
        this.acceleration = acceleration;
    }

    public static ItemInPipe EMPTY = new ItemInPipe();

    //Проверка на пустой объект
    public boolean isEmpty(){
        return itemStack == ItemStack.EMPTY;
    }

    /*Правильная запись/загрузка nbt предметов
    * */
    public static NbtCompound writeNbt(NbtCompound nbt, DefaultedList<ItemInPipe> stacks) {
        return writeNbt(nbt, stacks, true);
    }

    public static NbtCompound writeNbt(NbtCompound nbt, DefaultedList<ItemInPipe> stacks, boolean setIfEmpty) {
        NbtList nbtList = new NbtList();
        for (int i = 0; i < stacks.size(); ++i) {
            //ItemStack
            ItemInPipe itemInPipe = stacks.get(i);
            ItemStack itemStack = itemInPipe.itemStack;
            if (itemStack.isEmpty()) continue;
            NbtCompound nbtCompound = new NbtCompound();
            NbtCompound item = new NbtCompound();
            item.putByte("Slot", (byte)i);
            itemStack.writeNbt(item);
            nbtCompound.put("item", item);
            //PosInBlock
            Vec3f pos = itemInPipe.posInBlock;
            nbtCompound.putFloat("posX", pos.getX());
            nbtCompound.putFloat("posY", pos.getY());
            nbtCompound.putFloat("posZ", pos.getZ());
            //Direction, поменять на Byte для экономии места
            nbtCompound.putByte("direction", (byte) getDirection(itemInPipe.direction));
            //Speed and acceleration
            nbtCompound.putFloat("speed", itemInPipe.speed);
            nbtCompound.putFloat("acceleration", itemInPipe.acceleration);
            nbtList.add(nbtCompound);
        }
        if (!nbtList.isEmpty() || setIfEmpty) {
            nbt.put("Items", nbtList);
        }
        return nbt;
    }

    /*Оригинал
    public static void readNbt(NbtCompound nbt, DefaultedList<ItemStack> stacks) {
        NbtList nbtList = nbt.getList("Items", 10);
        for (int i = 0; i < nbtList.size(); ++i) {
            NbtCompound nbtCompound = nbtList.getCompound(i);
            int j = nbtCompound.getByte("Slot") & 0xFF;
            if (j < 0 || j >= stacks.size()) continue;
            stacks.set(j, ItemStack.fromNbt(nbtCompound));
        }
    }*/

    //Надо доделать чтение данных
    public static void readNbt(NbtCompound nbt, DefaultedList<ItemInPipe> stacks) {
        NbtList nbtList = nbt.getList("Items", 10);
        for (int i = 0; i < nbtList.size(); ++i) {
            NbtCompound nbtCompound = nbtList.getCompound(i);
            NbtCompound item = nbtCompound.getCompound("item");
            int j = item.getByte("Slot") & 0xFF;
            if (j < 0 || j >= stacks.size()) continue;
            ItemInPipe pipe = new ItemInPipe(ItemStack.fromNbt(item));
            pipe.posInBlock = new Vec3f(nbtCompound.getFloat("posX"),
                                        nbtCompound.getFloat("posY"),
                                        nbtCompound.getFloat("posZ"));
            pipe.direction = Direction.byId(nbtCompound.getByte("direction"));
            pipe.speed = nbtCompound.getFloat("speed");
            pipe.acceleration = nbtCompound.getFloat("acceleration");
            stacks.set(j, pipe);
        }
    }

    public static int getDirection(@NotNull Direction direction){
        return direction.getId();
    }
}
