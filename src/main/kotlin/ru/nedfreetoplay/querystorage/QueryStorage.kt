package ru.nedfreetoplay.querystorage

import com.mojang.logging.LogUtils
import net.minecraft.client.Minecraft
import net.minecraft.world.item.CreativeModeTab
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import org.slf4j.Logger
import ru.nedfreetoplay.querystorage.registry.RegistryItem
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.forge.runForDist

@Mod(QueryStorage.ID)
object QueryStorage {
    const val ID = "querystorage"

    // the logger for our mod
    val LOGGER: Logger = LogUtils.getLogger()
    val CreativeTab: CreativeModeTab = QueryCreativeTab(QueryStorage.ID)

    private val registryItems = DeferredRegister.create(ForgeRegistries.ITEMS, ID)

    init {
        LOGGER.info("Query Storage init!")

        // Register the KDeferredRegister to the mod-specific event bus
        //ModBlocks.REGISTRY.register(MOD_BUS)
        val items: Array<RegistryItem> = Items(
            registryItems,
            RegistryObjectList(
                RegistryItem()
            ))

        /*
        val items: Array<RegistryItem> = Items(registryItems
            RegistryObjectList(
                RegistryItem()
            ))
        */



        val obj = runForDist(
            clientTarget = {
                MOD_BUS.addListener(::onClientSetup)
                Minecraft.getInstance()
            },
            serverTarget = {
                MOD_BUS.addListener(::onServerSetup)
                "test"
            })

        println(obj)
    }

    /**
     * This is used for initializing client specific
     * things such as renderers and keymaps
     * Fired on the mod specific event bus.
     */
    private fun onClientSetup(event: FMLClientSetupEvent) {
    }

    /**
     * Fired on the global Forge bus.
     */
    private fun onServerSetup(event: FMLDedicatedServerSetupEvent) {
    }
}