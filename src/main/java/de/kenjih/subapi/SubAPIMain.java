package de.kenjih.subapi;

import de.kenjih.subapi.mongodb.MongoDB;
import de.kenjih.subapi.mongodb.MongoManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SubAPIMain extends JavaPlugin {

    private static SubAPIMain instance;
    private static MongoDB mongoDB;
    private static MongoManager mongoManager;

    @Override
    public void onEnable() {
        instance = this;
        mongoDB = new MongoDB("mongodb://TwitchSync:zV5YxxxJ0MuGM6Ie3f321pgtyvuvNkck7qWQ1iibhfDNtAcUiT@94.250.204.44:27017/?authMechanism=SCRAM-SHA-256&authSource=TwitchSync", "TwitchSync");
        mongoDB.openConnection();
        mongoManager = new MongoManager();
        Bukkit.getLogger().info("SubAPI started");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("SubAPI stopped");
    }

    public static SubAPIMain getInstance() {
        return instance;
    }

    public static MongoManager getMongoManager() {
        return mongoManager;
    }

    public static MongoDB getMongoDB() {
        return mongoDB;
    }
}
