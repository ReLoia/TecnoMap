package it.reloia.tecnomap.client.httpserver;

import com.sun.net.httpserver.HttpServer;
import it.reloia.tecnomap.client.httpserver.path.AnyHandler;
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
            server.createContext("/", new AnyHandler());
            
            server.setExecutor(Executors.newCachedThreadPool());
            server.start();
            LOGGER.info("HTTP server started on port 8998");
        } catch (IOException e) {
            LOGGER.error("Failed to start HTTP server: {}", e.getMessage());
        }
    }

    static class PositionHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                try {
                    MinecraftClient client = MinecraftClient.getInstance();
                    assert client.player != null;
                    Vec3d playerPos = client.player.getPos();

                    String response = String.format("""
                            {
                              "players": [{
                                "uuid": "%s",
                                "name": "%s",
                                "foreign": false,
                                "position": {"x": %f, "y": %f, "z": %f},
                                "rotation": {"pitch": %d, "yaw": %d, "roll": %d}
                              }]
                            }""", client.player.getUuid(), client.player.getName().getString(), playerPos.getX(), playerPos.getY(), playerPos.getZ(), 0, 0, 0);
                    
                    exchange.getResponseHeaders().add("Content-Type", "application/json");
                    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                    exchange.sendResponseHeaders(200, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    
                    os.write(response.getBytes());
                    os.close();
                } catch (Exception e) {
                    String errorResponse = "{\"error\": \"Failed to retrieve player position\"}";
                    exchange.getResponseHeaders().add("Content-Type", "application/json");
                    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                    exchange.sendResponseHeaders(500, errorResponse.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(errorResponse.getBytes());
                    os.close();
                }
            } else {
                String response = "Method Not Allowed";
                exchange.sendResponseHeaders(405, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }
}