package xyz.marvhus.beeviewer;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeeViewerClient implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("BeeViewer");
    @Override
    public void onInitializeClient() {
        LOGGER.info("Initializing Bee Viewer");
    }
}
