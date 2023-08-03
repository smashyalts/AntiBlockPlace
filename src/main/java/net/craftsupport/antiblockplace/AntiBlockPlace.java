package net.craftsupport.antiblockplace;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Objects;

public final class AntiBlockPlace extends JavaPlugin implements Listener {
    List<String> BlockList = getConfig().getStringList("BlockList");
    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getServer().getPluginManager().registerEvents(this,this);
        saveDefaultConfig();
    }
    @EventHandler
    public void onBlockPlace (BlockPlaceEvent event) {
        getLogger().info(event.getBlockPlaced().getType().toString());
        if (BlockList.contains(event.getBlockPlaced().getType().toString())) {
            event.getPlayer().sendMessage("You are not allowed to place " + event.getBlock().getType() );
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onEntitySpawn (EntitySpawnEvent event) {
        if (BlockList.contains("END_CRYSTAL") && event.getEntityType().equals(EntityType.ENDER_CRYSTAL)) {
            event.setCancelled(true);
        }
        else if (BlockList.contains("MINECART") && event.getEntityType().equals(EntityType.MINECART) || event.getEntityType().equals(EntityType.MINECART_CHEST) || event.getEntityType().equals(EntityType.MINECART_FURNACE) || event.getEntityType().equals(EntityType.MINECART_HOPPER) || event.getEntityType().equals(EntityType.MINECART_TNT)) {
            event.setCancelled(true);
        }
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
