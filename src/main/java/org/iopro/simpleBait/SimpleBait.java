package org.iopro.simpleBait;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleBait extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Recipes.register();
        getServer().getPluginManager().registerEvents(new SafetyListeners(), this);
        getServer().getPluginManager().registerEvents(new FishingListeners(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        HandlerList.unregisterAll(this);
        Bukkit.getScheduler().cancelTasks(this);
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.removeMetadata("CaughtFish", this);
            // ADD EVERY PLAYER METADATA CHANGE
        }
    }

    public static SimpleBait getInstance() {
        return getPlugin(SimpleBait.class);
    }
}
