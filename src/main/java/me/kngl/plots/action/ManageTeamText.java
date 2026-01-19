package me.kngl.plots.action;

import me.kngl.plots.KnglPlotsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

public class ManageTeamText {

    private KnglPlotsPlugin plugin;

    public ManageTeamText(KnglPlotsPlugin plugin) {
        this.plugin = plugin;
    }


    public void test() {
        List<String> admins = plugin.config().getStringList("Main.admins");

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (admins.contains(player.getName())) continue;
                Location loc = player.getLocation();
                checkAndSendTitle(player, loc, "brown", "§cOyun Başladı!", "§cBütcen 250 TL");
                checkAndSendTitle(player, loc, "pink", "§cOyun Başladı!", "§cBütcen 500 TL");
                checkAndSendTitle(player, loc, "yellow", "§cOyun Başladı!", "§cBütcen 750 TL");
                checkAndSendTitle(player, loc, "magenta", "§cOyun Başladı!", "§cBütcen 1000 TL");
                checkAndSendTitle(player, loc, "lightblue", "§cOyun Başladı!", "§cBütcen 2500 TL");
                checkAndSendTitle(player, loc, "purple", "§cOyun Başladı!", "§cBütcen 5000 TL");
                checkAndSendTitle(player, loc, "cyan", "§cOyun Başladı!", "§cBütcen 7500 TL");
                checkAndSendTitle(player, loc, "orange", "§cOyun Başladı!", "§cBütcen 10000 TL");
                checkAndSendTitle(player, loc, "red", "§cOyun Başladı!", "§cBütcen 15000 TL");
                checkAndSendTitle(player, loc, "lime", "§cOyun Başladı!", "§cBütcen 25000 TL");
            }

}

private void checkAndSendTitle(Player player, Location loc, String color, String title, String subtitle) {
    int x1 = plugin.getConfig().getInt("Messageteam." + color + "cords.pos1.x");
    int y1 = plugin.getConfig().getInt("Messageteam." + color + "cords.pos1.y");
    int z1 = plugin.getConfig().getInt("Messageteam." + color + "cords.pos1.z");
    int x2 = plugin.getConfig().getInt("Messageteam." + color + "cords.pos2.x");
    int y2 = plugin.getConfig().getInt("Messageteam." + color + "cords.pos2.y");
    int z2 = plugin.getConfig().getInt("Messageteam." + color + "cords.pos2.z");

    if (isWithinArea(loc, x1, y1, z1, x2, y2, z2)) {
        player.sendTitle(title, subtitle, 10, 70, 20);
    }
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



