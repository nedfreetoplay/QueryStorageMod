package ru.nedfreetoplay.querystorage;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nedfreetoplay.querystorage.util.ModBlocks;
import ru.nedfreetoplay.querystorage.util.ModBlockEntity;
import ru.nedfreetoplay.querystorage.util.ModItems;

public class QueryStorage implements ModInitializer {

	public static final String MOD_ID = "querystorage";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItems.init();
		ModBlocks.init();
		ModBlockEntity.init();
	}
}
