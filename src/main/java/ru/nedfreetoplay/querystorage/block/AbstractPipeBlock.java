package ru.nedfreetoplay.querystorage.block;

import net.minecraft.block.*;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import ru.nedfreetoplay.querystorage.block.custom.PipeBlockEntity;
import ru.nedfreetoplay.querystorage.block.help.ItemInPipe;

import java.util.ArrayList;

//Этот класс на основе которого будут созданы блоки труб
//По типу обычных транспортных до запросных.
//Сюда добавлять всё что будет реализовывается в блоках труб
public abstract class AbstractPipeBlock extends BlockWithEntity implements BlockEntityProvider {

    //Перенести предметы в PipeBlockEntity!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    ArrayList<ItemInPipe> items;

    //Здесь должен быть типа массива содержащего подключение трубы к соседним блокам по всем сторонам света
    //Так же здесь должен быть коллекция структур предметов который будет передаваться по трубам.

    //На сколько быстро двигаются предметы по этой трубе.
    public float speed = 1f;
    //На сколько быстро предметы перемещаемый по этой трубе потеряют скорость до скорости трубы(уменьшение скорости)
    public float resistance = 1f;
    //На сколько быстро предметы перемещаемый по этой трубе повысят скорость до скорости трубы(увелечения скорости)
    public float accelerator = 1f;

    protected AbstractPipeBlock(Settings settings) {
        super(settings);
        //На всякий случай отключаю подключение
        setDefaultState(this.getStateManager().getDefaultState()
            .with(Properties.UP, false)
            .with(Properties.DOWN, false)
            .with(Properties.NORTH, false)
            .with(Properties.EAST, false)
            .with(Properties.SOUTH, false)
            .with(Properties.WEST, false));
        //Метод из AbstractFurnaceBlock чтобы не забыть
        //this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)).with(LIT, false));
        //items = new ArrayList<>();
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        //Подключение блока к соседним блокам
        builder.add(Properties.UP, Properties.DOWN,
                    Properties.NORTH, Properties.EAST,
                    Properties.SOUTH, Properties.WEST);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        //Надо будет упросить это дело.
        BlockPos pos = ctx.getBlockPos();
        World world = ctx.getWorld();
        BlockState state = this.getDefaultState();
        if(world.getBlockState(pos.up()).getBlock() instanceof AbstractPipeBlock)
            state.with(Properties.UP, true);
        if(world.getBlockState(pos.down()).getBlock() instanceof AbstractPipeBlock)
            state.with(Properties.DOWN, true);
        if(world.getBlockState(pos.north()).getBlock() instanceof AbstractPipeBlock)
            state.with(Properties.NORTH, true);
        if(world.getBlockState(pos.east()).getBlock() instanceof AbstractPipeBlock)
            state.with(Properties.EAST, true);
        if(world.getBlockState(pos.south()).getBlock() instanceof AbstractPipeBlock)
            state.with(Properties.SOUTH, true);
        if(world.getBlockState(pos.west()).getBlock() instanceof AbstractPipeBlock)
            state.with(Properties.WEST, true);
        return state;
    }

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

    //При разрушении трубы выпадает все предметы, что находились в этом блоке
    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient())
        {
            player.sendMessage(Text.of("Выпали предметы:"), false);
            for (ItemInPipe item:
                 items) {
                player.sendMessage(Text.of(item.toString()), false);
                world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), item.itemStack));
            }
        }
        super.onBreak(world, pos, state, player);
    }

    //Создаем BlockEntity для хранения значений внутри блока
    //Метод интерфейса BlockEntityProvider
    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PipeBlockEntity(pos, state);
    }

    //Метод интерфейса BlockEntityProvider
    /*@Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return PipeBlock.checkType(world, type, BlockEntityType.FURNACE);
    }*/

    /*
    * Добавить метод обработки предметов попавших в зону влияния трубы, хотя это будет для всасывающей трубы.
    * И добавить метод при разбитии блока выпадают все предметы, что находились в трубе со своих мест.
    * И добавить метод что будет подсчитывать перемещение блоков внутри трубы.
    * */



    /*@Override
    public void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        super.onBlockBreakStart(state, world, pos, player);
        ItemInPipe itemInPipe = items.get(0);
        Item item = itemInPipe.getItem();


    }*/















}
