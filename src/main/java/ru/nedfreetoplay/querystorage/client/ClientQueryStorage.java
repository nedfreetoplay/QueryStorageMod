package ru.nedfreetoplay.querystorage.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;

@Environment(EnvType.CLIENT)
public class ClientQueryStorage implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        ModelLoadingRegistry.INSTANCE.registerResourceProvider(rm -> new ModelProvider());
    }
}
