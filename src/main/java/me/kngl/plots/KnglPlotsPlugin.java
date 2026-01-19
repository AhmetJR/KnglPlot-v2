package me.kngl.plots;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import me.alpho320.fabulous.core.bukkit.util.BukkitConfiguration;
import me.alpho320.fabulous.core.bukkit.util.debugger.Debug;
import me.kngl.plots.action.CountdownTask;
import me.kngl.plots.action.StickManager;
import me.kngl.plots.command.KnglPlotCommand;
import me.kngl.plots.command.PlayerCommand;
import me.kngl.plots.command.TestCommand;
import me.kngl.plots.configuration.ConfigurationManager;

import me.kngl.plots.plot.listener.BlockListener;
import me.kngl.plots.plot.listener.PlayerJoinListener;
import me.kngl.plots.plot.listener.PlotListener;
import me.kngl.plots.plot.listener.PreventionListener;
import me.kngl.plots.provider.DataProvider;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class KnglPlotsPlugin extends JavaPlugin {


    private static KnglPlotsPlugin instance;
    private CountdownTask countdownTask;

    private ConfigurationManager configurationManager;
    private DataProvider dataProvider;

    @Override
    public void onLoad() {
        if (instance != null) throw new IllegalStateException("KnglPlots cannot be started twice!");
        instance = this;

        CommandAPI.onLoad(new CommandAPIBukkitConfig(this).silentLogs(false));
    }

    @Override
    public void onEnable() {
        configurationManager = new ConfigurationManager(this);
        new KnglPlotCommand(this).register();
        new PlayerCommand(this).register();
        new TestCommand(this).register();

        setDataProvider(new DataProvider(this));
        setConfigurationManager(new ConfigurationManager(this));
        configurationManager().reload(false);
        getServer().getPluginManager().registerEvents(new BlockListener(this), this);

        getServer().getPluginManager().registerEvents(new PreventionListener(this), this);
        getServer().getPluginManager().registerEvents(new PlotListener(this), this);
        getServer().getPluginManager().registerEvents(new StickManager(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);


        PlotsAPI.reload(this);

        Debug.debug(0, "");
        Debug.debug(0, "============ KnglPlots ============");
        Debug.debug(0, "");
        Debug.debug(0, "KnglPlots Active!");
        Debug.debug(0, "Version: " + getDescription().getVersion());
        Debug.debug(0, "Developer: AhmetJR");
        Debug.debug(0, "");
        Debug.debug(0, "============ KnglPlots ============");
        Debug.debug(0, "");

        CommandAPI.onEnable();
    }

    @Override
    public void onDisable() {
        CommandAPI.onDisable();

        Debug.debug(0, "");
        Debug.debug(0, "============ KnglPlots ============");
        Debug.debug(0, "");
        Debug.debug(0, "KnglPlots Deactive!");
        Debug.debug(0, "Version: " + getDescription().getVersion());
        Debug.debug(0, "Developer: AhmetJR");
        Debug.debug(0, "");
        Debug.debug(0, "============ KnglPlots ============");
        Debug.debug(0, "");
    }

    public static KnglPlotsPlugin instance() {
        return instance;
    }

    @Override
    public @NotNull FileConfiguration getConfig() {
        return configurationManager().getConfig();
    }

    public @NotNull BukkitConfiguration config() {
        return configurationManager().getConfig();
    }

    public ConfigurationManager configurationManager() {
        return this.configurationManager;
    }

    public void setConfigurationManager(ConfigurationManager configurationManager) {
        this.configurationManager = configurationManager;
    }

    public DataProvider dataProvider() {
        return this.dataProvider;
    }

    public void startCountdown(int seconds) {
        if (countdownTask != null) {
            countdownTask.cancel();
        }
        countdownTask = new CountdownTask(this, seconds);
        countdownTask.runTaskTimer(this, 0, 20);
    }

    public CountdownTask getCountdownTask() {
        return countdownTask;
    }

    public void setDataProvider(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

}