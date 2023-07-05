package de.kenjih.subapi.objects;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class DefaultSyncUser {

    private String twitchId;
    private String discordId;
    private UUID uuid;

    public DefaultSyncUser(String twitchId, String discordId, UUID uuid){
        this.twitchId = twitchId;
        this.discordId = discordId;
        this.uuid = uuid;
    }

    public String getTwitchId() {
        return twitchId;
    }

    public String getDiscordId() {
        return discordId;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setDiscordId(String discordId) {
        this.discordId = discordId;
    }

    public void setTwitchId(String twitchId) {
        this.twitchId = twitchId;
    }

    public Player getPlayerIfOnline(){
        return Bukkit.getPlayer(getUuid());
    }

    @Override
    public String toString() {
        return "SyncUser{" +
                "twitchId='" + twitchId + '\'' +
                ", discordId='" + discordId + '\'' +
                ", uuid=" + uuid +
                '}';
    }

}
