package it.reloia.tecnomap.client;

import it.reloia.tecnomap.client.httpserver.PlayerPositionServer;
import net.fabricmc.api.ClientModInitializer;

public class TecnoMapClient implements ClientModInitializer {
    PlayerPositionServer playerPositionServer = new PlayerPositionServer();

    @Override
    public void onInitializeClient() {
        playerPositionServer.start();
    }
}
