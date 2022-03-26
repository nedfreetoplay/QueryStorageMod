package ru.nedfreetoplay.querystorage.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import ru.nedfreetoplay.querystorage.QueryStorage;
import ru.nedfreetoplay.querystorage.block.help.ItemInPipe;
import ru.nedfreetoplay.querystorage.util.ModBlockEntity;

import java.util.ArrayList;

public class PipeBlockEntity extends BlockEntity {

    //public ArrayList<ItemInPipe> items;

    //Попробую реализовать через инвентарь
    public DefaultedList<ItemInPipe> inventory = DefaultedList.ofSize(size(), ItemInPipe.EMPTY);

    public PipeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntity.PIPE_BLOCK_ENTITY, pos, state);
    }

    public boolean addItem(ItemStack itemStack) {
        if(itemStack.isEmpty())
            return false;
        for (int i = 0; i < size(); i++){
            ItemInPipe item = inventory.get(i);
            if(item.itemStack.isEmpty()) {
                QueryStorage.LOGGER.debug(itemStack.toString() + " added");
                inventory.set(i, new ItemInPipe(itemStack));
                break;
            }
        }
        return true;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        ItemInPipe.writeNbt(nbt, this.inventory);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.inventory = DefaultedList.ofSize(this.size(), ItemInPipe.EMPTY);
        ItemInPipe.readNbt(nbt, this.inventory);
    }

    /*
    * Здесь будут просчитываться предметы двигающийся по трубе для их показа
    * Надо будет периодические считывать показания скорости предмета
    * */
    public static void clientTick(World world, BlockPos pos, BlockState state, ChestBlockEntity blockEntity) {
        //blockEntity.lidAnimator.step();
    }

    public static void tick(World world, BlockPos pos, BlockState state, PipeBlockEntity pipeBlockEntity){

    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    //Сколько предметов может через себя пропустить труба одновременно.
    //Если количество предметов превысит возможное количество, то труба разрушится.
    public int size() {
        return 27;
    }
}
