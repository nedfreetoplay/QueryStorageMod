package ru.nedfreetoplay.querystorage.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.ModelProviderContext;
import net.fabricmc.fabric.api.client.model.ModelProviderException;
import net.fabricmc.fabric.api.client.model.ModelResourceProvider;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import ru.nedfreetoplay.querystorage.QueryStorage;
import ru.nedfreetoplay.querystorage.client.model.PipeModel;

@Environment(EnvType.CLIENT)
public class ModelProvider implements ModelResourceProvider {
    public static final Identifier FOUR_SIDED_FURNACE_MODEL = new Identifier(QueryStorage.MOD_ID, "block/pipe_block");
    @Override
    public @Nullable UnbakedModel loadModelResource(Identifier resourceId, ModelProviderContext context) throws ModelProviderException {
        if(resourceId.equals(FOUR_SIDED_FURNACE_MODEL)) {
            return new PipeModel();
        } else {
            return null;
        }
    }
}
