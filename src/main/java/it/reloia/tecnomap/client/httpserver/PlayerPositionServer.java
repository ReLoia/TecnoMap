package it.reloia.tecnomap.client.httpserver;

import com.sun.net.httpserver.HttpServer;
import it.reloia.tecnomap.client.httpserver.path.AnyHandler;
import it.reloia.tecnomap.client.httpserver.path.MarkerHandler;
import it.reloia.tecnomap.client.httpserver.path.PositionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class PlayerPositionServer {
    public static final Logger LOGGER = LoggerFactory.getLogger("player-position-server");

    public void start() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8998), 0);
            
            server.createContext("/position", new PositionHandler());
            server.createContext("/markers", new MarkerHandler());
            server.createContext("/", new AnyHandler());
            
            server.setExecutor(Executors.newCachedThreadPool());
            server.start();
            LOGGER.info("HTTP server started on port 8998");
        } catch (IOException e) {
            LOGGER.error("Failed to start HTTP server: {}", e.getMessage());
        }
    }

}