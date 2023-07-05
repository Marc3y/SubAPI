package de.kenjih.subapi.objects.user.impl;

import com.github.twitch4j.helix.domain.ChannelInformation;
import com.github.twitch4j.helix.domain.ChannelInformationList;
import com.github.twitch4j.helix.domain.Subscription;
import com.github.twitch4j.helix.domain.SubscriptionList;
import de.kenjih.subapi.SubAPI;
import de.kenjih.subapi.objects.DefaultSyncUser;
import de.kenjih.subapi.objects.enums.Response;
import de.kenjih.subapi.objects.enums.Tier;
import de.kenjih.subapi.objects.interfaces.AsynchroneCallback;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class TwitchInfo {

    private DefaultSyncUser defaultSyncUser;
    private JavaPlugin plugin;

    private String displayName;
    private String userId;
    private boolean sub;
    private Tier tier;

    private boolean loaded = false;

    public TwitchInfo(JavaPlugin plugin, DefaultSyncUser defaultSyncUser){
        this.plugin = plugin;
        this.defaultSyncUser = defaultSyncUser;
    }

    public void load(AsynchroneCallback callback){
        new BukkitRunnable() {
            @Override
            public void run() {

                Response response = Response.SUCCESS;

                ChannelInformationList list = SubAPI.getInstance().getTwitchBot().twitchClient.getHelix().getChannelInformation(defaultSyncUser.getTwitchId(), null).execute();
                if(list.getChannels().isEmpty()) response = Response.ERROR;
                ChannelInformation info = list.getChannels().get(0);
                displayName = info.getBroadcasterName();
                userId = info.getBroadcasterId();

                SubscriptionList subList = SubAPI.getInstance().getTwitchBot().twitchClient.getHelix().getSubscriptions("168334067", defaultSyncUser.getTwitchId(), null, null, null).execute();
                if(subList.getSubscriptions().isEmpty()){
                    sub = false;
                } else {
                    Subscription subscription = subList.getSubscriptions().get(0);
                    tier = subscription.getTier().equalsIgnoreCase("1000") ? Tier.TIER_1 : subscription.getTier().equalsIgnoreCase("2000") ? Tier.TIER_2 : Tier.TIER_3;
                }

                loaded = true;

                callback.onComplete(response);
            }
        }.runTaskAsynchronously(plugin);
    }

    public boolean isLoaded() {
        return loaded;
    }

    public DefaultSyncUser getDefaultSyncUser() {
        return defaultSyncUser;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getUserId() {
        return userId;
    }

    public boolean isSub() {
        return sub;
    }

    public Tier getTier() {
        return tier;
    }
}
