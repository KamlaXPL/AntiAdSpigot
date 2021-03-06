package me.kamlax.antiad;

import me.kamlax.antiad.AntiAdPlugin;

import java.util.List;

public class AntiAdConfiguration {
    private final AntiAdPlugin plugin;

    private List<String> blockedWords, message;
    private String permission, token, channelID, title, description;
    private boolean bypass;
    
    public AntiAdConfiguration(AntiAdPlugin plugin) {
        this.plugin = plugin;
        this.loadConfiguration();
    }

    private void loadConfiguration() {
        plugin.saveDefaultConfig();
        blockedWords = plugin.getConfig().getStringList("minecraft.blocked_words");
        permission = plugin.getConfig().getString("minecraft.permission");
        bypass = plugin.getConfig().getBoolean("minecraft.bypass");
        message = plugin.getConfig().getStringList("minecraft.message");
        token = plugin.getConfig().getString("discord.bot_token");
        channelID = plugin.getConfig().getString("discord.channel_id");
        title = plugin.getConfig().getString("discord.embed.title");
        description = plugin.getConfig().getString("discord.embed.description");
    }

    public List<String> getBlockedWords() {
        return blockedWords;
    }

    public String getPermission() {
        return permission;
    }

    public boolean isBypass() {
        return bypass;
    }

    public List<String> getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    public String getChannelID() {
        return channelID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
