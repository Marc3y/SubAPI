package de.kenjih.subapi.objects.user;

import de.kenjih.subapi.objects.DefaultSyncUser;
import de.kenjih.subapi.objects.user.impl.DiscordInfo;
import de.kenjih.subapi.objects.user.impl.MinecraftInfo;
import de.kenjih.subapi.objects.user.impl.TwitchInfo;
import org.bukkit.plugin.java.JavaPlugin;

public class SyncUser {

    private MinecraftInfo minecraftInfo;
    private TwitchInfo twitchInfo;
    private DiscordInfo discordInfo;

    private JavaPlugin plugin;

    private boolean isMinecraftLoaded = false;
    private boolean isTwitchLoaded = false;
    private boolean isDiscordLoaded = false;

    public SyncUser(JavaPlugin plugin, DefaultSyncUser defaultSyncUser){
        this.plugin = plugin;
        this.minecraftInfo = new MinecraftInfo(plugin, defaultSyncUser);
        this.twitchInfo = new TwitchInfo(plugin, defaultSyncUser);
        this.discordInfo = new DiscordInfo(plugin, defaultSyncUser);
    }

    public MinecraftInfo getMinecraft() {
        return minecraftInfo;
    }

    public DiscordInfo getDiscord() {
        return discordInfo;
    }

    public TwitchInfo getTwitch() {
        return twitchInfo;
    }
}
