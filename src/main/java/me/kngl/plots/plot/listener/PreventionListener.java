package me.kngl.plots.plot.listener;

import me.kngl.plots.KnglPlotsPlugin;
import me.kngl.plots.PlotsAPI;
import me.kngl.plots.command.KnglPlotCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.projectiles.ProjectileSource;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PreventionListener implements Listener {

    private final @NotNull KnglPlotsPlugin plugin;
    private final KnglPlotCommand knglPlotCommand;

    public PreventionListener(@NotNull KnglPlotsPlugin plugin) {
        this.plugin = plugin;
        this.knglPlotCommand = new KnglPlotCommand(plugin);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        if (!PlotsAPI.checkPlayerCan(player, event.getBlockPlaced().getLocation(), true)) {
            event.setCancelled(true);
        }
    }



    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        List<String> admins = plugin.config().getStringList("Main.admins");
        int blockY = event.getBlock().getY();

        // Check if the block Y is -2 and if the player is not an admin
        if (blockY == -2 && !admins.contains(player.getName())) {
            event.setCancelled(true);
            player.sendMessage("§cBu yükseklikte blok kıramazsınız.");
        }
    }



//    @EventHandler
//    public void onBreak(BlockBreakEvent event) {
//        Player player = event.getPlayer();
//        int blockY = event.getBlock().getY();
//        if (!PlotsAPI.checkPlayerCan(player, event.getBlock().getLocation(), true)) {
//            if (blockY == -2) {
//                event.setCancelled(true);
//                player.sendMessage("§cBu yükseklikte blok kıramazsınız.");
//                return;
//            }
//        }
//
//
//        if (!PlotsAPI.checkPlayerCan(player, event.getBlock().getLocation(), true)) {
//            event.setCancelled(true);
//        }
//    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!PlotsAPI.checkPlayerCan(player, player.getLocation(), true)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void OnMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Boolean gamehasbeenstart = plugin.config().getBoolean("gamehasbeenstart");
        if (gamehasbeenstart) {
            if (!PlotsAPI.checkPlayerCan(player, event.getTo(), true)) {
                player.chat("/arsa git");
                event.setCancelled(true);

        }
    }}

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        ProjectileSource shooter = event.getEntity().getShooter();
        if (shooter == null || !(shooter instanceof Player)) return;
        Player player = (Player) shooter;

        if (!PlotsAPI.checkPlayerCan(player, player.getLocation(), true)) {
            event.setCancelled(true);
        }
    }

}