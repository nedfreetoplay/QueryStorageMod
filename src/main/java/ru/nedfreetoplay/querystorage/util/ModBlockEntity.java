package ru.nedfreetoplay.querystorage.util;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import ru.nedfreetoplay.querystorage.QueryStorage;
import ru.nedfreetoplay.querystorage.block.custom.PipeBlockEntity;

public class ModBlockEntity {

    public static final BlockEntityType PIPE_BLOCK_ENTITY = QueryStorageRegistry.registerBlockEntityType("pipe_block_entity",
            FabricBlockEntityTypeBuilder.create(PipeBlockEntity::new, ModBlocks.ITEMS_PIPE).build(null));

    public static void init() {
        QueryStorage.LOGGER.info("Registering Mod BlockEntity for " + QueryStorage.MOD_ID);
    }
}
