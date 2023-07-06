package de.kenjih.subapi;

import de.kenjih.subapi.connections.discord.DiscordBot;
import de.kenjih.subapi.connections.twitch.TwitchBot;
import de.kenjih.subapi.objects.enums.By;
import de.kenjih.subapi.objects.DefaultSyncUser;
import de.kenjih.subapi.objects.user.SyncUser;
import de.kenjih.subapi.sql.MySQL;
import de.kenjih.subapi.sql.impl.SQLData;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SubAPI {

    private JavaPlugin plugin;
    private SQLData sqlData;
    private TwitchBot twitchBot;

    private static SubAPI instance;

    public SubAPI(JavaPlugin plugin){
        instance = this;
        this.plugin = plugin;
        MySQL sql = new MySQL();
        sql.connect();
        if(!sql.isConnected()){
            Bukkit.getLogger().warning("SubAPI >> MySQL cannot connect");
            return;
        }
        this.sqlData = new SQLData();
        this.sqlData.createTable();
        twitchBot = new TwitchBot();
        DiscordBot.getInstance().start();

    }

    public DefaultSyncUser getDefaultUser(String value, By by){
        return sqlData.getUser(value, by);
    }

    public SyncUser getUser(String value, By by){
        return new SyncUser(plugin, getDefaultUser(value, by));
    }

    public TwitchBot getTwitchBot() {
        return twitchBot;
    }

    public static SubAPI getInstance() {
        return instance;
    }
}
