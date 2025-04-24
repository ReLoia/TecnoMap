package it.reloia.tecnomap.dataparsing;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Singleton class that holds all the data parsed from the game.
 */
public class TecnoData {
    public static final TecnoData INSTANCE = new TecnoData();

    public static final int ONE_SECOND = 20;
    private static final int TICK_THRESHOLD = 1000;

    private final Map<String, Integer> tickCounter = new HashMap<>();

    private TecnoData() {
        tickCounter.put("main", TICK_THRESHOLD);
    }

    public void tickAll() {
        tickCounter.forEach((key, value) -> tickCounter.put(key, Math.max(value - 1, 0)));
    }

    public Integer getTick(String id) {
        return tickCounter.get(id);
    }

    public void setTick(String id, int value) {
        tickCounter.put(id, value);
    }

    public void tick() {
        Integer mainTick = tickCounter.get("main");

        if ((mainTick - TICK_THRESHOLD) % ONE_SECOND == 0) {
            fetchSidebarLines();
            checkIfInTecnoRoleplay();
        }

        if (mainTick == 0)
            tickCounter.put("main", TICK_THRESHOLD);

        tickAll();
    }

    private List<String> sidebarLines = Collections.emptyList();

    private void fetchSidebarLines() {
        sidebarLines = Utils.getSidebarLines();
    }

    public boolean inAServer = false;
    public boolean isInTecnoRoleplay = false;

    private void checkIfInTecnoRoleplay() {
        if (sidebarLines.size() >= 3) {
            isInTecnoRoleplay = sidebarLines.get(3).contains("ᴄᴏᴏʀᴅɪɴᴀᴛᴇ");
        } else {
            isInTecnoRoleplay = false;
        }
    }

}
