package it.reloia.tecnomap.client.httpserver.path;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import it.reloia.tecnomap.dataparsing.TecnoData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;

public class MarkerHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("GET".equals(exchange.getRequestMethod())) {
            try {
                MinecraftClient client = MinecraftClient.getInstance();
                assert client.player != null;
                
                if (!TecnoData.INSTANCE.isInTecnoRoleplay) {
                     throw new Exception("Not in TecnoRoleplay");
                }
                
                ResourceManager resourceManager = client.getResourceManager();
                Identifier id = new Identifier("tecnomap", "web/markers.json");

                Optional<Resource> optionalResource = resourceManager.getResource(id);

                if (optionalResource.isPresent()) {
                    Resource resource = optionalResource.get();
                    try (InputStream inputStream = resource.getInputStream()) {
                        byte[] bytes = inputStream.readAllBytes();

                        String contentType = "application/json";

                        exchange.getResponseHeaders().add("Content-Type", contentType);
                        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                        exchange.sendResponseHeaders(200, bytes.length);

                        OutputStream os = exchange.getResponseBody();
                        os.write(bytes);
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                
                exchange.sendResponseHeaders(404, -1);
            } catch (Exception e) {
                String errorResponse = "{\"error\": \"Failed to retrieve the markers data somehow\"}";
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
