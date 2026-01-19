package me.kngl.plots.action;

import me.kngl.plots.KnglPlotsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class CountdownTask extends BukkitRunnable {
    private final KnglPlotsPlugin plugin;
    private int timeRemaining;
    private BossBar bossBar;

    public CountdownTask(KnglPlotsPlugin plugin, int timeRemaining) {
        this.plugin = plugin;
        this.timeRemaining = timeRemaining;
        this.bossBar = Bukkit.createBossBar("Geri sayım: " + timeRemaining + " saniye kaldı!", BarColor.BLUE, BarStyle.SOLID);
        this.bossBar.setVisible(true);
        Bukkit.getOnlinePlayers().forEach(player -> this.bossBar.addPlayer(player));
    }

    @Override
    public void run() {
        if (timeRemaining > 0) {
            bossBar.setTitle("Geri sayım: " + timeRemaining + " saniye kaldı!");
            bossBar.setProgress(timeRemaining / timeRemaining); // Progress bar 0.0 to 1.0
            timeRemaining--;
        } else {
            bossBar.setTitle("Süre Sona Erdi!");
            List<String> admins = plugin.config().getStringList("Main.admins");
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (admins.contains(player.getName())) continue;
                player.setGameMode(GameMode.ADVENTURE);
                player.setGliding(true);
                player.setAllowFlight(true);

            }
            bossBar.setProgress(0.0);
            cancel();
            // Boss bar disappears after a short delay to show the final message
            new BukkitRunnable() {
                @Override
                public void run() {
                    bossBar.removeAll();
                }
            }.runTaskLater(plugin, 60); // Remove the boss bar after 3 seconds (60 ticks)
        }
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public void addPlayerToBossBar(org.bukkit.entity.Player player) {
        bossBar.addPlayer(player);
    }
}
