package de.kenjih.subapi.objects.user.impl;

import com.github.twitch4j.helix.domain.*;
import de.kenjih.subapi.SubAPI;
import de.kenjih.subapi.connections.discord.DiscordBot;
import de.kenjih.subapi.objects.DefaultSyncUser;
import de.kenjih.subapi.objects.enums.Response;
import de.kenjih.subapi.objects.enums.Tier;
import de.kenjih.subapi.objects.interfaces.AsynchroneCallback;
import de.kenjih.subapi.utils.Values;
import net.dv8tion.jda.api.entities.User;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class DiscordInfo {

    private DefaultSyncUser defaultSyncUser;
    private JavaPlugin plugin;

    private String userId;
    private String userName;
    private boolean isOnServer;

    private boolean loaded = false;

    public DiscordInfo(JavaPlugin plugin, DefaultSyncUser defaultSyncUser){
        this.plugin = plugin;
        this.defaultSyncUser = defaultSyncUser;
    }

    public void load(AsynchroneCallback callback){
        new BukkitRunnable() {
            @Override
            public void run() {

                Response response = Response.SUCCESS;

                User user = DiscordBot.getInstance().getJda().getUserById(defaultSyncUser.getDiscordId());
                userId = user.getId();
                userName = user.getName();
                isOnServer = DiscordBot.getInstance().getJda().getGuildById(Values.Discord_GuildID).getMemberById(userId) != null;

                loaded = true;

                callback.onComplete(response);
            }
        }.runTaskAsynchronously(plugin);
    }

    public DefaultSyncUser getDefaultSyncUser() {
        return defaultSyncUser;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public boolean isOnServer() {
        return isOnServer;
    }

    public boolean isLoaded() {
        return loaded;
    }
}
