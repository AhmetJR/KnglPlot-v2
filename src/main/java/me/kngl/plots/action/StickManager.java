package me.kngl.plots.action;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class StickManager implements Listener {

    private final Set<UUID> interactedPlayers = new HashSet<>();

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (!(event.getRightClicked() instanceof Player)) return;

        Player clickedPlayer = (Player) event.getRightClicked();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (item.getType() == Material.STICK && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if (meta.hasCustomModelData() && meta.getCustomModelData() == 3131) {
                if (player.hasPermission("cubuk.kullan")) {
                    if (interactedPlayers.contains(clickedPlayer.getUniqueId())) {
                        player.sendMessage(ChatColor.RED + "Bu oyuncuya zaten tıkladınız!");
                        return;
                    }

                    interactedPlayers.add(clickedPlayer.getUniqueId());

                    player.sendMessage(ChatColor.GREEN + "Elenen Oyuncunun adı: " + clickedPlayer.getName());
                    player.chat("/knglplot ele " + clickedPlayer.getName());
                    clickedPlayer.sendTitle(ChatColor.RED + clickedPlayer.getName(), ChatColor.RED + "Elendin!!");


                } else {
                    player.sendMessage(ChatColor.RED + "Bu çubuğu kullanma yetkiniz yok!");
                }
            }
        }
    }
}
