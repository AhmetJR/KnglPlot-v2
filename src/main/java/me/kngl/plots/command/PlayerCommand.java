package me.kngl.plots.command;

import dev.jorel.commandapi.CommandTree;
import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import dev.jorel.commandapi.arguments.LiteralArgument;
import jdk.jshell.execution.LoaderDelegate;
import me.kngl.plots.KnglPlotsPlugin;
import me.kngl.plots.PlotsAPI;
import me.kngl.plots.plot.KnglPlot;
import me.kngl.plots.plot.KnglPlotOwner;
import me.kngl.plots.plot.KnglPlots;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PlayerCommand {

    private final KnglPlotsPlugin plugin;

    public PlayerCommand(KnglPlotsPlugin plugin) {
        this.plugin = plugin;
    }

    public void register() {
        new CommandTree("arsa")
                .then(new LiteralArgument("git")
                        .executes((sender, args) -> {
                            Player player = (Player) sender;

                            plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
                                KnglPlot playerPlot = findPlayerPlot(player);

                                if (playerPlot == null) {
                                    player.sendMessage("§cArsan Yok.");
                                    return;
                                }

                                plugin.getServer().getScheduler().runTask(plugin, () -> {
                                    Location pos1 = playerPlot.pos1();
                                    Location pos2 = playerPlot.pos2();

                                    if (pos1 != null && pos2 != null) {
                                        player.teleport(PlotsAPI.findCenterBetweenTwoLocations(pos1, pos2));
                                        PlotsAPI.sendSoundFromConfig(player, "success");
                                    } else {
                                        player.sendMessage("§cbaşarılı.");
                                    }
                                });
                            });

                        })

                )
                .register(plugin);
    }



    private KnglPlot findPlayerPlot(Player player) {
        for (KnglPlot plot : KnglPlots.plots().values()) {
            KnglPlotOwner owner = plot.owner();
            if (owner != null && owner.id().equals(player.getUniqueId())) {
                return plot;
            }
        }
        return null;
    }



}
