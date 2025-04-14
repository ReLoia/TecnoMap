package it.reloia.tecnomap.dataparsing;

import com.google.common.collect.Iterables;
import net.minecraft.client.MinecraftClient;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.ScoreboardPlayerScore;
import net.minecraft.scoreboard.Team;
import net.minecraft.text.Text;
import org.apache.commons.compress.utils.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Utils {
    /**
     * I LOVE aaron1998ish
     * <a href="https://gist.github.com/aaron1998ish/33c4e1836bd5cf79501d163a1b5c8304">...</a>
     * +
     * I LOVE Apec
     * <p>
     * Fetching lines are based on how they're visually seen on your sidebar
     * and not based on the actual value score.
     * <p>
     * Written around Minecraft 1.8 Scoreboards, modify to work with your
     * current version of Minecraft.
     * <p>
     * <3 aaron1998ish
     *
     * @return a list of lines for a given scoreboard or empty
     *         if the worlds not loaded, scoreboard isnt present
     *         or if the sidebar objective isnt created.
     */
    public static List<String> getSidebarLines() {
        List<String> lines = new ArrayList<>();
        if (MinecraftClient.getInstance().world == null) return lines;
        Scoreboard scoreboard = MinecraftClient.getInstance().world.getScoreboard();
        if (scoreboard == null) {
            // TODO: create an error log class that sends messages in case of errors
            System.out.println("Scoreboard is null");
            return lines;
        }

        ScoreboardObjective objective = scoreboard.getObjectiveForSlot(1);

        if (objective == null) {
            System.out.println("Objective is null");
            return lines;
        }

        Collection<ScoreboardPlayerScore> scores = scoreboard.getAllPlayerScores(objective);
        List<ScoreboardPlayerScore> list = scores.stream()
                .filter(input -> input != null && input.getPlayerName() != null && !input.getPlayerName().startsWith("#"))
                .toList();

        if (list.size() > 15) {
            scores = Lists.newArrayList(Iterables.skip(list, scores.size() - 15).iterator());
        } else {
            scores = list;
        }

        for (ScoreboardPlayerScore score : scores) {
            Team team = scoreboard.getPlayerTeam(score.getPlayerName());
            lines.add(Team.decorateName(team, Text.of(score.getPlayerName())).getString());
        }

        return lines;
    }
}
