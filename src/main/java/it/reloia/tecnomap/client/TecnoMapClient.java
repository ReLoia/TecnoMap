package it.reloia.tecnomap.client;

import it.reloia.tecnomap.client.httpserver.PlayerPositionServer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import it.reloia.tecnomap.dataparsing.TecnoData;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;

public class TecnoMapClient implements ClientModInitializer {
    PlayerPositionServer playerPositionServer = new PlayerPositionServer();

    @Override
    public void onInitializeClient() {
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> 
                TecnoData.INSTANCE.inAServer = true);

        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> {
            TecnoData.INSTANCE.inAServer = false;
            TecnoData.INSTANCE.isInTecnoRoleplay = false;
        });
        
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (TecnoData.INSTANCE.inAServer)
                TecnoData.INSTANCE.tick();
        });
        
        playerPositionServer.start();
    }
}
