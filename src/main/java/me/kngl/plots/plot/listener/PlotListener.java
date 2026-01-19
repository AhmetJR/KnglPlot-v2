package me.kngl.plots.plot.listener;

import me.alpho320.fabulous.core.bukkit.BukkitCore;
import me.kngl.plots.KnglPlotsPlugin;
import me.kngl.plots.PlotsAPI;
import me.kngl.plots.plot.KnglPlot;
import me.kngl.plots.plot.KnglPlotOwner;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;

public class PlotListener implements Listener {

    private final @NotNull KnglPlotsPlugin plugin;

    public PlotListener(@NotNull KnglPlotsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        KnglPlot plot = PlotsAPI.getKnglPlotFromChunk(event.getTo().getChunk());

        if (plot == null || plot.owner() == null || (player.hasMetadata("FKnglPlotLastEntered") && !player.getMetadata("FKnglPlotLastEntered").get(0).value().toString().equals(plot.owner().name()))) {
            player.removeMetadata("FKnglPlotLastEntered", plugin);
            return;
        }
        KnglPlotOwner owner = plot.owner();
        if (owner == null) return;

        if (!player.hasMetadata("FKnglPlotLastEntered")) {
            if (plot.isEliminated()) {
                //player.sendTitle("§c" + owner.name(), "§7Bu arsa elenmiştir.");
                player.sendActionBar("§c" + owner.name() +" §7Bu arsa elenmiştir.");
            } else {
                //player.sendTitle("§a" + owner.name(), "§7Arsasına giriş yaptınız.");
                player.sendActionBar("§a" + owner.name() + " §7Arsasına giriş yaptınız.");
            }
            player.setMetadata("FKnglPlotLastEntered", new FixedMetadataValue(plugin, owner.name()));
        }

    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {event.setCancelled(true);}

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {event.setCancelled(true);}

}