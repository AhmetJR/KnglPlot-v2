package me.kngl.plots.command;

import dev.jorel.commandapi.CommandTree;
import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import dev.jorel.commandapi.arguments.LiteralArgument;
import me.kngl.plots.KnglPlotsPlugin;
import me.kngl.plots.PlotsAPI;
import me.kngl.plots.action.ManageTeamText;
import me.kngl.plots.plot.KnglPlot;
import me.kngl.plots.plot.KnglPlotOwner;
import me.kngl.plots.plot.KnglPlots;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


public class KnglPlotCommand {

    ManageTeamText manageteamtext;
    private final KnglPlotsPlugin plugin;



    public KnglPlotCommand(KnglPlotsPlugin plugin) {

        this.plugin = plugin;
        this.manageteamtext = new ManageTeamText(plugin);
    }

    public void register() {
        new CommandTree("knglplot")
                .withPermission("knglplot.admin")
                .then(new LiteralArgument("reload")
                        .executes((sender, args) -> {
                            PlotsAPI.reload(plugin);
                            sender.sendMessage("§aConfig yenilendi.");
                        })

                ).then(new LiteralArgument("arsam")
                                .executes((sender, args) -> {
                                    Player player = (Player) sender;

                                    plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
                                        KnglPlot playerPlot = findPlayerPlot(player);

                                        if (playerPlot == null) {
                                            player.sendMessage("§§cAtanmış bir arsanız yok.");
                                            return;
                                        }

                                        plugin.getServer().getScheduler().runTask(plugin, () -> {
                                            Location pos1 = playerPlot.pos1();
                                            Location pos2 = playerPlot.pos2();

                                            if (pos1 != null && pos2 != null) {
                                                player.teleport(PlotsAPI.findCenterBetweenTwoLocations(pos1, pos2));
                                                PlotsAPI.sendSoundFromConfig(player, "success");
                                            } else {
                                                player.sendMessage("§cPlot konumları düzgün ayarlanmamış.");
                                            }
                                        });
                                    });

                                })
                ).then(new LiteralArgument("yoket")
                                .then(new GreedyStringArgument("<player_name>")
                                        .replaceSuggestions(ArgumentSuggestions.strings(info -> {
                                            List<String> plots = new ArrayList<>();
                                            for (KnglPlot plot : KnglPlots.plots().values()) {
                                                if (plot == null) continue;
                                                if (plot.owner() == null) continue;
                                                plots.add(plot.owner().name());
                                            }
                                            return plots.toArray(new String[0]);
                                        }))
                                        .executes((sender, args) -> {
                                                    String playername = (String) args.args()[0];

                                                    KnglPlot playerPlot = findPlayerPlotTnt(playername);

                                                    if (playerPlot == null) {
                                                        sender.sendMessage("§cNo plot found for player: " + playername);
                                                        return;
                                                    }

                                                    Location pos1 = playerPlot.pos1();
                                                    Location pos2 = playerPlot.pos2();

                                                    if (pos1 == null || pos2 == null) {
                                                        sender.sendMessage("§cPlot positions are not set.");
                                                        return;
                                                    }
                                                    Location test1 = pos1.clone().add(0, 260, 0);
                                                    Location test2 = pos2.clone();

                                                    int minX = Math.min(test1.getBlockX(), test2.getBlockX());
                                                    int minY = Math.min(test1.getBlockY(), test2.getBlockY());
                                                    int minZ = Math.min(test1.getBlockZ(), test2.getBlockZ());
                                                    int maxX = Math.max(test1.getBlockX(), test2.getBlockX());
                                                    int maxY = Math.max(test1.getBlockY(), test2.getBlockY());
                                                    int maxZ = Math.max(test1.getBlockZ(), test2.getBlockZ());

                                                    for (int x = minX; x <= maxX; x++) {
                                                        for (int y = minY; y <= maxY; y++) {
                                                            for (int z = minZ; z <= maxZ; z++) {
                                                                Block block = test1.getWorld().getBlockAt(x, y, z);
                                                                block.setType(Material.AIR);
                                                            }
                                                        }
                                                    }



                                                Location loc = PlotsAPI.findCenterBetweenTwoLocations(pos1, pos2);
                                                int count = 10;

//                                                for (int i = 0; i < count; i++) {
//                                                    plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
//                                                        Firework firework = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
//                                                        FireworkMeta fireworkMeta = firework.getFireworkMeta();
//
//
//                                                        FireworkEffect effect = FireworkEffect.builder()
//                                                                .withColor(Color.RED, Color.RED, Color.RED)
//                                                                .withFade(Color.ORANGE, Color.YELLOW)
//                                                                .with(FireworkEffect.Type.CREEPER)
//                                                                .trail(true)
//                                                                .flicker(true)
//                                                                .build();
//
//                                                        fireworkMeta.addEffect(effect);
//                                                        fireworkMeta.setPower(1 + (int) (Math.random() * 3));
//                                                        firework.setFireworkMeta(fireworkMeta);
//                                                    }, i * 20L);
//                                                }
                                                }
                                        )
                                )
                ).then(new LiteralArgument("arsadagit")
                        .executes((sender, args) -> {
                            plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () ->{
                                List<String> admins = plugin.config().getStringList("Main.admins");
                                List<KnglPlot> plots = new ArrayList<>(KnglPlots.plots().values());

                                Collections.shuffle(plots);
                                plugin.config().set("gamehasbeenstart", true);
                                plugin.saveConfig();
                                for (Player player : Bukkit.getOnlinePlayers()) {
                                    if (admins.contains(player.getName())) continue;
                                    KnglPlot plot = plots.get(0);

                                    if (plot == null) {
                                        sender.sendMessage("§cNo more plots available for " + player.getName());
                                        return;
                                    }

                                    plot.setOwner(new KnglPlotOwner(player.getUniqueId(), player.getName()));
                                    plots.remove(0);

                                    PlotsAPI.sendSoundFromConfig(player, "success");
                                    plugin.getServer().getScheduler().runTask(plugin, () ->player.teleport(PlotsAPI.findCenterBetweenTwoLocations(plot.pos1(), plot.pos2())));
                                }

                                plugin.dataProvider().saveAll();
                            });
                        })
                ).then(new LiteralArgument("başlat")
                        .executes((sender, args) -> {
                            int time = plugin.config().getInt("gameendtime") * 60;
                            List<String> admins = plugin.config().getStringList("Main.admins");

//                            if (bgameendtimeossBarManager == null) {
//                                bossBarManager = new BossBarManager(plugin);
//                            }
//                            bossBarManager.startCountdown();
//                            sender.sendMessage("§aBoss bar has been started.")
                            plugin.startCountdown(time);

                            for (Player player : Bukkit.getOnlinePlayers()) {
                                if (admins.contains(player.getName())) continue;
                                player.setGameMode(GameMode.CREATIVE);
                                player.getInventory().clear();


                            }
                            manageteamtext.test();
                        })
                ).then(new LiteralArgument("elemeçubuk")
                        .executes((sender, args) -> {
                            Player player = (Player) sender;
                            ItemStack cubuk = new ItemStack(Material.STICK);
                            ItemMeta meta = cubuk.getItemMeta();
                            meta.setDisplayName("Eleme Çubuğu");
                            meta.setCustomModelData(3131);
                            cubuk.setItemMeta(meta);

                            player.getInventory().addItem(cubuk);
                            player.sendMessage("Özel çubuk verildi!");

                        })
                ).then(new LiteralArgument("ele")
                        .then(new GreedyStringArgument("<player_name>")
                                .replaceSuggestions(ArgumentSuggestions.strings(info -> {
                                    List<String> plots = new ArrayList<>();
                                    for (KnglPlot plot : KnglPlots.plots().values()) {
                                        if (plot == null) continue;
                                        if (plot.owner() == null) continue;
                                        plots.add(plot.owner().name());
                                    }

                                    return plots.toArray(new String[0]);
                                }))
                                .executes((sender, args) -> {
                                    plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
                                        String playerName = (String) args.args()[0];
                                        KnglPlot plot = null;

                                        for (KnglPlot knglPlot : KnglPlots.plots().values()) {
                                            if (knglPlot.owner() == null) continue;
                                            if (knglPlot.owner().name().equalsIgnoreCase(playerName)) {
                                                plot = knglPlot;
                                                break;
                                            }
                                        }

                                        if (plot == null) {
                                            sender.sendMessage("§cArsa bulunamadı:  " + playerName);
                                            return;
                                        }

                                        plot.setEliminated(true);
                                        Player player = Bukkit.getPlayer(playerName);
                                        player.sendTitle("§cElendin!", "§7Üzgünüm :(");
                                        Bukkit.broadcastMessage("§c" + playerName + " §7elendi!");

                                        plugin.dataProvider().saveAll();
                                        sender.sendMessage("§aArsa silindi:  " + playerName);

                                        KnglPlot playerPlot = findPlayerPlotTnt(playerName);
                                        Location pos1 = playerPlot.pos1();
                                        Location pos2 = playerPlot.pos2();

                                        Location loc = PlotsAPI.findCenterBetweenTwoLocations(pos1, pos2);
                                        int count = 10;

//                                        for (int i = 0; i < count; i++) {
//                                            plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
//                                                Firework firework = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
//                                                FireworkMeta fireworkMeta = firework.getFireworkMeta();
//
//
//                                                FireworkEffect effect = FireworkEffect.builder()
//                                                        .withColor(Color.RED, Color.RED, Color.RED)
//                                                        .withFade(Color.ORANGE, Color.YELLOW)
//                                                        .with(FireworkEffect.Type.STAR)
//                                                        .trail(true)
//                                                        .flicker(true)
//                                                        .build();
//
//                                                fireworkMeta.addEffect(effect);
//                                                fireworkMeta.setPower(1 + (int) (Math.random() * 3));
//                                                firework.setFireworkMeta(fireworkMeta);
//                                            }, i * 20L);
//                                        }

                                    });

                                })
                        )
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
    private KnglPlot findPlayerPlotTnt(String playerName) {
        for (KnglPlot plot : KnglPlots.plots().values()) {
            KnglPlotOwner owner = plot.owner();
            if (owner != null && owner.name().equals(playerName)) {
                return plot;
            }
        }
        return null;
    }





}