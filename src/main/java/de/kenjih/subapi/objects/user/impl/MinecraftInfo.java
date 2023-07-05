package de.kenjih.subapi.objects.user.impl;

import de.kenjih.subapi.objects.DefaultSyncUser;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class MinecraftInfo {

    private DefaultSyncUser defaultSyncUser;
    private Plugin plugin;
    private UUID uuid;

    public MinecraftInfo(JavaPlugin plugin, DefaultSyncUser defaultSyncUser){
        this.plugin = plugin;
        this.defaultSyncUser = defaultSyncUser;
        this.uuid = defaultSyncUser.getUuid();
    }

    public UUID getUuid() {
        return uuid;
    }

    public Player getPlayerIfOnline(){
        return Bukkit.getPlayer(getUuid());
    }

    public OfflinePlayer getOfflinePlayerIfExists(){
        return Bukkit.getOfflinePlayer(getUuid());
    }
}
