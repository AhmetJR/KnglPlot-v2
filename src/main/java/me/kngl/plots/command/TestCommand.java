package me.kngl.plots.command;

import dev.jorel.commandapi.CommandTree;
import dev.jorel.commandapi.arguments.LiteralArgument;
import me.kngl.plots.KnglPlotsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class TestCommand {

    private KnglPlotsPlugin plugin;

    public TestCommand(KnglPlotsPlugin plugin) {
        this.plugin = plugin;
    }

    public void register() {
        new CommandTree("test")
                .then(new LiteralArgument("brown")
                        .executes((sender, args) -> {
                            int brownx = plugin.getConfig().getInt("Messageteam.browncords.pos1.x");
                            int browny = plugin.getConfig().getInt("Messageteam.browncords.pos1.y");
                            int brownz = plugin.getConfig().getInt("Messageteam.browncords.pos1.z");
                            int brownx2 = plugin.getConfig().getInt("Messageteam.browncords.pos2.x");
                            int browny2 = plugin.getConfig().getInt("Messageteam.browncords.pos2.y");
                            int brownz2 = plugin.getConfig().getInt("Messageteam.browncords.pos2.z");

                            for (Player player : Bukkit.getOnlinePlayers()) {
                                if (player.getGameMode() == GameMode.ADVENTURE) {
                                    Location loc = player.getLocation();
                                    if (isWithinArea(loc, brownx, browny, brownz, brownx2, browny2, brownz2)) {
                                        player.sendTitle("§cOyun Başladı!", "§cBütcen 50 TL", 10, 70, 20);
                                    }
                                }
                            }
                        }))
                .then(new LiteralArgument("pink")
                        .executes((sender, args) -> {
                            int pinkx = plugin.getConfig().getInt("Messageteam.pinkcords.pos1.x");
                            int pinky = plugin.getConfig().getInt("Messageteam.pinkcords.pos1.y");
                            int pinkz = plugin.getConfig().getInt("Messageteam.pinkcords.pos1.z");
                            int pinkx2 = plugin.getConfig().getInt("Messageteam.pinkcords.pos2.x");
                            int pinky2 = plugin.getConfig().getInt("Messageteam.pinkcords.pos2.y");
                            int pinkz2 = plugin.getConfig().getInt("Messageteam.pinkcords.pos2.z");

                            for (Player player : Bukkit.getOnlinePlayers()) {
                                if (player.getGameMode() == GameMode.ADVENTURE) {
                                    Location loc = player.getLocation();
                                    if (isWithinArea(loc, pinkx, pinky, pinkz, pinkx2, pinky2, pinkz2)) {
                                        player.sendTitle("§cOyun Başladı!", "§cBütcen 100 TL", 10, 70, 20);
                                    }
                                }
                            }
                        }))
                .then(new LiteralArgument("yellow")
                        .executes((sender, args) -> {
                            int yellowx = plugin.getConfig().getInt("Messageteam.yellowcords.pos1.x");
                            int yellowy = plugin.getConfig().getInt("Messageteam.yellowcords.pos1.y");
                            int yellowz = plugin.getConfig().getInt("Messageteam.yellowcords.pos1.z");
                            int yellowx2 = plugin.getConfig().getInt("Messageteam.yellowcords.pos2.x");
                            int yellowy2 = plugin.getConfig().getInt("Messageteam.yellowcords.pos2.y");
                            int yellowz2 = plugin.getConfig().getInt("Messageteam.yellowcords.pos2.z");

                            for (Player player : Bukkit.getOnlinePlayers()) {
                                if (player.getGameMode() == GameMode.ADVENTURE) {
                                    Location loc = player.getLocation();
                                    if (isWithinArea(loc, yellowx, yellowy, yellowz, yellowx2, yellowy2, yellowz2)) {
                                        player.sendTitle("§cOyun Başladı!", "§cBütcen 250 TL", 10, 70, 20);
                                    }
                                }
                            }
                        }))
                .then(new LiteralArgument("magenta")
                        .executes((sender, args) -> {
                            int magentax = plugin.getConfig().getInt("Messageteam.magentacords.pos1.x");
                            int magentay = plugin.getConfig().getInt("Messageteam.magentacords.pos1.y");
                            int magentaz = plugin.getConfig().getInt("Messageteam.magentacords.pos1.z");
                            int magentax2 = plugin.getConfig().getInt("Messageteam.magentacords.pos2.x");
                            int magentay2 = plugin.getConfig().getInt("Messageteam.magentacords.pos2.y");
                            int magentaz2 = plugin.getConfig().getInt("Messageteam.magentacords.pos2.z");

                            for (Player player : Bukkit.getOnlinePlayers()) {
                                if (player.getGameMode() == GameMode.ADVENTURE) {
                                    Location loc = player.getLocation();
                                    if (isWithinArea(loc, magentax, magentay, magentaz, magentax2, magentay2, magentaz2)) {
                                        player.sendTitle("§cOyun Başladı!", "§cBütcen 500 TL", 10, 70, 20);
                                    }
                                }
                            }
                        }))
                .then(new LiteralArgument("lightblue")
                        .executes((sender, args) -> {
                            int lightblux = plugin.getConfig().getInt("Messageteam.lightbluecords.pos1.x");
                            int lightbluy = plugin.getConfig().getInt("Messageteam.lightbluecords.pos1.y");
                            int lightbluz = plugin.getConfig().getInt("Messageteam.lightbluecords.pos1.z");
                            int lightblux2 = plugin.getConfig().getInt("Messageteam.lightbluecords.pos2.x");
                            int lightbluy2 = plugin.getConfig().getInt("Messageteam.lightbluecords.pos2.y");
                            int lightbluz2 = plugin.getConfig().getInt("Messageteam.lightbluecords.pos2.z");

                            for (Player player : Bukkit.getOnlinePlayers()) {
                                if (player.getGameMode() == GameMode.ADVENTURE) {
                                    Location loc = player.getLocation();
                                    if (isWithinArea(loc, lightblux, lightbluy, lightbluz, lightblux2, lightbluy2, lightbluz2)) {
                                        player.sendTitle("§cOyun Başladı!", "§cBütcen 750 TL", 10, 70, 20);
                                    }
                                }
                            }
                        }))
                .then(new LiteralArgument("purple")
                        .executes((sender, args) -> {
                            int purplex = plugin.getConfig().getInt("Messageteam.purplecords.pos1.x");
                            int purpley = plugin.getConfig().getInt("Messageteam.purplecords.pos1.y");
                            int purplez = plugin.getConfig().getInt("Messageteam.purplecords.pos1.z");
                            int purplex2 = plugin.getConfig().getInt("Messageteam.purplecords.pos2.x");
                            int purpley2 = plugin.getConfig().getInt("Messageteam.purplecords.pos2.y");
                            int purplez2 = plugin.getConfig().getInt("Messageteam.purplecords.pos2.z");

                            for (Player player : Bukkit.getOnlinePlayers()) {
                                if (player.getGameMode() == GameMode.ADVENTURE) {
                                    Location loc = player.getLocation();
                                    if (isWithinArea(loc, purplex, purpley, purplez, purplex2, purpley2, purplez2)) {
                                        player.sendTitle("§cOyun Başladı!", "§cBütcen 1000 TL", 10, 70, 20);
                                    }
                                }
                            }
                        }))
                .then(new LiteralArgument("cyan")
                        .executes((sender, args) -> {
                            int cyanx = plugin.getConfig().getInt("Messageteam.cyancords.pos1.x");
                            int cyany = plugin.getConfig().getInt("Messageteam.cyancords.pos1.y");
                            int cyanz = plugin.getConfig().getInt("Messageteam.cyancords.pos1.z");
                            int cyanx2 = plugin.getConfig().getInt("Messageteam.cyancords.pos2.x");
                            int cyany2 = plugin.getConfig().getInt("Messageteam.cyancords.pos2.y");
                            int cyanz2 = plugin.getConfig().getInt("Messageteam.cyancords.pos2.z");

                            for (Player player : Bukkit.getOnlinePlayers()) {
                                if (player.getGameMode() == GameMode.ADVENTURE) {
                                    Location loc = player.getLocation();
                                    if (isWithinArea(loc, cyanx, cyany, cyanz, cyanx2, cyany2, cyanz2)) {
                                        player.sendTitle("§cOyun Başladı!", "§cBütcen 2500 TL", 10, 70, 20);
                                    }
                                }
                            }
                        }))
                .then(new LiteralArgument("orange")
                        .executes((sender, args) -> {
                            int orangex = plugin.getConfig().getInt("Messageteam.orangecords.pos1.x");
                            int orangey = plugin.getConfig().getInt("Messageteam.orangecords.pos1.y");
                            int orangez = plugin.getConfig().getInt("Messageteam.orangecords.pos1.z");
                            int orangex2 = plugin.getConfig().getInt("Messageteam.orangecords.pos2.x");
                            int orangey2 = plugin.getConfig().getInt("Messageteam.orangecords.pos2.y");
                            int orangez2 = plugin.getConfig().getInt("Messageteam.orangecords.pos2.z");

                            for (Player player : Bukkit.getOnlinePlayers()) {
                                if (player.getGameMode() == GameMode.ADVENTURE) {
                                    Location loc = player.getLocation();
                                    if (isWithinArea(loc, orangex, orangey, orangez, orangex2, orangey2, orangez2)) {
                                        player.sendTitle("§cOyun Başladı!", "§cBütcen 5000 TL", 10, 70, 20);
                                    }
                                }
                            }
                        }))
                .then(new LiteralArgument("red")
                        .executes((sender, args) -> {
                            int redx = plugin.getConfig().getInt("Messageteam.redcords.pos1.x");
                            int redy = plugin.getConfig().getInt("Messageteam.redcords.pos1.y");
                            int redz = plugin.getConfig().getInt("Messageteam.redcords.pos1.z");
                            int redx2 = plugin.getConfig().getInt("Messageteam.redcords.pos2.x");
                            int redy2 = plugin.getConfig().getInt("Messageteam.redcords.pos2.y");
                            int redz2 = plugin.getConfig().getInt("Messageteam.redcords.pos2.z");

                            for (Player player : Bukkit.getOnlinePlayers()) {
                                if (player.getGameMode() == GameMode.ADVENTURE) {
                                    Location loc = player.getLocation();
                                    if (isWithinArea(loc, redx, redy, redz, redx2, redy2, redz2)) {
                                        player.sendTitle("§cOyun Başladı!", "§cBütcen 10000 TL", 10, 70, 20);
                                    }
                                }
                            }
                        }))
                .then(new LiteralArgument("lime")
                        .executes((sender, args) -> {
                            int limex = plugin.getConfig().getInt("Messageteam.limecords.pos1.x");
                            int limey = plugin.getConfig().getInt("Messageteam.limecords.pos1.y");
                            int limez = plugin.getConfig().getInt("Messageteam.limecords.pos1.z");
                            int limex2 = plugin.getConfig().getInt("Messageteam.limecords.pos2.x");
                            int limey2 = plugin.getConfig().getInt("Messageteam.limecords.pos2.y");
                            int limez2 = plugin.getConfig().getInt("Messageteam.limecords.pos2.z");

                            for (Player player : Bukkit.getOnlinePlayers()) {
                                if (player.getGameMode() == GameMode.ADVENTURE) {
                                    Location loc = player.getLocation();
                                    if (isWithinArea(loc, limex, limey, limez, limex2, limey2, limez2)) {
                                        player.sendTitle("§cOyun Başladı!", "§cBütcen 20000 TL", 10, 70, 20);
                                    }
                                }
                            }
                        }))
                .register(plugin);

    }

    private boolean isWithinArea(Location loc, int x1, int y1, int z1, int x2, int y2, int z2) {
        int minX = Math.min(x1, x2);
        int maxX = Math.max(x1, x2);
        int minY = Math.min(y1, y2);
        int maxY = Math.max(y1, y2);
        int minZ = Math.min(z1, z2);
        int maxZ = Math.max(z1, z2);

        return loc.getX() >= minX && loc.getX() <= maxX &&
                loc.getY() >= minY && loc.getY() <= maxY &&
                loc.getZ() >= minZ && loc.getZ() <= maxZ;
    }
}
