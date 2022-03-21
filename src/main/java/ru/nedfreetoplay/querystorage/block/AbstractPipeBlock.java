package ru.nedfreetoplay.querystorage.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import ru.nedfreetoplay.querystorage.ItemInPipe;
import ru.nedfreetoplay.querystorage.block.help.DirectionConnectList;

import java.util.ArrayList;

//Этот класс на основе которого будут созданы блоки труб
//По типу обычных транспортных до запросных.
//Сюда добавлять всё что будет реализовыватся в блоках труб
public abstract class AbstractPipeBlock extends BlockWithEntity {

    //Здесь должен быть типа массива содержащего подключение трубы к соседним блокам по всем сторонам света
    //Так же здесь должен быть колекция структур предметов который будет передаваться по трубам.
    ArrayList<ItemInPipe> items;
    DirectionConnectList directionConnect;

    protected AbstractPipeBlock(Settings settings) {
        super(settings);
        //Метод из AbstractFurnaceBlock чтобы не забыть
        //this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)).with(LIT, false));
        directionConnect = new DirectionConnectList();
        items = new ArrayList<>();
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }
        this.openScreen(world, pos, player);
        return ActionResult.CONSUME;
    }

    protected abstract void openScreen(World var1, BlockPos var2, PlayerEntity var3);

    /*Здесь если ItemBlock был переименован, то при установке он примет новое имя после размещения в мире
    * Если это будет обычная труба, то ничего не будет.
    * Если это будет запросная, то измененное имя будет как namespace для системы запросов.
    * Хотя имя можно изменить и в меню самому.
    * */
    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        BlockEntity blockEntity;
        if (itemStack.hasCustomName() && (blockEntity = world.getBlockEntity(pos)) instanceof AbstractFurnaceBlockEntity) {
            ((AbstractFurnaceBlockEntity)blockEntity).setCustomName(itemStack.getName());
        }
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    /*
    * Добавить метод обработки предметов попавших в зону влияния трубы, хотя это будет для всасывающей трубы.
    * И добавить метод при разбитие блока выпадают все предметы что находились в трубе со своих мест.
    * И добавить метод что будет подсчитывать перемещение блоков внутри трубы.
    * */



    @Override
    public void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        super.onBlockBreakStart(state, world, pos, player);
        ItemInPipe itemInPipe = items.get(0);
        Item item = itemInPipe.getItem();


    }















}
