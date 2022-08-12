package ru.nedfreetoplay.querystorage.registry

import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import ru.nedfreetoplay.querystorage.QueryStorage
import java.util.function.Supplier

class RegistryBlocks(val mod_bus: IEventBus) {
    val BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, QueryStorage.ID)

    val TEST_BLOCK: RegistryObject<Block> = TODO();

    init {

    }

    private fun <T : Block> registerBlock(
        name: String,
        block: Supplier<T>,
        tab: CreativeModeTab = QueryStorage.CreativeTab
    ): RegistryObject<T> {
        val toReturn = BLOCKS.register(name, block)
        registerBlockItem(name, toReturn, tab)
        return toReturn
    }

    private fun <T : Block> registerBlockItem(
        name: String,
        block: RegistryObject<T>,
        tab: CreativeModeTab
    ): RegistryObject<Item> {
        return ITEMS.register(name) { BlockItem(block.get(), Item.Properties().tab(tab)) }
    }

    fun register(eventBus: IEventBus) {
        BLOCKS.register(eventBus)
    }
}