package me.kamlax.antiad;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AntiAdListener implements Listener {

    private final AntiAdPlugin plugin;

    public AntiAdListener(AntiAdPlugin plugin) {
        this.plugin = plugin;
    }

    public static String colored(String message){
        return ChatColor.translateAlternateColorCodes('&', message
                .replace(">>", "\u00BB")
                .replace("<<", "\u00AB"));
    }

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();
        final String message = event.getMessage();
        final TextChannel textChannel = plugin.getJda().getTextChannelById(plugin.getConfiguration().getChannelID());
        if (textChannel == null) return;

        if (!plugin.getConfiguration().getBlockedWords().contains(message.toLowerCase())) return;

        if (plugin.getConfiguration().isBypass() && player.hasPermission(plugin.getConfiguration().getPermission())) return;

        event.setCancelled(true);

        textChannel.sendTyping()
                .queue();
        textChannel.sendMessage(new EmbedBuilder()
                .setTitle(plugin.getConfiguration().getTitle())
                .setDescription(plugin.getConfiguration().getDescription()
                        .replace("{PLAYER}", player.getName())
                        .replace("{MESSAGE}", message))
                .build())
                .queue();

        for (final String string : plugin.getConfiguration().getMessage()) {
            for (final Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                if (!onlinePlayer.hasPermission(plugin.getConfiguration().getPermission())) continue;

                onlinePlayer.sendMessage(colored(string)
                        .replace("{PLAYER}", player.getName())
                        .replace("{MESSAGE}", message));
            }
        }
    }
}