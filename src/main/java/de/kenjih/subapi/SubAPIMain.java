package de.kenjih.subapi;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SubAPIMain extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("SubAPI started");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("SubAPI stopped");
    }
}
