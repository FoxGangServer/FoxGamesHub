package nl.officialfox.foxgameshub;

import nl.officialfox.foxgameshub.listeners.PlayerListeners;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class FoxGamesHub extends JavaPlugin {
    @Override
    public void onEnable() {
        //Command
        System.out.println();

        //Events
        getServer().getPluginManager().registerEvents(new PlayerListeners(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
