package me.kngl.plots.provider;

import me.alpho320.fabulous.core.bukkit.util.FileUtil;
import me.alpho320.fabulous.core.bukkit.util.debugger.Debug;
import me.kngl.plots.KnglPlotsPlugin;
import me.kngl.plots.plot.KnglPlot;
import me.kngl.plots.plot.KnglPlotOwner;
import me.kngl.plots.plot.KnglPlots;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

public class DataProvider {

    private final @NotNull KnglPlotsPlugin plugin;

    public DataProvider(@NotNull KnglPlotsPlugin plugin) {
        this.plugin = plugin;
    }

    public void loadAll() {
        ConfigurationSection configuration = FileUtil.callFile(plugin, "data");
        if (configuration.isConfigurationSection("Plots")) {
            for (String key : configuration.getConfigurationSection("Plots").getKeys(false)) {
                ConfigurationSection section = configuration.getConfigurationSection("Plots." + key);
                try {
                    KnglPlot plot = KnglPlots.find(key);
                    if (plot == null) {
                        Debug.debug(1, "DataProvider | Plot '" + key + "' is found in the data file but not in the cache.");
                        continue;
                    }

                    plot.setOwner(new KnglPlotOwner(
                            UUID.fromString(section.getString("owner-id", "")),
                            section.getString("owner-name", "")
                        )
                    );
                    plot.setEliminated(section.getBoolean("eliminated", false));
                    Debug.debug(0, "DataProvider | Loaded plot '" + key + "'.");
                } catch (Exception e) {
                    Debug.debug(1, "KnglPlots | Error while loading plot '" + key + "'.");
                    e.printStackTrace();
                }
            }
        } else {
            Debug.debug(1, "DataProvider | No plots found in the data file.");
        }
    }

    public void saveAll() {
        File file = new File(plugin.getDataFolder(), "data.yml");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.set("Plots", null);

        for (Map.Entry<String, KnglPlot> entry : KnglPlots.plots().entrySet()) {
            if (entry.getValue().owner() == null) continue;
            try {
                KnglPlot plot = entry.getValue();
                String key = entry.getKey();

                configuration.set("Plots." + key + ".owner-id", plot.owner().id().toString());
                configuration.set("Plots." + key + ".owner-name", plot.owner().name());
                configuration.set("Plots." + key + ".eliminated", plot.isEliminated());

                Debug.debug(0, "DataProvider | Saved plot '" + key + "'.");
            } catch (Exception e) {
                Debug.debug(1, "DataProvider | Error while saving plot '" + entry.getKey() + "'.");
                e.printStackTrace();
            }
        }

        try {
            configuration.save(file);
        } catch (Exception e) {
            Debug.debug(1, "DataProvider | Error while saving data file.");
            e.printStackTrace();
        }

    }

}