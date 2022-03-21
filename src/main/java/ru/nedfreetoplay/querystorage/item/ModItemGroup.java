package ru.nedfreetoplay.querystorage.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import ru.nedfreetoplay.querystorage.QueryStorage;
import ru.nedfreetoplay.querystorage.util.ModItems;

public class ModItemGroup {
    public static final ItemGroup QUERY_STORAGE = FabricItemGroupBuilder.build(new Identifier(QueryStorage.MOD_ID, "query_storage"),
            () -> new ItemStack(ModItems.TEST_INGOT));
}
