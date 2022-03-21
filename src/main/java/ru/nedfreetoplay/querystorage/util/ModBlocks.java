package ru.nedfreetoplay.querystorage.util;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import ru.nedfreetoplay.querystorage.QueryStorage;
import ru.nedfreetoplay.querystorage.block.PipeBlock;

import static ru.nedfreetoplay.querystorage.util.QueryStorageRegistry.registerBlock;

public class ModBlocks {

    public static final Block TEST_BLOCK = registerBlock("test_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(6f).requiresTool()));

    public static final Block ITEMS_PIPE = registerBlock("items_pipe",
            new PipeBlock(FabricBlockSettings.of(Material.METAL).strength(1f)));

    public static void init() {
        QueryStorage.LOGGER.info("Registering ModBlocks for " + QueryStorage.MOD_ID);
    }
}
