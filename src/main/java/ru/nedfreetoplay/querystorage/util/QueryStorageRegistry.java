package ru.nedfreetoplay.querystorage.util;


import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import ru.nedfreetoplay.querystorage.QueryStorage;
import ru.nedfreetoplay.querystorage.item.ModItemGroup;


import static ru.nedfreetoplay.querystorage.QueryStorage.MOD_ID;

public class QueryStorageRegistry {
    public static Item registerItem(String id, Item item) {
        if (item instanceof BlockItem) {
            ((BlockItem)item).appendBlocks(Item.BLOCK_ITEMS, item);
        }
        return Registry.register(Registry.ITEM, new Identifier(MOD_ID, id), item);
    }

    public static Block registerBlock(String id, Block block) {
        registerBlockItem(id, block, ModItemGroup.QUERY_STORAGE);
        return Registry.register(Registry.BLOCK, new Identifier(MOD_ID, id), block);
    }

    private static Item registerBlockItem(String id, Block block, ItemGroup group) {
        return Registry.register(Registry.ITEM, new Identifier(QueryStorage.MOD_ID, id),
                new BlockItem(block, new FabricItemSettings().group(group)));
    }

    public static BlockEntityType registerBlockEntityType(String id, BlockEntityType blockEntityType) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, id), blockEntityType);
    }
}
