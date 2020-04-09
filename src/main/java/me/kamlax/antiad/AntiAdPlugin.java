package me.kamlax.antiad;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

public class AntiAdPlugin extends JavaPlugin {
    private JDA jda;
    private AntiAdConfiguration configuration;


    @Override
    public void onEnable() {
        configuration = new AntiAdConfiguration(this);
            try {
                jda = new JDABuilder().setToken(configuration.getToken()).build();
            } catch (LoginException ex) {
                ex.printStackTrace();
            }
        Bukkit.getPluginManager().registerEvents(new AntiAdListener(this), this);
    }

    public JDA getJda() {
        return jda;
    }

    public AntiAdConfiguration getConfiguration() {
        return configuration;
    }
}
