package ru.nedfreetoplay.querystorage

import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items

class QueryCreativeTab(label: String): CreativeModeTab(label) {
    override fun makeIcon(): ItemStack {
        return ItemStack(Items.STICK)
    }
}