package me.kngl.plots.plot;

import me.alpho320.fabulous.core.bukkit.BukkitCore;
import me.alpho320.fabulous.core.bukkit.util.BukkitConfiguration;
import me.alpho320.fabulous.core.bukkit.util.debugger.Debug;
import me.kngl.plots.KnglPlotsPlugin;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class KnglPlots {

    // Plots.id Plot
    private static @NotNull Map<String, KnglPlot> PLOTS = new ConcurrentHashMap<>();
    // ChunkHash Plot.id
    private static final @NotNull Map<Long, String> PLOTS_CACHE = new ConcurrentHashMap<>();

    public static void setup(@NotNull KnglPlotsPlugin plugin) {
        BukkitConfiguration config = plugin.config();
        PLOTS.clear();

        if (config.isConfigurationSection("Plots")) {
            for (String key : config.getConfigurationSection("Plots").getKeys(false)) {
                ConfigurationSection section = config.getConfigurationSection("Plots." + key);
                try {
                    Location pos1 = BukkitCore.instance().location().deserialize(section.getString("pos1", "null"));
                    Location pos2 = BukkitCore.instance().location().deserialize(section.getString("pos2", "null"));
                    Set<KnglPlotCoord> coords = new HashSet<>();

                    int minX = Math.min(pos1.getBlockX(), pos2.getBlockX());
                    int minZ = Math.min(pos1.getBlockZ(), pos2.getBlockZ());

                    int maxX = Math.max(pos1.getBlockX(), pos2.getBlockX());
                    int maxZ = Math.max(pos1.getBlockZ(), pos2.getBlockZ());

                    for (int x = minX; x <= maxX; x+=16) {
                        for (int z = minZ; z <= maxZ; z+=16) {
                            Chunk chunk = pos1.getWorld().getChunkAt(x >> 4, z >> 4);
                            long coordID = chunk.getZ() ^ ((long) chunk.getX() << 32);

                            PLOTS_CACHE.put(coordID, key);
                            coords.add(new KnglPlotCoord(chunk));
                        }
                    }

                    PLOTS.put(key, new KnglPlot(key, pos1, pos2, coords, null, false));
                    Debug.debug(1, "KnglPlots | Loaded plot '" + key + "'.");
                } catch (Exception e) {
                    Debug.debug(1, "KnglPlots | Error while loading plot '" + key + "'.");
                    e.printStackTrace();
                }
            }
        } else {
            Debug.debug(1, "KnglPlots | No 'Plots' found in config.yml.");
        }

    }

    public static @Nullable KnglPlot find(@NotNull String id) {
        return PLOTS.getOrDefault(id, null);
    }

    public static @NotNull Map<Long, String> chunkCache() {
        return PLOTS_CACHE;
    }

    public static @NotNull Map<String, KnglPlot> plots() {
        return PLOTS;
    }


}