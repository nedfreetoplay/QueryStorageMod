package ru.nedfreetoplay.querystorage.registry

import com.google.common.base.Supplier
import com.google.common.collect.ImmutableList
import com.google.common.primitives.ImmutableIntArray
import net.minecraft.world.item.Item
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import ru.nedfreetoplay.querystorage.QueryStorage
import thedarkcolour.kotlinforforge.forge.MOD_BUS

class Items(private val DeferredRegister
            private val registry: DeferredRegister<Item> = DeferredRegister.create(ForgeRegistries.ITEMS, modId)) {

    private val items = array

    private val TEST_ITEM: RegistryObject<Item> = registerItem("test_item") {Item(Item.Properties().tab(QueryStorage.CreativeTab))}
    /*
    * При создание объекта недолжно быть вычислений, а только указание где что лежит
    *
    * */
    init {

    }



    private fun <T: Item>registerItem(name: String, item: Supplier<T>): RegistryObject<T>{

        return registry.register(name, item)
    }

    fun items(): Array<RegistryObject<Item>> {

    }

    fun subscribe(eventBus: IEventBus){
        registry.register(eventBus)
    }
}