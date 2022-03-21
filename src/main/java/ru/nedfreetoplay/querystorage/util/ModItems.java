package ru.nedfreetoplay.querystorage.util;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import ru.nedfreetoplay.querystorage.QueryStorage;
import ru.nedfreetoplay.querystorage.item.ModItemGroup;
import ru.nedfreetoplay.querystorage.item.custom.DowsingRodItem;

import static ru.nedfreetoplay.querystorage.util.QueryStorageRegistry.registerItem;

public class ModItems {

    public static final Item TEST_INGOT = registerItem("test_ingot",
            new Item(new FabricItemSettings().group(ModItemGroup.QUERY_STORAGE)));

    public static final Item DOWSING_ROD = registerItem("dowsing_rod",
            new DowsingRodItem(new FabricItemSettings().group(ModItemGroup.QUERY_STORAGE).maxDamage(16)));

    public static void init() {
        QueryStorage.LOGGER.info("Registering Mod Items for " + QueryStorage.MOD_ID);
    }

    /*private static Item registerItem(String name, Item item){
        return Registry.register(Registry.ITEM, new Identifier(QueryStorage.MOD_ID, name), item);
    }*/

    /*public static void registerModItems() {
        QueryStorage.LOGGER.info("Registering Mod Items for " + QueryStorage.MOD_ID);
    }*/
}
