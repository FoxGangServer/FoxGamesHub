package nl.officialfox.foxgameshub.listeners;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import com.velocitypowered.api.proxy.server.ServerInfo;
import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class PlayerListeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.getInventory().setItem(4, newItemStack(Material.COMPASS, "§aServer Selector"));
        player.getInventory().setItem(8, newItemStack(Material.GREEN_BANNER, "§aProfile"));
    }

    @EventHandler
    public void onCompas(PlayerInteractEvent event) {
        if(event.getItem() != null) {
            Player player = event.getPlayer();
            ItemStack item = event.getItem();
            if (item.getType() == Material.COMPASS) {
                Gui gui = Gui.gui()
                        .title(Component.text("Server Selector"))
                        .disableAllInteractions()
                        .rows(1)
                        .create();

                GuiItem server1 = ItemBuilder.from(newItemStack(Material.PLAYER_HEAD, "server 1", Bukkit.getOfflinePlayer("maxxam230"))).asGuiItem(
                        inventoryClickEvent -> {
                            player.performCommand("server max");
                            sendToServer(player,"max");
                        });

                GuiItem server2 = ItemBuilder.from(newItemStack(Material.PLAYER_HEAD, "server 2", Bukkit.getOfflinePlayer("OfficialFox_"))).asGuiItem(
                        inventoryClickEvent -> {
                            player.performCommand("server fox");
                            sendToServer(player,"fox");
                        });

                gui.setItem(3, server1);
                gui.setItem(5, server2);
                gui.open(player);
            }
        }
    }

    @EventHandler
    public void onBanner(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getItem() != null) {
            ItemStack item = event.getItem();
            if (item.getType() == Material.GREEN_BANNER) {
                Gui gui = Gui.gui()
                        .title(Component.text("Profile"))
                        .rows(4)
                        .disableAllInteractions()
                        .create();
                GuiItem playerInfo = ItemBuilder.from(newItemStack(Material.PLAYER_HEAD, "Player Info", player)).asGuiItem(inventoryClickEvent -> {

                });

                gui.setItem(13, playerInfo);
                gui.open(player);
            }
        }
    }

    private void sendToServer(Player player, String serverName){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        com.velocitypowered.api.proxy.Player proxyPlayer = Velocity

        RegisteredServer proxyServer = Velocity.getProxyServer();
        ServerInfo server = proxyServer.getServer(serverName).get();

    }

    private ItemStack newItemStack(Material mat, String name) {
        ItemStack item = new ItemStack(mat);
        ItemMeta itemm = item.getItemMeta();
        itemm.setDisplayName(name);
        item.setItemMeta(itemm);
        return item;
    }

    private ItemStack newItemStack(Material mat, String name, OfflinePlayer player) {
        ItemStack item = new ItemStack(mat);
        SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
        skullMeta.setOwningPlayer(player);
        skullMeta.setDisplayName(name);
        item.setItemMeta(skullMeta);
        return item;
    }

}
