package de.kenjih.subapi;

import de.kenjih.subapi.connections.discord.DiscordBot;
import de.kenjih.subapi.connections.twitch.TwitchBot;
import de.kenjih.subapi.objects.Token;
import de.kenjih.subapi.objects.enums.By;
import de.kenjih.subapi.objects.DefaultSyncUser;
import de.kenjih.subapi.objects.user.SyncUser;
import de.kenjih.subapi.utils.Values;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SubAPI {

    private JavaPlugin plugin;
    private TwitchBot twitchBot;
    private boolean twitchConnected;

    private static SubAPI instance;

    public SubAPI(JavaPlugin plugin){
        instance = this;
        this.plugin = plugin;
        Token token = SubAPIMain.getMongoManager().getToken("Kenjih");
        Values.KenjihAccessToken = token.getAccessToken();
        Values.KenjihRefreshToken = token.getRefreshToken();
        twitchBot = new TwitchBot();
        twitchConnected = true;
        DiscordBot.getInstance().start();
    }

    public DefaultSyncUser getDefaultUser(String value, By by){
        return SubAPIMain.getMongoManager().getUser(value, by);
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

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public boolean isTwitchConnected() {
        return twitchConnected;
    }

}
