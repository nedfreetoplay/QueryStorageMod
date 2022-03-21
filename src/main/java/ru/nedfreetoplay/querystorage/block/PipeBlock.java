package ru.nedfreetoplay.querystorage.block;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import ru.nedfreetoplay.querystorage.block.custom.PipeBlockEntity;

public class PipeBlock extends AbstractPipeBlock implements BlockEntityProvider {
    public PipeBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void openScreen(World var1, BlockPos var2, PlayerEntity var3) {

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

    override

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }


}
