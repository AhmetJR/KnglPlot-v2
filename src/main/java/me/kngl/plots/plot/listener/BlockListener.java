package me.kngl.plots.plot.listener;

import me.kngl.plots.KnglPlotsPlugin;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BlockListener implements Listener {

    private final KnglPlotsPlugin plugin;
    private final Set<Material> prohibitedMaterials;

    public BlockListener(KnglPlotsPlugin plugin) {
        this.plugin = plugin;
        this.prohibitedMaterials = initializeProhibitedMaterials();
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Material blockType = block.getType();

        if (prohibitedMaterials.contains(blockType)) {
            event.setCancelled(true);
            player.sendMessage("bunu yapamazsın");
        }
    }

    private Set<Material> initializeProhibitedMaterials() {
        Set<Material> materials = new HashSet<>();
        List<String> materialNames =  plugin.config().getStringList("yasakliblocklar");

        for (String materialName : materialNames) {
            try {
                Material material = Material.valueOf(materialName);
                materials.add(material);
            } catch (IllegalArgumentException e) {
                plugin.getLogger().warning("Eror    : " + materialName);
            }
        }

        return materials;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            Player player = event.getPlayer();
            Material itemType = player.getInventory().getItemInMainHand().getType();
            ItemStack item = event.getItem();

            if (item == null) return;



            if (item.getType().name().endsWith("_SPAWN_EGG")) {
                player.sendMessage("§cYaratık yumurtası kullanımı yasaktır!");
                event.setCancelled(true);
            }

            if (prohibitedMaterials.contains(itemType)) {
                event.setCancelled(true);
                player.sendMessage("§cBunu yapamazsın");
            }
        }
    }
    @EventHandler
    public void Project(ProjectileLaunchEvent event) {
        event.setCancelled(true);
    }
}
