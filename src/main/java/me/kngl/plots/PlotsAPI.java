package me.kngl.plots;

import me.alpho320.fabulous.core.api.util.RoundedNumberFormat;
import me.alpho320.fabulous.core.bukkit.BukkitCore;
import me.alpho320.fabulous.core.bukkit.util.debugger.Debug;
import me.kngl.plots.configuration.ConfigurationManager;
import me.kngl.plots.plot.KnglPlot;
import me.kngl.plots.plot.KnglPlotCoord;
import me.kngl.plots.plot.KnglPlots;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class PlotsAPI {

    public static void reload(@NotNull KnglPlotsPlugin plugin) {
        plugin.getServer().getScheduler().cancelTasks(plugin);

        plugin.setConfigurationManager(new ConfigurationManager(plugin));
        plugin.configurationManager().reload(true);

        setupFormats(plugin);

        KnglPlots.setup(plugin);
        plugin.dataProvider().loadAll();
    }

    public static void setupFormats(@NotNull KnglPlotsPlugin plugin) {
        if (!plugin.getConfig().getBoolean("Numbers.formats.enabled")) return;

        RoundedNumberFormat.setFormats(
                new String[]{
                        plugin.getConfig().getString("Numbers.formats.thousand", "K"),
                        plugin.getConfig().getString("Numbers.formats.million", "M"),
                        plugin.getConfig().getString("Numbers.formats.billion", "B"),
                        plugin.getConfig().getString("Numbers.formats.trillion", "T"),
                        plugin.getConfig().getString("Numbers.formats.quadrillion", "Q")
                }
        );
    }

    public static @Nullable KnglPlot getKnglPlotFromLoc(Location location) {
        return location == null ? null : getKnglPlotFromChunk(location.getChunk());
    }
    
    public static @Nullable KnglPlot getKnglPlotFromChunk(Chunk chunk) {
        long id = chunk.getZ() ^ ((long) chunk.getX() << 32);
        String realmID = KnglPlots.chunkCache().getOrDefault(id, null);
        KnglPlot realm = null;

        if (realmID == null) {
            for (Map.Entry<String, KnglPlot> land : KnglPlots.plots().entrySet()) {
                KnglPlot foundedKnglPlot = land.getValue();

                for (KnglPlotCoord coord : foundedKnglPlot.coords()) {
                    if (coord.equals(chunk)) {
                        Debug.debug(0, " | Chunk of + " + chunk + " equals coord of " + coord.getX() + "-" + coord.getZ());
                        Debug.debug(0, " | ChunkShiftID: " + id + "-" +  (coord.getZ() ^ ((long) coord.getX() << 32)));


                        for (KnglPlotCoord realmCoord : foundedKnglPlot.coords()) {
                            long coordID = realmCoord.getZ() ^ ((long) realmCoord.getX() << 32);
                            KnglPlots.chunkCache().put(coordID, foundedKnglPlot.id());
                        }

                        return foundedKnglPlot;
                    }
                }
            }
        } else {
            realm = KnglPlots.find(realmID);
        }
        return realm;
    }

    public static boolean checkPlayerCan(@NotNull Player player, @NotNull Location location) {
        KnglPlot plot = getKnglPlotFromLoc(location);

        if (player.isOp()) return true;
        if (plot == null) return false;
        if (plot.owner() == null) return false;
        if (plot.isEliminated()) return false;
        if (plot.owner().id().equals(player.getUniqueId())) return true;

        return false;
    }

    public static boolean checkPlayerCan(@NotNull Player player, @NotNull Location location, boolean notify) {
        if (checkPlayerCan(player, location)) return true;
        if (notify) {
            player.sendMessage("§cBunu yapamazsın!");
            sendSoundFromConfig(player, "fail");
        }
        return false;
    }

    public static void sendSoundFromConfig(Player player, String key) {
        if (key == null || player == null) return;
        BukkitCore.instance().sound().send(
                player,
                matchSound(KnglPlotsPlugin.instance().getConfig().getString("Sound." + key, "null"))
        );
    }

    public static @NotNull Sound matchSound(String key) {
        Sound sound = null;
        try {
            sound = Sound.valueOf(key);
        } catch (Exception ignored) {
            sound = Sound.values()[1];
            Debug.debug(1, " | Sound key of " + key + " is not valid! (Using: " + sound + ")");
        }

        return sound;
    }

    public static @NotNull Location findCenterBetweenTwoLocations(@NotNull Location loc1, @NotNull Location loc2) {
        return new Location(
                loc1.getWorld(),
                (loc1.getX() + loc2.getX()) / 2,
                (loc1.getY() + loc2.getY()) / 2,
                (loc1.getZ() + loc2.getZ()) / 2
        );
    }

}