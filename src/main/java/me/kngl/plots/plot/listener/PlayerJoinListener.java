package me.kngl.plots.plot.listener;

import me.kngl.plots.KnglPlotsPlugin;
import me.kngl.plots.action.CountdownTask;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private final KnglPlotsPlugin plugin;

    public PlayerJoinListener(KnglPlotsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        CountdownTask countdownTask = plugin.getCountdownTask();
        if (countdownTask != null) {
            countdownTask.addPlayerToBossBar(event.getPlayer());
        }
    }
}
