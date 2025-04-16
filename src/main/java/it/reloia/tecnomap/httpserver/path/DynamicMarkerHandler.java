package it.reloia.tecnomap.httpserver.path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import it.reloia.tecnomap.httpserver.data.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicMarkerHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("GET".equals(exchange.getRequestMethod())) {
            try {
                Map<String, MarkersDataSet> markerDataSets = getMarkerDataSets();

                Gson gson = new GsonBuilder().create();
                String json = gson.toJson(markerDataSets);
                
                
                exchange.getResponseHeaders().add("Content-Type", "application/json");
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.sendResponseHeaders(200, json.getBytes().length);
                
                OutputStream os = exchange.getResponseBody();
                os.write(json.getBytes());
                os.close();
            } catch (Exception e) {
                String errorResponse = "{\"error\": \"Failed to retrieve the dynamic markers data\"}";
                exchange.getResponseHeaders().add("Content-Type", "application/json");
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.sendResponseHeaders(500, errorResponse.getBytes().length);
                
                OutputStream os = exchange.getResponseBody();
                os.write(errorResponse.getBytes());
                os.close();
            }
        }
        else {
            String response = "Method Not Allowed";
            exchange.sendResponseHeaders(405, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    
    private Map<String, MarkersDataSet> getMarkerDataSets() {
        Map<String, MarkersDataSet> markerData = new HashMap<>();
        
        Map<String, Marker> markers = new HashMap<>();
        markers.put("central-square", new Marker(
                "Central Square",
                new MarkerColor(12, 251, 191, 1),
                new MarkerColor(12, 251, 191, 0.3f),
                List.of(
                        new MarkerShapeVertex(-581, -53),
                        new MarkerShapeVertex(-460, -53),
                        new MarkerShapeVertex(-460, 68),
                        new MarkerShapeVertex(-581, 68)
                ),
                new MarkerPosition(0, 0, 0)
        ));
        markers.put("ikea", new Marker(
                "IKEA",
                new MarkerColor(251, 218, 12, 1),
                new MarkerColor(251, 218, 12, 0.2f),
                List.of(
                        new MarkerShapeVertex(-432, 95),
                        new MarkerShapeVertex(-382, 95),
                        new MarkerShapeVertex(-382, 145),
                        new MarkerShapeVertex(-432, 145)
                ),
                new MarkerPosition(-432, 65, 95)
        ));
        markers.put("legione-carabinieri", new Marker(
                "Legione Carabinieri \"Chiaffredo Bergia\"",
                new MarkerColor(255, 0, 0, 1),
                new MarkerColor(255, 0, 0, 0.2f),
                List.of(
                        new MarkerShapeVertex(-665, 182),
                        new MarkerShapeVertex(-610, 182),
                        new MarkerShapeVertex(-610, 230),
                        new MarkerShapeVertex(-665, 230)
                ),
                new MarkerPosition(-665, 65, 181)
        ));
        markers.put("palazzo-sant-ayama", new Marker(
                "Palazzo Sant'Ayama",
                new MarkerColor(0, 255, 255, 1),
                new MarkerColor(0, 255, 255, 0.2f),
                List.of(
                        new MarkerShapeVertex(-757, 188),
                        new MarkerShapeVertex(-704, 188),
                        new MarkerShapeVertex(-704, 241),
                        new MarkerShapeVertex(-757, 241)
                ),
                new MarkerPosition(-757, 65, 188)
        ));
        markers.put("palazzo-di-giustizia", new Marker(
                "Palazzo di Giustizia",
                new MarkerColor(0, 255, 255, 1),
                new MarkerColor(0, 255, 255, 0.2f),
                List.of(
                        new MarkerShapeVertex(-790, 99),
                        new MarkerShapeVertex(-681, 99),
                        new MarkerShapeVertex(-681, 186),
                        new MarkerShapeVertex(-790, 186)
                ),
                new MarkerPosition(-790, 65, 99)
        ));
        markers.put("banca", new Marker(
                "Banca",
                new MarkerColor(255, 215, 0, 1),
                new MarkerColor(255, 215, 0, 0.2f),
                List.of(
                        new MarkerShapeVertex(-688, -49),
                        new MarkerShapeVertex(-611, -49),
                        new MarkerShapeVertex(-611, 65),
                        new MarkerShapeVertex(-688, 65)
                ),
                new MarkerPosition(-688, 65, -49)
        ));
        markers.put("scuola", new Marker(
                "Scuola",
                new MarkerColor(0, 100, 0, 1),
                new MarkerColor(0, 100, 0, 0.2f),
                List.of(
                        new MarkerShapeVertex(-576, 95),
                        new MarkerShapeVertex(-466, 95),
                        new MarkerShapeVertex(-466, 144),
                        new MarkerShapeVertex(-576, 144)
                ),
                new MarkerPosition(-576, 65, 95)
        ));
        markers.put("purissima", new Marker(
                "Purissima",
                new MarkerColor(173, 216, 230, 1),
                new MarkerColor(173, 216, 230, 0.3f),
                List.of(
                        new MarkerShapeVertex(-428, -44),
                        new MarkerShapeVertex(-389, -44),
                        new MarkerShapeVertex(-389, -5),
                        new MarkerShapeVertex(-428, -5)
                ),
                new MarkerPosition(-428, 65, -44)
        ));
        markers.put("giornalaio", new Marker(
                "Giornalaio / Biblioteca",
                new MarkerColor(173, 216, 230, 1),
                new MarkerColor(173, 216, 230, 0.3f),
                List.of(
                        new MarkerShapeVertex(-428, 20),
                        new MarkerShapeVertex(-389, 20),
                        new MarkerShapeVertex(-389, 59),
                        new MarkerShapeVertex(-428, 59)
                ),
                new MarkerPosition(-428, 65, 20)
        ));
        markers.put("galleria-d-baffoni", new Marker(
                "Galleria \"D. Baffoni\"",
                new MarkerColor(139, 69, 19, 1),
                new MarkerColor(139, 69, 19, 0.25f),
                List.of(
                        new MarkerShapeVertex(-433, -49),
                        new MarkerShapeVertex(-384, -49),
                        new MarkerShapeVertex(-384, 64),
                        new MarkerShapeVertex(-433, 64)
                ),
                new MarkerPosition(-433, 65, -49)
        ));
        markers.put("caserma-vigili-del-fuoco", new Marker(
                "Caserma Vigili del Fuoco",
                new MarkerColor(255, 69, 0, 1),
                new MarkerColor(255, 69, 0, 0.2f),
                List.of(
                        new MarkerShapeVertex(-437, 162),
                        new MarkerShapeVertex(-399, 162),
                        new MarkerShapeVertex(-399, 191),
                        new MarkerShapeVertex(-404, 191),
                        new MarkerShapeVertex(-404, 238),
                        new MarkerShapeVertex(-436, 238)
                ),
                new MarkerPosition(-437, 65, 162)
        ));
        markers.put("case-popolari", new Marker(
                "Case Popolari",
                new MarkerColor(0, 127, 255, 1),
                new MarkerColor(0, 127, 255, 0.2f),
                List.of(
                        new MarkerShapeVertex(-704, 193),
                        new MarkerShapeVertex(-671, 193),
                        new MarkerShapeVertex(-671, 240),
                        new MarkerShapeVertex(-704, 240)
                ),
                new MarkerPosition(-704, 65, 193)
        ));
        
        markerData.put("structures-marker-set", new MarkersDataSet(
                "Structures", markers
        ));
        
        return markerData;
    }
}
