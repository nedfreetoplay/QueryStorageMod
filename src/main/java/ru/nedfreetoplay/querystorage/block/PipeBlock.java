package ru.nedfreetoplay.querystorage.block;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.nedfreetoplay.querystorage.block.help.ItemInPipe;

public class PipeBlock extends AbstractPipeBlock {
    public PipeBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }
        this.openScreen(world, pos, player);

        ItemStack itemStack = player.getStackInHand(hand);
        if(!itemStack.isEmpty())
        {
            player.sendMessage(Text.of(itemStack.getTranslationKey() + " Добавлен в трубу"), false);
            items.add(new ItemInPipe(itemStack));
        }

        return ActionResult.CONSUME;
    }

    protected void openScreen(World var1, BlockPos var2, PlayerEntity var3) {

    }




}
